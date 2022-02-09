package google.com.ortona.hashcode.y_2022.pizza.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import google.com.ortona.hashcode.y_2022.pizza.model.Client;
import google.com.ortona.hashcode.y_2022.pizza.model.ProblemContainer;

public class ProblemReader {
	String folder = "src/main/resources/google/com/ortona/hashcode/y_2022.pizza2/io/";
	public ProblemContainer readProblem(String fileLocation) throws IOException {
		List<Client> allClients = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(new File(folder+fileLocation)));
		
		int clients = Integer.valueOf(br.readLine()); 
		for(int i = 0; i < clients; i++) {
			String likesS = br.readLine();
			String dislikesS = br.readLine();
			Client c = new Client();
			c.id = i;
			c.setLikes(getIngredients(likesS));
			c.setDislikes(getIngredients(dislikesS));
			allClients.add(c);
		}
		br.close();
		
		ProblemContainer pC = new ProblemContainer();
		pC.setClients(allClients);
		return pC;
	}
	
	
	private List<String> getIngredients(String s){
		String []tokens = s.split(" ");
		List<String> ingredients = new ArrayList<>();
		for(int i = 1; i < tokens.length; i++) {
			ingredients.add(tokens[i]);
		}
		return ingredients;
	}
}
