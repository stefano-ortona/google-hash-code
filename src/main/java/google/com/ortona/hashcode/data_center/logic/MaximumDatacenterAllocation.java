package google.com.ortona.hashcode.data_center.logic;

import java.util.List;

import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.ScoreComputation;
import google.com.ortona.hashcode.data_center.logic.model.Server;

public class MaximumDatacenterAllocation {

  public void allocate(List<Server> servers, Datacenter datacenter, int numPool) {
    final ScoreComputation scoreComput = new ScoreComputation();
    // get current score

    final Integer bestRow = null;
    final Integer bestPool = null;
    final int overallBestScore = -1;
    for (final Server ser : servers) {
      for (int pool = 0; pool < numPool; pool++) {
        final int bestScore = scoreComput.computeScore(pool, datacenter);
        for (int row = 0; row < datacenter.getRowNumber(); row++) {
          // try to place a server in the current row
          if (datacenter.canPlaceServer(ser, row)) {
            final int curPotScore = scoreComput.computeScore(pool, datacenter, ser, row);
            if (curPotScore > bestScore) {

            }
          }

        }
      }
    }

  }

}
