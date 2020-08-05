import crypto.*;
import crypto.algorithms.VigenereCrypto;
import io.StreamManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {

        Group group = new CipherGroup<CipherBlock>(Algorithms.BASIC);
        StreamManager streamManager = new StreamManager(new File("./plaintext.txt"), new File("./encrypted.txt"), group);

        streamManager.executeGroup().swap().executeGroup(true);
    }

}
