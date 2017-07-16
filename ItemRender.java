package App;


import javax.swing.*;
import java.awt.*;


/**
 * Created by aibar on 7/14/2017.
 */
public class ItemRender extends JLabel implements ListCellRenderer {
//    <Book>
    @Override
    public Component getListCellRendererComponent(JList list, Object book, int index, boolean isSelected, boolean cellHasFocus) {
//        <? extends Book>

        App.Book book1 = new App.Book();
        String title = book1.getTitle();
        setText(title);
//        String author = book.getAuthor();
//
//
//        String rowHTML = "<html><ul>\n" +
//                "<li>\n" + "<h3>" + book.getTitle() + "</h3>\n" +
//                "<p><strong>Author: </strong>" + book.getAuthor() +
//                "<br><strong>Status: </strong>" + book.getStatus() +
//                "<br><strong>Genre: </strong>" + book.getGenre() + "</li></ul>\n";
//        setText(rowHTML);

        return this;
    }
}
