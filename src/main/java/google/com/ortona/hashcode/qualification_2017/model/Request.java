package google.com.ortona.hashcode.qualification_2017.model;

public class Request {
	int id;
	Video v;
	int quantity;
	Endpoint e;

	public Video getV() {
		return v;
	}

	public void setV(Video v) {
		this.v = v;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Endpoint getE() {
		return e;
	}

	public void setE(Endpoint e) {
		this.e = e;
	}

}
