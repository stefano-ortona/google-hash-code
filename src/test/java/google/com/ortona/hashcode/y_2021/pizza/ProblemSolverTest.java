package google.com.ortona.hashcode.y_2021.pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2021.pizza.io.ProblemReader;
import google.com.ortona.hashcode.y_2021.pizza.io.ProblemWriter;
import google.com.ortona.hashcode.y_2021.pizza.logic.ProblemSolver;
import google.com.ortona.hashcode.y_2021.pizza.model.Pizza;
import google.com.ortona.hashcode.y_2021.pizza.model.ProblemContainer;
import google.com.ortona.hashcode.y_2021.pizza.model.SolutionContainer;

/**
 *
 */
public class ProblemSolverTest {

	private final static ProblemSolver SOLVER = new ProblemSolver();
	private final static ProblemReader READER = new ProblemReader();
	private final static ProblemWriter WRITER = new ProblemWriter();

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Test
	public void firstTest() {
		LOG.info("----------------------");
		LOG.info("First test is starting");
		// create here a mock input for test
		final Map<Integer, Integer> teamSize2Number = new HashMap<Integer,Integer>();
		teamSize2Number.put(2, 2);
		teamSize2Number.put(3, 2);
		teamSize2Number.put(4, 1);
		final List<Pizza> pizzaList = new ArrayList<>();
		final Pizza pizza1 = new Pizza();
		pizza1.setId(1);
		pizza1.setIngredientList(Arrays.asList("onion","pepper", "olive"));
		pizzaList.add(pizza1);
		final Pizza pizza2 = new Pizza();
		pizza2.setId(2);
		pizza2.setIngredientList(Arrays.asList("mushroom","tomato", "basil"));
		pizzaList.add(pizza2);
		final Pizza pizza3 = new Pizza();
		pizza3.setId(3);
		pizza3.setIngredientList(Arrays.asList("chicken","mushroom", "pepper"));
		pizzaList.add(pizza3);
		final Pizza pizza4 = new Pizza();
		pizza4.setId(4);
		pizza4.setIngredientList(Arrays.asList("tomato","mushroom", "basil"));
		pizzaList.add(pizza4);
		final Pizza pizza5 = new Pizza();
		pizza5.setId(5);
		pizza5.setIngredientList(Arrays.asList("chicken","basil"));
		pizzaList.add(pizza5);
		
		final ProblemContainer problem = new ProblemContainer(teamSize2Number, pizzaList);
		final SolutionContainer solution = SOLVER.solve(problem);
		Assert.assertNotNull(solution);
		LOG.info("test1 solution: " + solution.toString() + "\nScore: " + solution.getScore());
		
	}

	@Test
	public void testFirstInput() throws IOException {
		//final ProblemContainer p = READER.readProblem("a_example");
		//final ProblemContainer p = READER.readProblem("b_little_bit_of_everything.in");
		final ProblemContainer p = READER.readProblem("c_many_ingredients.in");
		//final ProblemContainer p = READER.readProblem("d_many_pizzas.in");
		//final ProblemContainer p = READER.readProblem("e_many_teams.in");

		// LOG.info(p.toString());

		final SolutionContainer sC = SOLVER.solve(p);
		WRITER.writeProblem("output_file", sC);
		LOG.info("test1 solution: " + sC.toString() + "\nScore: " + sC.getScore());
	}

}
