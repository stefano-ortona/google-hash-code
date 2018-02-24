package google.com.ortona.hashcode.data_center.logic;

import java.util.Collections;
import java.util.List;

import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.ServerComparator;

public class MinimalDatacenterAllocation {

  public void allocateServer(List<Server> servers, int numPool, Datacenter center) {
    Collections.sort(servers, new ServerComparator());

    // for each pool
    // pop 2 server
    // find next row available = i -> ask datacenter
    // add server to row i -> ask datacenter
    // find next row available != i -> ask datacenter
    // add server to row j -> ask datacenter

  }

}
