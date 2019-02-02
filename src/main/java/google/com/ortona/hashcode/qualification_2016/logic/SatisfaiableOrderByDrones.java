package google.com.ortona.hashcode.qualification_2016.logic;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import google.com.ortona.hashcode.qualification_2016.logic.ProblemSolverByOrders.NextSatisfaiableOrder;
import google.com.ortona.hashcode.qualification_2016.model.Action;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class SatisfaiableOrderByDrones {
	
	public NextSatisfaiableOrder computeSatisfaibaleOrder(Order o, ProblemContainer problem, List<Drone> availableDrones, ProblemSolverByOrders logic){
		//order drones by distance from order
		List<Drone> orderedDrones = Lists.newArrayList(availableDrones);
		orderedDrones.sort(new SortByDistanceFromOrder(o));
		List<Action> allLoadActions = new ArrayList<>();
		List<Action> allDeliveryActions = new ArrayList<>();

		//order warehouse by distance from order
		List<Warehouse> orderedWarehouses = Lists.newArrayList(problem.getWarehouses());
		orderedWarehouses.sort(new SortByDistanceFromOrder(o));
		//iterate all drones, iterate all warehouses, and stop as soon the order is satisfied or I haven't got any more drones
		Map<Product,Integer> pickedSoFar = new HashMap<Product,Integer>();
		boolean orderSatisfied = false;
		int totTime = 0;
		for(Drone d:orderedDrones){
			if(orderSatisfied){
				break;
			}
			List<Action> curLoadActions = new ArrayList<Action>();
			List<Action> curDelActions = new ArrayList<Action>();

			int curDroneCapacity = d.getCurAvailableCapacity();
			for(Warehouse w:orderedWarehouses){
				if(curDroneCapacity<=0){
					//cannot pick more items, change drone
					break;
				}
				curDroneCapacity = pickItems(d,o,w,pickedSoFar,curDroneCapacity,curLoadActions,curDelActions);
				//check order has finished
				if(pickedSoFar.equals(o.getProducts2quantity())){
					orderSatisfied = true;
					break;
				}
				
			}
			int curRow = d.getRow();
			int culColumn = d.getColumn();
			int curDroneTime = 0;
			for(Action a:curLoadActions){
				//get warehouse
				Warehouse w = orderedWarehouses.stream().filter(war -> war.getId()==a.getId()).findFirst().get();
				curDroneTime+=DistanceUtils.computeDistance(curRow, culColumn, w.getRow(), w.getColumn())+1;
				curRow = w.getRow();
				culColumn = w.getColumn();
			}
			for(Action a:curDelActions){
				//get warehouse
				Order or = a.getOrder();
				curDroneTime+=DistanceUtils.computeDistance(curRow, culColumn,or.getRow(), or.getColumn())+1;
				curRow = or.getRow();
				culColumn = or.getColumn();
			}
			allLoadActions.addAll(curLoadActions);
			allDeliveryActions.addAll(curDelActions);
			totTime = Math.max(totTime, curDroneTime);
		}
		
		NextSatisfaiableOrder nSOrder = logic.new NextSatisfaiableOrder(problem.getWarehouses());
		allLoadActions.addAll(allDeliveryActions);
		nSOrder.a = allLoadActions;
		nSOrder.totTime = totTime;
		nSOrder.o = o;
		return nSOrder;
	}
	
	/**
	 * Return new drone capacity
	 * @param d
	 * @param o
	 * @param w
	 * @param pickedSoFar
	 * @param droneCapacity
	 * @param pickUpActions
	 * @param deliveryActions
	 * @return
	 */
	private int pickItems(Drone d, Order o, Warehouse w, Map<Product,Integer> pickedSoFar, int droneCapacity,List<Action> pickUpActions,List<Action> deliveryActions){
		//check what you can pick from warehouse
		for(Product pr:w.getProduct2quantity().keySet()){
			if(o.getProducts2quantity().containsKey(pr) && pickedSoFar.getOrDefault(pr,0)<o.getProducts2quantity().get(pr)){
				int targetQuantity = getTargetQuantity(pr, w, o.getProducts2quantity().get(pr), droneCapacity);
				//remove what already taken
				int remainToTake = o.getProducts2quantity().get(pr) - pickedSoFar.getOrDefault(pr,0);
				targetQuantity = Math.min(targetQuantity, remainToTake);
				//create the actions by sending the drone to the warehouse and by delivering the product
				if(targetQuantity>0){
					Action pickUpAc = new Action();
					pickUpAc.setType("L");
					pickUpAc.setDrone(d);
					pickUpAc.setId(w.getId());
					pickUpAc.setProductId(pr.getId());
					pickUpAc.setQuantity(targetQuantity);
					pickUpActions.add(pickUpAc);
					
					Action delAction = new Action();
					delAction.setDrone(d);
					delAction.setId(o.getId());
					delAction.setOrder(o);
					delAction.setProductId(pr.getId());
					delAction.setQuantity(targetQuantity);
					delAction.setType("D");
					deliveryActions.add(delAction);
					
					//update pick so far map
					int newQuantity = pickedSoFar.getOrDefault(pr, 0) + targetQuantity;
					pickedSoFar.put(pr, newQuantity);
					//remove from drone capacity
					droneCapacity -= pr.getWeight()*targetQuantity;
					if(droneCapacity<0){
						throw new RuntimeException("Drone capacity has fallen below zero!");
					}
				}
			}
		}
		return droneCapacity;
	}
	
	private int getTargetQuantity(Product p, Warehouse w, int orderQuantity, int droneAvailableCapacity){
		int warehouseQuantity = w.getProduct2quantity().getOrDefault(p, 0);
		int targetQ = Math.min(warehouseQuantity, orderQuantity);
		
		for(int i=targetQ;i>0;i--){
			if(p.getWeight()*i<=droneAvailableCapacity){
				return i;
			}
		}
		return 0;
	}
	
	class SortByDistanceFromOrder implements Comparator<Object>{
		Order target;
		public SortByDistanceFromOrder(Order o){
			target = o;
		}
		
		@Override
		public int compare(Object o1, Object o2) {
			int row1;
			int col1;
			int row2;
			int col2;
			if(o1 instanceof Drone){
				Drone d1 = (Drone) o1;
				Drone d2 = (Drone) o2;
				row1 = d1.getRow();
				col1 = d1.getColumn();
				row2 = d2.getRow();
				col2 = d2.getColumn();
				
			} else {
				Warehouse w1 = (Warehouse) o1;
				Warehouse w2 = (Warehouse) o2;
				row1 = w1.getRow();
				col1 = w1.getColumn();
				row2 = w2.getRow();
				col2 = w2.getColumn();
				
			}
			return DistanceUtils.computeDistance(row1, col1, target.getRow(), target.getColumn()) -
					DistanceUtils.computeDistance(row2, col2, target.getRow(), target.getColumn());
		}
	}


}
