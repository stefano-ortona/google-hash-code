package google.com.ortona.hashcode.pizza.logic;

import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.pizza.model.Ingredient;
import google.com.ortona.hashcode.pizza.model.PizzaStatus;

public class PizzaLogicTest {
  static Ingredient[][] pizza;
  static IngredientScoreComputation score;

  @BeforeClass
  public static void bringUp() {
    score = new BestResidualIngredientsScore();

    pizza = new Ingredient[3][5];
    pizza[0][0] = Ingredient.m;
    pizza[0][1] = Ingredient.m;
    pizza[0][2] = Ingredient.m;
    pizza[0][3] = Ingredient.m;
    pizza[0][4] = Ingredient.m;
    pizza[1][0] = Ingredient.m;
    pizza[1][1] = Ingredient.t;
    pizza[1][2] = Ingredient.t;
    pizza[1][3] = Ingredient.t;
    pizza[1][4] = Ingredient.m;
    pizza[2][0] = Ingredient.m;
    pizza[2][1] = Ingredient.m;
    pizza[2][2] = Ingredient.m;
    pizza[2][3] = Ingredient.m;
    pizza[2][4] = Ingredient.m;
  }

  @Test
  public void testToyExample() {
    this.actualTest(pizza, 1);
  }

  private void actualTest(Ingredient[][] pizza, int l) {
    final MinimumSlicePizza min = new MinimumSlicePizza(score);
    final PizzaStatus status = min.computeSlices(pizza, l);
    
    final MaximunSliceDimension maxDimen = new MaximunSliceDimension(status, 6);
    maxDimen.maximizeSlicesDimension();
  }

}
