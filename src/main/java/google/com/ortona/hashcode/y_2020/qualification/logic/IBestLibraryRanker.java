package google.com.ortona.hashcode.y_2020.qualification.logic;

import google.com.ortona.hashcode.y_2020.qualification.model.Library;

import java.util.List;

public interface IBestLibraryRanker {

    Library.LibraryScoreBundle getBestLibraryScore(List<Library.LibraryScoreBundle> libraryScoreBundleList);
}
