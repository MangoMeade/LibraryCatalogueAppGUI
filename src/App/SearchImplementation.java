package App;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jenny on 7/13/2017.
 */
public class SearchImplementation {

    private Validator Validator = new Validator();

    public void runSelectionLoop(ArrayList<Book> catalogue) {

        int attributeSelection;

        HashMap<Integer, String> attributeMenu = new HashMap<>();

        attributeMenu.put(1, "Title");
        attributeMenu.put(2, "Author");
        attributeMenu.put(3, "Genre (Drama, Fiction, Nonfiction, Historical, Biographical, Mystery)");
        attributeMenu.put(4, "Return to Main Menu");

        final int SEARCH_MAX_ENTRY = attributeMenu.size();

        do {
            System.out.printf("\n----------------------------------------------------------------------------------------------------\n");
            System.out.println("Search Menu (Select Attribute to Search by):");
            System.out.printf("----------------------------------------------------------------------------------------------------\n");

            for (HashMap.Entry<Integer, String> option : attributeMenu.entrySet()) {
                System.out.printf("%d - %s\n", option.getKey(), option.getValue());
            }

            attributeSelection = attributeSelection(SEARCH_MAX_ENTRY);

            if (attributeSelection != SEARCH_MAX_ENTRY) {

                System.out.println();
                String searchString = (Validator.getString("Please enter a word to search by: "));

                attributeSearch(catalogue, attributeSelection, searchString);
            }

        } while (attributeSelection != SEARCH_MAX_ENTRY);

        //LibraryImplementation implementation = new LibraryImplementation();
        //implementation.runMainLoop(catalogue);
    }

    public int attributeSelection(int MAX_ENTRY) {
        int attributeSelection;

        System.out.println();
        attributeSelection = Validator.getInt("Please enter a number from the Search Menu above: ", "Please enter a valid menu number: ", 1, MAX_ENTRY);

        return attributeSelection;
    }

    public void attributeSearch(ArrayList<Book> catalogue, int attributeSelection, String searchString) {

        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");
        System.out.print("Here are the results of your search...");
        System.out.printf("\n----------------------------------------------------------------------------------------------------\n");

        HashMap<Integer, Book> bookIndex = new HashMap<>();
        int match = 0;
        for (Book book : catalogue) {
            if (attributeSelection == 1) {
                match = checkContains(book, book.getTitle(), searchString, match, catalogue);
            } else if (attributeSelection == 2) {
                match = checkContains(book, book.getAuthor(), searchString, match, catalogue);
            } else if (attributeSelection == 3) {
                match = checkEquals(book, book.getGenre().toString(), searchString, match, catalogue);
            }
        }

        if (match == 1) {
            System.out.println("\n" + match + " match found.");
        } else {
            System.out.println("\n" + match + " matches found.");
        }

        if (match != 0) {
            System.out.println();
            if ((Validator.getString("Would you like to checkout one of these books? (y/n)").equalsIgnoreCase("y"))) {
                CheckoutImplementation checkoutImpl = new CheckoutImplementation();
                checkoutImpl.runCheckoutLoop(catalogue);
            }
        }
    }

    //Print to console any books with title or author that contain the search string:
    public int checkContains(Book book, String attribute, String searchString, int match, ArrayList catalogue) {

        if (containsIgnoreCase(attribute, searchString)) {
            System.out.println((catalogue.indexOf(book) + 1) + " " + book.toConsoleFormat());
            match = match + 1;
        }

        return match;
    }

    //if book contains searchString  choosenBook is displated in the GUI
    public Book checkContainsGUI(Book book, String searchString) {
        TextFileReaderWriter reader = new TextFileReaderWriter();
        ArrayList<Book> catalogue = reader.readFromCatalogue();

        Book choosenBook = new Book();
        if (book.getAuthor().toLowerCase().contains(searchString.toLowerCase())) {
            choosenBook = book;
        }
        if (book.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
            choosenBook = book;
        }



        return choosenBook;

    }



    //Print to console any books with genre that equals the search string:
    public int checkEquals(Book book, String attribute, String searchString, int match, ArrayList catalogue) {

        if (attribute.equalsIgnoreCase(searchString.replaceAll("\\s", ""))) {
            System.out.println((catalogue.indexOf(book) + 1) + " " + book.toConsoleFormat());
            match = match + 1;
        }


        return match;
    }

    public boolean containsIgnoreCase(String attribute, String searchString) {
        if (attribute == null || searchString == null) return false;

        final int length = searchString.length();
        if (length == 0)
            return true;

        for (int i = attribute.length() - length; i >= 0; i--) {
            if (attribute.regionMatches(true, i, searchString, 0, length))
                return true;
        }
        return false;
    }

}