package mock.io;

import java.io.IOException;
import java.io.OutputStream;

public class RelayingOutputInterceptor extends OutputStream {

    private final OutputStream target;

    @Override
    public void write(int b) throws IOException {
        this.target.write(b);
    }

    // TODO: Write tests for this
    public String getText() {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    public RelayingOutputInterceptor(OutputStream relayTarget) {
        this.target = relayTarget;
    }

}
