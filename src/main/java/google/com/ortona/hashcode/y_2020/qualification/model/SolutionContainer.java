package google.com.ortona.hashcode.y_2020.qualification.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SolutionContainer {

    public List<Library> LIBRARY_SOLUTION_LIST = new ArrayList<>();


    public SolutionContainer() {
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("" + LIBRARY_SOLUTION_LIST.size() + "\n");
        for (final Library library : LIBRARY_SOLUTION_LIST) {
            List<Book> goodBooks = library.getBookList().stream()
                    .filter(book -> book.getLibraryId() == library.getId())
                    .collect(Collectors.toList());
            output.append(library.getId()).append(" ").append(goodBooks.size()).append("\n");
            output.append(goodBooks.stream().map(book -> book.getId() + "").collect(Collectors.joining(" "))).append("\n");

        }
        return output.toString();
    }

    public int getScore() {
        int score = 0;
        for (final Library library : LIBRARY_SOLUTION_LIST) {
        	score += library.getBookList().stream()
    				.filter(book -> book.getLibraryId() == library.getId())
    				.map(book -> book.getScore())
    				.reduce(0, (a, b) -> a + b);
		}
        return score;
    }
}

