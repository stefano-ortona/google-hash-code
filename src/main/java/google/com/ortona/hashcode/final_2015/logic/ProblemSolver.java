package google.com.ortona.hashcode.final_2015.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2015.model.ProblemContainer;
import google.com.ortona.hashcode.final_2015.model.SolutionContainer;
import google.com.ortona.hashcode.final_2015.model.Status;

//for each baloon b:
//	for each move m:
//		compute covered cell with m
//		for each other baloon b':
//			compute distance with b'
//	pick best move m based on covered cell and distance with other baloons
public class ProblemSolver {
	private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

	public SolutionContainer solve(ProblemContainer problem) {
		final Status status = problem.getStatus();

		//status.

		return null;



		// iterate for all turns
		// for every turns, iterate over baloons B
		// pick the best move for B
		// move B

	}

	public static void main(String[] args) {
		LOG.info("Hello World!");
	}

}
