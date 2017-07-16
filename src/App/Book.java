package App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by jenny on 7/12/2017.
 */
public class Book {

    private String title;
    private String author;
    private LocalDate dueDate;
    private Boolean braille;
    private Status status;
    private Genre genre;

    public Book() {

    }

    public Book(String title, String author, LocalDate dueDate, Boolean braille, Status status, Genre genre) {
        this.title = title;
        this.author = author;
        this.dueDate = dueDate;
        this.braille = braille;
        this.status = status;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getBraille() {
        return braille;
    }

    public void setBraille(Boolean braille) {
        this.braille = braille;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

/*    @Override
    public String toString() {
        return "Title: " + title + "   Author: " + author + "   Braille: " + braille + "   Status: " + status + "   Genre: " + genre + "   Previous Due Date: " + dueDate;
    }*/

    @Override
    public String toString() {
        return title + "  " + author + "  " + braille + "  " + status + "  " + dueDate + "  " + genre;

    }

    public String toFileFormat() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedString = dueDate.format(formatter);

        return title + "," + author + "," + formattedString + "," + braille + "," + status + "," + genre;
    }

    public String toConsoleFormat() {
        return title + " " + author + " " + genre + " " + braille + " " + status;
    }

    public String toCheckoutFormat() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedString = dueDate.format(formatter);

        return title + " " + author + " " + genre + " " + braille + " " + status + " " + formattedString;
    }

//    public String toFileFormat() {
//        return title + "," + author + "," + dueDate + "," + braille + "," + status + "," + genre);
//    }
}