package google.com.ortona.hashcode.y_2022.pizza.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import google.com.ortona.hashcode.y_2022.pizza.model.SolutionContainer;

public class ProblemWriter {

	public void writeProblem(String outputFile, SolutionContainer solution) throws IOException {
		final BufferedWriter wr = new BufferedWriter(new FileWriter(new File(outputFile)));
		wr.write(solution.toString());
		wr.close();
	}

}
