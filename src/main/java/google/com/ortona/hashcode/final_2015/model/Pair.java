package google.com.ortona.hashcode.final_2015.model;

public class Pair {
	public int x;
	public int y;

	public Pair(){

	}

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
