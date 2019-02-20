package google.com.ortona.hashcode.final_2014.logic;

import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;

public interface ScoreComputation {

  public double computeScore(Junction arrival, Street s, ProblemContainer c);

}
