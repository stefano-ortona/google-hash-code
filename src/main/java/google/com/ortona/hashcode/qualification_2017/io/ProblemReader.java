package google.com.ortona.hashcode.qualification_2017.io;

import google.com.ortona.hashcode.UtilsFileStreaming;
import google.com.ortona.hashcode.qualification_2017.model.ProblemContainer;

public class ProblemReader {
  public ProblemContainer readProblem(String fileLocation) {

    UtilsFileStreaming fr = new UtilsFileStreaming(fileLocation);

    return fr.getProblemContainer();
  }

}
