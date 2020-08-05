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
        this.executeGroup(false);
        return this;
    }

    public StreamManager executeGroup(boolean decrypt) {
        if (group.isBlock()) {
            executeBlockGroup(decrypt);
        } else {
            executeStreamGroup(decrypt);
        }

        return this;
    }

    private void executeBlockGroup(boolean decrypt) {
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

            outStream.close();
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeStreamGroup(boolean decrypt) {
        this.ensureCorrectStreams();

        try {
            int data;
            int encrypted;
            int size = inStream.available();
            while ((data = inStream.read()) != -1) {
                if (decrypt)
                    encrypted = this.group.decryptAllStreams((byte) data);
                else
                    encrypted = this.group.encryptAllStreams((byte) data);

                outStream.write(encrypted);
                System.out.print((char) encrypted);
                //this.displayProgress(0, size);
            }

            outStream.close();
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

    private void displayProgress(int pos, int total) {
        int changeMark = total / 15;
        for (int i = 0; i < 15; i++) {
            System.out.println();
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
            File in = this.in;
            File out = this.out;
            this.in = out;
            this.out = in;
            this.linkedFilesChanged = true;
        } else {
            throw new NullPointerException("in and out files are null");
        }

        return this;
    }

}
