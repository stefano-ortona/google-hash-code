package google.com.ortona.hashcode.final_2014.model;

import java.util.LinkedList;
import java.util.List;

public class Car {
  private int id;
  private Junction current;
  private double nextTimeAvailable = 0;
  List<Street> streetsVisited = new LinkedList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Junction getCurrent() {
    return current;
  }

  public void setCurrent(Junction current) {
    this.current = current;
  }

  public double getNextTimeAvailable() {
    return nextTimeAvailable;
  }

  public void setNextTimeAvailable(long nextTimeAvailable) {
    this.nextTimeAvailable = nextTimeAvailable;
  }

  public List<Street> getStreetsVisited() {
    return streetsVisited;
  }

  public void setStreetsVisited(List<Street> streetsVisited) {
    this.streetsVisited = streetsVisited;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Car other = (Car) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  public void moveToJunction(Junction j) {
    if (this.current != null) {
      this.current.removeCar(this);
      final Street target = this.current.getTargetStreet(j);
      target.setVisited(true);
      this.streetsVisited.add(target);
      nextTimeAvailable += target.getTimeCost();
    }
    this.current = j;
    j.addCar(this);
  }

}
