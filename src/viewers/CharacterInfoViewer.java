package viewers;

import calculators.TextCalculator;
import clipboardops.ImageSelection;
import fileops.FileChooserWithOverwriteGuard;
import fileops.PNGFileFilter;
import numerics.ComplexNumber;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

public class CharacterInfoViewer extends JFrame implements ActionListener,
        DocumentListener {

    private static final boolean MAC_OS_FLAG = System.getProperty("os.name")
            .equals("Mac OS X");

    private static final int MASK_CTRL_COMMAND = MAC_OS_FLAG
            ? InputEvent.META_MASK : InputEvent.CTRL_MASK;

    String glyphString = "?";

    final JPanel glyphPanel = new GlyphPanel();

    final InfoPanel infoPanel;

    String inputString;

    final JTextField input;

    int codePoint;

    // TODO: Write tests for this
    public String getGlyphString() {
        return this.glyphString;
    }

    public void actionPerformed(ActionEvent event) {
        // TODO: Determine if ActionListener is necessary
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO: Write tests for this
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO: Write tests for this
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO: Write tests for this
    }

    public CharacterInfoViewer(String s) {
        this.glyphString = s.substring(0, 1);
        this.infoPanel = new InfoPanel('?', '?');
        this.input = new JTextField("???");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) {
        CharacterInfoViewer viewer = new CharacterInfoViewer("EXAMPLE");
        viewer.add(viewer.glyphPanel);
        viewer.setVisible(true);
    }

    class GlyphPanel extends JPanel {

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

    final class InfoPanel extends JPanel {

        private static final int DEFAULT_HEIGHT = 200;

        private static final int DEFAULT_WIDTH = 400;

        private JTextField nameField;

        void update() {
            this.nameField.setText(Character.getName(CharacterInfoViewer.this.codePoint));
        }

        InfoPanel(char ch1, char ch2) {
            super(new GridLayout(5, 2));
            this.setBorder(new EmptyBorder(10, 10, 10, 10));
            Dimension dimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            this.setPreferredSize(dimension);
            this.add(new JLabel("Character name: "));
            this.nameField = new JTextField(Character.getName(CharacterInfoViewer.this.codePoint));
            this.add(this.nameField);
            this.add(new JLabel("???: "));
            this.add(new JTextField());
            this.add(new JLabel("???: "));
            this.add(new JTextField());
            this.add(new JLabel("???: "));
            this.add(new JTextField());
            this.add(new JLabel("???: "));
            this.add(new JTextField());
        }

    }

}
