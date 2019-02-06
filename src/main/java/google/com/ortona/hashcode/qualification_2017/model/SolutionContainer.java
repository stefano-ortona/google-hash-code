package google.com.ortona.hashcode.qualification_2017.model;

import java.util.Set;

public class SolutionContainer {
	Set<Cache> caches;
	public double score;

	public Set<Cache> getCaches() {
		return caches;
	}

	public void setCaches(Set<Cache> caches) {
		this.caches = caches;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		String caches = "";
		for (final Cache cache : this.getCaches()) {
			caches += " " + cache.toString();
		}
		return caches;
	}

}
