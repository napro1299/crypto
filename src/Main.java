import crypto.*;
import crypto.algorithms.VigenereCrypto;
import io.StreamManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Group group = new CipherGroup<CipherBlock>(Algorithms.ALWAYSADDONE, Algorithms.BASICSTREAM);
        StreamManager streamManager = new StreamManager(new File("./plaintext.txt"), new File("./encrypted.txt"), group);
        //streamManager.executeGroup();
        streamManager.executeGroup().swap().changeOut(new File("./decrypted.txt")).executeGroup(true);

    }

}
