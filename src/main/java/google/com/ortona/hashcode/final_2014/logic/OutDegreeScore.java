package google.com.ortona.hashcode.final_2014.logic;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;

public class OutDegreeScore implements ScoreComputation {

  @Override
  public double computeScore(Car car, Junction arrival, Street s, ProblemContainer c) {
    return arrival.getOutgoingStreets().size();
  }

}
