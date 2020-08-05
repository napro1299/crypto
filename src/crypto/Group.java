package crypto;

public interface Group {
    byte encryptAllStreams(byte b);

    byte[] encryptAllBlocks(byte[] bb);

    byte decryptAllStreams(byte b);

    byte[] decryptAllBlocks(byte[] bb);

    boolean isBlock();

    void finished();

    int length();
}
