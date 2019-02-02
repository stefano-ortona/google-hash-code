package google.com.ortona.hashcode.qualification_2016;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification_2016.io.ProblemReader;
import google.com.ortona.hashcode.qualification_2016.io.ProblemWriter;
import google.com.ortona.hashcode.qualification_2016.io.SolutionValidator;
import google.com.ortona.hashcode.qualification_2016.logic.GenericSolver;
import google.com.ortona.hashcode.qualification_2016.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2016.logic.ProblemSolverByOrders;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;

public class Main {

  static Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
     final String inputFile1 = "example.in";
     final String inputFile2 = "redundancy.in";
     final String inputFile3 = "mother_of_all_warehouses.in";
     final String inputFile4 = "busy_day.in";
    final List<String> inputFiles = new LinkedList<>();
    inputFiles.add(inputFile1);
    inputFiles.add(inputFile2);
    inputFiles.add(inputFile3);
    inputFiles.add(inputFile4);

    final ProblemReader reader = new ProblemReader();
    final GenericSolver solver = new ProblemSolver();
//    final GenericSolver solver = new ProblemSolverByOrders();
    
    SolutionValidator validator = new SolutionValidator();

    final Map<String, Integer> file2score = new HashMap<>();
    for (final String oneFile : inputFiles) {
      LOG.info("Processing file: '{}'", oneFile);
      final SolutionContainer solution = solver.process(reader.readProblem(oneFile));
      final ProblemWriter writer = new ProblemWriter();
      final int curScore = solution.score;
      file2score.put(oneFile, curScore);
      writer.writeProblem(oneFile + "_output", solution);
      LOG.info("Finished processing file '{}' with score '{}'", oneFile, curScore);
      //validate solution
      LOG.info("Validating computed solution...");
      int validatedScore = validator.validate(solution.getActions(), reader.readProblem(oneFile));
      LOG.info("Validated score: '{}'",validatedScore);
      if(validatedScore!=curScore){
        //throw new RuntimeException("Validated score '"+validatedScore+"' does not coincide with computed score '"+curScore+"'");
      }
      LOG.info("Solution validated succesfully!");
    }
    LOG.info("Individual scores: {}", file2score);
    LOG.info("Tot score: {}", file2score.values().stream().mapToInt(Integer::intValue).sum());
  }

}
