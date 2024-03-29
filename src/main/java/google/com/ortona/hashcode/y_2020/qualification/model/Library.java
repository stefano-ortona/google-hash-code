package google.com.ortona.hashcode.y_2020.qualification.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {

	private int id;

	private int books4days;

	private int signup;

	private List<Book> bookList;

	private Integer signupDay = null;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBooks4days() {
		return books4days;
	}

	public void setBooks4days(int books4days) {
		this.books4days = books4days;
	}

	public int getSignup() {
		return signup;
	}

	public void setSignup(int signup) {
		this.signup = signup;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Integer getSignupDay() {
		return signupDay;
	}

	public void setSignupDay(int signupDay) {
		this.signupDay = signupDay;
	}

	@Override
	public String toString() {
		return "Library{" +
				"id=" + id +
				", books4days=" + books4days +
				", signup=" + signup +
				", bookList=" + bookList +
				", signupDay=" + signupDay +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Library library = (Library) o;
		return id == library.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	/**
	 *  Utility
	 */

	public LibraryScoreBundle computeScore(int currentDay, int totalDayCount) {
		int daysForShipping = totalDayCount - (currentDay+signup);
		if(daysForShipping <= 0) {
			return null;
		}
		int totBooksToDeliver = daysForShipping * books4days;

		int actualDelivered = 0;
		int score = 0;
		List<Book> chosenBooks = new ArrayList<>();
		for(Book oneB : bookList) {
			if(oneB.getLibraryId() == null) {
				score += oneB.getScore();
				actualDelivered++;
				chosenBooks.add(oneB);
			}
			if(actualDelivered >= totBooksToDeliver) {
				break;
			}
		}
		if(score == 0) {
			return null;
		}

		LibraryScoreBundle lSB = new LibraryScoreBundle();
		lSB.library = this;
		lSB.score = score;
		int totDaysToDeliver = actualDelivered%books4days == 0 ? actualDelivered/books4days : actualDelivered/books4days+1;
		lSB.totalTime = signup + totDaysToDeliver;
		lSB.chosenBooks = chosenBooks;

		return lSB;
	}



	/*
	 * Structures
	 */

	public static class LibraryScoreBundle {
		public int score;
		public int totalTime;
		public List<Book> chosenBooks;
		public Library library;

	}

}
