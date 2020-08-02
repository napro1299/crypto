package crypto;

import crypto.algorithms.BasicCrypto;
import crypto.algorithms.VigenereCrypto;

import java.lang.reflect.InvocationTargetException;

public enum Algorithms {

    VIGENERE(false, VigenereCrypto.class),
    BASIC(false, BasicCrypto.class);

    boolean isBlock;
    Class<? extends Crypto> clazz;

    Algorithms(boolean isBlock, Class<? extends Crypto> clazz) {
        this.isBlock = isBlock;
        this.clazz = clazz;
    }

    public Crypto createCipher() {
        Crypto cipher = null;
        try {
            cipher = this.clazz.getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return cipher;
    }

}
