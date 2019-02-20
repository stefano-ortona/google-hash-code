package google.com.ortona.hashcode.final_2014;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2014.io.ProblemReader;
import google.com.ortona.hashcode.final_2014.io.ProblemWriter;
import google.com.ortona.hashcode.final_2014.logic.ProblemSolver;
import google.com.ortona.hashcode.final_2014.model.SolutionContainer;

public class Main {

  static Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    final String inputFile1 = "paris_54000.in";
    final String inputFile2 = "test_4.in";
    final List<String> inputFiles = new LinkedList<>();
    inputFiles.add(inputFile1);
    // inputFiles.add(inputFile2);

    final ProblemReader reader = new ProblemReader();
    final ProblemSolver solver = new ProblemSolver();

    final Map<String, Double> file2score = new HashMap<>();
    for (final String oneFile : inputFiles) {
      LOG.info("Processing file: '{}'", oneFile);
      final SolutionContainer solution = solver.process(reader.readProblem(oneFile));
      final ProblemWriter writer = new ProblemWriter();
      final double curScore = solution.score;
      file2score.put(oneFile, curScore);
      writer.writeProblem(oneFile + "_output", solution);
      LOG.info("Finished processing file '{}' with score '{}'", oneFile, curScore);
    }
    LOG.info("Individual scores: {}", file2score);
    LOG.info("Tot score: {}", file2score.values().stream().mapToDouble(Double::doubleValue).sum());
  }

}
