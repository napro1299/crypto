package io;

import java.nio.ByteBuffer;

public abstract class AbstractSource implements Source {

    protected ByteBuffer buf;
    private boolean locked;

    AbstractSource() {

    }

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public int read(TransitoryInputStream in) {
        return 0;
    }

    @Override
    public int write() {
        return 0;
    }

    @Override
    public void open() {
        this.locked = false;
    }

    @Override
    public boolean isOpen() {
        return !this.locked;
    }
}
