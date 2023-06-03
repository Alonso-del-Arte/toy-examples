package mock.io;

import java.io.OutputStream;

/**
 * Helps intercept output to a <code>java.io.PrintStream</code>.
 * @author Alonso del Arte
 */
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
