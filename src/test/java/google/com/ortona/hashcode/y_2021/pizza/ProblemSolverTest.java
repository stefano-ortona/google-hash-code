package google.com.ortona.hashcode.y_2021.pizza;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2021.pizza.io.ProblemReader;
import google.com.ortona.hashcode.y_2021.pizza.io.ProblemWriter;
import google.com.ortona.hashcode.y_2021.pizza.logic.ProblemSolver;
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
		final ProblemContainer problem = new ProblemContainer();
		final SolutionContainer solution = SOLVER.solve(problem);
		//make assertion here about the output result
		Assert.assertNotNull(solution);
		Assert.assertEquals(16, 4*4);
	}

	@Test
	public void testFirstInput() throws IOException {
		final ProblemContainer p = READER.readProblem("input_file");
		final SolutionContainer sC = SOLVER.solve(p);
		WRITER.writeProblem("output_file", sC);
		LOG.info("Final score: " + sC.getScore());
	}

}
