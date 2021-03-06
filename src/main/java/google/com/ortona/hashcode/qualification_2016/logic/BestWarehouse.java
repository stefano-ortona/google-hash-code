package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import google.com.ortona.hashcode.qualification_2016.model.BestWarehouseResult;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class BestWarehouse {

	public BestWarehouseResult getBestWarehouse(Drone drone, ProblemContainer problem) {
		final BestWarehouseResult result = new BestWarehouseResult();

		// order orders by distance from drone
		final List<Order> sortedOrder = sortOrderListByDrone(problem.getOrders(), drone);
		// order warehouses by distance from drone
		final List<Warehouse> sortedWarehouses = sortWrehousesListByDrone(problem.getWarehouses(), drone);

		int bestPathDistance = Integer.MAX_VALUE;
		
		boolean foundAPotentialComplete = false;

		for (final Order order : sortedOrder) {
			// if the order if further away of the best distance, you can stop
			final int distanceFromOrder = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), order.getRow(),
					order.getColumn());
			if (distanceFromOrder >= bestPathDistance) {
				break;
			}
			// per ogni prodotto di ogni ordine calcolo la distanza dalla warehouse e prendo la migliore
			for (final Warehouse warehouse : sortedWarehouses) {
				// if distance from that warehouse is bigger than the best distance, you can stop
				if ((distanceFromOrder + DistanceUtils.computeDistance(order.getRow(), order.getColumn(), warehouse.getRow(),
						warehouse.getColumn())) >= bestPathDistance) {
					break;
				}
				boolean canComplete = canCompleteOrder(warehouse, order);

				for (final Map.Entry<Product, Integer> productIntegerEntry : order.getProducts2quantity().entrySet()) {
					// check warehouse contains the product
					if (containsProduct(warehouse, productIntegerEntry.getKey())) {
						final int pathDistance = calculatePathDistance(drone, warehouse, order);
						if (pathDistance < bestPathDistance || (!foundAPotentialComplete && canComplete)) {
							bestPathDistance = pathDistance;
							// update result
							result.setOrder(order);
							result.setProduct(productIntegerEntry.getKey());
							result.setWarehouse(warehouse);
						}
						break;
					}
				}
				foundAPotentialComplete |= canComplete;
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

	public List<Order> sortOrderByDroneDistanceAndCompleteness(List<Order> orderList, Drone drone, Warehouse w) {
		final ArrayList<Order> resultOrderList = new ArrayList<>(orderList);
		resultOrderList.sort((o1, o2) -> {
			boolean completeO1 = canCompleteOrder(w,o1);
			boolean completeO2 = canCompleteOrder(w,o2);
			
			if(completeO1 && !completeO2){
				return -1;
			}
			if(!completeO1 && completeO2){
				return 1;
			}
			//if both can be completed, return the ones with less elements
			if(completeO1 && completeO2){
				if(o1.getProducts2quantity().size()!=o2.getProducts2quantity().size()){
					return o1.getProducts2quantity().size()-o2.getProducts2quantity().size();
				}
			}
			//if they can be both completed,or none of them can be completed, return distance
			final int distanceO1 = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), o1.getRow(),
					o1.getColumn());
			final int distanceO2 = DistanceUtils.computeDistance(drone.getRow(), drone.getColumn(), o2.getRow(),
					o2.getColumn());
			return distanceO1 - distanceO2;
		});
		return resultOrderList;
	}
	
	private boolean canCompleteOrder(Warehouse w, Order o){
		for(Entry<Product,Integer> entry:o.getProducts2quantity().entrySet()){
			if(!w.getProduct2quantity().containsKey(entry.getKey()) || w.getProduct2quantity().get(entry.getKey())<entry.getValue()){
				return false;
			}
		}
		return true;
	}

	public List<Warehouse> sortWrehousesListByDrone(List<Warehouse> orderList, Drone drone) {
		final ArrayList<Warehouse> resultOrderList = new ArrayList<>(orderList);
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
