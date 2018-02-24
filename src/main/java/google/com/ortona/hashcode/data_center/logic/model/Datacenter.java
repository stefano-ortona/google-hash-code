package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class Datacenter {

  int[][] serverSlots;
  Map<Integer, Server> pool2servers;

  public int[][] getServerSlots() {
    return serverSlots;
  }

  public Map<Integer, Server> getPool2servers() {
    return pool2servers;
  }

  // -1 available, -2 unavailable, 0-n server number
  public Datacenter(int row, int column, List<Slot> unavailableSlots) {
    Arrays.fill(serverSlots, -1);
    pool2servers = Maps.newHashMap();
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

  }

  /**
   * Return null if it cannot be places in that row
   * @param server
   * @param row
   * @return
   */
  public Slot canPlaceServer(Server server, int row) {

    return null;

  }

  public int getRowNumber() {
    return this.serverSlots.length;
  }

}
