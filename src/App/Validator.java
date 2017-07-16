package App;

import java.util.Scanner;

/**
 * Created by jenny on 7/12/2017.
 */

public class Validator {

    private Scanner entry = new Scanner(System.in);

    public String getString(String prompt) {
        System.out.print(prompt);
        String s = entry.nextLine();  // read user entry
        //entry.nextLine();  // discard any other data entered on the line
        return s;
    }

    /*public String getString(String prompt, String invalidPrompt, String valid1, String valid2) {
        boolean isValid = false;
        String s = "";
        while (isValid == false) {
            System.out.print(prompt);
            s = entry.next();  // read user entry
            entry.nextLine();  // discard any other data entered on the line
            if (s.equalsIgnoreCase(valid1) || s.equalsIgnoreCase(valid2)) {
                isValid = true;
            } else {
                System.out.print(invalidPrompt);
            }
        }
        return s;
    }*/

    public String getString(String prompt, String invalidPrompt, String valid1, String valid2, String valid3) {
        boolean isValid = false;
        String s = "";
        while (isValid == false) {
            System.out.print(prompt);
            s = entry.next();  // read user entry
            entry.nextLine();  // discard any other data entered on the line
            if (s.equalsIgnoreCase(valid1) || s.equalsIgnoreCase(valid2) || s.equalsIgnoreCase(valid3)) {
                isValid = true;
            } else {
                System.out.print(invalidPrompt);
            }
        }
        return s;
    }

    public int getInt(String prompt) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print(prompt);
            if (entry.hasNextInt()) {
                i = entry.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            entry.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    public int getInt(String prompt, String invalidPrompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            i = getInt(prompt);
            if (i < min)
                System.out.print(invalidPrompt);
            else if (i > max)
                System.out.print(invalidPrompt);
            else
                isValid = true;
        }
        return i;
    }

    public Boolean getString(String prompt, String invalidPrompt, String valid1, String valid2) {
        String s = "";
        Boolean braille = false;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print(prompt);
            s = entry.next();  // read user entry
            entry.nextLine();  // discard any other data entered on the line
            if (s.equalsIgnoreCase(valid1) || s.equalsIgnoreCase(valid2)) {
                if(s.equalsIgnoreCase("y")) {
                    braille = true;
                } else {
                    braille = false;
                }
                isValid = true;
            } else {
                System.out.print(invalidPrompt);
            }
        }
        return braille;
    }
}