package google.com.ortona.hashcode.pizza.model;

/**
 * This class represents a slice of pizza
 *
 * A slice of pizza is represented by two points:
 * upper left corner, and lowerRightCorner
 *
 * @author stefano
 *
 */
public class Slice {

  public int upperLeftX;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + lowerRightX;
    result = (prime * result) + lowerRightY;
    result = (prime * result) + upperLeftX;
    result = (prime * result) + upperLeftY;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Slice other = (Slice) obj;
    if (lowerRightX != other.lowerRightX) {
      return false;
    }
    if (lowerRightY != other.lowerRightY) {
      return false;
    }
    if (upperLeftX != other.upperLeftX) {
      return false;
    }
    if (upperLeftY != other.upperLeftY) {
      return false;
    }
    return true;
  }

  public int upperLeftY;
  public int lowerRightX;
  public int lowerRightY;

  public Slice(int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY) {
    this.upperLeftX = upperLeftX;
    this.upperLeftY = upperLeftY;
    this.lowerRightX = lowerRightX;
    this.lowerRightY = lowerRightY;
  }

  @Override
  public String toString() {
    return "UpL=[" + this.upperLeftX + "," + this.upperLeftY + "], LoR=[" + this.lowerRightX + "," + this.lowerRightY
        + "]";
  }

}
