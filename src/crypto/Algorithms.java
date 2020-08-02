package crypto;

public enum Algorithms {

    VIGENERE(false),
    BASIC(false);

    boolean isBlock;

    Algorithms(boolean isBlock) {
        this.isBlock = isBlock;
    }
}
