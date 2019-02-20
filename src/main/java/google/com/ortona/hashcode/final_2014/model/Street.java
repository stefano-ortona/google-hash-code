package google.com.ortona.hashcode.final_2014.model;

public class Street {
  private Junction start;
  private Junction end;
  private double length; // contribute to the score
  private double timeCost;
  private boolean isBidirectional;
  private boolean isVisited;

  public Junction getStart() {
    return start;
  }

  public void setStart(Junction start) {
    this.start = start;
  }

  public Junction getEnd() {
    return end;
  }

  public void setEnd(Junction end) {
    this.end = end;
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public double getTimeCost() {
    return timeCost;
  }

  public void setTimeCost(double timeCost) {
    this.timeCost = timeCost;
  }

  public boolean isBidirectional() {
    return isBidirectional;
  }

  public void setBidirectional(boolean isBidirectional) {
    this.isBidirectional = isBidirectional;
  }

  public boolean isVisited() {
    return isVisited;
  }

  public void setVisited(boolean isVisited) {
    this.isVisited = isVisited;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((end == null) ? 0 : end.hashCode());
    result = (prime * result) + ((start == null) ? 0 : start.hashCode());
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
    final Street other = (Street) obj;
    if (end == null) {
      if (other.end != null) {
        return false;
      }
    } else if (!end.equals(other.end)) {
      return false;
    }
    if (start == null) {
      if (other.start != null) {
        return false;
      }
    } else if (!start.equals(other.start)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    if (this.isBidirectional) {
      return start.toString() + "<->" + end.toString();
    }
    return this.start.toString() + "->" + end.toString();
  }

}
