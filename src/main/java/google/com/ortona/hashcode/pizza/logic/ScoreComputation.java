package google.com.ortona.hashcode.pizza.logic;

import java.util.Map;

import google.com.ortona.hashcode.pizza.model.Inrgedient;
import google.com.ortona.hashcode.pizza.model.Slice;

public interface ScoreComputation {

  double computeScore(Map<Inrgedient, Integer> ingredientLeft, Map<Inrgedient, Integer> toAdd, Slice slice);

}
