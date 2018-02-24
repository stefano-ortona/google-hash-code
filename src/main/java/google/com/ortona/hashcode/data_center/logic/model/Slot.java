package google.com.ortona.hashcode.data_center.logic.model;

public class Slot {
  int row;

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

  int column;

  public Slot(int row, int column) {
    this.row = row;
    this.column = column;
  }

  @Override
  public String toString() {
    return "Slot[" + this.row + "," + this.column + "]";
  }
}
