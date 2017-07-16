import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 * Created by jenny on 7/12/2017.
 */
public class TextFileReaderWriter {

    //Methods to Read from File:
    public ArrayList<Book> readFromCatalogue() {

        //Create ArrayList catalogue of Books to use throughout App:
        ArrayList<Book> catalogue = new ArrayList<>();

        try {
            //Read from "catalogue.txt" file:
            FileReader reader = new FileReader("catalogue.txt");
            BufferedReader buffReader = new BufferedReader(reader);

            String line = null;

            //Loop through lines in "catalogue.txt":
            while ((line = buffReader.readLine()) != null) {

                //Create Array of bookAttributes separated by "," in "catalogue.txt":
                String[] bookAttributes = line.split(",");

                //Convert bookAttributes to Book and assign book to catalogue:
                catalogue.add(convertToBook(bookAttributes));

            }

            reader.close();
            buffReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Return the complete catalogue for future reference and manipulation:
        return catalogue;
    }

    //Convert array of book Attributes to a book:
    private Book convertToBook(String[] bookAttributes) {

        //Create an instance of a Book called book (assign attributes below from attribute array):
        Book book = new Book();

        //Assign the book title:
        book.setTitle(bookAttributes[0]);

        //Assign the book author:
        book.setAuthor(bookAttributes[1]);

        //Convert string to Date and assign the book due date:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dueDate = LocalDate.parse(bookAttributes[2], formatter);
        book.setDueDate(dueDate);

        //Convert string to Boolean (instance) and assign book format (Braille: yes/no):
        Boolean braille = new Boolean(bookAttributes[3]);
        book.setBraille(braille);

        //Convert string to Status enum and assign book status:
        book.setStatus(Status.getEnumVersion(bookAttributes[4]));

        //Convert string to Genre enum and assign book genre:
        book.setGenre(Genre.getEnumVersion((bookAttributes[5]).toString().toLowerCase()));

        //Return the book:
        return book;
    }

    //Methods to Write to File:

    //Write complete catalogue to file:
    public void fileWriter(ArrayList<Book> catalogue) {
        try {
            //If the test.txt file does not exist, FileWriter will create it:
            FileWriter catalogueFileWriter = new FileWriter("catalogue.txt", false);

            //Loop through and write each book in the catalogue to "catalogue.txt":
            for (int i = 0; i < catalogue.size(); i++) {
                if (i != catalogue.size() - 1) {
                    catalogueFileWriter.write(catalogue.get(i).toFileFormat() + "\n");
                } else {
                    catalogueFileWriter.write(catalogue.get(i).toFileFormat());
                }
            }

            catalogueFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Append book to catalogue file:
    public void fileWriter(Book book) {
        try {
            FileWriter bookFileWriter = new FileWriter("catalogue.txt", true);

            //Add book to end of catalogue:
            bookFileWriter.write("\n" + book.toFileFormat());

            bookFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    public int getColumnWidths(ArrayList<Book> catalogue) {
        int maxLength = catalogue.get(0).getTitle().length();

        for (Book book : catalogue) {

            if (book.getTitle().length() > maxLength) {
                maxLength = book.getTitle().length();
            }

        }
        return maxLength;
    }*/
}