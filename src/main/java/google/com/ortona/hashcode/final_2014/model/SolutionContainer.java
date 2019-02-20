package google.com.ortona.hashcode.final_2014.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.util.concurrent.AtomicDouble;

public class SolutionContainer {

  public List<Car> allCars;

  public double score;

  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append(allCars.size() + "\n");
    for (final Car c : allCars) {
      final List<Junction> visitedJunc = c.getVisitedJunctions();
      s.append(visitedJunc.size() + "\n");
      visitedJunc.forEach(j -> s.append(j.getId() + "\n"));
    }
    return s.toString();
  }

  public void computeScore() {
    final Set<Street> allVisited = new HashSet<Street>();
    for (final Car c : allCars) {
      allVisited.addAll(c.getStreetsVisited());
    }
    final AtomicDouble score = new AtomicDouble();
    allVisited.forEach(s -> score.addAndGet(s.getLength()));
    this.score = score.get();
  }

  public List<Car> getAllCars() {
    return allCars;
  }

  public void setAllCars(List<Car> allCars) {
    this.allCars = allCars;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

}
