package google.com.ortona.hashcode.final_2014.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;

public class NaiveScoreComputation implements ScoreComputation {

  Logger LOG = LoggerFactory.getLogger(getClass());

  @Override
  public double computeScore(Junction arrival, Street s, ProblemContainer c) {
    final double score = (s.getLength() / s.getTimeCost()) * arrival.getJunctionScore();
    if (score <= 0) {
      LOG.info("Look I computed a negative score!");
    }
    return score;
  }

}
