package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class Datacenter {

	int[][] serverSlots;


	public void setServerSlots(int[][] serverSlots) {
		this.serverSlots = serverSlots;
	}

	public void setPool2servers(Map<Integer, List<Server>> pool2servers) {
		this.pool2servers = pool2servers;
	}

	Map<Integer, List<Server>> pool2servers;

	public int[][] getServerSlots() {
		return serverSlots;
	}

	public Map<Integer, List<Server>> getPool2servers() {
		return pool2servers;
	}

	// -1 available, -2 unavailable, 0-n server number
	public Datacenter(int row, int column, List<Slot> unavailableSlots) {
		this.serverSlots = new int[row][column];
		Arrays.fill(serverSlots, -1);
		pool2servers = Maps.newHashMap();
		unavailableSlots.forEach(slot -> setUnaivableSlot(slot.getRow(), slot.getColumn()));
	}

	public Slot getNextAvailableSlot(Server server) {

		return null;

	}

	public Slot getNextAvailableSlot(Server server, int i) {

		return null;

	}

	public void addServer(Server server, Slot slot, int pool) {

	}

	public void setUnaivableSlot(int row, int column) {
		this.serverSlots[row][column] = -2;

	}

	/**
	 * Return null if it cannot be places in that row
	 * @param server
	 * @param row
	 * @return
	 */
	public Slot canPlaceServer(Server server, int row) {
		// check can place server in that row
		final int rowLenght = this.serverSlots[row].length;
		int slotStarts = 0;
		while (slotStarts < rowLenght) {
			while (this.serverSlots[row][slotStarts] != -1) {
				slotStarts++;
			}
			// now can start the slot
			if (slotStarts >= rowLenght) {
				return null;
			}
			boolean canPlace = true;
			// check can place the server
			for (int i = 0; i < server.getSize(); i++) {
				if (this.serverSlots[row][slotStarts + i] != -1) {
					canPlace = false;
					slotStarts = slotStarts + i + 1;
				}
			}
			if (canPlace) {
				return new Slot(row, slotStarts);
			}
		}
		// cannot place
		return null;
	}

	public int getRowNumber() {
		return this.serverSlots.length;
	}

	public List<Server> getPool(int poolId) {
		return pool2servers.get(poolId);
	}

}
