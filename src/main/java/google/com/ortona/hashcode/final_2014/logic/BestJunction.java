package google.com.ortona.hashcode.final_2014.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;

public class BestJunction {
  Logger LOG = LoggerFactory.getLogger(getClass());

  // TO change if we want different strategies
  ScoreComputation scoreComp = new NaiveScoreComputation();

  public Junction computeBestJunction(Car c, ProblemContainer container) {
    final Junction j = c.getCurrent();
    double max = Double.MAX_VALUE * -1;
    Junction bestJunction = null;
    for (final Street s : j.getOutgoingStreets()) {
      Junction arrival = s.getEnd();
      if (arrival.equals(j)) {
        arrival = s.getStart();
      }
      final double curScore = scoreComp.computeScore(arrival, s, container);
      if (curScore > max) {
        max = curScore;
        bestJunction = arrival;
      } else {
        if ((curScore == max) && !s.isVisited()) {
          bestJunction = arrival;
        }
      }
    }
    return bestJunction;
  }

}
