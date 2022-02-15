package google.com.ortona.hashcode.y_2020.qualification.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class SolutionContainer {

    public List<Library> LIBRARY_SOLUTION_LIST = new ArrayList<>();


    public SolutionContainer() {
    }

    @Override
    public String toString() {
    	String output = "" + LIBRARY_SOLUTION_LIST.size() + "\n";
		for (final Library library : LIBRARY_SOLUTION_LIST) {
			List<Book> goodBooks = library.getBookList().stream()
				.filter(book -> book.getLibraryId() == library.getId())
				.collect(Collectors.toList());
			output += library.getId() + " " + goodBooks.size() + "\n";
			output += String.join(" ", goodBooks.stream().map(book -> book.getId() + "").collect(Collectors.toList())) + "\n";
			
		}
		return output;
    }

    public int getScore() {
        return 0;
    }
}
