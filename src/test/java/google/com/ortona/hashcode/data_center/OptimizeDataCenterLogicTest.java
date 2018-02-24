package google.com.ortona.hashcode.data_center;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.data_center.logic.MaximumDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.MinimalDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.Slot;

public class OptimizeDataCenterLogicTest {

	@BeforeClass
	public static void bringUp() {		
	}

	@Test
	public void testAllocateServer1() {
		System.out.println("TEST 1 ");
		Server server0 = new Server(0, 3, 10);
		Server server1 = new Server(1, 3 ,10);
		Server server2 = new Server(2, 2, 5);
		Server server3 = new Server(3, 1, 5);
		Server server4 = new Server(4, 1, 1);
		List<Server> servers = new ArrayList<Server>();
		servers.add(server0);
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);
		servers.add(server4);
		int numPool = 2;
		Slot slot = new Slot(0,0);
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(slot);
		Datacenter datacenter = new Datacenter(2, 5, slots);	
		
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter); 
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
		
		MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation(); 
		obj.allocate(servers, datacenter, 2);
		
		Assert.assertEquals(datacenter.getPool(0).contains(server0), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server1), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server2), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server3), true);
		System.out.println("----------------");
		
	}
	
	@Test
	public void testAllocateServer2() {
		System.out.println("TEST 2 ");
		Server server0 = new Server(0, 3, 10);
		Server server1 = new Server(1, 3 ,10);
		Server server2 = new Server(2, 2, 5);
		Server server3 = new Server(3, 1, 5);
		Server server4 = new Server(4, 1, 1);
		Server server5 = new Server(5, 1, 1);
		List<Server> servers = new ArrayList<Server>();
		servers.add(server0);
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);
		servers.add(server4);
		servers.add(server5);
		int numPool = 2;
		Slot slot = new Slot(0,0);
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(slot);
		Datacenter datacenter = new Datacenter(3, 5, slots);	
		
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter); 
			
		Assert.assertEquals(servers.get(0), server4);
		Assert.assertEquals(datacenter.getPool(0).contains(server0), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server1), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server2), true);
		Assert.assertEquals(datacenter.getPool(1).contains(server3), true);
		Assert.assertEquals(datacenter.getPool(0).contains(server4), false);
		Assert.assertEquals(datacenter.getPool(1).contains(server5), false);
		
		MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation(); 
		obj.allocate(servers, datacenter, 2);
		System.out.println("AFTER STEFANO's METHOD ");
		
		System.out.println("pool 0 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(0).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(0).get(1));
		System.out.print("third server : ");
		System.out.println(datacenter.getPool(0).get(2));
		
		System.out.println("pool 1 : ");
		System.out.print("first server :");
		System.out.println(datacenter.getPool(1).get(0));
		System.out.print("second server : ");
		System.out.println(datacenter.getPool(1).get(1));
		System.out.print("third server : ");
		System.out.println(datacenter.getPool(0).get(2));
		System.out.println("----------------");
		
	}
	
}
