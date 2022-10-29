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
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fileops;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * File filter for selecting Portable Network Graphics (PNG) files in a 
 * <code>JFileChooser</code> dialog box.
 * @author Alonso del Arte, based on the tutorial  
 * <a href="www.codejava.net/java-se/swing/add-file-filter-for-jfilechooser-dialog">at 
 * CodeJava</a>.
 */
public class PNGFileFilter extends FileFilter {

    /**
     * Tells <code>JFileChooser</code> whether it should accept or reject a
     * given file.
     * @param file The file to accept or reject.
     * @return True if the filename has the file extension ".png" or ".PNG", 
     * or if it is a directory, false otherwise.
     */
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().toLowerCase().endsWith(".png");
    }

    /**
     * The description of this filter. No internationalization as of yet.
     * @return The text "Portable Network Graphics files (*.png)"
     */
    @Override
    public String getDescription() {
        return "Portable Network Graphics files (*.png)";
    }

}
