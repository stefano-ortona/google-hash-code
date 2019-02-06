package google.com.ortona.hashcode.qualification_2017;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification_2017.io.ProblemReader;
import google.com.ortona.hashcode.qualification_2017.io.ProblemWriter;
import google.com.ortona.hashcode.qualification_2017.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2017.model.SolutionContainer;

public class Main {

	static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		final String inputFile1 = "example.in";
		final String inputFile2 = "";
		final String inputFile3 = "";
		final String inputFile4 = "";
		final List<String> inputFiles = new LinkedList<>();
		inputFiles.add(inputFile1);
		inputFiles.add(inputFile2);
		inputFiles.add(inputFile3);
		inputFiles.add(inputFile4);

		final ProblemReader reader = new ProblemReader();
		final ProblemSolver solver = new ProblemSolver();
		final ProblemWriter writer = new ProblemWriter();

		final Map<String, Double> file2score = new HashMap<>();
		for (final String oneFile : inputFiles) {
			LOG.info("Processing file: '{}'", oneFile);
			final SolutionContainer solution = solver.process(reader.readProblem(oneFile));
			final double curScore = solution.score;
			file2score.put(oneFile, curScore);
			writer.writeProblem(oneFile + "_output", solution);
			LOG.info("Finished processing file '{}' with score '{}'", oneFile, curScore);
		}
		LOG.info("Individual scores: {}", file2score);
		LOG.info("Tot score: {}", file2score.values().stream().mapToDouble(Double::doubleValue).sum());
	}

}
