package google.com.ortona.hashcode.preparation_2020;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.preparation_2020.logic.ProblemSolver;
import google.com.ortona.hashcode.preparation_2020.model.ProblemContainer;
import google.com.ortona.hashcode.preparation_2020.model.SolutionContainer;


/**
 *
 */
public class ProblemSolverTest {

	private final static ProblemSolver SOLVER = new ProblemSolver();

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Test
	public void firstTest() {
		LOG.info("----------------------");
		LOG.info("First test is starting");
		ProblemContainer problem = new ProblemContainer();
		SolutionContainer solution = SOLVER.solve(problem);
	        //TODO -- write tests	
	}

}
