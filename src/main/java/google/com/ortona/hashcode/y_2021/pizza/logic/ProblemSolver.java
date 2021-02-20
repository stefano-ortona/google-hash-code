package google.com.ortona.hashcode.y_2021.pizza.logic;

import google.com.ortona.hashcode.y_2021.pizza.model.TeamAllocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2021.pizza.model.ProblemContainer;
import google.com.ortona.hashcode.y_2021.pizza.model.SolutionContainer;

public class ProblemSolver {
    private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

    private APizzaChooser pizzaChooser = new AlwaysMaximumPizzaChooser();

    private SolutionContainer solutionContainer = new SolutionContainer();

    public SolutionContainer solve(ProblemContainer problem) {

        while (true) {
            TeamAllocation teamAllocation = pizzaChooser.choose(problem.getTeamMap(), problem.getPizzaList());

            if (teamAllocation == null) break; // EXIT

            int teamSize = teamAllocation.getPizzaList().size();

            problem.getTeamMap().put(teamSize, problem.getTeamMap().get(teamSize) - 1); // decrement team count

            solutionContainer.getAllocationList().add(teamAllocation);
        }

        return solutionContainer;
    }

    public static void main(String[] args) {
        LOG.info("Hello World!");
    }

}
