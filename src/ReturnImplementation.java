import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jenny on 7/13/2017.
 */
public class ReturnImplementation {

    //Use Validator to validate user entries throughout:
    private Validator Validator = new Validator();

    //Return books:
    public void runReturnLoop(ArrayList<Book> catalogue) {

        int returnSelection;

        //Create bookIndex HashMap to assign line numbers (Keys) to checked out books:
        HashMap<Integer, Book> bookIndex = new HashMap<>();

        //Create ArrayList of checked out books:
        ArrayList<Book> checkedoutCatalogue = new ArrayList<>();

        //If book is checked out, add the book to the checkedoutCatalogue:
        for (Book book : catalogue) {
            if (book.getStatus() == Status.CHECKED_OUT) {
                checkedoutCatalogue.add(book);
            }
        }

        if (checkedoutCatalogue.size() > 0) {

            //Print checked out books to console for user to select from:
            bookIndex = printReturnCatalogue(catalogue, checkedoutCatalogue, bookIndex);

            //Get book selection from user:
            returnSelection = Validator.getInt("Which book would you like to return? (Please enter the line number): ", "Please enter a valid line number: ", 1, checkedoutCatalogue.size());
            System.out.printf("----------------------------------------------------------------------------------------------------\n");

            //Proceed to return book to shelf:
            returnToShelf(bookIndex.get(returnSelection));

            //Inform user if return was successful:
            System.out.println("\nThank you. Your book was successfully returned! Returning to Main Menu.");
        } else {

            //Inform user if no books are checked out and return to Main Menu (runMainLoop):
            System.out.println("\nThere are currently no books checked out. Returning to Main Menu.\n");
        }
    }

    public void returnToShelf(Book returnedBook) {

        //Assign book status to on shelf:
        returnedBook.setStatus(Status.ON_SHELF);

        //Print updated book to console:
        System.out.println("\n" + returnedBook.toCheckoutFormat());
    }

    //Print currently checked out books to console:
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