package google.com.ortona.hashcode.final_2015.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Status {
	Logger LOG = LoggerFactory.getLogger(getClass());

	List<Baloon> baloons;
	boolean[][] originalGrid;
	boolean[][] transientGrid;
	int maxHeight;
	int maxTurns;
	List<Pair[][]> winds;
	int radius;
	int totScore = 0;

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
		final Pair[][] nextWind = winds.get((curHeight + move) - 1);
		final int nextI = i + nextWind[i][j].x;
		final int nextJ = modifyColumn(j + nextWind[i][j].y);
		if ((nextI < 0) || (nextI >= originalGrid.length)) {
			return null;
		}
		final Pair res = new Pair();
		res.x = nextI;
		res.y = nextJ;
		return res;
	}

	private int modifyColumn(int j) {
		if (j >= this.originalGrid[0].length) {
			return j % this.originalGrid[0].length;
		}
		if (j < 0) {
			return this.originalGrid[0].length + j;
		}
		return j;
	}

	public int getCoveredCell(int i, int j) {
		return getCoveredCellsAndModify(i, j, false);
	}

	private int getCoveredCellsAndModify(int row, int col, boolean modify) {
		int totCell = 0;
		for (int i = row - radius; i <= (row + this.radius); i++) {
			for (int j = col - radius; j <= (col + this.radius); j++) {
				totCell += countCell(i, j, row, col, modify);
			}
		}
		return totCell;
	}

	private int countCell(int i, int j, int row, int col, boolean modify) {
		if ((i < 0) || (i >= this.originalGrid.length)) {
			return 0;
		}
		j = modifyColumn(j);
		if (isRadiusCell(i, j, row, col) && this.transientGrid[i][j]) {
			if (modify) {
				this.transientGrid[i][j] = false;
			}
			return 1;
		}
		return 0;
	}

	private boolean isRadiusCell(int i, int j, int otherI, int otherJ) {
		final double val = Math.pow(i - otherI, 2)
				+ Math.pow(Math.min(Math.abs(j - otherJ), this.originalGrid[0].length - Math.abs(j - otherJ)), 2);
		return val <= Math.pow(this.radius, 2);
	}

	public List<Integer> getDistanceWithOtherBaloons(int i, int j, int baloonId) {
		final List<Integer> dis = new LinkedList<>();
		for (final Baloon b : this.baloons) {
			if (b.id != baloonId) {
				dis.add((int) Math.ceil(Math.pow(i - b.row, 2) + Math.pow(j - b.column, 2)));
			}
		}
		if (dis.isEmpty()) {
			dis.add(0);
		}
		return dis;
	}

	public void moveBaloon(int i, int j) {
		this.totScore += this.getCoveredCellsAndModify(i, j, true);
	}

	public int getTotalScore() {
		return this.totScore;
	}

	public void reset() {
		LOG.info("Current score is: {}", this.totScore);
		this.baloons.forEach(b -> b.reset());
		// put transient grid in the original state
		this.transientGrid = new boolean[this.originalGrid.length][this.originalGrid[0].length];
		for (int i = 0; i < this.originalGrid.length; i++) {
			this.transientGrid[i] = Arrays.copyOf(this.originalGrid[i], this.originalGrid[i].length);
		}
	}

	public static void main(String[] args) {
		final boolean[][] grid = new boolean[7][7];
		for (int i = 0; i < grid.length; i++) {
			Arrays.fill(grid[i], true);
		}
		final Status s = new Status(Collections.emptyList(), grid, 0, 0, null, 3);
		s.getCoveredCellsAndModify(3, 3, true);
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(s.transientGrid[i]));
		}
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
