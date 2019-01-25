package google.com.ortona.hashcode.qualification_2016.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import google.com.ortona.hashcode.qualification_2016.model.Action;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;

public class ProblemWriter {
  public static void writeProblem(String outputFile, SolutionContainer solution)
      throws FileNotFoundException, UnsupportedEncodingException {
    final PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
    for (final Action action : solution.getActions()) {
      writer.print(action.toString());
      writer.println();
    }
    writer.close();
  }
}