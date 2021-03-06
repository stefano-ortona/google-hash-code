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
	boolean isDead = false;

	public Baloon(int id, int row, int column, int height) {
		this.id = id;
		this.row = row;
		this.column = column;
		this.height = height;
	}

	public boolean isDead() {
		return this.isDead;
	}

	public void setDead() {
		this.isDead = true;
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

	public List<Integer> getMoves() {
		return this.moves;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Baloon{" + "id=" + id + ", row=" + row + ", column=" + column + ", height=" + height + ", moves="
				+ moves + ", hasMoved=" + hasMoved + '}';
	}
}
