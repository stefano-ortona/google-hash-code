package google.com.ortona.hashcode;

import google.com.ortona.hashcode.preparation_2020.model.ProblemContainer;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class UtilsFilePizzaPreparation2020Test {

    static private final String filePathExample = "a_example.in";
    static private UtilsFilePizzaPreparation2020 fr = new UtilsFilePizzaPreparation2020(filePathExample);

    static private ProblemContainer container = fr.getProblemContainer();

    static int expectedGoal = 17;
    ArrayList<Integer> expectedNumbers = new ArrayList<>();

    @Test
    public void testGoal() {
        Assert.assertEquals(expectedGoal, container.getGoal());
    }

    @Test
    public void testNumbers() {
        expectedNumbers.add(2);
        expectedNumbers.add(5);
        expectedNumbers.add(6);
        expectedNumbers.add(8);

        for (int i = 0; i < expectedNumbers.size(); i++){
            Assert.assertEquals( expectedNumbers.get(i), container.getNumbers().get(i));

        }

    }

}
