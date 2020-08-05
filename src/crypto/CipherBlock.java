package crypto;

public interface CipherBlock extends Crypto {
    byte[] encrypt(byte[] bb);

    byte[] decrypt(byte[] bb);
}
