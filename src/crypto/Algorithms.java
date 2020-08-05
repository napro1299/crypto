package crypto;

import crypto.algorithms.*;

import java.lang.reflect.InvocationTargetException;

public enum Algorithms {

    VIGENERESTREAM(false, VigenereCrypto.class),
    BASICSTREAM(false, BasicCrypto.class),
    BLANKSTREAM(false, BlankStreamCrypto.class),
    VIGENEREBLOCK(true, MatrixVigenereCrypto.class),
    BASICBLOCK(true, BasicBlockCrypto.class),
    BLANKBLOCK(true, BlankBlockCrypto.class),
    ALWAYSADDONE(false, AlwaysAddOne.class);

    private boolean isBlock;
    private Class<?> clazz;

    Algorithms(boolean isBlock, Class<? extends Crypto> clazz) {
        this.isBlock = isBlock;
        this.clazz = clazz;
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public Crypto newInstance() {
        Crypto c = null;
        try {
            c = (Crypto) clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Failed to create algorithm objects");
            e.printStackTrace();
        }

        return c;
    }

}
