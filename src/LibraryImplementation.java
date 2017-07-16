import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jenny on 7/13/2017.
 */
public class LibraryImplementation {

    //Use Validator to validate user entries throughout:
    private Validator Validator = new Validator();

    //Run Main Menu and make subsequent calls:
    public void runMainLoop(ArrayList<Book> catalogue) {

        int menuSelection;

        //Main Menu HashMap for user to select the corresponding Key:
        HashMap<Integer, String> menu = new HashMap<>();
        menu.put(1, "See the complete Barely Books Library catalogue");
        menu.put(2, "Search for a book");
        menu.put(3, "Checkout a book");
        menu.put(4, "Return a book");
        menu.put(5, "Library Catalogue Maintenance [Library Personnel Only]");
        menu.put(6, "Exit");

        //Constant value for "Exit":
        final int MAIN_MAX_ENTRY = menu.size();

        //Loop until user selects "Exit" (enters the key = MAIN_MAX_ENTRY):
        do {
            System.out.printf("\n----------------------------------------------------------------------------------------------------\n");
            System.out.println("Main Menu: Which action would you like to perform?");
            System.out.printf("----------------------------------------------------------------------------------------------------\n");

            //Print Main Menu:
            for (HashMap.Entry<Integer, String> option : menu.entrySet()) {
                System.out.printf("%d - %s\n", option.getKey(), option.getValue());
            }

            //Get user selection from Main Menu:
            menuSelection = menuSelection(MAIN_MAX_ENTRY);

            //Display the entire list of books:
            if (menuSelection == 1) {
                printCatalogue(catalogue);
            }
            //Search for a book (proceed to Search Menu in SearchImplementation):
            else if (menuSelection == 2) {
                SearchImplementation searchImpl = new SearchImplementation();
                searchImpl.runSelectionLoop(catalogue);
            }

            //Display list of books and proceed to CheckoutImplementation to Checkout a book:
            else if (menuSelection == 3) {
                printCatalogue(catalogue);

                CheckoutImplementation checkoutImpl = new CheckoutImplementation();
                checkoutImpl.runCheckoutLoop(catalogue);
            }

            //Proceed to ReturnImplementation to Return a book:
            else if (menuSelection == 4) {
                ReturnImplementation returnImpl = new ReturnImplementation();
                returnImpl.runReturnLoop(catalogue);
            }

            //Prompt for password (Library Personnel ONLY) and proceed to BookAdder to add a book:
            else if (menuSelection == 5) {
                if (Validator.getInt("Please enter password: ") == 1234) {
                    BookAdder adder = new BookAdder();
                    adder.addBook(catalogue);
                } else {
                    System.out.println("Sorry, you are not authorized to perform this function.");
                }
            }

            //Write updated catalogue of books to file before Exiting app:
            if (menuSelection == MAIN_MAX_ENTRY) {
                TextFileReaderWriter fileWriter = new TextFileReaderWriter();
                fileWriter.fileWriter(catalogue);
            }
        } while (menuSelection != MAIN_MAX_ENTRY);
        //Exit runMainLoop and return to startAndEnd
    }

    //Receive and validate user selection from Main Menu:
    public int menuSelection(int MAX_ENTRY) {
        int menuSelection;

        System.out.println();
        menuSelection = Validator.getInt("Please enter a number from the Main Menu above: ", "Please enter a valid menu number: ", 1, MAX_ENTRY);

        return menuSelection;
    }

    //Print current library catalogue to console:
    public void printCatalogue(ArrayList<Book> consoleCatalogue) {
        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");
        System.out.print("Current catalogue...");
        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");

        int i = 1;

        //Loop through each book and print attributes (including due date if checked out):
        for (Book book : consoleCatalogue) {
            System.out.print(i + " " + book.getTitle() + " " + book.getAuthor() + " " + book.getGenre() + " " + book.getBraille() + " " + book.getStatus());
            i = i + 1;
            if (book.getStatus() == Status.CHECKED_OUT) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String formattedString = book.getDueDate().format(formatter);

                System.out.print(" " + formattedString);
            }
            System.out.println();
        }
    }
}