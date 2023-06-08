package mock.io;

import java.io.PrintStream;

/**
 * Mocks a <code>java.io.PrintStream</code> for use with an {@link
 * OutputInterceptor}. Only a constructor is defined in this subclass of
 * <code>PrintStream</code>.
 * @deprecated Just use <code>java.io.PrintStream</code>. This unnecessary
 * subclass will be removed in a later commit.
 * @author Alonso del Arte
 */
@Deprecated
public class MockPrintStream extends PrintStream {

    public MockPrintStream(OutputInterceptor interceptor) {
        super(interceptor);
    }

}
