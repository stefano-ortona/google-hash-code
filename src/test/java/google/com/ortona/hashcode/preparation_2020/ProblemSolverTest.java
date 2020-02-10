package google.com.ortona.hashcode.preparation_2020;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
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
		ProblemContainer problem = new ProblemContainer(Arrays.asList(2,5,6,8), 17);
		SolutionContainer solution = SOLVER.solve(problem); // new SolutionContainer(Arrays.asList(0,2,3), 16); 
		Assert.assertEquals(16, solution.getScore());
		Assert.assertEquals(Arrays.asList(0,2,3), solution.getIndexes());
		Integer sum = 0;
		for (final Integer index : solution.getIndexes()) {
			LOG.info(index + " number: " + problem.getNumbers().get(index));
			sum += problem.getNumbers().get(index);
		}
		Assert.assertEquals(Integer.valueOf(solution.getScore()), sum);	
	}
	
	@Test
	public void secondTest() {
		LOG.info("----------------------");
		LOG.info("Second test is starting");
		ProblemContainer problem = new ProblemContainer(
				Arrays.asList(4,14,15,18,29,32,36,82,95,95), 
				100
		);
		SolutionContainer solution = SOLVER.solve(problem);
		Assert.assertEquals(16, solution.getScore());
		Assert.assertEquals(Arrays.asList(0,2,3), solution.getIndexes());
		Integer sum = 0;
		for (final Integer index : solution.getIndexes()) {
			LOG.info(index + " number: " + problem.getNumbers().get(index));
			sum += problem.getNumbers().get(index);
		}
		Assert.assertEquals(Integer.valueOf(solution.getScore()), sum);	
	}
	
	@Test
	public void thirdTest() {
		LOG.info("----------------------");
		LOG.info("Third test is starting");
		ProblemContainer problem = new ProblemContainer(
				Arrays.asList(
						7,12,12,13,14,28,29,29,30,32,32,
						34,41,45,46,56,61,61,62,63,65,68,
						76,77,77,92,93,94,97,103,113,114,
						114,120,135,145,145,149,156,157,
						160,169,172,179,184,185,189,194,
						195,195
				), 
				4500
		);
		SolutionContainer solution = SOLVER.solve(problem);
		Assert.assertEquals(16, solution.getScore());
		Assert.assertEquals(Arrays.asList(0,2,3), solution.getIndexes());
		Integer sum = 0;
		for (final Integer index : solution.getIndexes()) {
			LOG.info(index + " number: " + problem.getNumbers().get(index));
			sum += problem.getNumbers().get(index);
		}
		Assert.assertEquals(Integer.valueOf(solution.getScore()), sum);	
	}

}
