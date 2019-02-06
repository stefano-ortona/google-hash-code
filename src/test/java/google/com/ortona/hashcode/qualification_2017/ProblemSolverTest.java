package google.com.ortona.hashcode.qualification_2017;

import org.junit.Test;

import google.com.ortona.hashcode.qualification_2017.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2017.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2017.model.SolutionContainer;

public class ProblemSolverTest {
	
	private final static ProblemSolver SOLVER = new ProblemSolver();

  @Test
  public void firstTest() {

 
    final ProblemContainer problem = new ProblemContainer();
    final SolutionContainer solution = SOLVER.process(problem);
    int counter = 0;

    System.out.println("TEST PRINT %%% ");
  }

}
