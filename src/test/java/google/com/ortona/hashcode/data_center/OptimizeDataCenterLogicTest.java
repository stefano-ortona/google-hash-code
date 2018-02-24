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

	static int numPool;
	static Datacenter datacenter;
	static List<Server> servers;
	static Server server0 = new Server(0, 3, 10);
	static Server server1 = new Server(1, 3 ,10);
	static Server server2 = new Server(2, 2, 5);
	static Server server3 = new Server(3, 1, 5);
	static Server server4 = new Server(4, 1, 1);
	
	@BeforeClass
	public static void bringUp() {	
		servers = new ArrayList<Server>();
		servers.add(server0);
		servers.add(server1);
		servers.add(server2);
		servers.add(server3);
		servers.add(server4);
		numPool = 2;
		Slot slot = new Slot(0,0);
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(slot);
		datacenter = new Datacenter(2, 5, slots);	
	}

	@Test
	public void testAllocateServer1() {
		
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter); 
		
		Assert.assertEquals(servers.get(0), server4);
		Assert.assertEquals(datacenter.getPool(0).get(0), server0);
		Assert.assertEquals(datacenter.getPool(0).get(1), server1);
		Assert.assertEquals(datacenter.getPool(1).get(0), server2);
		Assert.assertEquals(datacenter.getPool(1).get(1), server3);
		
		MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation(); 
		obj.allocate(servers, datacenter, 2);

	}
	
	@Test 
	public void testMaximumDatacenterAllocationAlone() {
		servers = new ArrayList<Server>();
		Slot slot1 = new Slot(0,0);
		Slot slot2 = new Slot(0,1);
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(slot1);
		slots.add(slot2);
		Datacenter datacenter = new Datacenter(4, 6, slots);
		Slot slot3 = new Slot(0,2);
		datacenter.addServer(server0, slot3, 3);
	}
	
}
