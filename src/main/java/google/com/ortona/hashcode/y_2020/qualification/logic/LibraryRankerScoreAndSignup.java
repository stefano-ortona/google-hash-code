package google.com.ortona.hashcode.y_2020.qualification.logic;

import google.com.ortona.hashcode.y_2020.qualification.model.Library;

import java.util.List;

public class LibraryRankerScoreAndSignup implements IBestLibraryRanker {
	
	private int MAX_DIFFERENCE = Integer.MAX_VALUE;

    @Override
    public Library.LibraryScoreBundle getBestLibraryScore(List<Library.LibraryScoreBundle> libraryScoreBundleList) {

        Library.LibraryScoreBundle bestLibraryScore = null;

        for (Library.LibraryScoreBundle libraryScore : libraryScoreBundleList) {

            if (isBetter(bestLibraryScore, libraryScore)) {
                bestLibraryScore = libraryScore;
            }
        }

        return bestLibraryScore;
    }
    
    private boolean isBetter(Library.LibraryScoreBundle best, Library.LibraryScoreBundle current) {
    	if(best == null) {
    		return true;
    	}
    	
    	if(Math.abs(best.score - current.score) <= MAX_DIFFERENCE) {
    		return current.library.getSignup() < best.library.getSignup();
    	}
    	
    	return best.score > current.score ? false : true;
    	
    }

}
