package App.gui;

import App.Book;
import App.Genre;
import App.Status;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReturnBookDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField bookTitle;
    private JTextField bookAuthor;
    private JTextField bookGenre;
    private JTextField bookBraille;
    private Book result;

    public ReturnBookDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public Book getResult() {
        return result;
    }

    private void onOK() {
        // add your code here
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate dueDate = LocalDate.now();
        // add book object from data
        String title = bookTitle.getText();
        String author = bookAuthor.getText();
        String genre = bookGenre.getText().toLowerCase();
        String braille = bookBraille.getText();
        Boolean booleanBraille;
        if (braille.equalsIgnoreCase("yes")) {
            booleanBraille = true;
        }
        else {
            booleanBraille = false;
        }
        //set assign book object to result member variable
        result = new Book(title, author, dueDate, booleanBraille, Status.ON_SHELF, Genre.getEnumVersion(genre));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
