package crypto.algorithms;

import crypto.CipherStream;

import java.util.Random;

/**
 * The Vigenere stream cipher generates a random key which gets added
 * to the plaintext data that gets passed in
 */
public class VigenereCrypto implements CipherStream {

    private Random random;
    private long seed;
    private int[] key;
    private int pointer = 0;
    private final int KEY_LENGTH;
    private final int KEY_BOUND;
    private Mode mode;

    public VigenereCrypto() {
        this(100, 1000);
    }

    public VigenereCrypto(int keyLength, int keyBound) {
        if (keyLength <= 0) {
            throw new IllegalArgumentException("key length cannot be less than 1");
        }
        if (keyBound <= 0) {
            throw new IllegalArgumentException("key bound cannot be less than 1");
        }

        this.KEY_LENGTH = keyLength;
        this.KEY_BOUND = keyBound;
        this.key = new int[KEY_LENGTH];
        this.seed = new Random().nextLong();
        this.random = new Random(this.seed);
        this.mode = Mode.ENCRYPT;

        this.generateKeys();
    }

    @Override
    public byte encrypt(byte b) {
        if (this.mode == Mode.DECRYPT) {
            this.pointer = 0;
            this.mode = Mode.ENCRYPT;
        }

        int result = b + key[this.pointer];
        this.pointer++;
        return (byte) result;
    }

    @Override
    public byte decrypt(byte b) {
        if (this.mode == Mode.ENCRYPT) {
            this.pointer = 0;
            this.mode = Mode.DECRYPT;
        }

        int result = b - key[this.pointer];
        this.pointer++;
        return (byte) result;
    }

    private void generateKeys() {
        int keyVal;
        for (int i = 0; i < KEY_LENGTH; i++) {
            keyVal = this.random.nextInt(KEY_BOUND);
            key[i] = keyVal < (KEY_BOUND / 2) ? keyVal - (KEY_BOUND / 2) : (keyVal / 2);
        }
        // Print keys
        for (int key : key) System.out.print(key + " ");
    }

    public long getSeed() {
        return this.seed;
    }

    enum Mode {
        ENCRYPT, DECRYPT
    }
}
