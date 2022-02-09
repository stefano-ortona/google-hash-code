package google.com.ortona.hashcode.y_2022.pizza.model;

import java.util.List;

public class ProblemContainer {
	
	private List<Client> allClients;
	
	
	public List<Client> getClients(){
		return allClients;
	}
	
	public void setClients(List<Client> clients) {
		this.allClients = clients;
	}

	@Override
	public String toString() {
		return "ProblemContainer{" +
				"allClients=" + allClients +
				'}';
	}
}
