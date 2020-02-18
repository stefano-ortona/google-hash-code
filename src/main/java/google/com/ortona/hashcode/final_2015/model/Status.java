package google.com.ortona.hashcode.final_2015.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Status {
	List<Baloon> baloons;
	boolean[][] originalGrid;
	boolean[][] transientGrid;
	int maxHeight;
	int maxTurns;
	List<Pair[][]> winds;
	int radius;

	public Status(List<Baloon> baloons, boolean[][] originalGrid, int maxHeight, int maxTurns, List<Pair[][]> winds,
			int radius) {
		this.baloons = baloons;
		this.originalGrid = originalGrid;
		this.maxHeight = maxHeight;
		this.maxTurns = maxTurns;
		this.winds = winds;
		this.radius = radius;
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
		return getCoveredCellsAndModify(i, j, false);
	}

	private int getCoveredCellsAndModify(int row, int col, boolean modify) {
		int totCell = 0;
		for (int i = row - radius; i <= (row + this.radius); i++) {
			for (int j = col - radius; j <= (col + this.radius); j++) {
				totCell += countCell(i, j, modify);
			}
		}
		return totCell;
	}

	private int countCell(int i, int j, boolean modify) {
		if ((i < 0) || (i >= this.originalGrid.length)) {
			return 0;
		}
		return 0;
	}

	public List<Integer> getDistanceWithOtherBaloons(int i, int j, int baloonId) {
		final List<Integer> dis = new LinkedList<>();
		for (final Baloon b : this.baloons) {
			if (b.id != baloonId) {
				dis.add((int) Math.ceil(Math.pow(i - b.row, 2) + Math.pow(j - b.column, 2)));
			}
		}
		return dis;
	}

	public void moveBaloon(int i, int j) {
		// go in the sorrounding of i and j, and mark as false all cells in the
		// sorrounding

	}

	public void reset() {
		this.baloons.forEach(b -> b.reset());
		// put transient grid in the original state
		this.transientGrid = new boolean[this.originalGrid.length][this.originalGrid[0].length];
		for (int i = 0; i < this.originalGrid.length; i++) {
			this.transientGrid[i] = Arrays.copyOf(this.originalGrid[i], this.originalGrid[i].length);
		}
	}

	public static void main(String[] args) {
		System.out.println(3 % 4);
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

	public List<Pair[][]> getWinds() {
		return winds;
	}
}
