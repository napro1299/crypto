package crypto;

public interface CipherStream extends Crypto {
    byte encrypt(byte b);

    byte decrypt(byte b);
}
