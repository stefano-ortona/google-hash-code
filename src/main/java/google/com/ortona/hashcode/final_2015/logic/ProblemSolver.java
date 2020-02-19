package google.com.ortona.hashcode.final_2015.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.final_2015.model.Baloon;
import google.com.ortona.hashcode.final_2015.model.Pair;
import google.com.ortona.hashcode.final_2015.model.ProblemContainer;
import google.com.ortona.hashcode.final_2015.model.SolutionContainer;
import google.com.ortona.hashcode.final_2015.model.Status;

//for each baloon b:
//	for each move m:
//		compute covered cell with m
//		for each other baloon b':
//			compute distance with b'
//	pick best move m based on covered cell and distance with other baloons
public class ProblemSolver {
	private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

	public SolutionContainer solve(ProblemContainer problem) {
		final Status status = problem.getStatus();

		for (int i = 0; i < status.getMaxTurns(); i++) {
			for (final Baloon baloon : status.getBaloons()) {
				if (baloon.isDead()) {
					baloon.addMove(0);
					continue;
				}
				final NextBestPositionBundle nextPositionBundle = calculateNewBestPositionForBaloon(baloon, status);

				if (nextPositionBundle.nextPosition != null) {
					status.moveBaloon(nextPositionBundle.nextPosition.x, nextPositionBundle.nextPosition.y);
					baloon.addMove(nextPositionBundle.move);
					baloon.setRow(nextPositionBundle.nextPosition.x);
					baloon.setColumn(nextPositionBundle.nextPosition.y);
				} else {
					LOG.error("NEXT POSITION NOT FOUND! -> baloon id: " + baloon.getId());
					baloon.setDead();
				}
			}

			status.reset();
		}

		final SolutionContainer container = new SolutionContainer(status.getBaloons());

		return container;
	}

	/*
	 * Internal methods
	 */

	private NextBestPositionBundle calculateNewBestPositionForBaloon(Baloon baloon, Status status) {
		final List<Integer> possibleMoves = generatePossibleMovesForBaloon(baloon, status);

		int finalCoveredCells = 0;
		Pair finalPosition = null;
		int finalMove = -100;

		for (final Integer move : possibleMoves) {
			final Pair curPosition = status.getNextPosition(baloon.getRow(), baloon.getColumn(), baloon.getHeight(),
					move);

			if (curPosition == null) {
				continue; // SKIP
			}

			final int curCoveredCells = status.getCoveredCell(curPosition.x, curPosition.y);

			if ((finalPosition == null) || (curCoveredCells > finalCoveredCells)) { // check best coverage (or first
																					// step)
				finalPosition = curPosition;
				finalCoveredCells = curCoveredCells;
				finalMove = move;

			} else if (curCoveredCells == finalCoveredCells) { // check farther distance between baloons
				final int curMinDistance = Collections
						.min(status.getDistanceWithOtherBaloons(curPosition.x, curPosition.y, baloon.getId()));
				final int finalMinDistance = Collections
						.min(status.getDistanceWithOtherBaloons(finalPosition.x, finalPosition.y, baloon.getId()));

				if (curMinDistance > finalMinDistance) {
					finalPosition = curPosition;
					finalCoveredCells = curCoveredCells;
					finalMove = move;
				}
			}
		}

		return new NextBestPositionBundle(finalPosition, finalMove);
	}

	private List<Integer> generatePossibleMovesForBaloon(Baloon baloon, Status status) {
		final List<Integer> moves = new ArrayList<>();

		if (baloon.getHeight() > 1) {
			moves.add(-1);
		}
		if (baloon.getHeight() < status.getMaxHeight()) {
			moves.add(+1);
		}

		moves.add(0);

		return moves;
	}

	/*
	 * Internal class
	 */

	private static class NextBestPositionBundle {

		Pair nextPosition;
		int move;

		public NextBestPositionBundle(Pair nextPosition, int move) {
			this.nextPosition = nextPosition;
			this.move = move;
		}
	}

}
