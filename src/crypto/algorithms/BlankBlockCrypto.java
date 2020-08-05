package crypto.algorithms;

import crypto.CipherBlock;

public class BlankBlockCrypto implements CipherBlock {
    @Override
    public byte[] encrypt(byte[] bb) {
        return bb;
    }

    @Override
    public byte[] decrypt(byte[] bb) {
        return bb;
    }
}
