package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.*;

import google.com.ortona.hashcode.qualification_2016.model.*;

public class BestWarehouse {

    public BestWarehouseResult getBestWarehouse(Drone drone, ProblemContainer problem) {
        BestWarehouseResult result = new BestWarehouseResult();

        int bestPathDistance = Integer.MAX_VALUE;

        for (Order order : problem.getOrders()) {
            // per ogni prodotto di ogni ordine calcolo la distanza dalla warehouse e prendo la migliore

            for (Map.Entry<Product, Integer> productIntegerEntry : order.getProducts2quantity().entrySet()) {
                for (Warehouse warehouse : problem.getWarehouses()) {
                    int pathDistance = calculatePathDistance(drone, warehouse, order);
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

        return result;
    }


    public List<Order> sortOrderListByDrone(List<Order> orderList, Drone drone) {
        ArrayList<Order> resultOrderList = new ArrayList<>(orderList);
        resultOrderList.sort((o1, o2) -> {
            int distanceO1 = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), o1.getRow(), o1.getColumn());
            int distanceO2 = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), o2.getRow(), o2.getColumn());
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
        int distance = DistanceUtils.computeDistance(
                drone.getRow(), drone.getColumn()
                , warehouse.getRow(), drone.getColumn());
        // warehouse 2 order
        distance += DistanceUtils.computeDistance(
                warehouse.getRow(), drone.getColumn()
                , order.getRow(), order.getColumn());
        return distance;
    }

}
