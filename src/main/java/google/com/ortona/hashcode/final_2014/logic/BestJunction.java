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

  double curScore;

  public Junction computeBestJunction(Car c, ProblemContainer container) {
    Junction best = computeBestJunction(c, new NaiveScoreComputation(), container);
    if (curScore <= 0) {
      best = computeBestJunction(c, new BestStupidScore(), container);
      if (curScore <= 0) {
        best = computeBestJunction(c, new OutDegreeScore(), container);
      }
    }
    return best;
  }

  private Junction computeBestJunction(Car c, ScoreComputation score, ProblemContainer container) {
    final Junction j = c.getCurrent();
    double max = Double.MAX_VALUE * -1;
    Junction bestJunction = null;
    for (final Street s : j.getOutgoingStreets()) {
      Junction arrival = s.getEnd();
      if (arrival.equals(j)) {
        arrival = s.getStart();
      }
      final double curScore = score.computeScore(c, arrival, s, container);
      if (curScore > max) {
        max = curScore;
        bestJunction = arrival;
      } else {
        if ((curScore == max) && !s.isVisited()) {
          bestJunction = arrival;
        }
      }
    }
    curScore = max;
    return bestJunction;

  }

}
