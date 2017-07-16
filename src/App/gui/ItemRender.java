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
        if (!(book.getStatus()== Status.CHECKED_OUT)) {
            rowHTML = "<html><ul>\n" +
                    "<li>\n" + "<h3>" + book.getTitle() + "</h3>\n" +
                    "<p><strong>Author: </strong>" + book.getAuthor() +
                    "<br><strong>Status: </strong>" + book.getStatus() +
                    "<br><strong>Genre: </strong>" + book.getGenre() +
                    "</li></ul>\n";
        }
       else if (book.getStatus()== Status.CHECKED_OUT) {
            rowHTML = "<html><ul>\n" +
                    "<li>\n" + "<h3>" + book.getTitle() + "</h3>\n" +
                   "<p><strong>Author: </strong>" + book.getAuthor() +
                   "<br><strong>Status: </strong>" + book.getStatus() +
                   "<br><strong>Genre: </strong>" + book.getGenre() +
                   "<br><strong>Due Date: </strong>" + book.getDueDate() +
                   "</li></ul>\n";
       }
        setText(rowHTML);
        return this;
    }
}
