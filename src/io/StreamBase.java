package io;

import crypto.CryptoBase;

import java.io.*;

public abstract class StreamBase {

    protected abstract byte encryptData(byte b);

    /* Temp method */
    public void process() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("sample.txt"));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("end.txt"));
        byte b;

        while (in.available() != 0) {
            b = (byte) in.read();
            out.write(this.encryptData(b));
        }
        in.close();
        out.close();
    }

}
