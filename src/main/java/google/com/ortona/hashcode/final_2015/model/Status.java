package google.com.ortona.hashcode.final_2015.model;

import java.util.List;

public class Status {
	List<Baloon> baloons;
	boolean[][] originalGrid;
	boolean[][] transientGrid;
	int maxHeight;
	int maxTurns;
	List<Pair[][]> winds;

	public Status(List<Baloon> baloons, boolean[][] originalGrid, int maxHeight, int maxTurns, List<Pair[][]> winds) {
		this.baloons = baloons;
		this.originalGrid = originalGrid;
		this.maxHeight = maxHeight;
		this.maxTurns = maxTurns;
		this.winds = winds;
		this.reset();
	}

	public Pair getNextPosition(int i, int j, int curHeight, int move) {
		if (((curHeight + move) <= 0) || ((curHeight + move) > maxHeight)) {
			return null;
		}
		final Pair[][] nextWind = winds.get(curHeight + move);
		final int nextI = i + nextWind[i][j].x;
		final int nextJ = (j + nextWind[i][j].y) % originalGrid[0].length;
		if ((nextI < 0) || (nextI >= originalGrid.length)) {
			return null;
		}
		final Pair res = new Pair();
		res.x = nextI;
		res.y = nextJ;
		return res;
	}

	public int getCoveredCell(int i, int j) {
		return 0;

	}

	public List<Integer> getDistanceWithOtherBaloons(int i, int j) {
		return null;

	}

	public void moveBaloon(int i, int j) {
		// go in the sorrounding of i and j, and mark as false all cells in the
		// sorrounding

	}

	public void reset() {
		this.baloons.forEach(b -> b.reset());
		// put transient grid in the original state

	}

	public static void main(String[] args) {
		System.out.println(9 % 10);
	}

	/*
	 * Getters
	 */

	public List<Baloon> getBaloons() {
		return baloons;
	}

	public boolean[][] getOriginalGrid() {
		return originalGrid;
	}

	public boolean[][] getTransientGrid() {
		return transientGrid;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public int getMaxTurns() {
		return maxTurns;
	}

	public List<int[][]> getWinds() {
		return winds;
	}
}
