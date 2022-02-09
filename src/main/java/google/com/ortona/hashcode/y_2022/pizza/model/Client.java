package google.com.ortona.hashcode.y_2022.pizza.model;

import java.util.List;

public class Client {
	public int id;
	private List<String> likes;
	private List<String> dislikes;
	Boolean taken = null; // true = taken, false = scartato
	
	
	public List<String> getLikes() {
		return likes;
	}
	public void setLikes(List<String> likes) {
		this.likes = likes;
	}
	public List<String> getDislikes() {
		return dislikes;
	}
	public void setDislikes(List<String> dislikes) {
		this.dislikes = dislikes;
	}
	public Boolean getTaken() {
		return taken;
	}
	public void setTaken(Boolean taken) {
		this.taken = taken;
	}
	
	public String toString() {
		return "";
	}
	
	

}
