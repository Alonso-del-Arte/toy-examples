package mock.io;

import java.io.IOException;
import java.io.OutputStream;

public class RelayingOutputInterceptor extends OutputStream {

    @Override
    public void write(int b) throws IOException {
        //
    }

    public RelayingOutputInterceptor(OutputStream relayTarget) {
        //
    }

}
