package google.com.ortona.hashcode.data_center;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Maps;

import google.com.ortona.hashcode.data_center.logic.MaximumDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.MinimalDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.Slot;

import org.junit.Assert;

public class OptimizeDataCenterLogicTest {

	static int numPool;
	static Datacenter datacenter;
	static List<Server> servers;
	static Server server0 = new Server(3, 10, 0);
	static Server server1 = new Server(3 ,10, 1);
	static Server server2 = new Server(5, 2, 2);
	static Server server3 = new Server(5, 1, 3);
	static Server server4 = new Server(1, 1, 4);
	
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
		
		Server server4 = servers.get(4);
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter); 
		
		// expect datacenter has server0 and server 1 allocated
		Assert.assertEquals(servers.get(0), server4);
		Assert.assertEquals(datacenter.getPool(0).get(0), server0);
		Assert.assertEquals(datacenter.getPool(0).get(1), server1);
		Assert.assertEquals(datacenter.getPool(1).get(0), server2);
		Assert.assertEquals(datacenter.getPool(1).get(1), server3);
		
	}
	
}
