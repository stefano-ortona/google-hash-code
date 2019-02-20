package google.com.ortona.hashcode.final_2014;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import google.com.ortona.hashcode.final_2014.logic.ProblemSolver;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;
import google.com.ortona.hashcode.final_2014.model.SolutionContainer;
import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;

public class ProblemSolverTest {

  private final static ProblemSolver SOLVER = new ProblemSolver();

  @Test
  public void firstTest() {
  	Street street0 = new Street();
  	street0.setLength(100);
  	street0.setTimeCost(10);
  	street0.setBidirectional(true);
  	
  	Junction junction0 = new Junction();
  	street0.setStart(junction0);
  	List<Street> outgoingStreets = new ArrayList<Street>();
  	outgoingStreets.add(street0);
  	junction0.setOutgoingStreets(outgoingStreets);
  	junction0.setId(0);
  	junction0.setLat(10);
  	junction0.setLng(10);
  	Junction junction1 = new Junction();
  	street0.setEnd(junction1);
  	junction1.setOutgoingStreets(outgoingStreets);
  	junction1.setId(1);
  	junction1.setLat(20);
  	junction1.setLng(20);
  	
  	Car car0 = new Car();
  	car0.setId(0);
  	car0.setCurrent(junction0);
  	
  	List<Car> allCars = new ArrayList<Car>();
  	allCars.add(car0);
  	
  	List<Junction> allJunctions = new ArrayList<Junction>();
  	allJunctions.add(junction0);
  	allJunctions.add(junction1);
  	
    final ProblemContainer problem = new ProblemContainer();
    problem.setTotTime(0);
    problem.setAllCars(allCars);
    problem.setAllJunctions(allJunctions);
    SolutionContainer solution = SOLVER.process(problem);
    
    Assert.assertEquals(1, solution.getAllCars().size());
    int counter = 0;
    for (Car car: solution.getAllCars()) {
      System.out.print(counter + " Car: " + car.toString());
      counter++;
      Assert.assertEquals(0, car.getStreetsVisited().size());
    }
  }
  
  @Test
  public void secondTest() {
  	Street street0 = new Street();
  	street0.setLength(100);
  	street0.setTimeCost(10);
  	street0.setBidirectional(true);
  	
  	Junction junction0 = new Junction();
  	street0.setStart(junction0);
  	List<Street> outgoingStreets = new ArrayList<Street>();
  	outgoingStreets.add(street0);
  	junction0.setOutgoingStreets(outgoingStreets);
  	junction0.setId(0);
  	junction0.setLat(10);
  	junction0.setLng(10);
  	Junction junction1 = new Junction();
  	street0.setEnd(junction1);
  	junction1.setOutgoingStreets(outgoingStreets);
  	junction1.setId(1);
  	junction1.setLat(20);
  	junction1.setLng(20);
  	
  	Car car0 = new Car();
  	car0.setId(0);
  	car0.setCurrent(junction0);
  	
  	List<Car> allCars = new ArrayList<Car>();
  	allCars.add(car0);
  	
  	List<Junction> allJunctions = new ArrayList<Junction>();
  	allJunctions.add(junction0);
  	allJunctions.add(junction1);
  	
    final ProblemContainer problem = new ProblemContainer();
    problem.setTotTime(10);
    problem.setAllCars(allCars);
    problem.setAllJunctions(allJunctions);
    SolutionContainer solution = SOLVER.process(problem);
    
    Assert.assertEquals(1, solution.getAllCars().size());
    int counter = 0;
    for (Car car: solution.getAllCars()) {
      System.out.print(counter + " Car: " + car.toString());
      counter++;
      Assert.assertEquals(1, car.getStreetsVisited().size());
      Iterator<Street> iter = car.getStreetsVisited().iterator();
      Street first = iter.next();
      Assert.assertEquals(street0, first);
    }
  }
  
  @Test
  public void thirdTest() {
  	Street street0 = new Street();
  	street0.setLength(100);
  	street0.setTimeCost(10);
  	street0.setBidirectional(true);
  	Street street1 = new Street();
  	street1.setLength(10);
  	street1.setTimeCost(10);
  	
  	Junction junction0 = new Junction();
  	street0.setStart(junction0);
  	List<Street> outgoingStreets0 = new ArrayList<Street>();
  	outgoingStreets0.add(street0);
  	junction0.setOutgoingStreets(outgoingStreets0);
  	junction0.setId(0);
  	junction0.setLat(10);
  	junction0.setLng(10);
  	
  	Junction junction1 = new Junction();
  	street0.setEnd(junction1);
  	street1.setStart(junction1);
  	List<Street> outgoingStreets1 = new ArrayList<Street>();
  	outgoingStreets1.add(street0);
  	outgoingStreets1.add(street1);
  	junction1.setOutgoingStreets(outgoingStreets1);
  	junction1.setId(1);
  	junction1.setLat(20);
  	junction1.setLng(20);
  	
  	Junction junction2 = new Junction();
  	street1.setEnd(junction2);
  	junction2.setId(2);
  	junction2.setLat(30);
  	junction2.setLng(30);
  	
  	Car car0 = new Car();
  	car0.setId(0);
  	car0.setCurrent(junction0);
  	
  	List<Car> allCars = new ArrayList<Car>();
  	allCars.add(car0);
  	
  	List<Junction> allJunctions = new ArrayList<Junction>();
  	allJunctions.add(junction0);
  	allJunctions.add(junction1);
  	allJunctions.add(junction2);
  	
    final ProblemContainer problem = new ProblemContainer();
    problem.setTotTime(50);
    problem.setAllCars(allCars);
    problem.setAllJunctions(allJunctions);
    SolutionContainer solution = SOLVER.process(problem);
    
    Assert.assertEquals(1, solution.getAllCars().size());
    int counter = 0;
    for (Car car: solution.getAllCars()) {
      System.out.print(counter + " Car: " + car.toString());
      counter++;
      Assert.assertEquals(2, car.getStreetsVisited().size());
      Iterator<Street> iter = car.getStreetsVisited().iterator();
      Street first = iter.next();
      Assert.assertEquals(street0, first);
      Street second = iter.next();
      Assert.assertEquals(street1, second);
    }
  }
  
  @Test
  public void fourthTest() {
  	Street street0 = new Street();
  	street0.setLength(100);
  	street0.setTimeCost(10);
  	street0.setBidirectional(true);
  	Street street1 = new Street();
  	street1.setLength(10);
  	street1.setTimeCost(10);
  	Street street2 = new Street();
  	street2.setLength(20);
  	street2.setTimeCost(5);
  	
  	Junction junction0 = new Junction();
  	street0.setStart(junction0);
  	street2.setStart(junction0);
  	List<Street> outgoingStreets0 = new ArrayList<Street>();
  	outgoingStreets0.add(street0);
  	outgoingStreets0.add(street2);
  	junction0.setOutgoingStreets(outgoingStreets0);
  	junction0.setId(0);
  	junction0.setLat(10);
  	junction0.setLng(10);
  	
  	Junction junction1 = new Junction();
  	street0.setEnd(junction1);
  	street1.setStart(junction1);
  	List<Street> outgoingStreets1 = new ArrayList<Street>();
  	outgoingStreets1.add(street0);
  	outgoingStreets1.add(street1);
  	junction1.setOutgoingStreets(outgoingStreets1);
  	junction1.setId(1);
  	junction1.setLat(20);
  	junction1.setLng(20);
  	
  	Junction junction2 = new Junction();
  	street1.setEnd(junction2);
  	junction2.setId(2);
  	junction2.setLat(30);
  	junction2.setLng(30);
  	
  	Junction junction3 = new Junction();
  	street2.setEnd(junction3);
  	junction3.setId(3);
  	junction3.setLat(1);
  	junction3.setLng(1);
  	
  	Car car0 = new Car();
  	car0.setId(0);
  	car0.setCurrent(junction0);
  	Car car1 = new Car();
  	car1.setId(1);
  	car1.setCurrent(junction0);
  	
  	List<Car> allCars = new ArrayList<Car>();
  	allCars.add(car0);
  	allCars.add(car1);
  	
  	List<Junction> allJunctions = new ArrayList<Junction>();
  	allJunctions.add(junction0);
  	allJunctions.add(junction1);
  	allJunctions.add(junction2);
  	allJunctions.add(junction3);
  	
    final ProblemContainer problem = new ProblemContainer();
    problem.setTotTime(50);
    problem.setAllCars(allCars);
    problem.setAllJunctions(allJunctions);
    SolutionContainer solution = SOLVER.process(problem);
    
    Assert.assertEquals(1, solution.getAllCars().size());
    int counter = 0;
    for (Car car: solution.getAllCars()) {
      System.out.print(counter + " Car: " + car.toString());
      counter++;
      if(car.getId() == 0) {
      	Assert.assertEquals(1, car.getStreetsVisited().size());
      	Iterator<Street> iter = car.getStreetsVisited().iterator();
        Street first = iter.next();
        Assert.assertEquals(street2, first);
      } else if (car.getId() == 1 ){
      	Assert.assertEquals(2, car.getStreetsVisited().size());
        Iterator<Street> iter = car.getStreetsVisited().iterator();
        Street first = iter.next();
        Assert.assertEquals(street0, first);
        Street second = iter.next();
        Assert.assertEquals(street1, second);
      }
      
    }
  }

}
