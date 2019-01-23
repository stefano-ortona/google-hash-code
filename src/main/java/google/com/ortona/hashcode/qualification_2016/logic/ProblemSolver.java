package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.List;

import google.com.ortona.hashcode.qualification_2016.model.BestWarehouseResult;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
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
          final BestWarehouseResult w2p = warehousePicker.getBestWarehouse(d, problem);
          // processDrone(d, target, targetProduct);
        }
      }

    }
    return null;
  }

  private void processDrone(Drone d, Warehouse w, Product toPick) {

  }

  private List<Order> getNearOrders() {
    return null;
  }

}
