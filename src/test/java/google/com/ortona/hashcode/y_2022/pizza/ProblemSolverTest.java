package google.com.ortona.hashcode.y_2022.pizza;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2022.pizza.io.ProblemReader;
import google.com.ortona.hashcode.y_2022.pizza.io.ProblemWriter;
import google.com.ortona.hashcode.y_2022.pizza.logic.ProblemSolver;
import google.com.ortona.hashcode.y_2022.pizza.model.ProblemContainer;
import google.com.ortona.hashcode.y_2022.pizza.model.SolutionContainer;
import google.com.ortona.hashcode.y_2022.pizza.model.Client;


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
		final Client c1 = new Client();
		c1.setLikes(Arrays.asList("cheese","peppers"));
		c1.setDislikes(Collections.<String>emptyList());
		
		final Client c2 = new Client();
		c2.setLikes(Arrays.asList("basil"));
		c2.setDislikes(Arrays.asList("pineapple"));
		
		final Client c3 = new Client();
		c3.setLikes(Arrays.asList("mushrooms","tomatoes"));
		c3.setDislikes(Arrays.asList("basil"));
		
		final ProblemContainer problem = new ProblemContainer();
		problem.setClients(Arrays.asList(c1,c2,c3));
		final SolutionContainer solution = SOLVER.solve(problem);
		Assert.assertNotNull(solution);
		LOG.info("test1 solution: " + solution.toString() + "\nScore: " + solution.getScore());
		
	}

	@Test
	public void testRealInput() throws IOException {
		final String inputFile = "a_an_example";
//		final String inputFile = "b_little_bit_of_everything";
//		final String inputFile = "c_many_ingredients";
//		final String inputFile = "d_difficult_in";
//		final String inputFile = "e_elaborate_in";
		
		final ProblemContainer p = READER.readProblem(inputFile);

		final SolutionContainer sC = SOLVER.solve(p);
		WRITER.writeProblem("output_" + inputFile, sC);
		LOG.info("test1 solution: " + sC.toString() + "\nScore: " + sC.getScore());
	}

}
