package Lesson25.MamkinHacker;

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static char[] SYMBOLS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static List<String> HASHES = new ArrayList<>();
    public static long START;

    public static void main(String[] args) {
        HASHES.add("f016441d00c16c9b912d05e9d81d894d");
        HASHES.add("5ebe2294ecd0e0f08eab7690d2a6ee69");
        HASHES.add("13d70e09909669272b19647c2a55dacb");
        HASHES.add("5f50dfa5385e66ce46ad8d08a9c9be68");

        START = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 4; i < 10; i++) {
            for (char firstLetter : SYMBOLS) {
                executor.submit(new MamkinDecryptor(SYMBOLS, firstLetter, i));
            }
        }

        executor.shutdown();
    }
}

