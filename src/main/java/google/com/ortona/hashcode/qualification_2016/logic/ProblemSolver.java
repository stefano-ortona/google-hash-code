package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import google.com.ortona.hashcode.qualification_2016.model.Action;
import google.com.ortona.hashcode.qualification_2016.model.BestWarehouseResult;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

/**
 *
 * @author stefano
 *
 */
public class ProblemSolver {

  Logger LOG = LoggerFactory.getLogger(getClass());

  final int maxTravellingDistance = Integer.MAX_VALUE;

  public SolutionContainer process(ProblemContainer problem) {

    int curScore = 0;

    final BestWarehouse warehousePicker = new BestWarehouse();

    final int totTime = problem.getMaxInstant();

    final List<Action> allActions = new ArrayList<>();

    for (int i = 0; (i < totTime) && !problem.getOrders().isEmpty(); i++) {
      LOG.info("Iteration at time: {} (out of {})", i, totTime);
      int droneAvailable = 0;
      final AtomicInteger minWeight = new AtomicInteger(Integer.MAX_VALUE);
      problem.getOrders().forEach(o -> o.getProducts2quantity().keySet().forEach(p -> {
        minWeight.set(Math.min(p.getWeight(), minWeight.get()));
      }));
      int nextAvTime = Integer.MAX_VALUE;
      for (final Drone d : problem.getDrones()) {
        if (d.getNextTimeAvailable() <= i) {
          droneAvailable++;
          // reset cur available capacity as the drone is free
          d.setCurAvailableCapacity(d.getCapacity());
          final BestWarehouseResult res = warehousePicker.getBestWarehouse(d, problem);
          // check reaching best warehouse is not too far away
          final Map<Integer, Integer> loadProducts = new HashMap<>();
          final List<Action> deliveringActions = new ArrayList<>();
          final boolean orderCompleted = processDrone(d, res.getWarehouse(), res.getOrder(), res.getProduct(),
              loadProducts, deliveringActions);
          if (orderCompleted) {
            problem.getOrders().remove(res.getOrder());
          }
          final List<Order> proximityOrders = warehousePicker.sortOrderListByDrone(problem.getOrders(), d);
          final List<Order> completedOrdered = new ArrayList<>();
          for (final Order oneOrder : proximityOrders) {
            // check order is not too far away from warehouse
            if ((DistanceUtils.computeDistance(res.getWarehouse().getRow(), res.getWarehouse().getColumn(),
                oneOrder.getRow(), oneOrder.getColumn()) > maxTravellingDistance)
                || (d.getAvailableCapacity() < minWeight.get())) {
              // stop here as all other orders are too far away, let other drones deal with it
              break;
            }
            final List<Product> allCurProducts = Lists.newArrayList(oneOrder.getProducts2quantity().keySet());
            for (final Product p : allCurProducts) {
              final boolean isOrderCompleted = processDrone(d, res.getWarehouse(), oneOrder, p, loadProducts,
                  deliveringActions);
              if (isOrderCompleted) {
                completedOrdered.add(oneOrder);
              }
            }
          }
          problem.getOrders().removeAll(completedOrdered);
          // create actions for loading
          int curTotTime = d.getNextTimeAvailable() + DistanceUtils.computeDistance(d.getRow(), d.getColumn(),
              res.getWarehouse().getRow(), res.getWarehouse().getColumn());
          curTotTime += loadProducts.size();
          if (curTotTime >= totTime) {
            // don't do anything with the current drone
            continue;
          }
          int curRow = res.getWarehouse().getRow();
          int curColumn = res.getWarehouse().getColumn();
          for (final Integer product : loadProducts.keySet()) {
            final Action a = new Action();
            a.setDrone(d);
            a.setId(res.getWarehouse().getId());
            a.setProductId(product);
            a.setQuantity(loadProducts.get(product));
            a.setType("L");
            allActions.add(a);
          }
          for (final Action a : deliveringActions) {
            final int nextOrderDistance = DistanceUtils.computeDistance(curRow, curColumn, a.getOrder().getRow(),
                a.getOrder().getColumn());
            if ((curTotTime + nextOrderDistance + 1) >= totTime) {
              // cannot satisfy anymore order, stop it here
              break;
            }
            allActions.add(a);
            curTotTime += DistanceUtils.computeDistance(curRow, curColumn, a.getOrder().getRow(),
                a.getOrder().getColumn());
            curRow = a.getOrder().getRow();
            curColumn = a.getOrder().getColumn();
            curTotTime++;
            // move the drone to the order
            d.setRow(a.getOrder().getRow());
            d.setColumn(a.getOrder().getColumn());
            if (a.getOrder().isOrderSatisfied()) {
              final int score = (int) Math.ceil((((totTime - curTotTime) + 1) / (totTime * 1.)) * 100);
              LOG.info("I satisfied order '{}' with score '{}'", a.getOrder(), score);
              curScore += score;
            }

          }
          d.setNextTimeAvailable(curTotTime);
          nextAvTime = Math.min(nextAvTime, curTotTime);
          // if orders are finished, break it here
          if (problem.getOrders().isEmpty()) {
            break;
          }
        }
      }
      LOG.info("Iteration completed with {} drones assigned, curTotScore={}, {} orders left to complete",
          droneAvailable, curScore, problem.getOrders().size());
      i = nextAvTime;
    }
    final SolutionContainer c = new SolutionContainer();
    c.setActions(allActions);
    c.score = curScore;
    LOG.info("Computation ended with total score: '{}'", curScore);
    return c;
  }

  /**
   * Return true if order is satisfied
   *
   * @param d
   * @param w
   * @param o
   * @param toPick
   * @param product2quantity
   * @param productActions
   * @return
   */
  private boolean processDrone(Drone d, Warehouse w, Order o, Product toPick,
      final Map<Integer, Integer> product2quantity, List<Action> productActions) {
    final int targetQuantity = getTargetQuantity(d.getAvailableCapacity(), toPick, w, o);
    if (targetQuantity > 0) {
      // remove it from warehouse
      w.releaseProduct(toPick, targetQuantity);
      // load it to the drone
      d.loadProduct(toPick, targetQuantity);
      // remove it from order
      o.deliverProduct(toPick, targetQuantity);
      final Action c = new Action();
      c.setDrone(d);
      c.setId(o.getId());
      c.setProductId(toPick.getId());
      c.setQuantity(targetQuantity);
      c.setType("D");
      c.setOrder(o);
      product2quantity.put(toPick.getId(), product2quantity.getOrDefault(toPick.getId(), 0) + targetQuantity);
      productActions.add(c);
    }
    return o.isOrderSatisfied();
  }

  private int getTargetQuantity(int curCapacity, Product toPick, Warehouse w, Order o) {
    int productQuantity = 0;
    for (final Product p : o.getProducts2quantity().keySet()) {
      if (p.getId() == toPick.getId()) {
        productQuantity = o.getProducts2quantity().get(p);
      }
    }
    int warehouseQuantity = 0;
    for (final Product p : w.getProduct2quantity().keySet()) {
      if (p.getId() == toPick.getId()) {
        warehouseQuantity = o.getProducts2quantity().get(p);
      }
    }
    final int targetQuantity = Math.min(productQuantity, warehouseQuantity);
    if (curCapacity >= (targetQuantity * toPick.getWeight())) {
      return targetQuantity;
    }
    for (int i = targetQuantity - 1; i > 0; i--) {
      if (curCapacity >= (i * toPick.getWeight())) {
        return i;
      }
    }
    return 0;
  }

}
