package fileops;

import java.io.File;

import org.junit.jupiter.api.Test;

class PNGFileFilterTest {


    private static final PNGFileFilter FILTER = new PNGFileFilter();

    /**
     * Test of the accept function, of the PNGFileFilter class. The file filter
     * should accept PNG files.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        File file = new File("image.png");
        String msg = "PNGFileFilter should accept " + file.getName();
        System.out.println(msg);
        assert FILTER.accept(file) : msg;
    }

    /**
     * Another test of the accept function, of the PNGFileFilter class. The file
     * filter should accept PNG files, regardless of the case of the extension.
     */
    @Test
    public void testAcceptUpperCaseExtension() {
        File file = new File("image.PNG");
        String msg = "PNGFileFilter should accept " + file.getName();
        System.out.println(msg);
        assert FILTER.accept(file) : msg;
    }

    /**
     * Another test of the accept function, of the PNGFileFilter class. The file
     * filter should accept PNG files and reject all other files, even if they
     * are graphics files like JPEG.
     */
    @Test
    public void testRejectPNG() {
        File file = new File("image.jpeg");
        String msg = "PNGFileFilter should reject " + file.getName();
        System.out.println(msg);
        assert !FILTER.accept(file) : msg;
    }

    /**
     * Another test of the accept function, of the PNGFileFilter class. The file
     * filter should accept PNG files and reject all other files, such as
     * Microsoft Word files.
     */
    @Test
    public void testRejectMicrosoftWordFormat() {
        File file = new File("document.doc");
        String msg = "PNGFileFilter should reject " + file.getName();
        System.out.println(msg);
        assert !FILTER.accept(file) : msg;
    }

    /**
     * Another test of the accept function, of the PNGFileFilter class. The file
     * filter should accept PNG files and also accept directories, because
     * directories might contain PNG files.
     */
    @Test
    public void testAcceptDirectory() {
        String homeDir = System.getProperty("user.home");
        File dir = new File(homeDir);
        String msg = "PNGFileFilter should accept directory " + homeDir;
        System.out.println(msg);
        assert FILTER.accept(dir) : msg;
    }

    /**
     * Test of getDescription method, of class PNGFileFilter. The description
     * provided to JFileChooser should include the file extension *.png or
     *.PNG.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String description = FILTER.getDescription();
        System.out.println("PNGFileFilter description is \"" + description
                + "\"");
        String formatName = "Portable Network Graphics";
        String msg = "Filter description should include \"" + formatName + "\"";
        assert description.contains(formatName) : msg;
        msg = "Filter description should include \"png\" or \"PNG\"";
        assert description.toLowerCase().contains("png") : msg;
    }

}
