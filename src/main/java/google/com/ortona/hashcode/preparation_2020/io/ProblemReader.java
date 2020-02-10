package google.com.ortona.hashcode.preparation_2020.io;

import google.com.ortona.hashcode.UtilsFilePizzaPreparation2020;
import google.com.ortona.hashcode.preparation_2020.model.ProblemContainer;

public class ProblemReader {
	public ProblemContainer readProblem(String fileLocation) {

		UtilsFilePizzaPreparation2020 fr = new UtilsFilePizzaPreparation2020(fileLocation);
	  	return fr.getProblemContainer();
	}

}
