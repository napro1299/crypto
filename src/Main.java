import crypto.Algorithms;
import crypto.CipherBlock;
import crypto.CipherGroup;
import crypto.Group;
import io.StreamManager;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Group group = new CipherGroup<CipherBlock>(Algorithms.ALWAYSADDONE, Algorithms.BASICSTREAM, Algorithms.BLANKSTREAM);
        StreamManager streamManager = new StreamManager(new File("./plaintext.txt"), new File("./encrypted.txt"), group, 2048);
        //streamManager.executeGroup();

        streamManager.executeGroup().swap().changeOut(new File("./decrypted.png")).executeGroup(true);

    }

}
