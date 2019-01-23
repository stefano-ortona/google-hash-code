package google.com.ortona.hashcode.qualification_2016.logic;

import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class ProblemSolver {

  public SolutionContainer process(ProblemContainer problem) {

    final Warehouse w = new Warehouse();
    w.releaseProduct(p, quantity);
    final Drone d = new DroneO();
    d.loadProduct(p, quantity);
    final Order o = new Order();
    o.deliverProduct(p, quantity);

    // TODO
    return null;
  }

}
