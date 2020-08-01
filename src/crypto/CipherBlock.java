package crypto;

public interface CipherBlock extends Crypto {
    byte[] encrypt(byte[] b);

    byte[] decrypt(byte[] b);
}
