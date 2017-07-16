package App.gui;

import App.*;
import App.gui.ItemRender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Created by aibar on 7/14/2017.
 */
public class LibraryGui {
    private JButton displayBtn;
    private JPanel base;
    private JLabel logo;
    private JLabel titleTxt;
    private JLabel bannerImg;
    private JList bookList;
    private JButton searchByAuthorBtn;
    private JButton searchByTitleBtn;
    private JButton exitBtn;
    private JButton addItemBtn;
    private JButton checkoutBookBtn;
    private JTextField searchTxt;
    private JButton returnBookBtn;
    private DefaultListModel model = new DefaultListModel();
    private ListCellRenderer itemRenderer = new ItemRender();
    TextFileReaderWriter reader = new TextFileReaderWriter();
    ArrayList<Book> catalogue = reader.readFromCatalogue();
    ReturnImplementation returnImp = new ReturnImplementation();
    CheckoutImplementation checkImp = new CheckoutImplementation();

    public LibraryGui() {
        reader.fileWriter(catalogue);
        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
                for (Book book : catalogue) {
                    model.addElement(book);

                }
                bookList.setCellRenderer(itemRenderer);
                bookList.setModel(model);
            }
        });
        searchByAuthorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchTxt.getText();
                SearchImplementation search = new SearchImplementation();
                model.clear();
                for (Book book : catalogue) {
                    if ((book.getAuthor().toLowerCase()).contains(userInput.toLowerCase())) {
                        model.addElement(search.checkContainsGUI(book, userInput.toLowerCase()));
                    }
                }
                bookList.setCellRenderer(itemRenderer);
                bookList.setModel(model);

            }
        });
        searchByTitleBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchTxt.getText();
                SearchImplementation search = new SearchImplementation();
                model.clear();
                for (Book book : catalogue) {
                    if ((book.getTitle().toLowerCase()).contains(userInput.toLowerCase())) {
                        model.addElement(search.checkContainsGUI(book, userInput.toLowerCase()));
                    }
                    //model.addElement(book.get);
                }
                bookList.setCellRenderer(itemRenderer);
                bookList.setModel(model);

            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
        }
        });
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //show add book
                AddBookDialog addBook = new AddBookDialog();
                addBook.pack();
                addBook.setVisible(true);
                //get book object from add book dialog
                Book result = addBook.getResult();
                reader.fileWriter(result);
                //add book result to text file
            }
        });

        //return item
        //dialog prompts user to tpe the name and author of the book. dialog works similar to the add book dialog.
        //search for the book among the books that are currently checked out and change status to on shelf.
        returnBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnBookDialog returnBook = new ReturnBookDialog();
                returnBook.pack();
                returnBook.setVisible(true);
                Book result = returnBook.getResult();
                for (Book book : catalogue) {
                    returnImp.returnToShelfGUI(result, book);
                }
                reader.fileWriter(catalogue);
            }
        });
        //checkout item
        //dialog prompts user to tpe the name and author of the book. dialog works similar to the add book dialog.
        //search for the book among the books that are currently on shelf and change status to checked out.
        checkoutBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutBookDialog checkoutBook = new CheckoutBookDialog();
                checkoutBook.pack();
                checkoutBook.setVisible(true);
                //checkoutBook
                Book result = checkoutBook.getResult();
                for (Book book : catalogue) {
                    if (result.getStatus() == Status.ON_SHELF) {
                        checkImp.checkOutBookGUI(result, book);
                    }
                }
                reader.fileWriter(catalogue);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LibraryGui");
        frame.setContentPane(new LibraryGui().base);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Custom Initialization Code
    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageIcon logoImg = new ImageIcon("img/logo.jpeg");
        logo = new JLabel(logoImg);
        ImageIcon banner = new ImageIcon("img/banner.jpg");
        bannerImg = new JLabel(banner);

        bookList = new JList();

    }
}
