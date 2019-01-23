package google.com.ortona.hashcode.qualification_2016.model;

public class Drone {
  int id;
  int capacity;
  int curAvailableCapacity;
  int nextTimeAvailable;

  // current position when is available
  int row;
  int column;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getNextTimeAvailable() {
    return nextTimeAvailable;
  }

  public void setNextTimeAvailable(int nextTimeAvailable) {
    this.nextTimeAvailable = nextTimeAvailable;
  }

  public void loadProduct(Product p, int quantity) {
    // need
  }

  public int getAvailableCapacity() {
    return this.curAvailableCapacity;
  }

  public int getCurAvailableCapacity() {
    return curAvailableCapacity;
  }

  public void setCurAvailableCapacity(int curAvailableCapacity) {
    this.curAvailableCapacity = curAvailableCapacity;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }
}
