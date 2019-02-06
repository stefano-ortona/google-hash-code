package google.com.ortona.hashcode.qualification_2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import google.com.ortona.hashcode.qualification_2017.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.Endpoint;
import google.com.ortona.hashcode.qualification_2017.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2017.model.Request;
import google.com.ortona.hashcode.qualification_2017.model.SolutionContainer;
import google.com.ortona.hashcode.qualification_2017.model.Video;

public class ProblemSolverTest {
	
	private final static ProblemSolver SOLVER = new ProblemSolver();

  @Test
  public void firstTest() {
  	
  	final Cache c = new Cache();
  	c.setId(0);
  	c.setSize(1);
  	final Map<Cache, Integer> cache2latency = new HashMap<Cache,Integer>();
  	cache2latency.put(c, 1);
  	
  	final Endpoint e = new Endpoint();
  	e.setId(0);
		e.setCache2latency(cache2latency);
		e.setDataCenterLatency(10);
  	
  	final Video v = new Video();
  	v.setId(0);
  	v.setSize(1);
  	
  	final Request request = new Request();
  	request.setV(v);
  	request.setId(0);
  	request.setE(e);
  	request.setQuantity(1);
  	
  	List<Request> requests = new ArrayList<Request>();
  	requests.add(request);
 
    final ProblemContainer problem = new ProblemContainer();
    problem.setRequest(requests);
    final SolutionContainer solution = SOLVER.process(problem);
    int counter = 0;

    System.out.println("TEST 1 PRINT %%% ");
    Assert.assertEquals(1, solution.getCaches().size());
    for (Cache cache: solution.getCaches()) {
      System.out.print(counter + " Cache: " + cache.toString());
      counter++;
      Assert.assertEquals(0, cache.getId());
      Assert.assertEquals(1, cache.getVideos().size());
      Iterator<Video> iter = cache.getVideos().iterator();
      Video first = iter.next();
      Assert.assertEquals(0, first.getId());
      System.out.println("----------");
    }
  }
 
  @Test
  public void secondTest() {
  	
  	final Cache c = new Cache();
  	c.setId(0);
  	c.setSize(1);
  	final Map<Cache, Integer> cache2latency = new HashMap<Cache,Integer>();
  	cache2latency.put(c, 2);
  	
  	final Endpoint e = new Endpoint();
  	e.setId(0);
		e.setCache2latency(cache2latency);
		e.setDataCenterLatency(1);
  	
  	final Video v = new Video();
  	v.setId(0);
  	v.setSize(1);
  	
  	final Request request = new Request();
  	request.setV(v);
  	request.setId(0);
  	request.setE(e);
  	request.setQuantity(1);
  	
  	List<Request> requests = new ArrayList<Request>();
  	requests.add(request);
 
    final ProblemContainer problem = new ProblemContainer();
    problem.setRequest(requests);
    final SolutionContainer solution = SOLVER.process(problem);
    
    System.out.println("TEST 2 PRINT %%% ");
    Assert.assertEquals(0, solution.getCaches().size());
  }
  
  @Test
	public void thirdTest() {
	  	
		final Cache c = new Cache();
		c.setId(0);
		c.setSize(2);
		final Map<Cache, Integer> cache2latency = new HashMap<Cache,Integer>();
		cache2latency.put(c, 1);
		
		final Cache c2 = new Cache();
		c.setId(1);
		c.setSize(1);
		final Map<Cache, Integer> cache2latency2 = new HashMap<Cache,Integer>();
		cache2latency.put(c2, 1);
		
		final Endpoint e = new Endpoint();
		e.setId(0);
		e.setCache2latency(cache2latency);
		e.setDataCenterLatency(10);
		
		final Endpoint e2 = new Endpoint();
		e2.setId(1);
		e.setCache2latency(cache2latency2);
		e2.setDataCenterLatency(10);
		
		final Video v = new Video();
		v.setId(0);
		v.setSize(2);
		
		final Request request = new Request();
		request.setV(v);
		request.setId(0);
		request.setE(e);
		request.setQuantity(2);
		
		final Request request2 = new Request();
		request2.setV(v);
		request2.setId(1);
		request2.setE(e2);
		request2.setQuantity(1);
		
		List<Request> requests = new ArrayList<Request>();
		requests.add(request);
		requests.add(request2);
	
	  final ProblemContainer problem = new ProblemContainer();
	  problem.setRequest(requests);
	  final SolutionContainer solution = SOLVER.process(problem);
	  int counter = 0;
	
	  System.out.println("TEST 3 PRINT %%% ");
	  Assert.assertEquals(1, solution.getCaches().size());
	  Assert.assertEquals(18, solution.getScore(), 0);
	  for (Cache cache: solution.getCaches()) {
	    System.out.print(counter + " Cache: " + cache.toString());
	    counter++;
	    Assert.assertEquals(0, cache.getId());
	    Assert.assertEquals(1, cache.getVideos().size());
	    Iterator<Video> iter = cache.getVideos().iterator();
	    Video first = iter.next();
	    Assert.assertEquals(0, first.getId());
	    System.out.println("----------");
	  }
	}

}
