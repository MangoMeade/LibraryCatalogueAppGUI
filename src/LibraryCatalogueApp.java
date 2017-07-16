import java.util.ArrayList;

/**
 * Created by jenny on 7/12/2017.
 */

//Console program to search Barely Books Library catalog and checkout books:
public class LibraryCatalogueApp {

    //Main method:
    public static void main(String[] args) {

        //Call startAndEnd to begin implementing the App:
        startAndEnd();

    }

    public static void startAndEnd() {

        //Welcomes user:
        System.out.println("Welcome to the Barely Books Library Terminal:");

        //Read catalogue of books from file before proceeding:
        TextFileReaderWriter reader = new TextFileReaderWriter();
        ArrayList<Book> catalogue = reader.readFromCatalogue();

        //Display size of catalogue to user:
        System.out.printf("There are " + catalogue.size() + " books in the library.\n");

        //Call the Main Menu (and subsequent methods):
        LibraryImplementation implementation = new LibraryImplementation();
        implementation.runMainLoop(catalogue);

        //Dismiss the user:
        System.out.println("\nGoodbye. Thank you for visiting Barely Books!");
    }
}