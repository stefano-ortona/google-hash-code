package google.com.ortona.hashcode.final_2015.logic;

import google.com.ortona.hashcode.final_2015.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            for (Baloon baloon : status.getBaloons()) {
                NextBestPositionBundle nextPositionBundle = calculateNewBestPositionForBaloon(baloon, status);

                if (nextPositionBundle.nextPosition != null) {
                    status.moveBaloon(nextPositionBundle.nextPosition.x, nextPositionBundle.nextPosition.y);
                    baloon.addMove(nextPositionBundle.move);
                } else {
                    LOG.error("NEXT POSITION NOT FOUND! -> baloon id: " + baloon.getId());
                }
            }

            status.reset();
        }

        SolutionContainer container = new SolutionContainer(status.getBaloons());

        return container;
    }


    /*
     * Internal methods
     */

    private NextBestPositionBundle calculateNewBestPositionForBaloon(Baloon baloon, Status status) {
        List<Integer> possibleMoves = generatePossibleMovesForBaloon(baloon, status);

        int finalCoveredCells = 0;
        Pair finalPosition = null;
        int finalMove = -100;

        for (Integer move : possibleMoves) {
            Pair curPosition = status.getNextPosition(baloon.getRow(), baloon.getColumn(), baloon.getHeight(), move);

            if (curPosition == null) {
                continue; // SKIP
            }

            int curCoveredCells = status.getCoveredCell(curPosition.x, curPosition.y);

            if (finalPosition == null || curCoveredCells > finalCoveredCells) { // check best coverage (or first step)
                finalPosition = curPosition;
                finalCoveredCells = curCoveredCells;
                finalMove = move;

            } else if (curCoveredCells == finalCoveredCells) { // check farther distance between baloons
                int curMinDistance = Collections.min(status.getDistanceWithOtherBaloons(curPosition.x, curPosition.y, baloon.getId()));
                int finalMinDistance = Collections.min(status.getDistanceWithOtherBaloons(finalPosition.x, finalPosition.y, baloon.getId()));

                if (curMinDistance > finalMinDistance) {
                    finalPosition = curPosition;
                    finalCoveredCells = curCoveredCells;
                    finalMove = move;
                }
            }
        }

        if (finalPosition == null || finalMove == -100) {
            throw new RuntimeException("NO NEXT POSITION FOUND for baloon: " + baloon.getId());
        }

        return new NextBestPositionBundle(finalPosition, finalMove);
    }

    private List<Integer> generatePossibleMovesForBaloon(Baloon baloon, Status status) {
        List<Integer> moves = new ArrayList<>();

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
