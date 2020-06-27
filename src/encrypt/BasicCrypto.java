package encrypt;

/**
 * Shifts a byte by 1 or -1 depending on if the index
 * is even or odd
 */
public class BasicCrypto implements Crypto {

    @Override
    public byte[] encrypt(byte[] data) {
        byte[] enc = new byte[data.length];

        for (int i = 0; i < enc.length; i++) {
            enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
        }

        return enc;
    }

    // Inverse
    @Override
    public byte[] decrypt(byte[] data) {
        byte[] enc = new byte[data.length];

        for (int i = 0; i < enc.length; i++) {
            enc[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
        }

        return enc;
    }
}
