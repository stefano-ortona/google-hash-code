package google.com.ortona.hashcode.final_2014.logic;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.Path;
import google.com.ortona.hashcode.final_2014.model.Street;

public class ComputeAllPaths {

  private final int MAX_LEN = 20;
  private final int MAX_SCORE_DIFF = 1;

  public Path computeBest(Car c, double maxTime) {
    final List<Path> allPaths = new LinkedList<>();
    computeAllPossiblePaths(new Path(c.getCurrent()), allPaths, 0, c.getNextTimeAvailable(), maxTime);
    allPaths.sort(new Comparator<Path>() {
      @Override
      public int compare(Path o1, Path o2) {
        if (Math.abs(o1.score - o2.score) >= MAX_SCORE_DIFF) {
          return (int) (o2.score - o1.score);
        }
        return 0;
      }
    });
    return allPaths.isEmpty() ? null : allPaths.get(0);
  }

  private void computeAllPossiblePaths(Path current, List<Path> allPaths, int curLeng, double curTime, double maxTime) {
    if ((curLeng >= MAX_LEN)) {
      return;
    }
    final Junction lastJ = current.getLastJunction();
    final List<Street> outgoing = current.getLastJunction().getOutgoingStreets();
    for (final Street s : outgoing) {
      Junction end = s.getEnd();
      if (end.equals(lastJ)) {
        end = s.getStart();
      }
      // TO CHECK
      if (!isUnwantedCycle(current, end) && ((curTime + s.getTimeCost()) <= maxTime)) {
        // add a new valid junction
        final Path newPath = current.clone();
        newPath.addJunction(end);
        allPaths.add(newPath);
        computeAllPossiblePaths(newPath, allPaths, curLeng + 1, curTime + s.getTimeCost(), maxTime);
      }
    }
  }

  private boolean isUnwantedCycle(Path current, Junction next) {
    // if (current.getJunctions().size() > 10) {
    // return current.getJunctions().subList(current.getJunctions().size() - 10, current.getJunctions().size())
    // .contains(next);
    // }
    // return current.getJunctions().contains(next);
    return current.getJunctions().contains(next);
  }

}
