package App;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jenny on 7/13/2017.
 */
public class LibraryImplementation {

    private Validator Validator = new Validator();

    public void runMainLoop(ArrayList<Book> catalogue) {

        int menuSelection;

        HashMap<Integer, String> menu = new HashMap<>();
        menu.put(1, "See the complete Barely Books Library catalogue");
        menu.put(2, "Search for a book");
        menu.put(3, "Checkout a book");
        menu.put(4, "Return a book");
        menu.put(5, "Library Catalogue Maintenance [Library Personnel Only]");
        menu.put(6, "Exit");

        final int MAIN_MAX_ENTRY = menu.size();

        do {
            System.out.printf("\n----------------------------------------------------------------------------------------------------\n");
            System.out.println("Main Menu: Which action would you like to perform?");
            System.out.printf("----------------------------------------------------------------------------------------------------\n");

            for (HashMap.Entry<Integer, String> option : menu.entrySet()) {
                System.out.printf("%d - %s\n", option.getKey(), option.getValue());
            }

            menuSelection = menuSelection(MAIN_MAX_ENTRY);

            if (menuSelection == 1) {
                printCatalogue(catalogue);//Display entire list of books

            } else if (menuSelection == 2) {
                SearchImplementation searchImpl = new SearchImplementation();
                searchImpl.runSelectionLoop(catalogue); //Search for a book

            } else if (menuSelection == 3) {
                //LibraryImplementation libraryImpl = new LibraryImplementation();
                //libraryImpl.printCatalogue(catalogue);
                printCatalogue(catalogue);

                CheckoutImplementation checkoutImpl = new CheckoutImplementation(); //Checkout a book
                checkoutImpl.runCheckoutLoop(catalogue);

            } else if (menuSelection == 4) {
                ReturnImplementation returnImpl = new ReturnImplementation(); //Return a book
                returnImpl.runReturnLoop(catalogue);

            } else if (menuSelection == 5) {
                if(Validator.getInt("Please enter password: ") == 1234) {
                    BookAdder adder = new BookAdder();
                    adder.addBook(catalogue);
                } else {
                    System.out.println("Sorry, you are not authorized to perform this function.");
                }
            }

            if (menuSelection == MAIN_MAX_ENTRY) {
                TextFileReaderWriter fileWriter = new TextFileReaderWriter(); //Write updated catalogue of books to file before exiting
                fileWriter.fileWriter(catalogue);
            }

        } while (menuSelection != MAIN_MAX_ENTRY); //Exit runMainLoop and return to startAndEnd
    }

    public int menuSelection(int MAX_ENTRY) {
        int menuSelection;

        System.out.println();
        menuSelection = Validator.getInt("Please enter a number from the Main Menu above: ", "Please enter a valid menu number: ", 1, MAX_ENTRY);

        return menuSelection;
    }

    public void printCatalogue(ArrayList<Book> consoleCatalogue) {
        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");
        System.out.print("Current catalogue...");
        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");

        int i = 1;

        for (Book book : consoleCatalogue) {
            System.out.print(i + " " + book.getTitle() + " " + book.getAuthor() + " " + book.getGenre() + " " + book.getBraille() + " " + book.getStatus());
            i = i + 1;
            if (book.getStatus()== Status.CHECKED_OUT){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String formattedString = book.getDueDate().format(formatter);

                System.out.print(" " + formattedString);
            }
            System.out.println();
        }
    }
}