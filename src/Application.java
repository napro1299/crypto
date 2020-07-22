import crypto.VigenereCrypto;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        VigenereCrypto crypto = new VigenereCrypto();
        crypto.process();
    }

}
