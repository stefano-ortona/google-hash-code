package google.com.ortona.hashcode.final_2014.io;

import google.com.ortona.hashcode.UtilsFileStreet;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;

public class ProblemReader {
  public ProblemContainer readProblem(String fileLocation) {

    UtilsFileStreet fr = new UtilsFileStreet(fileLocation);
    return fr.getProblemContainer();
  }

}
