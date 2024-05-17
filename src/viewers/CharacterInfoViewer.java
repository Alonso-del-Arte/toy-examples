package viewers;

import calculators.TextCalculator;
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

public class CharacterInfoViewer extends JFrame implements ActionListener {

    private static final boolean MAC_OS_FLAG = System.getProperty("os.name")
            .equals("Mac OS X");

    private static final int MASK_CTRL_COMMAND = MAC_OS_FLAG
            ? InputEvent.META_MASK : InputEvent.CTRL_MASK;

    String glyphString = "?";

    private final JPanel glyphPanel = new GlyphPanel();

    // TODO: Write tests for this
    public String getGlyphString() {
        return "SORRY NOT IMPLEMENTED YET";
    }

    public void actionPerformed(ActionEvent event) {
        //
    }

    public CharacterInfoViewer(String s) {
        this.pack();
    }

    public static void main(String[] args) {
        CharacterInfoViewer viewer = new CharacterInfoViewer("EXAMPLE");
        viewer.add(viewer.glyphPanel);
        viewer.setVisible(true);
    }

    private class GlyphPanel extends JPanel {

        private static final int DEFAULT_HEIGHT = 400;

        private static final int DEFAULT_WIDTH = 300;

        public void paintComponent(Graphics g) {
            g.drawString(CharacterInfoViewer.this.glyphString, 100, 100);
        }

        GlyphPanel() {
            Dimension dimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            this.setPreferredSize(dimension);
        }

    }

}
