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
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public TransitoryChannel getChannel() {
        return this.channel;
    }

}
