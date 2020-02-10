package google.com.ortona.hashcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilsFilePizzaPreparation2020Test {

    static private final String filePathExample = "a_example.in";
    static private UtilsFilePizzaPreparation2020 fr = new UtilsFilePizzaPreparation2020(filePathExample);

    static int expectedGoal = 17;
    static Integer[] integers = new Integer[] {1,2,3,4,5};
    static List<Integer> listOfInteger =  Arrays.asList(integers); // returns a fixed-size list backed by the specified array.
    static List<Integer> expectedNumbers = new ArrayList<>(listOfInteger); // good

    @Test
    private void testGoal() {
        Assert.assertEquals(expectedGoal, fr.getGoal());
    }

    @Test
    private void testNumbers() {
        Assert.assertEquals(true, expectedNumbers.equals(fr.getData()));
    }

}
