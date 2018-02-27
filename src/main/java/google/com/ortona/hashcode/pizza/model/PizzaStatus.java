package google.com.ortona.hashcode.pizza.model;

import java.util.List;

import com.google.common.collect.Lists;

public class PizzaStatus {

  /* all slices of pizza in the current pizza */
  public List<Slice> allSlices;

  /*
   * for a coordinate i,j, pizzaStatus[i][j]= true if a slice currently covers that cell
   */
  boolean[][] pizzaStatus;

  /* for each cell contains which ingredient is in that cell */
  public Ingredient[][] pizza;

  int numRows;
  int numColumns;

  public PizzaStatus(Ingredient[][] pizza) {
    this.pizza = pizza;
    // at the beginning no slices
    allSlices = Lists.newLinkedList();
    // initialise status with all false
    pizzaStatus = new boolean[pizza.length][pizza[0].length];
    this.numRows = pizza.length;
    this.numColumns = pizza[0].length;
  }

  /*
   * Return true if no cells horizontally between x1 and x2 are currently occupied, x1 and x2 included
   */
  public boolean canExpandHorizontally(final int y1, final int y2, final int row) {
    if (y1 > y2) {
      return false;
    }
    // check did not go over outside the pizza
    if ((row < 0) || (row >= numRows)) {
      return false;
    }
    for (int j = y1; j <= y2; j++) {
      if (pizzaStatus[row][j]) {
        // cell is occupied, cannot expand
        return false;
      }
    }
    // all cell are not occupied, can expand
    return true;
  }

  /*
   * Return true if no cells vertically between y1 and y2 are currently occupied, y1 and y2 included
   */
  public boolean canExpandVertically(final int x1, final int x2, final int column) {
    if (x1 > x2) {
      return false;
    }
    // check did not go over outside the pizza
    if ((column < 0) || (column >= numColumns)) {
      return false;
    }
    for (int i = x1; i <= x2; i++) {
      if (pizzaStatus[i][column]) {
        // cell is occupied, cannot expand
        return false;
      }
    }
    // all cell are not occupied, can expand
    return true;
  }

  public boolean isCellOccupied(int x, int y) {
    return this.pizzaStatus[x][y];
  }

  public boolean isRowOutOfPizza(int row) {
    return (row < 0) || (row >= numRows);
  }

  public boolean isColumnOutOfPizza(int column) {
    return (column < 0) || (column >= numColumns);
  }

  public void addSlice(Slice slice) {
    // add slice and update status
    this.allSlices.add(slice);
    for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
      for (int j = slice.upperLeftY; j <= slice.lowerRightY; j++) {
        pizzaStatus[i][j] = true;
      }
    }
  }

  public void removeSlice(Slice slice) {
    this.allSlices.remove(slice);
    for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
      for (int j = slice.upperLeftY; j <= slice.lowerRightY; j++) {
        pizzaStatus[i][j] = false;
      }
    }
  }

  public List<Slice> getSliceList() {
    return allSlices;
  }

  public void expandSlice(final int direction, final Slice slice) {
    switch (direction) {
    case 0: {
      slice.upperLeftY--;
      // occupy all cells
      for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
        pizzaStatus[i][slice.upperLeftY] = true;
      }
      break;
    }

    case 1:
      slice.lowerRightY++;
      // occupy all cells
      for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
        pizzaStatus[i][slice.lowerRightY] = true;
      }
      break;

    case 2:
      slice.upperLeftX--;
      // occupy all cells
      for (int i = slice.upperLeftY; i <= slice.lowerRightY; i++) {
        pizzaStatus[slice.upperLeftX][i] = true;
      }
      break;

    case 3:
      slice.lowerRightX++;
      // occupy all cells
      for (int i = slice.upperLeftY; i <= slice.lowerRightY; i++) {
        pizzaStatus[slice.lowerRightX][i] = true;
      }
      break;
    }
  }

}
