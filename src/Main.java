import crypto.*;
import crypto.algorithms.VigenereCrypto;
import io.StreamManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IOException {

        Group group = new CipherGroup<CipherBlock>(Algorithms.BASIC, Algorithms.VIGENERE);
        StreamManager streamManager = new StreamManager(new File("plaintext.txt"), new File("encrypted.txt"), group);

//        streamManager.executeGroup()
//                .swap()
//                .executeGroup();


        Banana banana = (Banana) Things.BANANA.newInstance();
        System.out.println(banana.getRipeness());
        banana.printBanana();

    }

    public enum Things {
        BANANA(Banana.class);

        private Class<?> clazz;

        Things(Class<? extends Fruit> clazz) {
            this.clazz = clazz;
        }

        public Fruit newInstance() {
            Fruit fruit = null;
            try {
                fruit = (Fruit) clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return fruit;
        }
    }

    public static class Banana extends Fruit {
        private int ripeness;
        public Banana() {
            ripeness = 10;
        }

        public int getRipeness() {
            return ripeness;
        }

        public void printBanana() {
            System.out.println("BANANA");
        }
    }

    public static class Fruit {
        private Fruit() {
        }
    }

}
