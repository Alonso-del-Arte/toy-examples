package mock.io;

import java.io.PrintStream;

/**
 * Mocks a <code>java.io.PrintStream</code> for use with an {@link
 * OutputInterceptor}. Only a constructor is defined in this subclass of
 * <code>PrintStream</code>.
 * @author Alonso del Arte
 */
public class MockPrintStream extends PrintStream {

    public MockPrintStream(OutputInterceptor interceptor) {
        super(interceptor);
    }

}
