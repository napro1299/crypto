package io;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TransitorySource extends AbstractSource {

    private ByteBuffer buf;

    public TransitorySource() {
    }

    @Override
    public int read() {
        return 0;
    }

    @Override
    public int write() {
        return 0;
    }
}
