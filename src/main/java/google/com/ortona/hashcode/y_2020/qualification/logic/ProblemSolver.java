package google.com.ortona.hashcode.y_2020.qualification.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2020.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.y_2020.qualification.model.SolutionContainer;

public class ProblemSolver {
    private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

    // SCORRERE LIBRERIE
    // PER OGNI LIBRERIA CALCOLARE LO SCORE (bXS/d)? RICORDA LIMITE GIORNALIERO
    //


    private SolutionContainer solutionContainer = new SolutionContainer();

    public SolutionContainer solve(ProblemContainer problem) {

        return solutionContainer;
    }

    public static void main(String[] args) {
        LOG.info("Hello World!");
    }

}
