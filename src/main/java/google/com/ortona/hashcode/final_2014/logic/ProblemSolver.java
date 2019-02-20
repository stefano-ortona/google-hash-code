package google.com.ortona.hashcode.final_2014.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.SolutionContainer;
import google.com.ortona.hashcode.final_2014.model.Street;

/**
 *
 * @author stefano
 *
 */
public class ProblemSolver {

  Logger LOG = LoggerFactory.getLogger(getClass());

  BestJunction bJ = new BestJunction();

  public SolutionContainer process(ProblemContainer problem) {
    final SolutionContainer sCont = new SolutionContainer();
    sCont.setAllCars(problem.getAllCars());
    for (long i = 0; i < problem.getTotTime(); i++) {
      // get all cars currently available
      final long tmp = i;
      final List<Car> availableCars = problem.getAllCars().stream().filter(c -> c.getNextTimeAvailable() <= tmp)
          .collect(Collectors.toList());
      for (final Car c : availableCars) {
        // get next best junction
        final Junction target = bJ.computeBestJunction(c, problem);
        final Street connection = c.getCurrent().getTargetStreet(target);
        if ((c.getNextTimeAvailable() + connection.getTimeCost()) >= problem.getTotTime()) {
          LOG.info("I'm trying to move a car but I have no more time!");
        } else {
          c.moveToJunction(target);
        }
      }
    }
    sCont.computeScore();
    return sCont;
  }

}
