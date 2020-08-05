package crypto.algorithms;

import crypto.CipherBlock;

/**
 * Same implementation as {@link BasicCrypto} but
 * processes plaintext data as blocks, reducing
 * method calls
 */
public class BasicBlockCrypto implements CipherBlock {

    @Override
    public byte[] encrypt(byte[] bb) {
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] bb) {
        return new byte[0];
    }
}
