package google.com.ortona.hashcode.preparation_2020.model;

import java.util.List;

public class SolutionContainer {

  private List<Integer> indexes;
  private int score;

  public List<Integer> getIndexes() {
    return indexes;
  }

  public void setIndexes(List<Integer> indexes) {
    this.indexes = indexes;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public SolutionContainer(List<Integer> indexes, int score) {
    this.indexes = indexes;
    this.score = score;
  }

  @Override
  public String toString() {
    // TODO
    return null;
  }
}
