package google.com.ortona.hashcode.pizza.model;

public class Point {
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
    // TODO Auto-generated constructor stub
  }

  public int x;
  public int y;

  @Override
  public String toString() {
    return "[" + x + "," + y + "]";
  }

}
