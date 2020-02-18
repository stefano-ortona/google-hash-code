package google.com.ortona.hashcode.final_2015.model;

import java.util.List;

import org.junit.Assert;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;

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
	    for (int i=0; i<getBaloons().get(0).moves.size(); i++) {
	    	for (final Baloon baloon : getBaloons()) {
		    	string.append(baloon.moves.get(i));
		    }
	    	string.append("\n");
	    }
	    return string.toString();
	}
}
