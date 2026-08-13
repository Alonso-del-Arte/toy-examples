package viewers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import retail.books.ISBN;

public class BookInfoViewer extends JFrame {

    private static final String QUERY_PATH_BEGIN
            = "https://openlibrary.org/api/books?bibkeys=ISBN:";

    private static final String QUERY_PATH_END = "&jscmd=details&format=json";

    private static final String USER_AGENT_ID
            = "Book Info Viewer - Java/" + System.getProperty("java.version");

    private static final String JSON_TITLE_TAG = "\"title\":";

    private static final String JSON_AUTHOR_TAG = "\"by_statement\":";

    private static final char STRAIGHT_DOUBLE_QUOTE_CHAR = '"';

    final JTextField numberField = new JTextField("9780000000002", 18);

    final JLabel bookTitle = new JLabel("no book scanned yet");

    final JLabel bookAuthors = new JLabel("no authors");

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //
        }

    }

    public BookInfoViewer() {
        this(ISBN.parseISBN("978-0-00-000000-2"));
    }

    public BookInfoViewer(ISBN isbn) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //
    }

}
