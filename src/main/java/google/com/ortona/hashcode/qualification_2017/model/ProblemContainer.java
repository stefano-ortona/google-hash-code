package google.com.ortona.hashcode.qualification_2017.model;

import java.util.List;

public class ProblemContainer {
	List<Request> request;

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}
	
	public String toString() {
		String requests = "";
		for(Request request : this.getRequest()) {
			requests += " " + request.toString();
		}
		return requests;
	}

}
