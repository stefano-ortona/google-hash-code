package google.com.ortona.hashcode.qualification_2016.model;

import java.util.ArrayList;
import java.util.List;

public class SolutionContainer {
  List<Action> actions;

  public SolutionContainer() {
    this.actions = new ArrayList<>();
  }

  public void addSolution(Action action) {
    this.actions.add(action);
  }

}
