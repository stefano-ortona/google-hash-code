package google.com.ortona.hashcode.final_2015;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2015.io.ProblemReader;
import google.com.ortona.hashcode.final_2015.io.ProblemWriter;
import google.com.ortona.hashcode.final_2015.logic.ProblemSolver;
import google.com.ortona.hashcode.final_2015.model.Baloon;
import google.com.ortona.hashcode.final_2015.model.Pair;
import google.com.ortona.hashcode.final_2015.model.ProblemContainer;
import google.com.ortona.hashcode.final_2015.model.SolutionContainer;
import google.com.ortona.hashcode.final_2015.model.Status;

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

		final Baloon baloon = new Baloon(0, 1, 2, 0);
		final List<Baloon> baloons = new ArrayList<Baloon>();
		baloons.add(baloon);

		final Pair wind0[][] = new Pair[3][5];
		wind0[0][0] = new Pair(0, 1);
		wind0[0][1] = new Pair(0, 1);
		wind0[0][2] = new Pair(0, 1);
		wind0[0][3] = new Pair(0, 1);
		wind0[0][4] = new Pair(0, 1);
		wind0[1][0] = new Pair(0, 1);
		wind0[1][1] = new Pair(0, 1);
		wind0[1][2] = new Pair(0, 1);
		wind0[1][3] = new Pair(0, 1);
		wind0[1][4] = new Pair(0, 1);
		wind0[2][0] = new Pair(0, 1);
		wind0[2][1] = new Pair(0, 1);
		wind0[2][2] = new Pair(0, 1);
		wind0[2][3] = new Pair(0, 1);
		wind0[2][4] = new Pair(0, 1);

		final Pair wind1[][] = new Pair[3][5];
		wind1[0][0] = new Pair(-1, 0);
		wind1[0][1] = new Pair(-1, 0);
		wind1[0][2] = new Pair(-1, 0);
		wind1[0][3] = new Pair(-1, 0);
		wind1[0][4] = new Pair(-1, 0);
		wind1[1][0] = new Pair(-1, 0);
		wind1[1][1] = new Pair(-1, 0);
		wind1[1][2] = new Pair(-1, 0);
		wind1[1][3] = new Pair(-1, 0);
		wind1[1][4] = new Pair(-1, 0);
		wind1[2][0] = new Pair(-1, 0);
		wind1[2][1] = new Pair(-1, 0);
		wind1[2][2] = new Pair(-1, 0);
		wind1[2][3] = new Pair(-1, 0);
		wind1[2][4] = new Pair(-1, 0);

		final Pair wind2[][] = new Pair[3][5];
		wind2[0][0] = new Pair(0, 1);
		wind2[0][1] = new Pair(0, 1);
		wind2[0][2] = new Pair(0, 1);
		wind2[0][3] = new Pair(0, 2);
		wind2[0][4] = new Pair(0, 1);
		wind2[1][0] = new Pair(0, 2);
		wind2[1][1] = new Pair(0, 1);
		wind2[1][2] = new Pair(0, 2);
		wind2[1][3] = new Pair(0, 3);
		wind2[1][4] = new Pair(0, 2);
		wind2[2][0] = new Pair(0, 1);
		wind2[2][1] = new Pair(0, 1);
		wind2[2][2] = new Pair(0, 1);
		wind2[2][3] = new Pair(0, 2);
		wind2[2][4] = new Pair(0, 1);

		final List<Pair[][]> winds = Arrays.asList(wind0, wind1, wind2);
		final boolean originalGrid[][] = { { false, false, true, false, true }, { false, false, false, false, false },
				{ false, false, false, false, false } };
		final Status status = new Status(baloons, originalGrid, 3, 5, winds, 1);
		final ProblemContainer problem = new ProblemContainer(status);
		final SolutionContainer solution = SOLVER.solve(problem); // new SolutionContainer(Arrays.asList(0,2,3), 16);
		for (final Baloon bln : solution.getBaloons()) {
			Assert.assertEquals(Integer.valueOf(bln.getMoves().size()),
					Integer.valueOf(problem.getStatus().getMaxTurns()));
		}
		LOG.info(solution.toString());
	}

	@Test
	public void secondTest() throws IOException {
		final ProblemReader reader = new ProblemReader();
		final String file = "hashcode_2015_final_round.in";
		final ProblemContainer c = reader.readProblem(file);
		final SolutionContainer sC = SOLVER.solve(c);
		final ProblemWriter wC = new ProblemWriter();
		wC.writeProblem(file.replaceAll("in", "out"), sC);
	}

	@Test
	public void thirdTest() {
	}

}
