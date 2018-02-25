package google.com.ortona.hashcode.data_center;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.data_center.logic.MaximumDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.MinimalDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.ServerComparator;
import google.com.ortona.hashcode.data_center.logic.model.Slot;

public class OptimizeDataCenterLogicTest {

	private static MaximumDatacenterAllocation obj;
	private static Server server0;
	private static Server server1;
	private static Server server2;
	private static Server server3;
	private static Server server4;
	private static Server server5;
	private static List<Server> servers;
	private static Slot slot1;
	private static Slot slot2;
	private static Slot slot3;
	private static List<Slot> unavailableSlots;
	
	@BeforeClass
	public static void bringUp() {	
		obj = new MaximumDatacenterAllocation(); 
		server0 = new Server(0, 3, 10);
		server1 = new Server(1, 3 ,10);
		server2 = new Server(2, 2, 5);
		server3 = new Server(3, 1, 5);
		server4 = new Server(4, 1, 1);
		server5 = new Server(5, 1, 1);
		slot1 = new Slot(0,0);
		slot2 = new Slot(0,1);
		slot3 = new Slot(1,0);
	}
	
	@Before
	public void eachTest() {
		servers = new ArrayList<Server>();
		unavailableSlots = new ArrayList<Slot>();
	}

	@Test
	public void testAllocateServer1() {
		System.out.println("TEST 1 ");
		servers.add(server0);
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);
		servers.add(server4);
		unavailableSlots.add(slot1);
		int numPool = 2;
		int numRows = 2;
		int numCols = 5;
		Datacenter datacenter = new Datacenter(numRows, numCols, unavailableSlots);	
		
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter,new ServerComparator()); 
		
		System.out.println("AFTER CARLO's METHOD ");
		System.out.println("pool 0 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(0).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(0).get(1));
		System.out.println("pool 1 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(1).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(1).get(1));
		
		Assert.assertEquals(servers.get(0), server4);
		Assert.assertEquals(datacenter.getPool(0).contains(server0), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server1), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server2), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server3), true);
		
		obj.allocate(servers, datacenter, numPool);
		
		Assert.assertEquals(datacenter.getPool(0).contains(server0), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server1), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server2), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server3), true);
		System.out.println("----------------");
	}
	
	@Test
	public void testAllocateServer2() {
		System.out.println("TEST 2 ");
		servers.add(server0);
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);
		servers.add(server4);
		servers.add(server5);
		unavailableSlots.add(slot1);
		int numPool = 2;
		int numRows = 3;
		int numCols = 5;
		Datacenter datacenter = new Datacenter(numRows, numCols, unavailableSlots);	
		
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter, new ServerComparator()); 
			
		Assert.assertEquals(datacenter.getPool(0).contains(server0), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server1), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server2), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server3), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server4), false);
		Assert.assertEquals(datacenter.getPool(0).contains(server5), false);
		Assert.assertEquals(datacenter.getPool(1).contains(server4), false);
		Assert.assertEquals(datacenter.getPool(1).contains(server5), false);
		
		MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation(); 
		obj.allocate(servers, datacenter, 2);
		
		System.out.println("AFTER STEFANO's METHOD ");
		
		System.out.println("pool 0 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(0).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(0).get(1));
		
		System.out.println("pool 1 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(1).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(1).get(1));
		System.out.print("third server : ");
		System.out.println(datacenter.getPool(1).get(2));
		System.out.print("fourth server : ");
		System.out.println(datacenter.getPool(1).get(3));
		System.out.println("----------------");
		
	}
	
	@Test
	public void testAllocateServer3() {
		System.out.println("TEST 3 ");
		servers.add(server0);
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);
		servers.add(server4);
		unavailableSlots.add(slot1);
		int numPool = 2;
		int numRows = 2;
		int numCols = 6;
		Datacenter datacenter = new Datacenter(numRows, numCols, unavailableSlots);	
		
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter,new ServerComparator()); 
			
		Assert.assertEquals(datacenter.getPool(0).contains(server0), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server1), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server2), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server3), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server4), false);
		
		MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation(); 
		obj.allocate(servers, datacenter, 2);
		System.out.println("AFTER STEFANO's METHOD ");
		
		System.out.println("pool 0 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(0).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(0).get(1));
		
		System.out.println("pool 1 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(1).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(1).get(1));
		System.out.println("----------------");
		
	}
	
	@Test
	public void testAllocateServer4() {
		System.out.println("TEST 4 ");
		servers.add(server0);
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);
		servers.add(server4);
		servers.add(server5);
		int numPool = 2;
		int numRows = 3;
		int numCols = 6;
		unavailableSlots.add(slot1);
		unavailableSlots.add(slot2);
		unavailableSlots.add(slot3);
		Datacenter datacenter = new Datacenter(numRows, numCols, unavailableSlots);	
		
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter,new ServerComparator()); 
			
		Assert.assertEquals(datacenter.getPool(0).contains(server0), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server1), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server2), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server3), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server4), false);
		Assert.assertEquals(datacenter.getPool(1).contains(server4), false);
		
		
		MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation(); 
		obj.allocate(servers, datacenter, 2);
		System.out.println("AFTER STEFANO's METHOD ");
		
		System.out.println("pool 0 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(0).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(0).get(1));
		
		System.out.println("pool 1 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(1).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(1).get(1));
		System.out.print("third server : ");
		System.out.println(datacenter.getPool(1).get(2));
		System.out.print("fourth server : ");
		System.out.println(datacenter.getPool(1).get(3));
		System.out.println("----------------");
		
	}
	
}
