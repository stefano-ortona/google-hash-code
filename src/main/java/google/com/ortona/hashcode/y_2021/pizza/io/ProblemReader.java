package google.com.ortona.hashcode.y_2021.pizza.io;

import google.com.ortona.hashcode.UtilsPizza2021;
import google.com.ortona.hashcode.y_2021.pizza.model.ProblemContainer;

public class ProblemReader {
	public ProblemContainer readProblem(String fileLocation) {
		UtilsPizza2021 utils = new UtilsPizza2021(fileLocation);
		ProblemContainer pc = utils.getProblemContainer();
		return pc;
	}
}
