package google.com.ortona.hashcode.qualification_2016.model;

import java.util.Map;

public class Warehouse {
  int id;
  int row;
  int column;
  Map<Product, Integer> product2quantity;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Map<Product, Integer> getProduct2quantity() {
    return product2quantity;
  }

  public void setProduct2quantity(Map<Product, Integer> product2quantity) {
    this.product2quantity = product2quantity;
  }

  public void releaseProduct(Product p, int quantity) {
    final Integer prevQuantity = product2quantity.get(p);
    if (prevQuantity != null) {
      product2quantity.put(p, Math.max(0, prevQuantity - quantity));
    }
  }

  @Override
  public String toString() {
    return this.id + "[" + this.row + "," + this.column + "]";
  }

}
