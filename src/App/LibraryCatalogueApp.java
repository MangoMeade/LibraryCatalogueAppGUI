package App;

import java.util.ArrayList;

/**
 * Created by jenny on 7/12/2017.
 */

//Console program to search library catalog and reserve books
public class LibraryCatalogueApp {

    private Validator Validator = new Validator();

    public static void main(String[] args) {

        startAndEnd();

    }

    public static void startAndEnd() {

        System.out.println("Welcome to the Barely Books Library Terminal:");

        //Read catalogue of books from file upon start
        TextFileReaderWriter reader = new TextFileReaderWriter();
        ArrayList<Book> catalogue = reader.readFromCatalogue();

        System.out.printf("There are " + catalogue.size() + " books in the library.\n");

        LibraryImplementation implementation = new LibraryImplementation();
        implementation.runMainLoop(catalogue);

        System.out.println("\nGoodbye. Thank you for visiting Barely Books!");
    }
}