package crypto;

import io.StreamBase;

public abstract class CryptoBase extends StreamBase implements Crypto {

    public abstract byte encrypt(byte b);

    public abstract byte decrypt(byte b);

    @Override
    public byte encryptData(byte b) {
        return this.encrypt(b);
    }

    @Override
    public byte decryptData(byte b) {
        return this.decrypt(b);
    }
}
