package io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channel;

/**
 *
 */
public class TransitoryOutputStream extends OutputStream {

    private TransitoryChannel channel;

    public TransitoryOutputStream() {

    }

    @Override
    public void write(int i) throws IOException {

    }

    public TransitoryChannel getChannel() {
        return this.channel;
    }

}
