package google.com.ortona.hashcode.pizza.logic;

import java.util.Map;

import google.com.ortona.hashcode.pizza.model.Ingredient;
import google.com.ortona.hashcode.pizza.model.Slice;

public interface IngredientScoreComputation {

  double computeScore(Map<Ingredient, Integer> ingredientLeft, Map<Ingredient, Integer> toAdd, Slice slice,
      int mimIngrSlize);

  void initialise(Ingredient[][] pizza);

}
