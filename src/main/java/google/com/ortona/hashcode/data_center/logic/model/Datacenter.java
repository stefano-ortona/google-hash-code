package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
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
    for (int i = 0; i < row; i++) {
      Arrays.fill(serverSlots[i], -1);
    }
    pool2servers = Maps.newHashMap();
    unavailableSlots.forEach(slot -> setUnaivableSlot(slot.getRow(), slot.getColumn()));
  }

  public Slot getNextAvailableSlot(Server server) {
    for (int i = 0; i < this.serverSlots.length; i++) {
      final Slot s = canPlaceServer(server, i);
      if (s != null) {
        return s;
      }
    }
    return null;

  }

  public Slot getNextAvailableSlot(Server server, int i) {
    for (int row = 0; row < this.serverSlots.length; row++) {
      if (row != i) {
        final Slot s = canPlaceServer(server, row);
        if (s != null) {
          return s;
        }
      }
    }
    return null;
  }

  public void addServer(Server server, Slot slot, int pool) {
    final List<Server> servers = this.pool2servers.getOrDefault(pool, Lists.newArrayList());
    servers.add(server);
    this.pool2servers.put(pool, servers);
    server.setPool(pool);
    server.setInitialSlot(slot);
    for (int i = 0; i < server.getSize(); i++) {
      this.serverSlots[slot.getRow()][slot.getColumn() + i] = server.id;
    }

  }

  public List<Server> getPool(int poolId) {
    if (this.pool2servers.containsKey(poolId)) {
      return this.pool2servers.get(poolId);
    }
    return Lists.newArrayList();
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
      while ((slotStarts < rowLenght) && (this.serverSlots[row][slotStarts] != -1)) {
        slotStarts++;
      }
      // now can start the slot
      if (slotStarts >= rowLenght) {
        return null;
      }
      boolean canPlace = true;
      // check can place the server
      for (int i = 0; i < server.getSize(); i++) {
        if (((slotStarts + i) >= rowLenght) || (this.serverSlots[row][slotStarts + i] != -1)) {
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

}
