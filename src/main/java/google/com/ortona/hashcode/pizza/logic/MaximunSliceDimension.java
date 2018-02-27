package google.com.ortona.hashcode.pizza.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import google.com.ortona.hashcode.pizza.logic.comparator.SliceDimensionComparator;
import google.com.ortona.hashcode.pizza.model.PizzaStatus;
import google.com.ortona.hashcode.pizza.model.Slice;

public class MaximunSliceDimension {

  private static final int LEFT = 0;
  private static final int RIGHT = 1;
  private static final int UP = 2;
  private static final int DOWN = 3;

  private static final SliceDimensionComparator DIMENSION_COMPARATOR = new SliceDimensionComparator();

  private final PizzaStatus status;
  private final int H;

  private final List<Slice> sliceToBeExpandedList;

  public MaximunSliceDimension(PizzaStatus status, int H) {
    this.status = status;
    this.H = H;
    this.sliceToBeExpandedList = Lists.newArrayList(this.status.getSliceList());
  }

  public PizzaStatus maximizeSlicesDimension() {
    while (!sliceToBeExpandedList.isEmpty()) {
      expandSmallerSlice();
    }
    return this.status;
  }

  private boolean expandSmallerSlice() {
    Collections.sort(sliceToBeExpandedList, DIMENSION_COMPARATOR);
    final Slice slice = this.sliceToBeExpandedList.get(0);

    Slice sliceCopy = slice.copy();
    expandSlice(sliceCopy, LEFT); // == RIGHT
    final boolean maxSizeVerticalExpasionOK = checkSliceSize(sliceCopy);

    sliceCopy = slice.copy();
    expandSlice(sliceCopy, UP); // == DOWN
    final boolean maxSizeHorizontalExpasionOK = checkSliceSize(sliceCopy);

    // left
    int distanceLeft = 0;
    if (maxSizeVerticalExpasionOK) {
      if (status.canExpandVertically(slice.upperLeftX, slice.lowerRightX, slice.upperLeftY - 1 - distanceLeft)) {
        distanceLeft += (slice.lowerRightX - slice.upperLeftX) + 1;
      }
    }

    // right
    int distanceRight = 0;
    if (maxSizeVerticalExpasionOK) {
      if (status.canExpandVertically(slice.upperLeftX, slice.lowerRightX, slice.lowerRightY + 1 + distanceRight)) {
        distanceRight += (slice.lowerRightX - slice.upperLeftX) + 1;
      }
    }

    // up
    int distanceUp = 0;
    if (maxSizeHorizontalExpasionOK) {
      if (status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY, slice.upperLeftX - 1 - distanceUp)) {
        distanceUp += (slice.lowerRightY - slice.upperLeftY) + 1;
      }
    }

    // down
    int distanceDown = 0;
    if (maxSizeHorizontalExpasionOK) {
      if (status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY, slice.lowerRightX + 1 + distanceDown)) {
        distanceDown += (slice.lowerRightY - slice.upperLeftY) + 1;
        ;
      }
    }

    final List<Integer> scoreList = Arrays.asList(distanceLeft, distanceRight, distanceUp, distanceDown);
    final int bestScoreIndex = getBestIndexScore(scoreList);
    if (bestScoreIndex == -1) {
      sliceToBeExpandedList.remove(slice);
      return false;
    }
    status.expandSlice(bestScoreIndex, slice);

    return true; // expanded
  }

  private int getBestIndexScore(final List<Integer> scoreList) {
    // TODO: break ties
    final int bestScore = Collections.max(scoreList);

    if (bestScore == 0) {
      return -1; // not expanded
    }

    return scoreList.indexOf(bestScore);
  }

  private void expandSlice(Slice slice, int direction) {
    switch (direction) {
    case LEFT:
      slice.upperLeftY--;
      break;

    case RIGHT:
      slice.lowerRightY++;
      break;

    case UP:
      slice.upperLeftX--;
      break;

    case DOWN:
      slice.lowerRightX++;
      break;
    }
  }

  private boolean checkSliceSize(Slice slice) {
    return slice.dimen() <= this.H;
  }

}
