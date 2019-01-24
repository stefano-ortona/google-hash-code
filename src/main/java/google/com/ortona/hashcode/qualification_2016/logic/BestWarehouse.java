package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import google.com.ortona.hashcode.qualification_2016.model.BestWarehouseResult;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class BestWarehouse {

  public BestWarehouseResult getBestWarehouse(Drone drone, ProblemContainer problem) {
    final BestWarehouseResult result = new BestWarehouseResult();

    int bestPathDistance = Integer.MAX_VALUE;

    for (final Order order : problem.getOrders()) {
      // per ogni prodotto di ogni ordine calcolo la distanza dalla warehouse e prendo la migliore

      for (final Map.Entry<Product, Integer> productIntegerEntry : order.getProducts2quantity().entrySet()) {
        for (final Warehouse warehouse : problem.getWarehouses()) {
          // check warehouse contains the product
          if (containsProduct(warehouse, productIntegerEntry.getKey())) {
            final int pathDistance = calculatePathDistance(drone, warehouse, order);
            if (pathDistance < bestPathDistance) {
              bestPathDistance = pathDistance;
              // update result
              result.setOrder(order);
              result.setProduct(productIntegerEntry.getKey());
              result.setWarehouse(warehouse);
            }
          }
        }
      }
    }

    return result;
  }

  private boolean containsProduct(Warehouse w, Product p) {
    return w.getProduct2quantity().keySet().stream().filter(pr -> pr.getId() == p.getId()).findFirst().isPresent();
  }

  public List<Order> sortOrderListByDrone(List<Order> orderList, Drone drone) {
    final ArrayList<Order> resultOrderList = new ArrayList<>(orderList);
    resultOrderList.sort((o1, o2) -> {
      final int distanceO1 = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), o1.getRow(),
          o1.getColumn());
      final int distanceO2 = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), o2.getRow(),
          o2.getColumn());
      return distanceO1 - distanceO2;
    });
    return resultOrderList;
  }

  /*
   * Internal methods
   */

  /**
   * Algoritmo da migliorare
   *
   * @param drone
   * @param warehouse
   * @param order
   * @return
   */
  private int calculatePathDistance(Drone drone, Warehouse warehouse, Order order) {
    // drone 2 warehouse
    int distance = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), warehouse.getRow(),
        drone.getColumn());
    // warehouse 2 order
    distance += DistanceUtils.computeDistance(warehouse.getRow(), drone.getColumn(), order.getRow(), order.getColumn());
    return distance;
  }

}
