package google.com.ortona.hashcode.qualification_2017;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification_2017.io.ProblemReader2;
import google.com.ortona.hashcode.qualification_2017.io.ProblemWriter;
import google.com.ortona.hashcode.qualification_2017.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2017.model.SolutionContainer;

public class Main {

  static Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    final String exampleFile = "example.in";
    final String inputFile1 = "videos_worth_spreading.in";
    final String inputFile2 = "kittens.in";
    final String inputFile3 = "me_at_the_zoo.in";
    final String inputFile4 = "trending_today.in";
    final List<String> inputFiles = new LinkedList<>();
    inputFiles.add(inputFile1);
    inputFiles.add(inputFile2);
    inputFiles.add(inputFile3);
    inputFiles.add(inputFile4);
    // inputFiles.add(exampleFile);

    final ProblemReader2 reader = new ProblemReader2();
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
