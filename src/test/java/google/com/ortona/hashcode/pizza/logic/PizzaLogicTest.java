package google.com.ortona.hashcode.pizza.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.UtilsFile;
import google.com.ortona.hashcode.pizza.model.Ingredient;
import google.com.ortona.hashcode.pizza.model.PizzaStatus;
import google.com.ortona.hashcode.pizza.model.Slice;

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
    final List<Slice> slices = this.actualTest(pizza, 1, 6);

    // 3 slices in output
    final Slice s1 = new Slice(0, 0, 2, 1);
    final Slice s2 = new Slice(0, 2, 2, 2);
    final Slice s3 = new Slice(0, 3, 2, 4);
    Assert.assertTrue(slices.contains(s1));
    Assert.assertTrue(slices.contains(s2));
    Assert.assertTrue(slices.contains(s3));
  }

  private List<Slice> actualTest(Ingredient[][] pizza, int l, int maxSize) {
    final MinimumSlicePizza min = new MinimumSlicePizza(score);
    final PizzaStatus status = min.computeSlices(pizza, l, maxSize);
    status.allSlices.forEach(slice -> {
      System.out.println(slice);
    });
    final MaximunSliceDimension maxDimen = new MaximunSliceDimension(status, maxSize);
    maxDimen.maximizeSlicesDimension();
    // print final output
    // System.out.println(status.allSlices.size());
    status.allSlices.forEach(slice -> {
      // System.out.println(slice.upperLeftX + " " + slice.upperLeftY + " " + slice.lowerRightX + " " +
      // slice.lowerRightY);
    });
    // write output file
    BestResidualIngredientsScore.printTotalScore(status);
    return status.allSlices;
  }

  private void readFromFileTest(final String filePath) throws IOException {
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
    final List<Slice> outputSlices = actualTest(pizza, l, max);
    // write outputfile
    final BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath + ".out")));
    writer.write(outputSlices.size() + "\n");
    outputSlices.forEach(slice -> {
      try {
        writer.write(
            slice.upperLeftX + " " + slice.upperLeftY + " " + slice.lowerRightX + " " + slice.lowerRightY + "\n");
      } catch (final IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    writer.close();
  }

  @Test
  public void testSmall() throws Exception {
    final String filePath = "small.in";
    readFromFileTest(filePath);
  }

  @Test
  public void testMedium() throws Exception {
    final String filePath = "medium.in";
    readFromFileTest(filePath);
  }

  @Test
  public void testBig() throws Exception {
    final String filePath = "big.in";
    readFromFileTest(filePath);
  }

}
