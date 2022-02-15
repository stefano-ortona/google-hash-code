package google.com.ortona.hashcode.y_2020.qualification.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import google.com.ortona.hashcode.y_2020.qualification.model.Book;
import google.com.ortona.hashcode.y_2020.qualification.model.Library;
import google.com.ortona.hashcode.y_2020.qualification.model.ProblemContainer;

public class ProblemReader {
	public ProblemContainer readProblem(String fileLocation) throws IOException {
		ProblemContainer pC = new ProblemContainer();


		BufferedReader br = new BufferedReader(new InputStreamReader(ProblemReader.class.getResourceAsStream(fileLocation)));

		int []firstLine = parseLine(br.readLine()); 
		int libraries = firstLine[1]; 
		int totDays =  firstLine[2];
		int []allScores = parseLine(br.readLine());

		Map<Integer, Book> id2book = new HashMap<>();
		for(int i = 0; i < allScores.length; i++) {
			Book b = new Book();
			b.setId(i);
			b.setScore(allScores[i]);
			id2book.put(i, b);
		}

		List<Library> allLibraries = new ArrayList<>();
		for(int i=0; i < libraries; i++) {
			int []libParts = parseLine(br.readLine());
			Library l = new Library();
			l.setId(i);
			l.setSignup(libParts[1]);
			l.setBooks4days(libParts[2]);
			int []allBooks = parseLine(br.readLine());
			List<Book> libBooks = new ArrayList<>();
			for(int oneB:allBooks) {
				libBooks.add(id2book.get(oneB));
			}
			Collections.sort(libBooks, new Comparator<Book>() {

				@Override
				public int compare(Book o1, Book o2) {
					if(o1.getScore() == o2.getScore()) {
						return o1.getId() - o2.getId();
					}
					return o2.getScore() - o1.getScore();
				}
			});
			l.setBookList(libBooks);
			allLibraries.add(l);
		}

		pC.LIBRARY_LIST = allLibraries;
		pC.TOTAL_DAY_COUNT = totDays;
		return pC;

	}


	private int[] parseLine(String line) {
		String []parts = line.split(" ");
		int []out = new int[parts.length];
		for(int i = 0; i < parts.length; i++) {
			out[i] = Integer.parseInt(parts[i]);
		}
		return out;
	}
}
