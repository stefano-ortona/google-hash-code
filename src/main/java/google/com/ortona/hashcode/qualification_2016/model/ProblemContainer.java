package google.com.ortona.hashcode.qualification_2016.model;

import java.util.List;

public class ProblemContainer {

  List<Order> orders;
  List<Drone> drones;
  List<Warehouse> warehouses;
  int numRows;
  int numColumns;

  int maxInstant;

  public int getMaxInstant() {
    return maxInstant;
  }

  public void setMaxInstant(int maxInstant) {
    this.maxInstant = maxInstant;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public List<Drone> getDrones() {
    return drones;
  }

  public void setDrones(List<Drone> drones) {
    this.drones = drones;
  }

  public List<Warehouse> getWarehouses() {
    return warehouses;
  }

  public void setWarehouses(List<Warehouse> warehouses) {
    this.warehouses = warehouses;
  }

  public int getNumRows() {
    return numRows;
  }

  public void setNumRows(int numRows) {
    this.numRows = numRows;
  }

  public int getNumColumns() {
    return numColumns;
  }

  public void setNumColumns(int numColumns) {
    this.numColumns = numColumns;
  }

}
