package google.com.ortona.hashcode.qualification_2016.logic;

public class DistanceUtils {

  public static int computeDistance(int row1, int column1, int row2, int column2) {
    return (int) Math.ceil(Math.sqrt(Math.pow(row1 - row2, 2) + Math.pow(column1 - column2, 2)));
  }

}
