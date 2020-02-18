package google.com.ortona.hashcode.final_2020;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2015.io.ProblemReader;
import google.com.ortona.hashcode.final_2015.logic.ProblemSolver;
import google.com.ortona.hashcode.final_2015.model.ProblemContainer;
import google.com.ortona.hashcode.final_2015.model.SolutionContainer;

/**
 *
 */
public class ProblemSolverTest {

  private final static ProblemSolver SOLVER = new ProblemSolver();

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Test
  public void firstTest() {
  }

  @Test
  public void secondTest() {
  }

  @Test
  public void thirdTest() {
  }

  @Test
  public void testFirstInput() {
    final ProblemReader r = new ProblemReader();
    final ProblemContainer p = r.readProblem("");
    final SolutionContainer sC = SOLVER.solve(p);
    LOG.info("Final score: " + sC.getScore());
  }

  @Test
  public void testSecondInput() {
    final ProblemReader r = new ProblemReader();
    final ProblemContainer p = r.readProblem("");
    final SolutionContainer sC = SOLVER.solve(p);
    LOG.info("Final score: " + sC.getScore());
  }

  @Test
  public void testThirdInput() {
    final ProblemReader r = new ProblemReader();
    final ProblemContainer p = r.readProblem("");
    final SolutionContainer sC = SOLVER.solve(p);
    LOG.info("Final score: " + sC.getScore());
  }

  @Test
  public void testFourthInput() {
    final ProblemReader r = new ProblemReader();
    final ProblemContainer p = r.readProblem("");
    final SolutionContainer sC = SOLVER.solve(p);
    LOG.info("Final score: " + sC.getScore());
  }

  @Test
  public void testFifthInput() {
    final ProblemReader r = new ProblemReader();
    final ProblemContainer p = r.readProblem("");
    final SolutionContainer sC = SOLVER.solve(p);
    LOG.info("Final score: " + sC.getScore());
  }

}
