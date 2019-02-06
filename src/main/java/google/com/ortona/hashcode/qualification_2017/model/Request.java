package google.com.ortona.hashcode.qualification_2017.model;

import java.util.Map;

public class Request {
	int id;
	Video v;
	int quantity;
	Endpoint e;

	// internal
	Cache cacheDesignated;
	int score;

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

	public Cache getCacheDesignated() {
		return cacheDesignated;
	}

	public void setCacheDesignated(Cache cacheDesignated) {
		this.cacheDesignated = cacheDesignated;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Request other = (Request) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return this.id + " " + this.getQuantity() + " " + this.getV().getId() + " " + this.getE().getId();
	}
}
