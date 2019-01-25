package google.com.ortona.hashcode.qualification_2016.io;

import google.com.ortona.hashcode.UtilsFileDrone;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;

public class ProblemReader {

  public ProblemContainer readProblem(String fileLocation) {
    
    UtilsFileDrone fr = new UtilsFileDrone(fileLocation);

    return fr.getProblemContainer();
  }

}
