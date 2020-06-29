package io;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class AbstractSource implements Source {

    private boolean locked;

    AbstractSource() {
    }

    @Override
    public void lock() {
        this.locked = true;
    }

    public abstract int read() throws IOException;

    public abstract int write() throws IOException;

    @Override
    public void open() {
        this.locked = false;
    }

    @Override
    public boolean isOpen() {
        return !this.locked;
    }
}
