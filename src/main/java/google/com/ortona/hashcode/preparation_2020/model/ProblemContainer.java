package google.com.ortona.hashcode.preparation_2020.model;

import java.util.List;

public class ProblemContainer {

  private List<Integer> numbers;

  public List<Integer> getNumbers() {
    return numbers;
  }

  public void setNumbers(List<Integer> numbers) {
    this.numbers = numbers;
  }

  public int getGoal() {
    return goal;
  }

  public void setGoal(int goal) {
    this.goal = goal;
  }

  private int goal;

  public ProblemContainer(List<Integer> numbers, int goal) {
    this.numbers = numbers;
    this.goal = goal;
  }

}
