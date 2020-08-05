package crypto.algorithms;

import crypto.CipherStream;

/**
 * Blank algorithm that rebounds data for testing
 */
public class BlankStreamCrypto implements CipherStream {
    @Override
    public byte encrypt(byte b) {
        return b;
    }

    @Override
    public byte decrypt(byte b) {
        return b;
    }
}
