package google.com.ortona.hashcode.final_2015.model;

import java.util.List;

public class SolutionContainer {

	List<Baloon> baloons;

	public SolutionContainer(List<Baloon> baloons) {
		this.baloons = baloons;
	}

	private int score;

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

	@Override
	public String toString() {
		// TODO
		return null;
	}
}
