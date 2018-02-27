package google.com.ortona.hashcode.pizza.logic;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;

import google.com.ortona.hashcode.pizza.model.Ingredient;
import google.com.ortona.hashcode.pizza.model.PizzaStatus;
import google.com.ortona.hashcode.pizza.model.Slice;

public class BestResidualIngredientsScore implements IngredientScoreComputation {

  private final Map<Ingredient, Integer> totalQuantity = Maps.newHashMap();

  private final double epsilon = 0.01;

  @Override
  public double computeScore(Map<Ingredient, Integer> curStatusIngredient, Map<Ingredient, Integer> toAdd, Slice slice,
      int mimIngrSlize) {
    final Map<Ingredient, Integer> ingrLeft = getRemainingIngredients(curStatusIngredient, toAdd);
    double score = 0;
    // check how much is left to complete slice, and choose the one closer to complete slice
    for (final Ingredient oneIngr : Ingredient.values()) {
      final int curValue = Math.min(slice.ingr2quantity.getOrDefault(oneIngr, 0) + toAdd.getOrDefault(oneIngr, 0),
          mimIngrSlize);
      double multiplyFactor = ingrLeft.get(oneIngr) / (totalQuantity.get(oneIngr) + 0.);
      multiplyFactor = multiplyFactor == 0 ? epsilon : multiplyFactor;
      score += (curValue * multiplyFactor);
    }
    return score;
  }

  @Override
  public void initialise(final Ingredient[][] pizza) {
    for (int i = 0; i < pizza.length; i++) {
      for (int j = 0; j < pizza[0].length; j++) {
        final int curValue = totalQuantity.getOrDefault(pizza[i][j], 0) + 1;
        totalQuantity.put(pizza[i][j], curValue);
      }
    }
  }

  private Map<Ingredient, Integer> getRemainingIngredients(final Map<Ingredient, Integer> curStatusIngredient,
      final Map<Ingredient, Integer> toAdd) {
    final Map<Ingredient, Integer> ingrLeft = Maps.newHashMap();
    for (final Ingredient oneINgr : totalQuantity.keySet()) {
      final int value = totalQuantity.get(oneINgr) - curStatusIngredient.getOrDefault(oneINgr, 0)
          - toAdd.getOrDefault(oneINgr, 0);
      ingrLeft.put(oneINgr, value);
    }
    return ingrLeft;
  }

  public static void printTotalScore(final PizzaStatus pizStatus) {
    final AtomicDouble totScore = new AtomicDouble(0);
    final int totPizza = pizStatus.pizza.length * pizStatus.pizza[0].length;
    pizStatus.allSlices.forEach(slice -> {
      totScore.getAndAdd(slice.dimen());
    });
    System.out.println(totScore.get() + "(" + (totScore.get() / totPizza) + ", TOT PIZZA=" + totPizza + ")");
  }

}
