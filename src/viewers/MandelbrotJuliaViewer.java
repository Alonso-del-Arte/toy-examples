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
package viewers;

import clipboardops.ImageSelection;
import fileops.FileChooserWithOverwriteGuard;
import fileops.PNGFileFilter;
import numerics.ComplexNumber;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public final class MandelbrotJuliaViewer extends JPanel
        implements ActionListener, MouseListener, MouseMotionListener {

    private static final boolean MAC_OS_FLAG = System.getProperty("os.name")
            .equals("Mac OS X");

    private static int maskCtrlCommand;

    /**
     * The complex number 0.0 + 0.0<i>i</i>.
     */
    private static final ComplexNumber ZERO = new ComplexNumber(0.0, 0.0);

    private static final int BASELINE_READOUT_FIELD_COLUMNS = 20;

    private static final int DEFAULT_HORIZ_MAX = 1080;

    private static final int DEFAULT_VERTIC_MAX = 720;

    private int maxX, maxY;

    private int mousePressedX, mousePressedY;

    private static final ComplexNumber DEFAULT_MANDELBROT_TOP_LEFT_CORNER
            = new ComplexNumber(-2.75, -1.25);

    private static final ComplexNumber DEFAULT_JULIA_TOP_LEFT_CORNER
            = new ComplexNumber(-2.0, -1.25);

    private ComplexNumber mandelbrotTopLeftCorner;

    private ComplexNumber juliaTopLeftCorner;

    private ComplexNumber topLeftCorner;

    private static final int DEFAULT_PIXELS_PER_UNIT_INTERVAL = 256;

    private static final int MINIMUM_PIXELS_PER_UNIT_INTERVAL = 2;

    private static final int MAXIMUM_PIXELS_PER_UNIT_INTERVAL = 131072;

    private int pixelsPerUnitInterval;

    private int prevMandelPxui;

    private static final int DEFAULT_ITERATION_MAXIMUM = 256;

    private int iterMax = DEFAULT_ITERATION_MAXIMUM;

    private ComplexNumber currPoint;

    private ComplexNumber juliaPoint;

    private boolean juliaFlag;

    private JFrame frame;

    private JTextField readoutRe, readoutIm;

    private JMenuItem zoomInMenuItem, zoomOutMenuItem;

    private JCheckBoxMenuItem toggleReadoutsEnabled;

    private boolean haveSavedBefore = false;

    private String prevSavePathname;

    public static final String PROGRAM_NAME = "Mandelbrot/Julia Set Viewer";

    public static final String VERSION_ID = "Version 0.1";

    public static final String COPYRIGHT_YEAR_AND_NAME = " 2021 Alonso del Arte";

    public static final String ABOUT_BOX_MSG = PROGRAM_NAME + "\n" + VERSION_ID
            + "\n\u00A9" + COPYRIGHT_YEAR_AND_NAME;

    public static final String ABOUT_BOX_MSG_ASCII = PROGRAM_NAME + "\n"
            + VERSION_ID + "\n(c)" + COPYRIGHT_YEAR_AND_NAME;

    private ComplexNumber getRelativeNumber(int x, int y) {
        double re = (double) x / this.pixelsPerUnitInterval;
        double im = (double) y / this.pixelsPerUnitInterval;
        return new ComplexNumber(re, im);
    }

    private ComplexNumber getNumber(int x, int y) {
        ComplexNumber offset = this.getRelativeNumber(x, y);
        return this.topLeftCorner.plus(offset);
    }

    private Color chooseColor(int iterationCount) {
        int adjustedCount = iterationCount % DEFAULT_ITERATION_MAXIMUM;
        int r = (adjustedCount % 170) * 65536;
        int g = (adjustedCount % 85) * 256;
        int b = adjustedCount * 16;
        int rgb = r + g + b;
        return new Color(rgb);
    }

    private Color iteratePointJulia(ComplexNumber c) {
        int iterCount = 0;
        ComplexNumber z = c;
        while (z.abs() < 2.0 && iterCount < this.iterMax) {
            z = z.times(z).plus(this.juliaPoint);
            iterCount++;
        }
        return this.chooseColor(iterCount);
    }

    private void drawJulia(Graphics g) {
        ComplexNumber curr;
        Color color;
        for (int x = 0; x < this.maxX; x++) {
            for (int y = 0; y < this.maxY; y++) {
                curr = this.getNumber(x, y);
                color = this.iteratePointJulia(curr);
                g.setColor(color);
                g.drawRect(x, y, 1, 1);
            }
        }
    }

    private Color iteratePointMandel(ComplexNumber c) {
        int iterCount = 0;
        ComplexNumber z = c;
        while (z.abs() < 2.0 && iterCount < this.iterMax) {
            z = z.times(z).plus(c);
            iterCount++;
        }
        return this.chooseColor(iterCount);
    }

    private void drawMandelbrot(Graphics g) {
        ComplexNumber curr;
        Color color;
        for (int x = 0; x < this.maxX; x++) {
            for (int y = 0; y < this.maxY; y++) {
                curr = this.getNumber(x, y);
                color = this.iteratePointMandel(curr);
                g.setColor(color);
                g.drawRect(x, y, 1, 1);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.juliaFlag) {
            this.drawJulia(g);
        } else {
            this.drawMandelbrot(g);
        }
    }

    private void saveDiagramAs() {
        BufferedImage diagram = new BufferedImage(this.maxX,
                this.maxY, BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = diagram.createGraphics();
        this.paint(graph);
        String suggestedFilename;
        if (this.juliaFlag) {
            String pointStr = this.juliaPoint.toString().replace("\u2212", "n")
                    .replace(".", "d").replace("+", "p");
            suggestedFilename = "Julia Set " + pointStr + " pxui"
                    + this.pixelsPerUnitInterval + ".png";
        } else {
            suggestedFilename = "Mandelbrot Set pxui"
                    + this.pixelsPerUnitInterval + ".png";
        }
        File diagramFile = new File(suggestedFilename);
        FileChooserWithOverwriteGuard fileChooser
                = new FileChooserWithOverwriteGuard();
        FileFilter pngFilter = new PNGFileFilter();
        fileChooser.addChoosableFileFilter(pngFilter);
        if (this.haveSavedBefore) {
            fileChooser.setCurrentDirectory(new File(prevSavePathname));
        }
        fileChooser.setSelectedFile(diagramFile);
        int fcRet = fileChooser.showSaveDialog(this);
        String msg;
        switch (fcRet) {
            case JFileChooser.APPROVE_OPTION:
                diagramFile = fileChooser.getSelectedFile();
                String filePath = diagramFile.getAbsolutePath();
                prevSavePathname = filePath.substring(0,
                        filePath.lastIndexOf(File.separator));
                haveSavedBefore = true;
                try {
                    ImageIO.write(diagram, "PNG", diagramFile);
                } catch (IOException ioe) {
                    msg = "Image input/output exception occurred:\n "
                            + ioe.getMessage();
                    JOptionPane.showMessageDialog(this.frame, msg);
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                msg = "File save canceled";
                JOptionPane.showMessageDialog(this.frame, msg);
                break;
            case JFileChooser.ERROR_OPTION:
                msg = "An error occurred trying to choose a file to save to";
                JOptionPane.showMessageDialog(this.frame, msg);
                break;
            default:
                msg = "Unexpected option " + fcRet + " from file chooser";
                JOptionPane.showMessageDialog(this.frame, msg);
        }
    }

    private void copyReadoutsToClipboard() {
        String readouts = this.frame.getTitle() + "\n"
                + this.currPoint.toASCIIString();
        StringSelection strSel = new StringSelection(readouts);
        this.getToolkit().getSystemClipboard().setContents(strSel, strSel);
    }

    private void copyDiagramToClipboard() {
        BufferedImage diagram = new BufferedImage(this.maxX, this.maxY,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = diagram.createGraphics();
        this.paint(graph);
        ImageSelection imgSel = new ImageSelection(diagram);
        this.getToolkit().getSystemClipboard().setContents(imgSel, imgSel);
    }

    private void toggleJuliaFlag() {
        this.juliaFlag = !this.juliaFlag;
        if (this.juliaFlag) {
            String text = "Julia set for " + this.juliaPoint.toString();
            this.frame.setTitle(text);
            this.prevMandelPxui = this.pixelsPerUnitInterval;
            this.pixelsPerUnitInterval = DEFAULT_PIXELS_PER_UNIT_INTERVAL;
            this.mandelbrotTopLeftCorner = this.topLeftCorner;
            this.topLeftCorner = this.juliaTopLeftCorner;
        } else {
            this.frame.setTitle("Mandelbrot set");
            this.pixelsPerUnitInterval = this.prevMandelPxui;
            this.topLeftCorner = this.juliaTopLeftCorner;
            this.topLeftCorner = this.mandelbrotTopLeftCorner;
        }
        this.checkZoomMenuEnablements();
        this.repaint();
        this.checkIterationMaximum();
    }

    private void checkZoomMenuEnablements() {
        boolean zoomInAllowed
                = this.pixelsPerUnitInterval < MAXIMUM_PIXELS_PER_UNIT_INTERVAL;
        this.zoomInMenuItem.setEnabled(zoomInAllowed);
        boolean zoomOutAllowed
                = this.pixelsPerUnitInterval > MINIMUM_PIXELS_PER_UNIT_INTERVAL;
        this.zoomOutMenuItem.setEnabled(zoomOutAllowed);
    }

    private void checkIterationMaximum() {
        if (this.pixelsPerUnitInterval < 32768) {
            this.iterMax = DEFAULT_ITERATION_MAXIMUM;
        } else {
            this.iterMax = 2048;
        }
    }

    private void zoomIn() {
        int replacementX = this.maxX / 4;
        int replacementY = this.maxY / 4;
        this.topLeftCorner = this.getNumber(replacementX, replacementY);
        this.pixelsPerUnitInterval *= 2;
        this.repaint();
        this.checkZoomMenuEnablements();
        this.checkIterationMaximum();
    }

    private void zoomOut() {
        int replacementX = this.maxX / 4;
        int replacementY = this.maxY / 4;
        this.topLeftCorner = this.getNumber(replacementX, replacementY);
        this.pixelsPerUnitInterval /= 2;
        this.repaint();
        this.checkZoomMenuEnablements();
        this.checkIterationMaximum();
    }

    private void setToggleReadoutsEnabled() {
        if (this.toggleReadoutsEnabled.isSelected()) {
            this.addMouseMotionListener(this);
        } else {
            this.removeMouseMotionListener(this);
        }
    }

    private void showAboutBox() {
        String title = "About";
        JOptionPane.showMessageDialog(this.frame, ABOUT_BOX_MSG, title,
                JOptionPane.PLAIN_MESSAGE);
        System.out.println(ABOUT_BOX_MSG_ASCII);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();
        switch (cmd) {
            case "saveDiagramAs":
                this.saveDiagramAs();
                break;
            case "close":
                this.frame.dispose();
                break;
            case "exit":
                System.exit(0);
                break;
            case "copyReadouts":
                this.copyReadoutsToClipboard();
                break;
            case "copyDiagram":
                this.copyDiagramToClipboard();
                break;
            case "mjToggle":
                this.toggleJuliaFlag();
                break;
            case "zoomIn":
                this.zoomIn();
                break;
            case "zoomOut":
                this.zoomOut();
                break;
            case "decreaseZoomStep":
//                this.decreaseZoomStep();
                break;
            case "increaseZoomStep":
//                this.increaseZoomStep();
                break;
            case "toggleReadOuts":
                this.setToggleReadoutsEnabled();
                break;
            case "showUserManual":
//                this.showUserManual();
                break;
            case "about":
                this.showAboutBox();
                break;
            default:
                System.err.println("Command " + cmd + " not recognized");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //
    }

    @Override
    public void mousePressed(MouseEvent me) {
        this.mousePressedX = me.getX();
        this.mousePressedY = me.getY();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        int mouseReleasedX = me.getX();
        int mouseReleasedY = me.getY();
        if (this.mousePressedX != mouseReleasedX
                || this.mousePressedY != mouseReleasedY) {
            int x = this.mousePressedX - mouseReleasedX;
            int y = this.mousePressedY - mouseReleasedY;
            ComplexNumber offset = this.getRelativeNumber(x, y);
            this.topLeftCorner = this.topLeftCorner.plus(offset);
            this.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        this.currPoint = this.getNumber(me.getX(), me.getY());
        this.readoutRe.setText(Double.toString(this.currPoint.getRealPart()));
        this.readoutIm.setText(Double.toString(this.currPoint.getImagPart()));
        if (!juliaFlag) this.juliaPoint = this.currPoint;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //
    }

    private JMenuItem makeMenuItem(String menuItemText,
                                   String accessibleDescription,
                                   String actionCommand,
                                   KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(menuItemText);
        menuItem.getAccessibleContext()
                .setAccessibleDescription(accessibleDescription);
        menuItem.setActionCommand(actionCommand);
        menuItem.setAccelerator(accelerator);
        menuItem.addActionListener(this);
        return menuItem;
    }

    private JMenu makeFileMenu() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext()
                .setAccessibleDescription("Menu for file operations");
        String accDescr = "Save currently displayed diagram to a PNG file";
        KeyStroke accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S,
                maskCtrlCommand + Event.SHIFT_MASK);
        JMenuItem menuItem = this.makeMenuItem("Save diagram as...",
                accDescr, "saveDiagramAs", accelerator);
        menu.add(menuItem);
        menu.addSeparator();
        accDescr = "Close the window";
        accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_W, maskCtrlCommand);
        menuItem = this.makeMenuItem("Close", accDescr, "close", accelerator);
        menu.add(menuItem);
        if (!MAC_OS_FLAG) {
            accDescr = "Exit the program";
            accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_Q, maskCtrlCommand);
            menuItem = this.makeMenuItem("Exit", accDescr, "exit", accelerator);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenu makeEditMenu() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menu.getAccessibleContext()
                .setAccessibleDescription("Menu to change certain parameters");
        JMenuItem menuItem;
        String accDescr;
        KeyStroke accelerator;
        accDescr = "Copy the readouts to the clipboard";
        accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_C, maskCtrlCommand
                + Event.SHIFT_MASK);
        menuItem = this.makeMenuItem("Copy readouts to clipboard", accDescr,
                "copyReadouts", accelerator);
        menu.add(menuItem);
        accDescr = "Copy the currently displayed diagram to the clipboard";
        accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_C, maskCtrlCommand
                + Event.ALT_MASK);
        menuItem = this.makeMenuItem("Copy diagram to clipboard", accDescr,
                "copyDiagram", accelerator);
        menu.add(menuItem);
        return menu;
    }

    private JMenu makeViewMenu() {
        JMenu menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext()
                .setAccessibleDescription("Menu to zoom in or zoom out");
        String accDescr = "Toggle between Mandelbrot set and Julia set display";
        KeyStroke accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_J, 0);
        JMenuItem menuItem = this.makeMenuItem("Toggle Mandelbrot/Julia",
                accDescr, "mjToggle", accelerator);
        menu.add(menuItem);
        menu.addSeparator();
        accDescr = "Zoom in, by increasing pixels per unit interval";
        if (MAC_OS_FLAG) {
            accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,
                    Event.SHIFT_MASK);
        } else {
            accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_ADD,
                    Event.CTRL_MASK);
        }
        menuItem = this.makeMenuItem("Zoom in", accDescr, "zoomIn",
                accelerator);
        this.zoomInMenuItem = menu.add(menuItem);
        if (this.pixelsPerUnitInterval >= MAXIMUM_PIXELS_PER_UNIT_INTERVAL) {
            this.zoomInMenuItem.setEnabled(false);
        }
        accDescr = "Zoom out, by decreasing pixels per unit interval";
        if (MAC_OS_FLAG) {
            accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0);
        } else {
            accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT,
                    Event.CTRL_MASK);
        }
        menuItem = this.makeMenuItem("Zoom out", accDescr, "zoomOut",
                accelerator);
        this.zoomOutMenuItem = menu.add(menuItem);
        if (this.pixelsPerUnitInterval <= MINIMUM_PIXELS_PER_UNIT_INTERVAL) {
            this.zoomOutMenuItem.setEnabled(false);
        }
        menu.addSeparator();
        this.toggleReadoutsEnabled = new JCheckBoxMenuItem("Update readouts",
                true);
        this.toggleReadoutsEnabled.getAccessibleContext()
                .setAccessibleDescription("Toggle whether the are updated as the mouse moves");
        this.toggleReadoutsEnabled.setActionCommand("toggleReadOuts");
        if (MAC_OS_FLAG) {
            this.toggleReadoutsEnabled.setAccelerator(KeyStroke
                    .getKeyStroke(KeyEvent.VK_R, 0));
        } else {
            this.toggleReadoutsEnabled.setAccelerator(KeyStroke
                    .getKeyStroke(KeyEvent.VK_F2, 0));
        }
        this.toggleReadoutsEnabled.addActionListener(this);
        menu.add(this.toggleReadoutsEnabled);
        return menu;
    }

    private JMenu makeHelpMenu() {
        JMenu menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.getAccessibleContext()
                .setAccessibleDescription("Menu to provide help and documentation");
        String accDescr = "Use default Web browser to show user manual";
        JMenuItem menuItem = this.makeMenuItem("User Manual...", accDescr,
                "showUserManual", null);
        menu.add(menuItem);
        accDescr = "Display information about this program";
        menuItem = this.makeMenuItem("About...", accDescr, "about", null);
        menu.add(menuItem);
        return menu;
    }

    private JMenuBar setUpMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(this.makeFileMenu());
        menuBar.add(this.makeEditMenu());
        menuBar.add(this.makeViewMenu());
        menuBar.add(this.makeHelpMenu());
        return menuBar;
    }

    private JPanel setUpReadOuts() {
        JPanel readOutsPane = new JPanel();
        readOutsPane.add(new JLabel("Re: "));
        this.readoutRe = new JTextField(BASELINE_READOUT_FIELD_COLUMNS);
        this.readoutRe.setText(Double.toString(this.currPoint.getRealPart()));
        this.readoutRe.setEditable(false);
        readOutsPane.add(this.readoutRe);
        readOutsPane.add(new JLabel(" Im: "));
        this.readoutIm = new JTextField(BASELINE_READOUT_FIELD_COLUMNS);
        this.readoutIm.setText(Double.toString(this.currPoint.getImagPart()));
        this.readoutIm.setEditable(false);
        readOutsPane.add(this.readoutIm);
        return readOutsPane;
    }

    private void setUpFrame() {
        if (MAC_OS_FLAG) {
            maskCtrlCommand = Event.META_MASK;
        } else {
            maskCtrlCommand = Event.CTRL_MASK;
        }
        this.setBackground(Color.BLACK);
        Dimension dimension = new Dimension(DEFAULT_HORIZ_MAX,
                DEFAULT_VERTIC_MAX);
        this.setPreferredSize(dimension);
        this.maxX = DEFAULT_HORIZ_MAX;
        this.maxY = DEFAULT_VERTIC_MAX;
        this.mandelbrotTopLeftCorner = DEFAULT_MANDELBROT_TOP_LEFT_CORNER;
        this.juliaTopLeftCorner = DEFAULT_JULIA_TOP_LEFT_CORNER;
        this.pixelsPerUnitInterval = DEFAULT_PIXELS_PER_UNIT_INTERVAL;
        this.prevMandelPxui = this.pixelsPerUnitInterval;
        String text = "Mandelbrot set";
        if (this.juliaFlag) {
            text = "Julia set for " + this.juliaPoint.toString();
            this.topLeftCorner = this.juliaTopLeftCorner;
        } else {
            this.topLeftCorner = this.mandelbrotTopLeftCorner;
        }
        this.frame = new JFrame(text);
        this.frame.setJMenuBar(this.setUpMenuBar());
        this.frame.add(this, BorderLayout.CENTER);
        this.frame.add(this.setUpReadOuts(), BorderLayout.PAGE_END);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public MandelbrotJuliaViewer() {
        this(ZERO, false);
    }

    public MandelbrotJuliaViewer(ComplexNumber point, boolean showJulia) {
        this.currPoint = point;
        this.juliaPoint = this.currPoint;
        this.juliaFlag = showJulia;
    }

    public static void main(String[] args) {
        MandelbrotJuliaViewer viewer = new MandelbrotJuliaViewer();
        viewer.setUpFrame();
    }

}
