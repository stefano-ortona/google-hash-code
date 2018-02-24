package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreComputation {

	public int computeScore(int poolId, Datacenter center) {
		List<Server> poolServers = center.getPool(poolId);

		Map<Integer, Integer> row2ScoreMap = calculateRow2ScoreMap(poolServers);

		Collection<Integer> scores = row2ScoreMap.values();
		
		return calculateMaxScores(scores);
	}

	public int computeScore(int poolId, Datacenter center, Server serverToAdd, int row) {
		List<Server> poolServers = center.getPool(poolId);
		
		Map<Integer, Integer> row2ScoreMap = calculateRow2ScoreMap(poolServers);
		
		addCapacityToMap(row2ScoreMap, row, serverToAdd.getCapacity());

		Collection<Integer> scores = row2ScoreMap.values();
		
		return calculateMaxScores(scores);
	}
	
	
	/*
	 * Internal methods
	 */

	private static Map<Integer, Integer> calculateRow2ScoreMap(List<Server> poolServers) {
		Map<Integer, Integer> row2ScoreMap = new HashMap<>();

		for (Server server : poolServers) {
			int currentRow = server.getInitialSlot().row;
			addCapacityToMap(row2ScoreMap, currentRow, server.getCapacity());
		}

		return row2ScoreMap;
	}
	
	private static void addCapacityToMap(Map<Integer, Integer> row2ScoreMap, int currentRow, int capacity) {
		Integer currentRowScore = row2ScoreMap.get(currentRow);
		if (currentRowScore == null) {
			currentRowScore = 0;
		}
		currentRowScore += capacity;
		row2ScoreMap.put(currentRow, currentRowScore);
	}
	
	private static int calculateMaxScores(Collection<Integer> scores) {
		int maxScore = Collections.max(scores);
		scores.remove(maxScore);
		return Collections.max(scores); // second max score
	}

}
