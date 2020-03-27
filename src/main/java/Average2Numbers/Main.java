package Average2Numbers;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static Scanner inScanner = new Scanner(System.in);

    public static void main(String[] args) {
        BigDecimal firstNumber = numberGetter("Please input first number:");
        BigDecimal secondNumber = numberGetter("Please input second  number:");
        System.out.println("Average is: " + ((firstNumber.add(secondNumber)).divide(new BigDecimal(2))));
    }

    private static BigDecimal numberGetter(String message) {
        String enteredString = "";
        do {
            System.out.println(message);
            enteredString = inScanner.next();
        } while (!isValid(enteredString));
        return new BigDecimal(enteredString);
    }

    private static boolean isValid(String enteredNumber) {
        try {
            BigDecimal.valueOf(Double.parseDouble(enteredNumber));
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}