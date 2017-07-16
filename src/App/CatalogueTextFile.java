package App;

import java.io.*;

/**
 * Created by jenny on 7/12/2017.
 */
public class CatalogueTextFile {


    public static void readFromCatalogue() {
        try {
            FileReader reader = new FileReader("catalogue.txt");
            BufferedReader buffReader = new BufferedReader(new FileReader("catalogue.txt"));

            String line = null;

            System.out.println();
            while ((line = buffReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    System.out.println(line);
                }
            }
            System.out.println();

            reader.close();
            buffReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToCatalogue(String userInput) {
        try {
            //If the test.txt file does not exist, FileWriter will create it
            FileWriter writer = new FileWriter("catalogue.txt", false);
            //BufferedWriter buffWriter = new BufferedWriter(writer);
            writer.write(userInput + "  ");
            writer.write("\n");
            System.out.println();

            //To add a book:
            //writer.write("\n" + Validator.getString("Enter book title: "));
            //System.out.println("This book has been saved!\n");

            writer.close();
            //buffWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}