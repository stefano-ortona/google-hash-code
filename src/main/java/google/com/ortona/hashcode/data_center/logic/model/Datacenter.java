package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.Maps;

public class Datacenter {

  int[][] serverSlots;
  Map<Integer, Server> pool2servers;

  // -1 available, -2 unavailable, 0-n server number
  public Datacenter() {
    Arrays.fill(serverSlots, -1);
    pool2servers = Maps.newHashMap();
  }

  public Slot getNextAvailableSlot(Server server) {

    return null;

  }

  public Slot getNextAvailableSlot(Server server, int i) {

    return null;

  }

  public void addServer(Server server, Slot slot) {

  }

  public void setUnaivableSlot(int row, int column) {

  }

  public boolean canPlaceServer(Server server, int row) {

    return false;

  }

}
