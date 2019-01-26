package google.com.ortona.hashcode.qualification_2016;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification_2016.io.ProblemReader;
import google.com.ortona.hashcode.qualification_2016.io.ProblemWriter;
import google.com.ortona.hashcode.qualification_2016.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;

public class Main {

  static Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
    // final String inputFile = "example.in";
	  // 
    final List<String> inputFiles = Arrays.asList("example.in","redundancy.in","mother_of_all_warehouses.in","busy_day.in");
    final ProblemReader reader = new ProblemReader();
    final ProblemSolver solver = new ProblemSolver();
    final Map<String, Integer> file2score = new HashMap<>();
    for (final String oneFile : inputFiles) {
      LOG.info("Processing file: '{}'", oneFile);
      final SolutionContainer solution = solver.process(reader.readProblem(oneFile));
      final ProblemWriter writer = new ProblemWriter();
      final int curScore = solution.score;
      file2score.put(oneFile, curScore);
      writer.writeProblem(oneFile + "_output", solution);
      LOG.info("Finished processing file '{}' with score '{}'", oneFile, curScore);
    }
    LOG.info("Individual scores: {}", file2score);
    LOG.info("Tot score: {}", file2score.values().stream().mapToInt(Integer::intValue).sum());
  }

}
