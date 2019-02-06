package google.com.ortona.hashcode.qualification_2017.model;

import java.util.Map;

public class Video {
	int id;
	int size;

	public Video() {
	}

	public Video(int id, int size) {
		this.id = id;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
		final Video other = (Video) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return this.id + " " + this.size;
	}

}
