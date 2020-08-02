package crypto;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CipherGroup<T extends Crypto> implements Group {

    private List<T> cryptoGroup = new LinkedList<T>();
    T t;

    public CipherGroup() {
    }

    public CipherGroup(T... t) {
        cryptoGroup.addAll(Arrays.asList(t));
    }

    public CipherGroup(Algorithms... algorithms) {
        for (Algorithms algorithm : algorithms) {
            cryptoGroup.add();
        }
    }

    @Override
    public void encryptAll() {
        if (t instanceof CipherStream) {

        } else if (t instanceof CipherBlock) {

        } else {
            try {
                throw new Exception("invalid ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void decryptAll() {

    }
}
