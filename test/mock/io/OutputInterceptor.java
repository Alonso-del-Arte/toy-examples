package mock.io;

import java.io.OutputStream;

/**
 * Helps intercept output to a <code>java.io.PrintStream</code>.
 * @deprecated For most purposes, <code>java.io.ByteArrayOutputStream</code>
 * should be sufficient.
 * @author Alonso del Arte
 */
@Deprecated
public class OutputInterceptor extends OutputStream {

    private final StringBuffer buffer = new StringBuffer();

    /**
     *
     * @return
     */
    public String getText() {
        return this.buffer.toString();
    }

    @Override
    public void write(int b) {
        this.buffer.append((char) b);
    }

}
