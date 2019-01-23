package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.Map;

import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class ProblemSolver {

  public SolutionContainer process(ProblemContainer problem) {

    final BestWarehouse warehousePicker = new BestWarehouse();

    final int totTime = problem.getMaxInstant();

    for (int i = 0; i < totTime; i++) {
      for (final Drone d : problem.getDrones()) {
        if (d.getNextTimeAvailable() <= i) {
          final Map<Warehouse, Product> w2p = warehousePicker.getBestWarehouse(d, problem);
          final Warehouse target = w2p.keySet().iterator().next();
          final Product targetProduct = w2p.values().iterator().next();
          processDrone(d, target, targetProduct);
        }

      }

    }
    return null;
  }

  private void processDrone(Drone d, Warehouse w, Product toPick) {

  }

}
