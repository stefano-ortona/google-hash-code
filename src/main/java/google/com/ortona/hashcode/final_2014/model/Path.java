package google.com.ortona.hashcode.final_2014.model;

import java.util.LinkedList;
import java.util.List;

public class Path {
  List<Junction> junctions = new LinkedList<>();
  Junction startJunction;
  public double score = 0;
  public double penalty = 0;

  public Path(Junction start) {
    this.startJunction = start;
    this.junctions.add(start);
  }

  public List<Junction> getJunctions() {
    return junctions;
  }

  public void setJunctions(List<Junction> junctions) {
    this.junctions = junctions;
  }

  public Junction getStartJunction() {
    return startJunction;
  }

  public void setStartJunction(Junction startJunction) {
    this.startJunction = startJunction;
  }

  public void addJunction(Junction j) {
    final Street s = junctions.get(junctions.size() - 1).getTargetStreet(j);
    if (!s.isVisited()) {
      score += s.getLength();
    }
    penalty += s.getTimeCost();
    this.junctions.add(j);
  }

  public Junction getLastJunction() {
    return this.junctions.get(this.junctions.size() - 1);
  }

  @Override
  public Path clone() {
    final Path p = new Path(this.startJunction);
    final List<Junction> newList = new LinkedList<>();
    newList.addAll(this.junctions);
    p.score = this.score;
    p.junctions = newList;
    return p;
  }

}
