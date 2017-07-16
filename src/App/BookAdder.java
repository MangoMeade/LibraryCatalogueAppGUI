package App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Created by jenny on 7/12/2017.


public class BookAdder extends CatalogueTextFile {

    private Validator Validator = new Validator();

    public void addBook(ArrayList<Book> catalogue) {

        Status onShelf = Status.ON_SHELF;
        Genre fiction = Genre.FICTION;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate dueDate = LocalDate.now();
        Book book = new Book();

        book.setTitle(Validator.getString("Book title? "));
        book.setAuthor(Validator.getString("Book author? "));
        book.setBraille(Validator.getString("Is this book Braille? (y/n)", "Invalid entry. Please enter \"y\" or \"n\"", "y", "n"));
        book.setGenre(Genre.getEnumVersion(Validator.getString("What is the book genre? (Biographical, Drama, Fiction, Nonfiction, Historical, Mystery)").toLowerCase()));
        Book book1 = new Book(book.getTitle(), book.getAuthor(), dueDate, book.getBraille(), Status.ON_SHELF, book.getGenre());

        TextFileReaderWriter fileWriter = new TextFileReaderWriter();
        fileWriter.fileWriter(book1);

        catalogue.add(book1);

        TextFileReaderWriter reader = new TextFileReaderWriter();
        reader.readFromCatalogue();
    }
}

