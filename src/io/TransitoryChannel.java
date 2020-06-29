package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;

public class TransitoryChannel extends AbstractInterruptibleChannel implements ByteChannel {

    private TransitorySource src;
    private boolean connected;

    public TransitoryChannel() {
    }

    public TransitoryChannel(TransitorySource src) {
        this.connect(src);
    }

    @Override
    public int read(ByteBuffer byteBuffer) throws IOException {
        if (this.isConnected() && this.src.isOpen()) {
            byteBuffer.put(this.src.read());
        } else {
            throw new IllegalStateException("channel has no source");
        }
    }

    @Override
    public int write(ByteBuffer byteBuffer) throws IOException {

    }

    public void connect(TransitorySource src) {
        if (!connected) {
            this.src = src;
            this.connected = true;
        } else {
            throw new IllegalStateException("already connected");
        }
    }

    public boolean isConnected() {
        return this.connected;
    }

    @Override
    protected void implCloseChannel() throws IOException {
        System.out.println("implCloseChannel");
    }

    private void ensureOpen() throws IOException {
        if (!this.isOpen())
            throw new ClosedChannelException();
    }

}
