package crypto;

public enum Algorithms {

    VIGENERE(false);

    boolean isBlock;

    Algorithms(boolean isBlock) {
        this.isBlock = isBlock;
    }

}
