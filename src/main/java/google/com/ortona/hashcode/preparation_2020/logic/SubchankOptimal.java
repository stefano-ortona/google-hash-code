package google.com.ortona.hashcode.preparation_2020.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.preparation_2020.model.Pair;

public class SubchankOptimal {

  int bestScore = -1;
  List<Integer> bestSequence;

  Logger LOG = LoggerFactory.getLogger(getClass());

  public List<Integer> solve(List<Pair> input, int goal) {

    bestScore = -1;
    computeBestScore(goal, input, new ArrayList<>(), 0, 0);
    final int size = input.stream().map(p -> p.number).reduce((a, b) -> a + b).get();
    LOG.info("Chunk with size '{}' has optimal value of '{}'", size, bestScore);
    Collections.sort(bestSequence);
    return bestSequence;
  }

  private void computeBestScore(int goal, List<Pair> input, ArrayList<Integer> soFar, int index, int curScore) {
    if (index == input.size()) {
      if ((curScore <= goal) && (curScore > bestScore)) {
        bestSequence = new ArrayList<>();
        bestSequence.addAll(soFar);
        bestScore = curScore;
      }
    } else {
      // try to put it
      soFar.add(input.get(index).index);
      computeBestScore(goal, input, soFar, index + 1, curScore + input.get(index).number);
      // remove it
      soFar.remove(soFar.size() - 1);
      computeBestScore(goal, input, soFar, index + 1, curScore);
    }
  }

  public static void main(String[] args) {
    final SubchankOptimal sO = new SubchankOptimal();
    final int goal = 19;
    // 4 14 15 18 29 32 36 82 95 95
    final List<Pair> input = Arrays.asList(new Pair(0, 4), new Pair(1, 14), new Pair(2, 10));
    System.out.println(sO.solve(input, goal));
  }

}
