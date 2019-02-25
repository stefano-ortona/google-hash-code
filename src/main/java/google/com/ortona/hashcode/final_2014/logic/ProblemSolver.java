package google.com.ortona.hashcode.final_2014.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Path;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.SolutionContainer;

/**
 *
 * @author stefano
 *
 */
public class ProblemSolver {

  Logger LOG = LoggerFactory.getLogger(getClass());

  BestJunction bJ = new BestJunction();

  ComputeAllPaths computeP = new ComputeAllPaths();

  public SolutionContainer process(ProblemContainer problem) {
    final SolutionContainer sCont = new SolutionContainer();
    sCont.setAllCars(problem.getAllCars());
    for (long i = 0; i < problem.getTotTime(); i++) {
      // get all cars currently available
      final long tmp = i;
      final List<Car> availableCars = problem.getAllCars().stream().filter(c -> c.getNextTimeAvailable() <= tmp)
          .collect(Collectors.toList());
      if (availableCars.size() > 0) {
        LOG.info("At iteration '{}' (out of '{}') I have '{}' cars to assign", i, problem.getTotTime(),
            availableCars.size());
      }
      for (final Car c : availableCars) {
        final Path bestP = computeP.computeBest(c, problem.getTotTime());
        if (bestP != null) {
          for (int j = 1; j < bestP.getJunctions().size(); j++) {
            c.moveToJunction(bestP.getJunctions().get(j));
          }
        }
      }
    }
    sCont.computeScore();
    return sCont;
  }

}
