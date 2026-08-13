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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import retail.books.ISBN;

class BookInfoViewerTest {

    public static final ISBN DEFAULT_ISBN = ISBN.parseISBN("978-0-00-000000-2");

    @Test
    void testDefaultCloseOperation() {
        JFrame instance = new BookInfoViewer(DEFAULT_ISBN);
        int expected = WindowConstants.EXIT_ON_CLOSE;
        int actual = instance.getDefaultCloseOperation();
        String message = "Default close operation should be exit on close";
        assertEquals(expected, actual, message);
    }

    @Test
    void testDefaultCloseOperationAuxConstructor() {
        JFrame instance = new BookInfoViewer();
        int expected = WindowConstants.EXIT_ON_CLOSE;
        int actual = instance.getDefaultCloseOperation();
        String message = "Default close operation should be exit on close";
        assertEquals(expected, actual, message);
    }

}
