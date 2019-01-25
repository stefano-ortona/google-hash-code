package google.com.ortona.hashcode.qualification_2016;

import google.com.ortona.hashcode.qualification_2016.io.ProblemReader;
import google.com.ortona.hashcode.qualification_2016.io.ProblemWriter;
import google.com.ortona.hashcode.qualification_2016.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;

public class Main {

  public static void main(String[] args) {
    final String inputFile = Main.class.getResource("example.in").getPath();
    final ProblemReader reader = new ProblemReader();
    final ProblemSolver solver = new ProblemSolver();
    final SolutionContainer solution = solver.process(reader.readProblem(inputFile));
    final ProblemWriter writer = new ProblemWriter();
    //writer.writeProblem(inputFile + "_output", solution);
  }

}
