package crypto;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CipherGroup<T extends Crypto> implements Group {

    private final boolean isBlock;
    private final List<T> cryptoGroup = new LinkedList<T>();
    private int length;
    private int listIndex = 0;

    public CipherGroup(T... t) {
        this.filterElements(t);
        cryptoGroup.addAll(Arrays.asList(t));
        this.isBlock = t[0] instanceof CipherBlock;
        this.length = cryptoGroup.size();
    }

    public CipherGroup(Algorithms... algorithms) {
        this.filterElements(algorithms);
        this.isBlock = algorithms[0].isBlock();
        for (Algorithms algorithm : algorithms) {
            cryptoGroup.add((T) algorithm.newInstance());
        }
    }

    private <T> void filterElements(T[] arr) {
        for (T elem : arr) {
            if (elem == null) {
                throw new IllegalStateException("Array contains null indices");
            }
        }
    }

    @Override
    public byte[] encryptAllBlocks(byte[] bb) {
        return new byte[3];
    }

    @Override
    public byte encryptAllStreams(byte b) {
        CipherStream cs = (CipherStream) this.cryptoGroup.get(listIndex);
        return cs.encrypt(b);
    }

    @Override
    public byte[] decryptAllBlocks(byte[] bb) {
        return new byte[3];
    }

    @Override
    public byte decryptAllStreams(byte b) {
        return 0;
    }

    @Override
    public void finished() {
        this.listIndex++;
    }

    @Override
    public boolean isBlock() {
        return this.isBlock;
    }

    @Override
    public int length() {
        return this.length;
    }

}
