package google.com.ortona.hashcode.data_center.logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import google.com.ortona.hashcode.App;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.ServerComparator;
import google.com.ortona.hashcode.data_center.logic.model.Slot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinimalDatacenterAllocation {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void allocateServer(List<Server> servers, int numPool, Datacenter center, Comparator<Server> comparator) {
		Collections.sort(servers, comparator);

		for (int currentPool= 0; currentPool< numPool; currentPool++) {
			Slot slot =  null;
			for (int j=0; j< 2; j++) { // 2 server
				while (!servers.isEmpty()) { // loop to discard servers too big to existing rows
					Server server = popServer(servers);
					if (slot == null) {
						slot = center.getNextAvailableSlot(server);
					} else {
						slot = center.getNextAvailableSlot(server, slot.getRow());
					}
					if (slot == null) { // if there isn't a proper slot for current server go on (current server is discarded)
						LOGGER.info("ERROR: NO SLOT RETRIEVED, Server id:"+server.getId());
					} else {
						center.addServer(server, slot, currentPool);
						break; // exit loop: there is a proper slot for this server
					}
				}
			}
		}
	}

	private static Server popServer(List<Server> servers) {
		return servers.remove(0);
	}

}
