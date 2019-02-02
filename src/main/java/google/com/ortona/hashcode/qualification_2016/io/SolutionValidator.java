package google.com.ortona.hashcode.qualification_2016.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification_2016.logic.DistanceUtils;
import google.com.ortona.hashcode.qualification_2016.model.Action;
import google.com.ortona.hashcode.qualification_2016.model.Drone;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.Warehouse;

public class SolutionValidator {
  
  Logger LOG = LoggerFactory.getLogger(getClass());
  
  public int validate(List<Action> action, ProblemContainer problem){
    int totScore = 0;
    for(Drone drone:problem.getDrones()){
      totScore += computeDroneAction(drone,action.stream().filter(a -> a.getDrone().getId()==drone.getId()).collect(Collectors.toList()),problem);
    }
    return totScore;
  }
  
  private int computeDroneAction(Drone d,List<Action> droneAc, ProblemContainer problem){
    Map<Product,Integer> curLaodDrone = new HashMap<Product,Integer>();
    int curScore = 0;
    int curTime = 0;
    for(Action oneAc:droneAc){
      if(curTime>=problem.getMaxInstant()){
        throw new RuntimeException("Time for drone went out of max time!");
      }
      if(oneAc.getType().equals("L")){
        Warehouse w = getWarehouseFromId(problem, oneAc.getId());
        Product p = getProductFromWarehosueAndId(w, oneAc.getProductId());
        int quantity = oneAc.getQuantity();
        w.releaseProduct(p, quantity);
        d.loadProduct(p, quantity);
        curLaodDrone.put(p, curLaodDrone.getOrDefault(p, 0)+quantity);
        curTime+=DistanceUtils.computeDistance(d.getRow(), d.getColumn(), w.getRow(), w.getColumn())+1;
        d.setRow(w.getRow());
        d.setColumn(w.getColumn());
      } else {
        Order o = getOrderFromId(problem, oneAc.getId());
        Product p = getProductFromProductAndId(o,oneAc.getProductId());
        int quantity = oneAc.getQuantity();
        Integer avQuantity = curLaodDrone.get(p);
        if(avQuantity==null || avQuantity<quantity){
          throw new RuntimeException("Trying to deliver more quantity than available in drone!");
        }
        o.deliverProduct(p, quantity);
        curLaodDrone.put(p, avQuantity-quantity);
        d.setCurAvailableCapacity(d.getCurAvailableCapacity()+p.getWeight()*quantity);
        curTime+=DistanceUtils.computeDistance(d.getRow(), d.getColumn(), o.getRow(), o.getColumn())+1;
        d.setRow(o.getRow());
        d.setColumn(o.getColumn());
        if(o.isOrderSatisfied()){
          double score = Math.ceil((problem.getMaxInstant()-(curTime-1))/(problem.getMaxInstant()*1.)*100);
          //LOG.info("I satisfied order '{}' with score '{}'", o, score);
          curScore+=score;
        }
      }
    }
    return curScore;
  }
  
  private Product getProductFromWarehosueAndId(Warehouse w, int prodId){
    return w.getProduct2quantity().keySet().stream().filter(p->p.getId()==prodId).findFirst().get();
    
  }
  
  private Product getProductFromProductAndId(Order o, int prodId){
    return o.getProducts2quantity().keySet().stream().filter(p->p.getId()==prodId).findFirst().get();
    
  }
  
  private Warehouse getWarehouseFromId(ProblemContainer problem,int warId){
    return problem.getWarehouses().stream().filter(w -> w.getId()==warId).findFirst().get();
  }
  
  private Order getOrderFromId(ProblemContainer problem,int ordId){
    return problem.getOrders().stream().filter(o -> o.getId()==ordId).findFirst().get();
  }
  

}
