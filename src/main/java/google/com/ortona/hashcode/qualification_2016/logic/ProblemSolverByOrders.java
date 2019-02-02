package google.com.ortona.hashcode.qualification_2016.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import google.com.ortona.hashcode.qualification_2016.model.Action;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class ProblemSolverByOrders implements GenericSolver{

	Logger LOG = LoggerFactory.getLogger(getClass());
	
	SatisfaiableOrderByDrones sOrder = new SatisfaiableOrderByDrones();

	public SolutionContainer process(ProblemContainer problem) {
		final int totTime = problem.getMaxInstant();
		List<Action> allActions = new LinkedList<>();

		int curScore = 0;

		for (int i = 0; (i < totTime) && !problem.getOrders().isEmpty(); i++) {
			List<Drone> availableDrones = getAvailableDrones(problem.getDrones(), i);
			int totDronesAssigned = availableDrones.size();
			if(!availableDrones.isEmpty()){
				LOG.info("Iteration at time: {} (out of {})", i, totTime);
				List<NextSatisfaiableOrder> satisfaiableOrders = new LinkedList<>();
				List<Order> stillOpenOrders = Lists.newArrayList(problem.getOrders());
				stillOpenOrders.forEach(o -> satisfaiableOrders.add(sOrder.computeSatisfaibaleOrder(o, problem,availableDrones,this)));
				satisfaiableOrders.sort(new CompareSatisfaibaleOrder());
				while(!availableDrones.isEmpty() && !satisfaiableOrders.isEmpty()){
					NextSatisfaiableOrder satOrder = satisfaiableOrders.remove(0);
					if(!satOrder.containsOnlyUnusedDrones(availableDrones)){
						//needs to recompute as the available drones list might have changed
						satisfaiableOrders.clear();
						stillOpenOrders.forEach(o -> satisfaiableOrders.add(sOrder.computeSatisfaibaleOrder(o, problem,availableDrones,this)));
						satisfaiableOrders.sort(new CompareSatisfaibaleOrder());
						continue;
					}
					//remove from still open orders
					stillOpenOrders.remove(satOrder.o);
					//remove drones
					availableDrones.removeAll(satOrder.getUsedDrones());
					if(satOrder.totTime>=totTime){
						LOG.info("Order cannot be satisfied in time, dropping it: '{}'",satOrder.o);
						problem.getOrders().remove(satOrder.o);
					}
					allActions.addAll(satOrder.applyOrder());
					if (satOrder.o.isOrderSatisfied()) {
						final double score = Math.ceil((((totTime - (satOrder.totTime+i-1))) / (totTime * 1.)) * 100);
						LOG.info("I satisfied order '{}' with score '{}'", satOrder.o, score);
						curScore += score;
						problem.getOrders().remove(satOrder.o);
					}
				}
				LOG.info("Iteration completed with {} drones assigned, curTotScore={}, {} orders left to complete",
						totDronesAssigned-availableDrones.size(), curScore, problem.getOrders().size());
			}
		}
		final SolutionContainer c = new SolutionContainer();
		c.setActions(allActions);
		c.score = curScore;
		LOG.info("Computation ended with total score: '{}'", curScore);
		return c;
	}

	private List<Drone> getAvailableDrones(List<Drone> drones, final int curInstant){
		return drones.stream().filter(d->d.getNextTimeAvailable()<=curInstant).collect(Collectors.toList());  
	}

	public class NextSatisfaiableOrder{
		Order o;
		List<Warehouse> w;
		//first action are drones loads from warehouses, then delivers to the order
		List<Action> a;
		int totTime = 0;
		
		public NextSatisfaiableOrder(List<Warehouse> allWarehouses){
			this.w = allWarehouses;
		}

		public List<Action> applyOrder(){
			a.forEach(ac -> {
				Drone curDrone = ac.getDrone();
				Product p = o.getProducts2quantity().keySet().stream().filter(pr->pr.getId()==ac.getProductId()).findFirst().get();
				if(ac.getType().equals("D")){
					//deliver to product
					int dis = DistanceUtils.computeDistance(curDrone.getRow(), curDrone.getColumn(), o.getRow(), o.getColumn());
					o.deliverProduct(p, ac.getQuantity());
					curDrone.setNextTimeAvailable(curDrone.getNextTimeAvailable()+dis+1);
					curDrone.setRow(o.getRow());
					curDrone.setColumn(o.getColumn());
					curDrone.setCurAvailableCapacity(curDrone.getCurAvailableCapacity()+p.getWeight()*ac.getQuantity());
				} else {
					//load from warehouse
					Warehouse targetW = w.stream().filter(war->war.getId()==ac.getId()).findFirst().get();
					targetW.releaseProduct(p, ac.getQuantity());
					curDrone.loadProduct(p, ac.getQuantity());
					int dis = DistanceUtils.computeDistance(curDrone.getRow(), curDrone.getColumn(), targetW.getRow(), targetW.getColumn());
					curDrone.setNextTimeAvailable(curDrone.getNextTimeAvailable()+dis+1);
					curDrone.setRow(targetW.getRow());
					curDrone.setColumn(targetW.getColumn());
				}
			});
			return a;
		}
		
		public List<Drone> getUsedDrones(){
			List<Drone> usedDrones = new ArrayList<>();
			a.forEach(a -> usedDrones.add(a.getDrone()));
			return usedDrones;
		}

		public boolean containsOnlyUnusedDrones(List<Drone> drones){
			//remove all actions related to those drones
			return drones.containsAll(this.getUsedDrones());
		}

		public boolean canBeSatisfied(){
			//check all actions contain exactly all the products the order needs
			Map<Product,Integer> newProdQuantity = Maps.newHashMap(o.getProducts2quantity());
			a.stream().filter(ac -> ac.getType().equals("D")).forEach(ac -> {
				Product acProd = newProdQuantity.keySet().stream().filter(p -> p.getId()==ac.getProductId()).findFirst().get();
				int newQuantValue = newProdQuantity.get(acProd)-ac.getQuantity();
				if(newQuantValue<0){
					throw new RuntimeException("Delivering more than needed for a product!");
				}
				if(newQuantValue == 0){
					newProdQuantity.remove(acProd);
				} else {
					newProdQuantity.put(acProd, newQuantValue);
				}
			});
			return newProdQuantity.isEmpty();
		}
	}

	class CompareSatisfaibaleOrder implements Comparator<NextSatisfaiableOrder>{

		@Override
		public int compare(NextSatisfaiableOrder o1, NextSatisfaiableOrder o2) {
			if(o1.canBeSatisfied() && !o2.canBeSatisfied()){
				return -1;
			}
			if(!o1.canBeSatisfied() && o2.canBeSatisfied()){
				return 1;
			}
			//if they both cannot be satisfied, or if they both can be satisfied, return the min amount of time
			return o1.totTime - o2.totTime;
		}

	}


}
