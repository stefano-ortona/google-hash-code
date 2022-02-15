package google.com.ortona.hashcode.y_2020.qualification.logic;

import google.com.ortona.hashcode.y_2020.qualification.model.Book;
import google.com.ortona.hashcode.y_2020.qualification.model.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.y_2020.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.y_2020.qualification.model.SolutionContainer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemSolver {
    private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

    private SolutionContainer solutionContainer = new SolutionContainer();

    public static void main(String[] args) {
        LOG.info("Hello World!");
    }

    public SolutionContainer solve(ProblemContainer problem) {

        int day = 0;
        while (true) {
            Library.LibraryScoreBundle libraryScore = getBestLibraryScore(problem.LIBRARY_LIST, day, problem.TOTAL_DAY_COUNT); // TODO: 15/02/2022 gestire diverse liste per evitare iterazione intere

            if (libraryScore == null) {
                break;
            }

            trackBooks(libraryScore, day);

            solutionContainer.LIBRARY_SOLUTION_LIST.add(libraryScore.library);

            day += libraryScore.library.getSignupDay();
        }

        return solutionContainer;
    }


    /*
     * Internal logic
     */

    private Library.LibraryScoreBundle getBestLibraryScore(List<Library> libraryList, int day, int totDayCount) {
        List<Library.LibraryScoreBundle> libraryScoreBundleList = libraryList.stream()
                .filter(library -> {library.getSignupDay() == null})
                .map(library -> library.computeScore(day, totDayCount))
                .collect(Collectors.toList());

        libraryScoreBundleList.sort(Comparator.comparingInt(o -> o.score));

        try {
            return libraryScoreBundleList.get(0);
        } catch (Exception e) {
            LOG.debug("no more libraries to choose!!!");
            return null;
        }
    }

    private void trackBooks(Library.LibraryScoreBundle libraryScore, int day) {
        libraryScore.library.setSignupDay(day);

        for (Book book : libraryScore.chosenBooks) {
            book.setLibraryId(libraryScore.library.getId());
        }
    }

}
