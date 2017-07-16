import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by jenny on 7/13/2017.
 */
public class CheckoutImplementation {

    //Use Validator to validate user entries throughout:
    private Validator Validator = new Validator();

    //Check out books:
    public void runCheckoutLoop(ArrayList<Book> catalogue) {

        int checkoutSelection;

        //Get user to select a book to check out using relevant line number:
        System.out.println();
        checkoutSelection = Validator.getInt("Which book would you like to checkout? (Please enter the line number): ", "Please enter a valid line number: ", 1, catalogue.size());

        //Verify book is on the shelf and if it is call dueDateCreation to change status and due date:
        if (catalogue.get(checkoutSelection - 1).getStatus() == Status.ON_SHELF) {
            System.out.printf("----------------------------------------------------------------------------------------------------\n");
            System.out.println("\nCheckout successful...\n");
            dueDateCreation(catalogue.get(checkoutSelection - 1));
            System.out.println();
            System.out.println("Please stop by the circulation desk to pick up your book.");
        }

        //If book is not on the shelf, inform the user and return to the Main Menu (runMainLoop):
        else {
            System.out.println("\nSorry that book is no longer available. Returning to Main Menu.\n");
        }
    }

    public void dueDateCreation(Book checkoutBook) {

        //Assign book due date to 2 weeks from check out date:
        checkoutBook.setDueDate(LocalDate.now().plusWeeks(2));

        //Assign book status to check out:
        checkoutBook.setStatus(Status.CHECKED_OUT);

        //Print book to console (with updated attributes)
        System.out.println(checkoutBook.toCheckoutFormat());
    }
}
