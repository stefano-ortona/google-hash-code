package google.com.ortona.hashcode.preparation_2020.logic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.preparation_2020.model.ProblemContainer;
import google.com.ortona.hashcode.preparation_2020.model.SolutionContainer;

public class ProblemSolver {
	private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);
	
	public SolutionContainer solve(ProblemContainer problem) {
	  List<Integer> allnumbers = problem.getNumbers();
	  List<Integer> allResults = new ArrayList<>();
	  //TODO
	  
	  SolutionContainer solution = new SolutionContainer(allResults, 0);
	  return null;
	}
	
	public static void main(String []args) {
	  LOG.info("Hello World!");
	}

}
