package mock.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This is to be used to intercept content for a
 * <code>java.io.OutputStream</code> and then relay it to that
 * <code>OutputStream</code>.
 * @author Alonso del Arte
 */
public class RelayingOutputInterceptor extends OutputStream {

    private final OutputStream target;

    private final StringBuffer buffer = new StringBuffer();

    /**
     * Writes to this interceptor and relays it to the stream indicated when
     * this interceptor was constructed.
     * @param b The byte to write.
     * @throws IOException If the relay target throws this exception, it will be
     * passed on to this procedure's caller.
     */
    @Override
    public void write(int b) throws IOException {
        this.buffer.append((char) b);
        this.target.write(b);
    }

    /**
     * Retrieves the text that has been written to this interceptor and passed
     * on to the relay target.
     * @return The text that has been written to this interceptor. Should be the
     * empty <code>String</code> if nothing has been sent.
     */
    public String getText() {
        return this.buffer.toString();
    }

    /**
     * Sole constructor.
     * @param relayTarget The stream to which to relay content after
     *                    intercepting it.
     */
    public RelayingOutputInterceptor(OutputStream relayTarget) {
        this.target = relayTarget;
    }

}
