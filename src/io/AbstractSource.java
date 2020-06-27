package io;

import java.nio.ByteBuffer;

public abstract class AbstractSource implements Source {

    protected ByteBuffer buf;

    protected AbstractSource() {

    }

    @Override
    public void lock(boolean locked) {
        if (locked)
            this.buf.limit(0);
        else
            this.buf.limit(this.buf.position());
    }

    @Override
    public int read(TransitoryInputStream in) {
        return 0;
    }

    @Override
    public int write() {
        return 0;
    }
}
