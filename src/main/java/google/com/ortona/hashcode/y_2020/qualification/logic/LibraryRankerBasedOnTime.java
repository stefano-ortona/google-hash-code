package google.com.ortona.hashcode.y_2020.qualification.logic;

import google.com.ortona.hashcode.y_2020.qualification.model.Library;

import java.util.List;

public class LibraryRankerBasedOnTime implements IBestLibraryRanker {

    @Override
    public Library.LibraryScoreBundle getBestLibraryScore(List<Library.LibraryScoreBundle> libraryScoreBundleList) {

        Library.LibraryScoreBundle bestLibraryScore = null;

        for (Library.LibraryScoreBundle libraryScore : libraryScoreBundleList) {
            if (bestLibraryScore == null) {
                bestLibraryScore = libraryScore;
                continue;
            }

            if (libraryScore.library.getSignup() < bestLibraryScore.library.getSignup()) {
                bestLibraryScore = libraryScore;
            }
        }

        return bestLibraryScore;
    }

}
