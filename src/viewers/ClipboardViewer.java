/*
 * Copyright (C) 2022 Alonso del Arte
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

import java.awt.Graphics2D;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class ClipboardViewer extends JPanel implements ActionListener {

    private JFrame frame;

    // TODO: Write tests for this
    public DataFlavor getCurrFlavor() {
        return DataFlavor.stringFlavor;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        switch (command) {
            case "exit":
            case "quit":
                System.exit(0);
                break;
            default:
                System.err.println("Command " + command + " not recognized");
        }
    }

    private JMenu makeFileMenu() {
        JMenu menu = new JMenu("File");
        return menu;
    }

    private JMenuBar makeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(this.makeFileMenu());
        return menuBar;
    }

    public void startDisplay() {
        //
    }

    public ClipboardViewer() {
        //
    }

    public static void main(String[] args) {
        ClipboardViewer viewer = new ClipboardViewer();
        viewer.startDisplay();
    }

}
