package google.com.ortona.hashcode.pizza.logic;

import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.UtilsFile;
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
    this.actualTest(pizza, 1, 6);
  }

  private void actualTest(Ingredient[][] pizza, int l, int maxSize) {
    final MinimumSlicePizza min = new MinimumSlicePizza(score);
    final PizzaStatus status = min.computeSlices(pizza, l, maxSize);
    status.allSlices.forEach(slice -> {
      System.out.println(slice);
    });
    final MaximunSliceDimension maxDimen = new MaximunSliceDimension(status, 6);
    maxDimen.maximizeSlicesDimension();
  }

  private void readFromFileTest(final String filePath) {
    final UtilsFile file = new UtilsFile(filePath);
    file.createHeader();
    file.createData();
    final char[][] pizzaInput = file.getData();
    // convert to correct input
    final Ingredient[][] pizza = new Ingredient[pizzaInput.length][pizzaInput[0].length];
    for (int i = 0; i < pizza.length; i++) {
      for (int j = 0; j < pizza[0].length; j++) {
        if (pizzaInput[i][j] == 'T') {
          pizza[i][j] = Ingredient.t;
        }
        if (pizzaInput[i][j] == 'M') {
          pizza[i][j] = Ingredient.m;
        }
      }
    }
    final int max = file.getHeader()[3];
    final int l = file.getHeader()[2];
    actualTest(pizza, l, max);
  }

  @Test
  public void testSmall() {
    final String filePath = "small.in";
    readFromFileTest(filePath);
  }

}
