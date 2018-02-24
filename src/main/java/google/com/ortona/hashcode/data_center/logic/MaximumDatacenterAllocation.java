package google.com.ortona.hashcode.data_center.logic;

import java.util.List;

import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;

public class MaximumDatacenterAllocation {

  public void allocate(List<Server> servers, Datacenter datacenter, int numPool) {
    
    //for each server s
      //for each pool p
        //for each row i
          //computeScore assigning s to p to row i if it can place to i
    
    // -> get best score among all possible p

  }

}
