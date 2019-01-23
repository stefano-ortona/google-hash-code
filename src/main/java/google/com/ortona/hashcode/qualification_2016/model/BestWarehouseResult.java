package google.com.ortona.hashcode.qualification_2016.model;

import java.util.Objects;

public class BestWarehouseResult {

    Order order;

    Product product;

    Warehouse warehouse;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "BestWarehouseResult{" +
                "order=" + order +
                ", product=" + product +
                ", warehouse=" + warehouse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestWarehouseResult that = (BestWarehouseResult) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product) &&
                Objects.equals(warehouse, that.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product, warehouse);
    }

}
