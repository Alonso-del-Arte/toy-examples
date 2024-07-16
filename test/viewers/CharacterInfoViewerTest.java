package viewers;

import clipboardops.ImageSelection;
import fileops.FileChooserWithOverwriteGuard;
import fileops.PNGFileFilter;
import numerics.ComplexNumber;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.alphanumeric;
import static randomness.ExtendedRandom.nextInt;

class CharacterInfoViewerTest {

    @Test
    void testGetGlyphString() {
        System.out.println("getGlyphString");
        for (char ch = ' '; ch < '\u007F'; ch++) {
            String expected = "" + ch;
            CharacterInfoViewer viewer = new CharacterInfoViewer(expected);
            String actual = viewer.getGlyphString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testGlyphStringChopsOffSecondCharAndLater() {
        int length = nextInt(28) + 2;
        String s = alphanumeric(length);
        CharacterInfoViewer viewer = new CharacterInfoViewer(s);
        String expected = s.substring(0, 1);
        String actual = viewer.getGlyphString();
        assertEquals(expected, actual);
    }

    // TODO: Test glyph string for surrogate pairs

}
