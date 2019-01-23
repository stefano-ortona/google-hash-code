package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.HashMap;
import java.util.Map;

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
                        // TODO: 2019-01-23
                        result.setOrder(order);
                        result.setProduct(productIntegerEntry.getKey());
                        result.setWarehouse(warehouse);
                    }
                }
            }
        }

        return result;
    }


    /*
     * Internal methods
     */

    private int calculatePathDistance(Drone drone, Warehouse warehouse, Order order) {
        return 0;
    }

}
