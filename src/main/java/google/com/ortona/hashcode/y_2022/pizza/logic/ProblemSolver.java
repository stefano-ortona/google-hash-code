package google.com.ortona.hashcode.y_2022.pizza.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2022.pizza.model.Client;
import google.com.ortona.hashcode.y_2022.pizza.model.ProblemContainer;
import google.com.ortona.hashcode.y_2022.pizza.model.SolutionContainer;

public class ProblemSolver {
	private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

	private SolutionContainer solutionContainer = new SolutionContainer();

	Set<String> takenIngredients = new HashSet<>();

	public SolutionContainer solve(ProblemContainer problem) {
		boolean condition = true;

		int bestScore = -1;
		List<String> finalIngredients = null;

		while (condition) {
			Map<String, Integer> ingr2score = updateIngredientScore(problem.getClients(), takenIngredients);
			int currentScore = (int) problem.getClients().stream().filter(c -> c.getTaken()).count();
			LOG.debug("Current score: {} with {} ingredients", currentScore, takenIngredients.size());
			if (currentScore > bestScore) {
				bestScore = currentScore;
				finalIngredients = new ArrayList<>();
				finalIngredients.addAll(takenIngredients);
			}
			Client best = getBestClient(problem.getClients(), ingr2score);
			takenIngredients.addAll(best.getLikes());
			// compute current score

		}


		SolutionContainer sC = new SolutionContainer();
		return solutionContainer;
	}


	private Client getBestClient(List<Client> allClients, Map<String, Integer> ingr2score) {
		Client bestClient = null;
		int bestScore = Integer.MIN_VALUE;
		for (Client c : allClients) {
			if (c.getTaken() != null) {
				continue;
			}
			//compute score of the client and return the client with the best score
			// skip already taken ingredients
			int score = (int) c.getLikes().stream().filter(ingr -> !takenIngredients.contains(ingr)).count()
					- (int) c.getDislikes().stream().filter(ingr -> !takenIngredients.contains(ingr)).count();
			if (bestClient == null || score > bestScore) {
				bestClient = c;
				bestScore = score;
			}

		}
		return bestClient;
	}


	private Map<String, Integer> updateIngredientScore(List<Client> allClients, Set<String> takenIngredients) {
		Map<String, Integer> ing2score = new HashMap<>();
		for (Client c : allClients) {
			if (c.getTaken() != null && !c.getTaken()) {
				continue;
			}
			if (c.getDislikes().stream().anyMatch(ing -> takenIngredients.contains(ing))) {
				c.setTaken(false);
			} else {
				if (c.getLikes().stream().allMatch(ing -> takenIngredients.contains(ing))) {
					c.setTaken(true);
				}
			}
			for (String oneIng : c.getDislikes()) {
				if (!takenIngredients.contains(oneIng)) {
					ing2score.put(oneIng, ing2score.getOrDefault(oneIng, 0) - 1);
				}
			}

			if (c.getTaken() == null) {
				for (String oneIng : c.getLikes()) {
					if (!takenIngredients.contains(oneIng)) {
						ing2score.put(oneIng, ing2score.getOrDefault(oneIng, 0) + 1);
					}
				}
			}
		}
		return ing2score;
	}

	public static void main(String[] args) {
		LOG.info("Hello World!");
	}

}
