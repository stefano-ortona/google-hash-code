package google.com.ortona.hashcode.qualification_2016;

import org.junit.Assert;
import org.junit.Test;

import google.com.ortona.hashcode.qualification_2016.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;

public class ProblemSolverTest {

  private final static ProblemSolver SOLVER = new ProblemSolver();

  @Test
  public void firstTest() {
    final ProblemContainer problem = new ProblemContainer();
    final SolutionContainer solution = SOLVER.process(problem);
    Assert.assertNotNull(solution);
    Assert.assertEquals("I'M EXPECTING THIS", solution.toString());
  }

}
