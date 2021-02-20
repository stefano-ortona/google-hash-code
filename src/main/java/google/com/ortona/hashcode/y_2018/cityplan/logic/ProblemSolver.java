package google.com.ortona.hashcode.y_2018.cityplan.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2018.cityplan.model.ProblemContainer;
import google.com.ortona.hashcode.y_2018.cityplan.model.SolutionContainer;

public class ProblemSolver {
    private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);


    public SolutionContainer solve(ProblemContainer problem) {
        return new SolutionContainer();
    }

    public static void main(String[] args) {
        LOG.info("Hello World!");
    }

}
