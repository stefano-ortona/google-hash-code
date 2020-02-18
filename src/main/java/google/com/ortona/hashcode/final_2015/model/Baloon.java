package google.com.ortona.hashcode.final_2015.model;

import java.util.ArrayList;
import java.util.List;

public class Baloon {
	int id;
	int row;
	int column;
	int height;
	List<Integer> moves = new ArrayList<>();
	boolean hasMoved = false;
	
	public Baloon(int id, int row, int column, int height) {
		this.id = id;
		this.row = row;
		this.column = column;
		this.height = height;
	}

	// move = 0, +1, -1
	public void addMove(int move) {
		this.height += move;
		this.moves.add(move);
		hasMoved = true;
	}

	public void reset() {
		this.hasMoved = false;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
