package google.com.ortona.hashcode.data_center.logic;

import java.util.List;

import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.ScoreComputation;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.Slot;

public class MaximumDatacenterAllocation {

  public void allocate(List<Server> servers, Datacenter datacenter, int numPool) {
    final ScoreComputation scoreComput = new ScoreComputation();
    // get current score
    for (final Server ser : servers) {
      Slot bestSlot = null;
      Integer bestPool = null;
      int lowestMin = Integer.MAX_VALUE;
      for (int pool = 0; pool < numPool; pool++) {
        int curScore = scoreComput.computeScore(pool, datacenter);
        if (curScore < lowestMin) {
          lowestMin = curScore;
          for (int row = 0; row < datacenter.getRowNumber(); row++) {
            // try to place a server in the current row
            final Slot curSlot = datacenter.canPlaceServer(ser, row);
            if (curSlot != null) {
              final int curPotScore = scoreComput.computeScore(pool, datacenter, ser, row);
              if (curPotScore > curScore) {
                curScore = curPotScore;
                bestSlot = curSlot;
                bestPool = pool;
              }
            }
          }
        }
      }
      // check if it could be place
      if ((bestSlot != null) && (bestPool != null)) {
        datacenter.addServer(ser, bestSlot, bestPool);
      }
    }
  }

}
