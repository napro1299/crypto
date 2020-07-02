import crypto.Crypto;
import crypto.BasicCrypto;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        BasicCrypto crypto = new BasicCrypto();
        crypto.process();
    }

}
