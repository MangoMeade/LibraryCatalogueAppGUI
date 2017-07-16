import java.io.*;

/**
 * Created by jenny on 7/12/2017.
 */

//CatalogueTextFile was replaced by TextFileReaderWriter:

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
            writer.write(userInput + "  ");
            writer.write("\n");
            System.out.println();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}