package Lesson25.MamkinHacker;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MamkinDecryptor implements Runnable {
    private char firstSymbol;
    private int passwordLength;
    private char[] symbols;

    public MamkinDecryptor(char[] symbols, char firstSymbol, int passwordLength) {
        this.firstSymbol = firstSymbol;
        this.passwordLength = passwordLength;
        this.symbols = symbols;
    }

    @Override
    public void run() {
        System.out.println("Begin letter: " + firstSymbol + " with length: " + passwordLength);
        checkCombinations(passwordLength, firstSymbol, symbols);
    }

    public static void combinationsProcessing(char[] combination, int passwordLength, char fSymbol, char[] symbols) {
        if (passwordLength == 1) {
            for (int i = 0; i < symbols.length; i++) {
                combination[combination.length - passwordLength] = symbols[i];
                String currentCombination = new String(combination);
                if (Main.HASHES.contains(md5HashGenerator(fSymbol + currentCombination))) {
                    System.out.println("Finded secret word: " + fSymbol + currentCombination);
                    System.out.println("Time is: " + (System.currentTimeMillis() - Main.START));
                }
            }
            combination[combination.length - passwordLength] = symbols[0];
        } else {
            for (int i = 0; i < symbols.length; i++) {
                combination[combination.length - passwordLength] = symbols[i];
                combinationsProcessing(combination, passwordLength - 1, fSymbol, symbols);
            }
            combination[combination.length - passwordLength] = symbols[0];
        }
    }

    private static void checkCombinations(int passwordLength, char fSymbol, char[] symbols) {
        char[] combination = new char[passwordLength - 1];
        for (int i = 0; i < passwordLength - 1; i++) {
            combination[i] = symbols[0];
        }
        combinationsProcessing(combination, passwordLength - 1, fSymbol, symbols);
    }

    private static String md5HashGenerator(String text) {
        String generatedHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(text.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            generatedHash = sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NullPointerException ex) {
            System.out.println(ex);
        }
        return generatedHash;
    }
}
