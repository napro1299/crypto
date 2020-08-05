package crypto.algorithms;

import crypto.CipherBlock;

/**
 * Same intent as {@link VigenereCrypto} but instead of a 1-dimensional key
 * this utilizes a 2d matrix to obfuscate plaintext
 */
public class MatrixVigenereCrypto implements CipherBlock {

    @Override
    public byte[] encrypt(byte[] b) {
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] b) {
        return new byte[0];
    }
}
