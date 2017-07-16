package App.gui;

import App.Book;
import App.Status;

import javax.swing.*;
import java.awt.*;

/**
 * Created by aibar on 7/15/2017.
 */
public class ItemRender extends JLabel implements ListCellRenderer<Book> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Book> list, Book book, int index, boolean isSelected, boolean cellHasFocus) {

        String rowHTML = "";
        String braille = "no";
        if (book.getBraille()) {
            braille = "Yes";
            rowHTML = getString(book, rowHTML, braille);

        }
        else {
            braille = "No";
            rowHTML = getString(book, rowHTML, braille);
        }
        setText(rowHTML);
        return this;
    }

    private String getString(Book book, String rowHTML, String braille) {
        if (!(book.getStatus() == Status.CHECKED_OUT)) {
            rowHTML = "<html><ul>\n" +
                    "<li>\n" + "<h3>" + book.getTitle() + "</h3>\n" +
                    "<p><strong>Author: </strong>" + book.getAuthor() +
                    "<br><strong>Status: </strong>" + book.getStatus() +
                    "<br><strong>Genre: </strong>" + book.getGenre() +
                    "<br><strong>Braille: </strong>" + braille +
                    "</li></ul>\n";
        } else if (book.getStatus() == Status.CHECKED_OUT) {
            rowHTML = "<html><ul>\n" +
                    "<li>\n" + "<h3>" + book.getTitle() + "</h3>\n" +
                    "<p><strong>Author: </strong>" + book.getAuthor() +
                    "<br><strong>Status: </strong>" + book.getStatus() +
                    "<br><strong>Genre: </strong>" + book.getGenre() +
                    "<br><strong>Braille: </strong>" + braille +
                    "<br><strong>Due Date: </strong>" + book.getDueDate() +
                    "</li></ul>\n";
        }
        return rowHTML;
    }
}
