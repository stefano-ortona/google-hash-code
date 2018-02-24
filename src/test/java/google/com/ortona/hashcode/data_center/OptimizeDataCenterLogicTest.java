package google.com.ortona.hashcode.data_center;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.data_center.logic.MinimalDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;

public class OptimizeDataCenterLogicTest {

	int numPool;
	Datacenter datacenter;
	List<Server> servers;
	
	@BeforeClass
	public static void bringUp() {
		

	}

	@Test
	public void testToyExample() {
		MinimalDatacenterAllocation.allocateServer(servers, numPool, datacenter);
		
		// todo
	}
	
}
