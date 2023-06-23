package clipboardops;

import java.awt.datatransfer.DataFlavor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClipboardUtilitiesTest {

    @Test
    void testFindPrimaryFlavor() {
        System.out.println("findPrimaryFlavor");
        DataFlavor[] flavors = {DataFlavor.stringFlavor, DataFlavor.imageFlavor,
                DataFlavor.stringFlavor};
        DataFlavor flavor = ClipboardUtilities.findPrimaryFlavor(flavors);
        String msg = DataFlavor.imageFlavor.getMimeType()
                + " should be MIME type of " + flavor;
        assert flavor.isMimeTypeEqual(DataFlavor.imageFlavor) : msg;
    }

    @Test
    void testFindPrimaryFlavorString() {
        DataFlavor[] flavors = {DataFlavor.stringFlavor,
                DataFlavor.stringFlavor};
        DataFlavor flavor = ClipboardUtilities.findPrimaryFlavor(flavors);
        String msg = DataFlavor.stringFlavor.getMimeType()
                + " should be MIME type of " + flavor;
        assert flavor.isMimeTypeEqual(DataFlavor.stringFlavor) : msg;
    }

}
