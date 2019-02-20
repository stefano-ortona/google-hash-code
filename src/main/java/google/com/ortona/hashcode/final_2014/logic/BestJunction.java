package google.com.ortona.hashcode.final_2014.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;

public class BestJunction {
  Logger LOG = LoggerFactory.getLogger(getClass());

  public Junction computeBestJunction(Car c, ProblemContainer container) {
    final Junction j = c.getCurrent();
    double max = Double.MIN_VALUE;
    Junction bestJunction = null;
    for (final Street s : j.getOutgoingStreets()) {
      Junction arrival = s.getEnd();
      if (arrival.equals(j)) {
        arrival = s.getStart();
      }
      final double curScore = computeScore(arrival, s);
      if (curScore > max) {
        max = curScore;
        bestJunction = arrival;
      }
    }
    return bestJunction;
  }

  private double computeScore(Junction arrival, Street s) {
    final double score = (s.getLength() / s.getTimeCost()) * arrival.getJunctionScore();
    if (score <= 0) {
      LOG.info("Look I computed a negative score!");
    }
    return score;
  }

}
