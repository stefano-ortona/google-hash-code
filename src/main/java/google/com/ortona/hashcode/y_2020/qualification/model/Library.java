package google.com.ortona.hashcode.y_2020.qualification.model;

import java.util.List;
import java.util.Objects;

public class Library {

    private int id;

    private int books4days;

    private int signup;

    private List<Book> bookList;

    private int signupDay;


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

    public int getSignupDay() {
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
        return null;
    }


    /*
     * Structures
     */

    public static class LibraryScoreBundle {
        public int score;
        public int totalTime;
    }

}
