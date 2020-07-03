import crypto.Crypto;
import crypto.BasicCrypto;
import crypto.VigenereCrypto;

import java.io.IOException;
import java.util.Random;

public class Application {

    public static void main(String[] args) throws IOException {
        VigenereCrypto crypto = new VigenereCrypto();
        crypto.process();
    }

}
