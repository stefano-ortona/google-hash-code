package google.com.ortona.hashcode.data_center.logic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.App;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.ScoreComputation;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.Slot;

public class MaximumDatacenterAllocation {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public void allocate(List<Server> servers, Datacenter datacenter, int numPool) {
    final ScoreComputation scoreComput = new ScoreComputation();
    // get current score
    for (final Server ser : servers) {
      LOGGER.info("Trying to place server '{}'", ser);
      Slot bestSlot = null;
      Integer bestPool = null;
      int lowestMin = Integer.MAX_VALUE;
      for (int pool = 0; pool < numPool; pool++) {
        int curScore = scoreComput.computeScore(pool, datacenter);
        LOGGER.info("Considering pool '{}' with cur score '{}'", pool, curScore);
        if (curScore < lowestMin) {
          lowestMin = curScore;
          for (int row = 0; row < datacenter.getRowNumber(); row++) {
            LOGGER.info("Trying to place it in row '{}'", row);
            // try to place a server in the current row
            final Slot curSlot = datacenter.canPlaceServer(ser, row);
            if (curSlot != null) {
              final int curPotScore = scoreComput.computeScore(pool, datacenter, ser, row);
              if (curPotScore > curScore) {
                LOGGER.info("Row '{}' is a good one to place the server, storing as candidate.", row);
                curScore = curPotScore;
                bestSlot = curSlot;
                bestPool = pool;
              }
            } else {
              LOGGER.info("Row is too full, cannot be placed here.");
            }
          }
        } else {
          LOGGER.info("Score is not less than minimum score, skipping pool.");
        }
      }
      // check if it could be place
      if ((bestSlot != null) && (bestPool != null)) {
        LOGGER.info("Server will be placed at row '{}' and column '{}' with pool '{}'", bestSlot.getRow(),
            bestSlot.getColumn(), bestPool);
        datacenter.addServer(ser, bestSlot, bestPool);
      }
    }
  }

}
