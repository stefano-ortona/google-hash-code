package google.com.ortona.hashcode.final_2014;

import org.junit.Test;

import google.com.ortona.hashcode.final_2014.logic.ProblemSolver;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;

public class ProblemSolverTest {

  private final static ProblemSolver SOLVER = new ProblemSolver();

  @Test
  public void firstTest() {
    final ProblemContainer problem = new ProblemContainer();
    SOLVER.process(problem);
  }

}
