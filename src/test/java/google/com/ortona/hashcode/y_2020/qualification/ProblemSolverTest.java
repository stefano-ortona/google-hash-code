package google.com.ortona.hashcode.y_2020.qualification;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2020.qualification.io.ProblemReader;
import google.com.ortona.hashcode.y_2020.qualification.io.ProblemWriter;
import google.com.ortona.hashcode.y_2020.qualification.logic.ProblemSolver;
import google.com.ortona.hashcode.y_2020.qualification.model.Book;
import google.com.ortona.hashcode.y_2020.qualification.model.Library;
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
		final Book b1 = new Book();
		b1.setId(0);
		b1.setScore(1);
		final Book b2 = new Book();
		b2.setId(1);
		b2.setScore(2);
		final Book b3 = new Book();
		b3.setId(2);
		b3.setScore(3);
		final Book b4 = new Book();
		b4.setId(3);
		b4.setScore(6);
		final Book b5 = new Book();
		b5.setId(4);
		b5.setScore(5);
		final Book b6 = new Book();
		b6.setId(5);
		b6.setScore(4);
		final Library l1 = new Library();
		l1.setId(0);
		l1.setBookList(Arrays.asList(b4,b5,b3,b2,b1));
		l1.setBooks4days(2);
		l1.setSignup(2);
		final Library l2 = new Library();
		l2.setId(1);
		l2.setBookList(Arrays.asList(b4,b6,b3,b1));
		l2.setBooks4days(1);
		l2.setSignup(3);

		ProblemContainer pC = new ProblemContainer();
		pC.LIBRARY_LIST = Arrays.asList(l1,l2);
		pC.TOTAL_DAY_COUNT = 7;
		// create here a mock input for test


		ProblemSolver pS = new ProblemSolver();
		SolutionContainer sC = pS.solve(pC);
		Assert.assertNotNull(sC);
		LOG.info("Final score is: {}, with solution: {}",sC.getScore(), sC.toString());

	}

	@Test
	public void testRealInput() throws IOException {
//						final String inputFile = "a_example.txt";
//						final String inputFile = "b_read_on.txt";
//		final String inputFile = "c_incunabula.txt";
						final String inputFile = "d_tough_choices.txt";
//						final String inputFile = "e_so_many_books.txt";
//		final String inputFile = "f_libraries_of_the_world.txt";

		final ProblemContainer p = READER.readProblem(inputFile);

		final SolutionContainer sC = SOLVER.solve(p);
		WRITER.writeProblem("output_" + inputFile, sC);
		LOG.info("test1 solution: " + sC.toString() + "\nScore: " + sC.getScore());
	}
}
