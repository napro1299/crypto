package crypto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CipherGroup<T extends Crypto> implements Group {

    private final boolean isBlock;
    private final List<T> cryptoGroup = new ArrayList<T>();
    private int length;

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
        byte[] tempBlock = new byte[bb.length];
        boolean first = true;
        for (T elem : cryptoGroup) {
            CipherBlock cs = (CipherBlock) elem;
            if (first) {
                tempBlock = cs.encrypt(bb);
                first = false;
            } else {
                tempBlock = cs.encrypt(tempBlock);
            }
        }

        return tempBlock;
    }

    @Override
    public byte encryptAllStreams(byte b) {
        byte tempByte = 0;
        boolean first = true;
        for (T elem : cryptoGroup) {
            CipherStream cs = (CipherStream) elem;
            if (first) {
                tempByte = cs.encrypt(b);
                first = false;
            } else {
                tempByte = cs.encrypt(tempByte);
            }
        }

        return tempByte;
    }

    @Override
    public byte[] decryptAllBlocks(byte[] bb) {
        byte[] tempBlock = new byte[bb.length];
        boolean first = true;
        ArrayList<Crypto> tempList = this.reverseList();

        for (T elem : cryptoGroup) {
            CipherBlock cs = (CipherBlock) elem;
            if (first) {
                tempBlock = cs.decrypt(bb);
                first = false;
            } else {
                tempBlock = cs.decrypt(tempBlock);
            }
        }

        return tempBlock;
    }

    @Override
    public byte decryptAllStreams(byte b) {
        byte tempByte = 0;
        boolean first = true;
        ArrayList<Crypto> tempList = this.reverseList();

        for (Crypto elem : tempList) {
            CipherStream cs = (CipherStream) elem;
            if (first) {
                tempByte = cs.decrypt(b);
                first = false;
            } else {
                tempByte = cs.decrypt(tempByte);
            }
        }

        return tempByte;
    }

    private ArrayList<Crypto> reverseList() {
        ArrayList<Crypto> tempList = new ArrayList<>();
        for (int i = cryptoGroup.size() - 1; i != -1; --i) {
            tempList.add(cryptoGroup.get(i));
        }

        return tempList;
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
