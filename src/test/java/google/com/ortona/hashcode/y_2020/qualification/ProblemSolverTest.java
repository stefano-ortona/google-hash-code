package google.com.ortona.hashcode.y_2020.qualification;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2020.qualification.io.ProblemReader;
import google.com.ortona.hashcode.y_2020.qualification.io.ProblemWriter;
import google.com.ortona.hashcode.y_2020.qualification.logic.ProblemSolver;
import google.com.ortona.hashcode.y_2020.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.y_2020.qualification.model.SolutionContainer;
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
		ProblemContainer pC = new ProblemContainer();
		// create here a mock input for test

		
		ProblemSolver pS = new ProblemSolver();
		SolutionContainer sC = pS.solve(pC);
		LOG.info("Final score is: {}, with solution: {}",sC.getScore(), sC.toString());

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
