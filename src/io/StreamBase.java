package io;

import java.io.*;

public abstract class StreamBase {

    protected abstract byte encryptData(byte b);

    protected abstract byte decryptData(byte b);

    /* Temp method */
    public void process() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("sample.txt"));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("end.txt"));
        BufferedOutputStream dec = new BufferedOutputStream(new FileOutputStream("dec.txt"));

        byte b;
        byte enc;

        while (in.available() != 0) {
            b = (byte) in.read();
            enc = this.encryptData(b);
            out.write(enc);
            dec.write(this.decryptData(enc));
        }

        in.close();
        out.close();
        dec.close();
    }

}
