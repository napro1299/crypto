package io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public final class FileIO {

    public static void iotest(File file, ByteBuffer srcBuf, TransitoryChannel channel) throws IOException {
        try (FileChannel source = new FileInputStream(file).getChannel()) {
            source.read(srcBuf);
        } finally {
            srcBuf.flip();
        }
    }

    public static void readToByteBuffer(File file, ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new NullPointerException("buffer not allocated");
        }


        try (FileChannel source = new FileInputStream(file).getChannel()) {
            source.read(byteBuffer);
        } finally {
            byteBuffer.flip();
        }
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null)
                source.close();

            if (destination != null)
                destination.close();
        }
    }

}
