package google.com.ortona.hashcode.pizza.logic;

import java.util.ArrayList;
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

	private PizzaStatus status;
	private int H;

	private List<Slice> sliceToBeExpandedList;

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
		Slice slice = this.sliceToBeExpandedList.get(0);

		Slice sliceCopy = slice.copy();
		expandSlice(sliceCopy, LEFT); // == RIGHT
		boolean maxSizeVerticalExpasionOK = checkSliceSize(sliceCopy);

		sliceCopy = slice.copy();
		expandSlice(sliceCopy, UP); // == DOWN
		boolean maxSizeHorizontalExpasionOK = checkSliceSize(sliceCopy);

		// left
		int distanceLeft = 0;
		if (maxSizeVerticalExpasionOK) {
			while (status.canExpandVertically(slice.upperLeftX, slice.lowerRightX,
					slice.upperLeftY - 1 - distanceLeft)) {
				distanceLeft++;
				int nextColumn = slice.upperLeftY - 1 - distanceLeft;
				// check did not go over outside the pizza
				if (this.status.isColumnOutOfPizza(nextColumn)) {
					distanceLeft = Integer.MAX_VALUE;
					break;
				}
			}
		}

		// right
		int distanceRight = 0;
		if (maxSizeVerticalExpasionOK) {
			while (status.canExpandVertically(slice.upperLeftX, slice.lowerRightX,
					slice.lowerRightY + 1 + distanceRight)) {
				distanceRight++;
				int nextColumn = slice.lowerRightY + 1 + distanceRight;
				// check did not go over outside the pizza
				if (this.status.isColumnOutOfPizza(nextColumn)) {
					distanceRight = Integer.MAX_VALUE;
					break;
				}
			}
		}

		// up
		int distanceUp = 0;
		if (maxSizeHorizontalExpasionOK) {
			while (status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY,
					slice.upperLeftX - 1 - distanceUp)) {
				distanceUp++;
				int nextRow = slice.upperLeftX - 1 - distanceUp;
				// check did not go over outside the pizza
				if (this.status.isRowOutOfPizza(nextRow)) {
					distanceUp = Integer.MAX_VALUE;
					break;
				}
			}
		}

		// down
		int distanceDown = 0;
		if (maxSizeHorizontalExpasionOK) {
			while (status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY,
					slice.upperLeftX + 1 + distanceDown)) {
				distanceDown++;
				int nextRow = slice.upperLeftX - 1 - distanceUp;
				// check did not go over outside the pizza
				if (this.status.isRowOutOfPizza(nextRow)) {
					distanceDown = Integer.MAX_VALUE;
					break;
				}
			}
		}

		List<Integer> scoreList = Arrays.asList(distanceLeft, distanceRight, distanceUp, distanceDown);
		int bestScore = Collections.max(scoreList);

		if (bestScore == 0) {
			sliceToBeExpandedList.remove(slice);
			return false; // not expanded
		}

		int bestScoreIndex = scoreList.indexOf(bestScore);

		expandSlice(slice, bestScoreIndex);

		return true; // expanded
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
