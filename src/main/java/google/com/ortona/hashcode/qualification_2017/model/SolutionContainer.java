package google.com.ortona.hashcode.qualification_2017.model;

import java.util.List;

public class SolutionContainer {
	List<Cache> caches;
	public int score;

	public List<Cache> getCaches() {
		return caches;
	}

	public void setCaches(List<Cache> caches) {
		this.caches = caches;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
