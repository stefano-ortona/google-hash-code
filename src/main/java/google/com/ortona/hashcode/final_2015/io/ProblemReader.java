package google.com.ortona.hashcode.final_2015.io;

import google.com.ortona.hashcode.UtilsBalloons;
import google.com.ortona.hashcode.final_2015.model.ProblemContainer;

public class ProblemReader {

  public ProblemContainer readProblem(String fileLocation) {
    UtilsBalloons fr = new UtilsBalloons(fileLocation);
    return fr.getProblemContainer();
  }

}
