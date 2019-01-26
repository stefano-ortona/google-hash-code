package google.com.ortona.hashcode.qualification_2016.model;

import java.util.Map;
import java.util.Objects;

public class Warehouse {

    int id;
    int row;
    int column;
    Map<Product, Integer> product2quantity;

    public Warehouse() {
    }

    public Warehouse(int id, int row, int column, Map<Product, Integer> product2quantity) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.product2quantity = product2quantity;
    }

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
        if(prevQuantity==null || prevQuantity<quantity){
        	throw new RuntimeException("Trying to release more quantity than available!");
        }
        if (prevQuantity != null) {
            product2quantity.put(p, prevQuantity - quantity);
        }
    }

    @Override
    public String toString() {
        return this.id + "[" + this.row + "," + this.column + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return id == warehouse.id &&
                row == warehouse.row &&
                column == warehouse.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, column);
    }

}
