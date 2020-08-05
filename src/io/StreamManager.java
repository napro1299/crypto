package io;

import crypto.Group;

import java.io.*;

public class StreamManager {

    private File in;
    private File out;
    private Group group;
    private int blockSize = 0;
    private BufferedInputStream inStream;
    private BufferedOutputStream outStream;
    private boolean linkedFilesChanged;
    public static final int DEFAULT_BLOCK_SIZE = 20;
    public static final int MAX_BLOCK_SIZE = 2147483639;

    public StreamManager(File in, File out, Group group) {
        this.in = in;
        this.out = out;
        this.group = group;
        this.linkedFilesChanged = false;
    }

    public StreamManager(File in, File out, Group group, int blockSize) {
        this(in, out, group);
        if (blockSize <= 0) {
            throw new IllegalArgumentException("Block size <= 0");
        } else if (blockSize < MAX_BLOCK_SIZE) {
            throw new IllegalArgumentException("Block size > max size");
        } else {
            this.blockSize = blockSize;
        }
    }

    public StreamManager executeGroup() {
        if (group.isBlock()) {
            executeBlockGroup();
        } else {
            executeStreamGroup();
        }

        return this;
    }

    private void executeBlockGroup() {
        this.ensureCorrectStreams();

        try {
            byte[] buffer;
            if (this.blockSize == 0) {
                buffer = new byte[DEFAULT_BLOCK_SIZE];
            } else {
                buffer = new byte[this.blockSize];
            }

            int off = -1;
            int b;

            while (inStream.available() != 0) {
                off++;
                if (off > buffer.length) {
                    outStream.write(this.group.encryptAllBlocks(buffer));
                    off = 0;
                }
                else
                    off++;
                b = inStream.read();
                buffer[off] = (byte) b;
            }

            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeStreamGroup() {
        this.ensureCorrectStreams();

        try {
            for (int i = 0; i < this.group.length(); i++) {
                while (inStream.available() != 0) {
                    outStream.write(this.group.encryptAllStreams((byte) inStream.read()));
                }
                this.group.finished();
            }

            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureCorrectStreams() {
        if ((this.inStream == null || this.outStream == null) || (this.linkedFilesChanged)) {
            try {
                this.inStream = new BufferedInputStream(new FileInputStream(in));
                this.outStream = new BufferedOutputStream(new FileOutputStream(out));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public StreamManager changeIn(File in) {
        this.in = in;
        this.linkedFilesChanged = true;
        return this;
    }

    public StreamManager changeOut(File out) {
        this.out = out;
        this.linkedFilesChanged = true;
        return this;
    }

    public StreamManager swap() {
        if (this.in != null && this.out != null) {
            this.in = out;
            this.out = in;
            this.linkedFilesChanged = true;
        } else {
            throw new NullPointerException("in and out files are null");
        }

        return this;
    }

}
