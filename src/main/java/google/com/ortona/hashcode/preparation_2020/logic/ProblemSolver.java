package google.com.ortona.hashcode.preparation_2020.logic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.preparation_2020.model.Pair;
import google.com.ortona.hashcode.preparation_2020.model.ProblemContainer;
import google.com.ortona.hashcode.preparation_2020.model.SolutionContainer;

public class ProblemSolver {
    private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

    private static int CHUNK_LENGTH = 25;

    private SubchankOptimal subChunkProcessor = new SubchankOptimal();

    public SolutionContainer solve(ProblemContainer problem) {
        List<Integer> allNumbers = problem.getNumbers();

        int startIndex = 0;
        int endIndex = allNumbers.size() - 1;

        List<Integer> allResults = new ArrayList<>();

        boolean isLeft = false;

        while (startIndex < endIndex) {
            int chunkCurrentSize = 0;

            List<Pair> chunk = new ArrayList<>();

            while (chunkCurrentSize < CHUNK_LENGTH) {
                if (startIndex >= endIndex) {
                    break;
                }
                if (isLeft) {
                    chunk.add(new Pair(startIndex, allNumbers.get(startIndex)));
                    startIndex++;
                } else {
                    chunk.add(new Pair(endIndex, allNumbers.get(endIndex)));
                    endIndex--;
                }
                isLeft = !isLeft;
				chunkCurrentSize++;
            }

            allResults.addAll(subChunkProcessor.solve(chunk, Math.max(problem.getGoal(), problem.getGoal() / CHUNK_LENGTH)));
        }

        int totalScore = 0;
        for (Integer index : allResults) {
            totalScore += allNumbers.get(index);
        }

        SolutionContainer solution = new SolutionContainer(allResults, totalScore);

        return solution;
    }

    public static void main(String[] args) {
        LOG.info("Hello World!");
    }

}
