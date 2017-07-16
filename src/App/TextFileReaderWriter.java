package App;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by jenny on 7/12/2017.
 */
public class TextFileReaderWriter {

    //Read from file functions:

    public ArrayList<Book> readFromCatalogue() {

        ArrayList<Book> catalogue = new ArrayList<>();

        try {
            FileReader reader = new FileReader("catalogue.txt");
            BufferedReader buffReader = new BufferedReader(reader);

            String line = null;

            while ((line = buffReader.readLine()) != null) {

                String[] bookAttributes = line.split(",");

                catalogue.add(convertToBook(bookAttributes));

            }

            reader.close();
            buffReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return catalogue;
    }

    //Convert to 1 book at a time:
    private Book convertToBook(String[] bookAttributes) {

        Book book = new Book();

        book.setTitle(bookAttributes[0]);

        book.setAuthor(bookAttributes[1]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dueDate = LocalDate.parse(bookAttributes[2], formatter);
        book.setDueDate(dueDate);

        Boolean braille = new Boolean(bookAttributes[3]);
        book.setBraille(braille);

        book.setStatus(Status.getEnumVersion(bookAttributes[4]));

        book.setGenre(Genre.getEnumVersion((bookAttributes[5]).toString().toLowerCase()));
        return book;
    }

    //Write to file functions:

    public void fileWriter(ArrayList<Book> catalogue) {
        try {
            //If the test.txt file does not exist, FileWriter will create it
            FileWriter catalogueFileWriter = new FileWriter("catalogue.txt", false);


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

    public void fileWriter(Book book) {
        try {
            FileWriter bookFileWriter = new FileWriter("catalogue.txt", true);

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