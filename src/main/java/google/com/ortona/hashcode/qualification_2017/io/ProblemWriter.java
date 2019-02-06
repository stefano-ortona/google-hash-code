package google.com.ortona.hashcode.qualification_2017.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.SolutionContainer;

public class ProblemWriter {

	public static void writeProblem(String outputFile, SolutionContainer solution)
      throws FileNotFoundException, UnsupportedEncodingException {
    final PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
    for (final Cache cache: solution.getCaches()) {
      writer.print(cache.toString());
      writer.println();
    }
    writer.close();
  }

}
