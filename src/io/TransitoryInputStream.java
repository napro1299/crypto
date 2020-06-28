package io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Takes source data and pipes it somewhere else
 */
public class TransitoryInputStream extends InputStream {

    private TransitoryChannel channel;
    private TransitorySource src;

    public TransitoryInputStream(TransitorySource src) {
        this.src = src;
        this.channel = new TransitoryChannel();
    }

    @Override
    public int read() throws IOException {
        channel.read(src.buf);
        return 0;
    }

    public TransitoryChannel getChannel() {
        return this.channel;
    }

}
