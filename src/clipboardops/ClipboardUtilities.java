package clipboardops;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

public class ClipboardUtilities {

    // TODO: Write tests for this
    public static DataFlavor findPrimaryFlavor(DataFlavor[] flavors) {
        return DataFlavor.getTextPlainUnicodeFlavor();
    }

}
