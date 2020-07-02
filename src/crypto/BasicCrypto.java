package crypto;

/**
 * Shifts a byte by 1 or -1 depending on if the index
 * is even or odd
 * UNDER CONSTRUCTION
 */
public class BasicCrypto extends CryptoBase {

    @Override
    public byte encrypt(byte b) {
        return (byte) (b + 1);
    }

    @Override
    public byte decrypt(byte b) {
        return 0;
    }

}
