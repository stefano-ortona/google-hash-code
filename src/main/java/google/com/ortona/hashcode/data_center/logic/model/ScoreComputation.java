package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreComputation {

	public int computeScore(int poolId, Datacenter center) {
		List<Server> poolServers = center.getPool(poolId);
		Map<Integer, Integer> rowScores = new HashMap<>();
		
		for (Server server : poolServers) {
			int currentRow = server.getInitialSlot().row;
			Integer currentRowScore = rowScores.get(currentRow);
			if (currentRowScore == null) {
				currentRowScore = 0;
			}
			currentRowScore += server.getCapacity();
			rowScores.put(currentRow, currentRowScore);
		}
		
		int maxScore = Collections.max(rowScores.values());

		return maxScore;
	}

	public int computeScore(int pool, Datacenter center, Server toAdd, int row) {

		return -1;
	}

}
