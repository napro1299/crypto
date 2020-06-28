package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;

public class TransitoryChannel extends AbstractInterruptibleChannel implements ByteChannel {

    private ByteBuffer buf;

    public TransitoryChannel() {
    }

    @Override
    public int read(ByteBuffer byteBuffer) throws IOException {
        this.ensureOpen();
        byteBuffer.put(this.buf);
        return byteBuffer.position();
    }

    @Override
    public int write(ByteBuffer byteBuffer) throws IOException {
        this.ensureOpen();
        this.buf.put(byteBuffer);
        return byteBuffer.position();
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
