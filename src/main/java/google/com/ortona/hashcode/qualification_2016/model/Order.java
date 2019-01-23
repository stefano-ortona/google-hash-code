package google.com.ortona.hashcode.qualification_2016.model;

import java.util.Map;

public class Order {
    int id;
    int row;
    int column;
    Map<Product, Integer> products2quantity;

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

    public Map<Product, Integer> getProducts2quantity() {
        return products2quantity;
    }

    public void setProducts2quantity(Map<Product, Integer> products2quantity) {
        this.products2quantity = products2quantity;
    }

    public void deliverProduct(Product p, int quantity) {
        Integer prevQuantity = products2quantity.get(p);
        if (prevQuantity != null) {
            int resultQuantity = Math.max(0, prevQuantity - quantity);
            if (resultQuantity <= 0) {
                products2quantity.remove(p);
            } else {
                products2quantity.put(p, resultQuantity);
            }
        }
    }

    public boolean isOrderSatisfied() {
        return products2quantity.isEmpty();
    }

}
