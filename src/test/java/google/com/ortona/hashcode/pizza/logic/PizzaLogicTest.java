package google.com.ortona.hashcode.pizza.logic;

import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.pizza.model.Inrgedient;
import google.com.ortona.hashcode.pizza.model.PizzaStatus;

public class PizzaLogicTest {
  static Inrgedient[][] pizza;
  static ScoreComputation score;

  @BeforeClass
  public static void bringUp() {
    score = new BestResidualIngredientsScore();

    pizza = new Inrgedient[3][5];
    pizza[0][0] = Inrgedient.m;
    pizza[0][1] = Inrgedient.m;
    pizza[0][2] = Inrgedient.m;
    pizza[0][3] = Inrgedient.m;
    pizza[0][4] = Inrgedient.m;
    pizza[1][0] = Inrgedient.m;
    pizza[1][1] = Inrgedient.t;
    pizza[1][2] = Inrgedient.t;
    pizza[1][3] = Inrgedient.t;
    pizza[1][4] = Inrgedient.m;
    pizza[2][0] = Inrgedient.m;
    pizza[2][1] = Inrgedient.m;
    pizza[2][2] = Inrgedient.m;
    pizza[2][3] = Inrgedient.m;
    pizza[2][4] = Inrgedient.m;
  }

  @Test
  public void testToyExample() {
    this.actualTest(pizza, 1);
  }

  private void actualTest(Inrgedient[][] pizza, int l) {
    final MinimumSlicePizza min = new MinimumSlicePizza(score);
    final PizzaStatus status = min.computeSlices(pizza, l);
  }

}
