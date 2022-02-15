package google.com.ortona.hashcode.y_2020.qualification.logic;

import google.com.ortona.hashcode.y_2020.qualification.model.Book;
import google.com.ortona.hashcode.y_2020.qualification.model.Library;
import google.com.ortona.hashcode.y_2020.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.y_2020.qualification.model.SolutionContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProblemSolver {
    private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

    private SolutionContainer solutionContainer = new SolutionContainer();

    private IBestLibraryRanker RANKER = new SimpleBestLibraryRanker();


    public static void main(String[] args) {
        LOG.info("Hello World!");
    }

    public SolutionContainer solve(ProblemContainer problem) {

        int day = 0;
        while (true) {
            Library.LibraryScoreBundle libraryScore = getBestLibraryScore(problem.LIBRARY_LIST, day, problem.TOTAL_DAY_COUNT);

            if (libraryScore == null) {
                break;
            }

            trackBooks(libraryScore, day);

            solutionContainer.LIBRARY_SOLUTION_LIST.add(libraryScore.library);

            day += libraryScore.library.getSignup();
        }

        return solutionContainer;
    }


    /*
     * Internal logic
     */

    private Library.LibraryScoreBundle getBestLibraryScore(List<Library> libraryList, int day, int totDayCount) {
        List<Library.LibraryScoreBundle> libraryScoreBundleList = libraryList.stream()
                .filter(library -> library.getSignupDay() == null)
                .map(library -> library.computeScore(day, totDayCount))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Library.LibraryScoreBundle bestLibraryScore = RANKER.getBestLibraryScore(libraryScoreBundleList);

        if (bestLibraryScore != null) {
            return bestLibraryScore;
        } else {
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
