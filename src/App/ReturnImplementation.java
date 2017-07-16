package App;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jenny on 7/13/2017.
 */
public class ReturnImplementation {

    private Validator Validator = new Validator();

    public void runReturnLoop(ArrayList<Book> catalogue) {

        int returnSelection;

        HashMap<Integer, Book> bookIndex = new HashMap<>();

        ArrayList<Book> checkedoutCatalogue = new ArrayList<>();

        for (Book book : catalogue) {
            if (book.getStatus() == Status.CHECKED_OUT) {
                checkedoutCatalogue.add(book);
            }
        }

        if (checkedoutCatalogue.size() > 0) {

            //libraryImpl.printCatalogue(checkedoutCatalogue);
            bookIndex = printReturnCatalogue(catalogue, checkedoutCatalogue, bookIndex);

            returnSelection = Validator.getInt("Which book would you like to return? (Please enter the line number): ", "Please enter a valid line number: ", 1, checkedoutCatalogue.size());

            System.out.printf("----------------------------------------------------------------------------------------------------\n");
            //returnToShelf(catalogue.get(returnSelection - 1));
            returnToShelf(bookIndex.get(returnSelection));

            System.out.println("\nThank you. Your book was successfully returned! Returning to Main Menu.");
        } else {

            System.out.println("\nThere are currently no books checked out. Returning to Main Menu.\n");

            LibraryImplementation implementation = new LibraryImplementation();
            implementation.runMainLoop(catalogue);
        }
    }

    public void returnToShelf(Book returnedBook) {

        returnedBook.setStatus(Status.ON_SHELF);
        System.out.println("\n" + returnedBook.toCheckoutFormat());

    }

    public void returnToShelfGUI(Book returnedBook, Book nameBook) {

        if ((returnedBook.getTitle().toLowerCase().equalsIgnoreCase(nameBook.getTitle().toLowerCase())) && (returnedBook.getAuthor().toLowerCase().equalsIgnoreCase(nameBook.getAuthor().toLowerCase()))) {
            nameBook.setStatus(Status.ON_SHELF);
        }
    }

    public HashMap printReturnCatalogue(ArrayList<Book> catalogue, ArrayList<Book> checkedoutCatalogue, HashMap<Integer, Book> bookIndex) {
        int i = 1;

        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");
        System.out.print("The following books are currently checked out...");
        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");

        for (Book book : checkedoutCatalogue) {
            System.out.print(i + " " + book.getTitle() + " " + book.getAuthor() + " " + book.getGenre() + " " + book.getBraille() + " " + book.getStatus());
            bookIndex.put(i, book);
            i = i + 1;
            if (book.getStatus() == Status.CHECKED_OUT) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String formattedString = book.getDueDate().format(formatter);

                System.out.print(" " + formattedString);
            }
            System.out.println();
        }
        System.out.println();

        return bookIndex;
    }

}