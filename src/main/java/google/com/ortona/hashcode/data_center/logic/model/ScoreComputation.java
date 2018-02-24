package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreComputation {

  public int computeScore(int poolId, Datacenter center) {
    final List<Server> poolServers = center.getPool(poolId);

    final Map<Integer, Integer> row2ScoreMap = calculateRow2ScoreMap(poolServers);

    final Collection<Integer> scores = row2ScoreMap.values();

    return calculateMaxScores(scores);
  }

  public int computeScore(int poolId, Datacenter center, Server serverToAdd, int row) {
    final List<Server> poolServers = center.getPool(poolId);

    final Map<Integer, Integer> row2ScoreMap = calculateRow2ScoreMap(poolServers);

    addCapacityToMap(row2ScoreMap, row, serverToAdd.getCapacity());

    final Collection<Integer> scores = row2ScoreMap.values();

    return calculateMaxScores(scores);
  }

  /*
   * Internal methods
   */

  private static Map<Integer, Integer> calculateRow2ScoreMap(List<Server> poolServers) {
    final Map<Integer, Integer> row2ScoreMap = new HashMap<>();

    for (final Server server : poolServers) {
      final int currentRow = server.getInitialSlot().row;
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
    final int maxScore = Collections.max(scores);
    scores.remove(maxScore);
    final AtomicInteger finalScore = new AtomicInteger(0);
    scores.forEach(t -> finalScore.getAndAdd(t));
    return finalScore.get(); // second max score
  }

}
