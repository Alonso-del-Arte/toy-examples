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
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Image;

/**
 * This is like <code>StringSelection</code>, but for images drawn using AWT and
 * Swing. This class is immutable except for clipboard ownership status.
 * @author Alonso del Arte, based on a tutorial at ProgramCreek.com (the
 * specific page I was looking at seems to have been moved).
 */
public class ImageSelection implements ClipboardOwner, Transferable {

    private final Image img;

    private final DataFlavor[] FLAVOR_ARRAY = {DataFlavor.imageFlavor};

    private boolean clipboardOwnershipFlag;

    /**
     * Gives a list of "data flavors" supported by this class.
     * @return An array containing just one element: {@link
     * DataFlavor#imageFlavor}.
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return this.FLAVOR_ARRAY;
    }

    /**
     * Determines whether a given "data flavor" is supported by this class.
     * @param flavor The flavor for which a determination is needed.
     * @return True if the flavor is {@link DataFlavor#imageFlavor}, false
     * otherwise.
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(this.FLAVOR_ARRAY[0]);
    }

    /**
     * Retrieves the data stored by the instance of this class.
     * @param flavor The expected "data flavor," preferably {@link
     * DataFlavor#imageFlavor}.
     * @return An {@link Image} presented as an {@link Object}.
     * @throws UnsupportedFlavorException Thrown if the flavor is not {@link
     * DataFlavor#imageFlavor}.
     */
    @Override
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException {
        if (!flavor.equals(this.FLAVOR_ARRAY[0])) {
            throw new UnsupportedFlavorException(this.FLAVOR_ARRAY[0]);
        }
        this.clipboardOwnershipFlag = true;
        return img;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        this.clipboardOwnershipFlag = false;
    }

    /**
     * Tells whether an instance of this class has ownership of the clipboard.
     * @return True if the instance does have ownership of the clipboard, false
     * otherwise.
     */
    public boolean hasOwnership() {
        return this.clipboardOwnershipFlag;
    }

    /**
     * Sole constructor.
     * @param image The image to be made available to the system clipboard. This
     * image can't be changed, except by creating a new
     * <code>ImageSelection</code> object.
     */
    public ImageSelection(Image image) {
        this.img = image;
        this.clipboardOwnershipFlag = false;
    }

}
