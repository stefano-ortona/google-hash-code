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

	public List<Baloon> getBaloons() {
		return this.baloons;
	}

	@Override
	public String toString() {
		final StringBuilder string = new StringBuilder();
		for (int i = 0; i < getBaloons().get(0).getMoves().size(); i++) {
			for (final Baloon baloon : getBaloons()) {
				string.append(baloon.getMoves().get(i) + " ");
			}
			string.append("\n");
		}
		return string.toString();
	}
}
