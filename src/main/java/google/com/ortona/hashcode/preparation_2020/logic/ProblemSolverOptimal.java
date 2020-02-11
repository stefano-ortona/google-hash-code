package google.com.ortona.hashcode.preparation_2020.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import google.com.ortona.hashcode.preparation_2020.model.ProblemContainer;
import google.com.ortona.hashcode.preparation_2020.model.SolutionContainer;

public class ProblemSolverOptimal {

  private int optimalTarget;
  private List<Integer> optimalSolution = null;

  public SolutionContainer solve(ProblemContainer problem) {

    optimalTarget = -1;
    final List<Integer> allNumbbers = problem.getNumbers();

    for (int i = allNumbbers.size() - 1; i >= 0; i--) {
      final int curSum = allNumbbers.get(i);
      final List<Integer> solution = new LinkedList<>();
      solution.add(i);
      final int computeSum = solve(curSum, allNumbbers, i - 1, solution, problem.getGoal());
      if (solution.size() > 1) {
        // try to remove last numbers and re-compute it
        final int removedNum = solution.remove(0);
        solve(computeSum - allNumbbers.get(removedNum), allNumbbers, removedNum - 1, solution, problem.getGoal());
      }
    }

    Collections.sort(optimalSolution);
    final SolutionContainer sC = new SolutionContainer(optimalSolution, optimalTarget);
    return sC;
  }

  private int solve(int curSum, List<Integer> allNums, int index, List<Integer> sol, int goal) {
    for (int i = index; i >= 0; i--) {
      final int curNum = allNums.get(i);
      if ((curSum + curNum) <= goal) {
        sol.add(i);
        curSum += curNum;
        if (curSum == goal) {
          // cannot really do better than this
          break;
        }
      }
    }
    if (curSum > optimalTarget) {
      optimalTarget = curSum;
      optimalSolution = new ArrayList<>();
      optimalSolution.addAll(sol);
    }
    return curSum;
  }

}
