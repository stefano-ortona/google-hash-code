package google.com.ortona.hashcode.qualification_2017.model;

import java.util.Set;

public class SolutionContainer {
	Set<Cache> caches;
	public int score;

	public Set<Cache> getCaches() {
		return caches;
	}

	public void setCaches(Set<Cache> caches) {
		this.caches = caches;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		String caches = "";
		for(Cache cache : this.getCaches()) {
			caches += " " + cache.toString();
		}
		return caches;
	}

}
