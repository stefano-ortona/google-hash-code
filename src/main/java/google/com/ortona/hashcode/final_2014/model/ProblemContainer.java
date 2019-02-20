package google.com.ortona.hashcode.final_2014.model;

import java.util.List;

public class ProblemContainer {
  private long totTime;
  private List<Car> allCars;
  private List<Junction> allJunctions;

  public long getTotTime() {
    return totTime;
  }

  public void setTotTime(long totTime) {
    this.totTime = totTime;
  }

  public List<Car> getAllCars() {
    return allCars;
  }

  public void setAllCars(List<Car> allCars) {
    this.allCars = allCars;
  }

  public List<Junction> getAllJunctions() {
    return allJunctions;
  }

  public void setAllJunctions(List<Junction> allJunctions) {
    this.allJunctions = allJunctions;
  }

}
