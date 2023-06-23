package clipboardops;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

public class ClipboardUtilities {

    public static DataFlavor findPrimaryFlavor(DataFlavor[] flavors) {
        for (DataFlavor flavor : flavors) {
            if (flavor.isMimeTypeEqual(DataFlavor.imageFlavor)) {
                return flavor;
            }
        }
        return flavors[0];
    }

}
