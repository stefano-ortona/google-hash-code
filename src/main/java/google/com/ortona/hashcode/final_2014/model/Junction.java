package google.com.ortona.hashcode.final_2014.model;

import java.util.LinkedList;
import java.util.List;

public class Junction {
  private int id;
  private List<Street> outgoingStreets = new LinkedList<>();
  private List<Car> currentOnIt = new LinkedList<>();
  private double lat;
  private double lng;

  public long getOutgoingDegree() {
    return outgoingStreets.stream().filter(s -> !s.isVisited()).count();
  }

  public long getJunctionScore() {
    return this.getOutgoingDegree() - this.currentOnIt.size();
  }

  public void addCar(Car c) {
    this.currentOnIt.add(c);
  }

  public void removeCar(Car c) {
    this.currentOnIt.remove(c);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Street> getOutgoingStreets() {
    return outgoingStreets;
  }

  public Street getTargetStreet(Junction arrival) {
    return this.outgoingStreets.stream().filter(s -> s.getEnd().equals(arrival)).findFirst().get();
  }

  public void setOutgoingStreets(List<Street> outgoingStreets) {
    this.outgoingStreets = outgoingStreets;
  }

  public List<Car> getCurrentOnIt() {
    return currentOnIt;
  }

  public void setCurrentOnIt(List<Car> currentOnIt) {
    this.currentOnIt = currentOnIt;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
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
    final Junction other = (Junction) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
