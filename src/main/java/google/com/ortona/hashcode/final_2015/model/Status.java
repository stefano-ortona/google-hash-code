package google.com.ortona.hashcode.final_2015.model;

import java.util.List;

public class Status {
	List<Baloon> baloons;
	boolean[][] originalGrid;
	boolean[][] transientGrid;
	int maxHeight;
	int maxTurns;
	List<int[][]> winds;

	public Status(List<Baloon> baloons, boolean[][] originalGrid, int maxHeight, int maxTurns, List<int[][]> winds) {
		this.baloons = baloons;
		this.originalGrid = originalGrid;
		this.maxHeight = maxHeight;
		this.maxTurns = maxTurns;
		this.winds = winds;
		this.reset();
	}

	public int[] getNextPosition(int i, int j, int curHeight, int move) {
		return null;

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
