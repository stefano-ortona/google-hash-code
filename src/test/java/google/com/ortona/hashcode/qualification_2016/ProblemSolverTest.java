package google.com.ortona.hashcode.qualification_2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import google.com.ortona.hashcode.qualification_2016.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2016.model.Action;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class ProblemSolverTest {

  private final static ProblemSolver SOLVER = new ProblemSolver();

  @Test
  public void firstTest() {

    final Product product0 = new Product();
    product0.setId(0);
    product0.setWeight(100);
    final Product product1 = new Product();
    product1.setId(1);
    product1.setWeight(5);
    final Product product2 = new Product();
    product2.setId(2);
    product2.setWeight(450);

    final Map<Product, Integer> product2quantityOrder0 = new HashMap<Product, Integer>();
    product2quantityOrder0.put(product2, 1);
    product2quantityOrder0.put(product0, 1);
    final Order order0 = new Order();
    order0.setId(0);
    order0.setRow(1);
    order0.setColumn(1);
    order0.setProducts2quantity(product2quantityOrder0);

    final Map<Product, Integer> product2quantityOrder1 = new HashMap<Product, Integer>();
    product2quantityOrder1.put(product0, 1);
    final Order order1 = new Order();
    order1.setId(1);
    order1.setRow(3);
    order1.setColumn(3);
    order1.setProducts2quantity(product2quantityOrder1);

    final Map<Product, Integer> product2quantityOrder2 = new HashMap<Product, Integer>();
    product2quantityOrder2.put(product2, 1);
    final Order order2 = new Order();
    order2.setId(2);
    order2.setRow(5);
    order2.setColumn(6);
    order2.setProducts2quantity(product2quantityOrder2);

    final Drone drone0 = new Drone();
    drone0.setId(0);
    drone0.setCapacity(500);
    drone0.setNextTimeAvailable(0);

    final Drone drone1 = new Drone();
    drone1.setId(1);
    drone1.setCapacity(500);
    drone1.setNextTimeAvailable(0);

    final Drone drone2 = new Drone();
    drone2.setId(2);
    drone2.setCapacity(500);
    drone2.setNextTimeAvailable(0);

    final Map<Product, Integer> product2quantityWarehouse0 = new HashMap<Product, Integer>();
    product2quantityWarehouse0.put(product0, 5);
    product2quantityWarehouse0.put(product1, 1);
    final Warehouse warehouse0 = new Warehouse();
    warehouse0.setId(0);
    warehouse0.setRow(0);
    warehouse0.setColumn(0);
    warehouse0.setProduct2quantity(product2quantityWarehouse0);

    final Map<Product, Integer> product2quantityWarehouse1 = new HashMap<Product, Integer>();
    product2quantityWarehouse1.put(product1, 10);
    product2quantityWarehouse1.put(product2, 2);
    final Warehouse warehouse1 = new Warehouse();
    warehouse1.setId(1);
    warehouse1.setRow(5);
    warehouse1.setColumn(5);
    warehouse1.setProduct2quantity(product2quantityWarehouse1);

    final ProblemContainer problem = new ProblemContainer();
    problem.setMaxInstant(50);
    problem.setNumRows(100);
    problem.setNumColumns(100);
    final ArrayList<Drone> drones = new ArrayList<Drone>();
    drones.add(drone0);
    drones.add(drone1);
    drones.add(drone2);
    final ArrayList<Order> orders = new ArrayList<Order>();
    orders.add(order0);
    orders.add(order1);
    orders.add(order2);
    final ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
    warehouses.add(warehouse0);
    warehouses.add(warehouse1);
    problem.setDrones(drones);
    problem.setOrders(orders);
    problem.setWarehouses(warehouses);
    final SolutionContainer solution = SOLVER.process(problem);
    Assert.assertNotNull(solution);
    int counter = 0;

    for (final Action action : solution.getActions()) {
      System.out.println("TEST PRINT %%% ");
      System.out.print("Command " + counter + " to Drone: " + action.getDrone().getId() + " || ");
      final String object = (action.getType().equals("L")) ? "Load at Warehouse" : "Deliver for Order";
      System.out.print(object + ": " + action.getId() + " || ");
      System.out.print("products of type: " + action.getProductId() + " || ");
      System.out.print(", " + action.getQuantity() + " of them/it || ");
      counter++;
    }
  }

}
