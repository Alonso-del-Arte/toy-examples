/*
 * Copyright (C) 2021 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package clipboardops;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the ImageSelection class.
 * @author Alonso del Arte
 */
class ImageSelectionTest {

    private static BufferedImage img;

    private static ImageSelection imgSel;

    private static TestImagePanel imgWindow;

    private static Clipboard sysClip;

    /**
     * Prints out to the console a list of "data flavors" the system clipboard
     * can give the contents in. If there is {@link DataFlavor#stringFlavor},
     * the String will also appear in the console output.
     */
    private static void reportClip() {
        DataFlavor[] currFlavors = sysClip.getAvailableDataFlavors();
        for (DataFlavor flavor : currFlavors) {
            System.out.print("* " + flavor.toString());
            if (flavor.equals(DataFlavor.stringFlavor)) {
                String fromClip;
                try {
                    fromClip = (String) sysClip.getData(DataFlavor.stringFlavor);
                    System.out.print(" --> \"" + fromClip + "\"");
                } catch (UnsupportedFlavorException ufe) {
                    System.out.println("DataFlavor.stringFlavor not supported");
                    System.out.println(ufe.getMessage());
                } catch (IOException ioe) {
                    System.out.println("IOException reading from clipboard");
                    System.out.println(ioe.getMessage());
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Queries the system clipboard prior to the tests, sets up an image to use
     * in the tests.
     */
    @BeforeAll
    public static void setUpClass() {
        imgWindow = new TestImagePanel();
        sysClip = imgWindow.getToolkit().getSystemClipboard();
        System.out.println("Clipboard has the following data flavors:");
        reportClip();
        String initClipMsg = "Message from setUpClass()";
        StringSelection strSel = new StringSelection(initClipMsg);
        sysClip.setContents(strSel, strSel);
        img = new BufferedImage(TestImagePanel.PANEL_WIDTH,
                TestImagePanel.PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = img.createGraphics();
        imgWindow.paint(graph);
        imgSel = new ImageSelection(img);
    }

    /**
     * Puts the test instance of ImageSelection into the system clipboard.
     */
    @BeforeEach
    void setUp() {
        sysClip.setContents(imgSel, imgSel);
    }

    /**
     * Test of the getTransferDataFlavors function, of class ImageSelection.
     */
    @Test
    void testGetTransferDataFlavors() {
        System.out.println("getTransferDataFlavors");
        DataFlavor[] expResult = {DataFlavor.imageFlavor};
        DataFlavor[] result = imgSel.getTransferDataFlavors();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of the isDataFlavorSupported function, of class ImageSelection. Only
     * {@link DataFlavor#imageFlavor} should register as supported, the others
     * should not.
     */
    @Test
    void testIsDataFlavorSupported() {
        System.out.println("isDataFlavorSupported");
        assert imgSel.isDataFlavorSupported(DataFlavor.imageFlavor);
        assert !imgSel.isDataFlavorSupported(DataFlavor.allHtmlFlavor);
        assert !imgSel.isDataFlavorSupported(DataFlavor.fragmentHtmlFlavor);
        assert !imgSel.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
        assert !imgSel.isDataFlavorSupported(DataFlavor.selectionHtmlFlavor);
        assert !imgSel.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    /**
     * Test of the getTransferData function, of class ImageSelection.
     */
    @Test
    void testGetTransferData() {
        System.out.println("getTransferData");
        Object data;
        try {
            data = imgSel.getTransferData(DataFlavor.imageFlavor);
            assertEquals(img, data);
        } catch (UnsupportedFlavorException ufe) {
            String msg = "Getting data should not have caused exception";
            System.out.println(msg);
            System.out.println("\"" + ufe.getMessage() + "\"");
            fail(msg);
        }
    }

    /**
     * Test of the hasOwnership function, of class ImageSelection.
     */
    @Test
    void testHasOwnership() {
        System.out.println("hasOwnership");
        String preMsg = "ImageSelection should own clipboard at this point";
        assert imgSel.hasOwnership() : preMsg;
        String testClipMsg = "This message was placed by testHasOwnership()";
        StringSelection strSel = new StringSelection(testClipMsg);
        sysClip.setContents(strSel, strSel);
        imgSel.lostOwnership(sysClip, strSel);
        String postMsg = "ImageSelection should not own clipboard at this point";
        assert !imgSel.hasOwnership() : postMsg;
    }

    /**
     * Reports on the contents of the clipboard after each test.
     */
    @AfterEach
    void tearDown() {
        System.out.println("Right now the clipboard has the following data flavors:");
        reportClip();
    }

    /**
     * Reports on the contents of the clipboard after running the tests. Also
     * makes sure to close the window with the test image.
     */
    @AfterAll
    public static void tearDownClass() {
        System.out.println("After the tests, the clipboard has the following data flavors:");
        reportClip();
        imgWindow.closePanel();
    }

}
