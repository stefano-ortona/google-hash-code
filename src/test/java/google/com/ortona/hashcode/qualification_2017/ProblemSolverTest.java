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
		c2.setId(1);
		c2.setSize(1);
		final Map<Cache, Integer> cache2latency2 = new HashMap<Cache,Integer>();
		cache2latency.put(c2, 1);
		
		final Endpoint e = new Endpoint();
		e.setId(0);
		e.setCache2latency(cache2latency);
		e.setDataCenterLatency(10);
		
		final Endpoint e2 = new Endpoint();
		e2.setId(1);
		e2.setCache2latency(cache2latency2);
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
	  Assert.assertEquals(6000, solution.getScore(), 0);
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
  public void fourthTest() {
  	
		final Cache c0 = new Cache();
		c0.setId(0);
		c0.setSize(100);
		final Cache c1 = new Cache();
		c1.setId(1);
		c1.setSize(100);
		final Cache c2 = new Cache();
		c2.setId(2);
		c2.setSize(100);
		
		final Endpoint e0 = new Endpoint();
		e0.setId(0);
		final Map<Cache, Integer> cache2latency0 = new HashMap<Cache,Integer>();
		cache2latency0.put(c0, 100);
		cache2latency0.put(c2, 200);
		cache2latency0.put(c1, 300);
		e0.setCache2latency(cache2latency0);
		e0.setDataCenterLatency(1000);
		
		final Endpoint e1 = new Endpoint();
		e1.setId(1);
		final Map<Cache, Integer> cache2latency1 = new HashMap<Cache,Integer>();
		e1.setCache2latency(cache2latency1);
		e1.setDataCenterLatency(500);
		
		final Video v0 = new Video();
		v0.setId(0);
		v0.setSize(50);
		
		final Video v1 = new Video();
		v1.setId(1);
		v1.setSize(50);
		
		final Video v2 = new Video();
		v2.setId(2);
		v2.setSize(80);
		
		final Video v3 = new Video();
		v3.setId(3);
		v3.setSize(30);
		
		final Video v4 = new Video();
		v4.setId(4);
		v4.setSize(110);
		
		final Request request0 = new Request();
		request0.setV(v3);
		request0.setId(0);
		request0.setE(e0);
		request0.setQuantity(1500);
		
		final Request request1 = new Request();
		request1.setV(v0);
		request1.setId(1);
		request1.setE(e1);
		request1.setQuantity(1000);
		
		final Request request2 = new Request();
		request2.setV(v4);
		request2.setId(2);
		request2.setE(e0);
		request2.setQuantity(500);
		
		final Request request3 = new Request();
		request3.setV(v1);
		request3.setId(3);
		request3.setE(e0);
		request3.setQuantity(1000);
		
		List<Request> requests = new ArrayList<Request>();
		requests.add(request0);
		requests.add(request1);
		requests.add(request2);
		requests.add(request3);
		
	  final ProblemContainer problem = new ProblemContainer();
	  problem.setRequest(requests);
	  final SolutionContainer solution = SOLVER.process(problem);
	  int counter = 0;
	
	  System.out.println("TEST 4 PRINT %%% ");
	  for (Cache cache: solution.getCaches()) {
	  	System.out.println(counter + " Cache: " + cache.toString());
	  }
	  Assert.assertEquals(1, solution.getCaches().size());
	  Assert.assertEquals(562500, solution.getScore(), 0);
	  for (Cache cache: solution.getCaches()) {
	    System.out.print(counter + " Cache: " + cache.toString());
	    counter++;
	    if (cache.getId() == 0) {
	    	Assert.assertEquals(cache.getVideos().size(), 2);
	    	Assert.assertTrue(cache.getVideos().contains(v1));
	    	Assert.assertTrue(cache.getVideos().contains(v3));
	    } 
	    System.out.println("----------");
	  }
	}

}
