package crypto.algorithms;

import crypto.CipherStream;

public class AlwaysAddOne implements CipherStream {
    @Override
    public byte encrypt(byte b) {
        return (byte) (b + 1);
    }

    @Override
    public byte decrypt(byte b) {
        return (byte) (b - 1);
    }
}
