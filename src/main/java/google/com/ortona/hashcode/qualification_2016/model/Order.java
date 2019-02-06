package google.com.ortona.hashcode.qualification_2016.model;

import java.util.Map;
import java.util.Objects;

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
		final Integer prevQuantity = products2quantity.get(p);
		if(prevQuantity==null || prevQuantity<quantity){
			throw new RuntimeException("Cannot satisfy order with more than quantity needed!");
		}
		final int resultQuantity = prevQuantity - quantity;
		if (resultQuantity == 0) {
			products2quantity.remove(p);
		} else {
			products2quantity.put(p, resultQuantity);
		}
	}

	public boolean isOrderSatisfied() {
		return products2quantity.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final Order order = (Order) o;
		return (id == order.id) && (row == order.row) && (column == order.column);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, row, column);
	}

	@Override
	public String toString() {
		return this.id + "[" + this.row + "," + this.column + "]";
	}
}
