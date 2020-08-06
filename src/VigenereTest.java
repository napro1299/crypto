import crypto.algorithms.VigenereCrypto;

import java.io.*;

public class VigenereTest {

    public static void main(String[] args) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File("plaintext.txt")));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("encrypted.txt")));
        BufferedOutputStream dec = new BufferedOutputStream(new FileOutputStream(new File("decrypted.txt")));

        VigenereCrypto vc = new VigenereCrypto();
        int b;
        int enc;
        int de;
        while((b = in.read()) != -1) {
            enc = vc.encrypt((byte) b);
            out.write(enc);

            de = vc.decrypt((byte) enc);
            dec.write(de);
        }
        in.close();
        out.close();
        dec.close();
    }

}
