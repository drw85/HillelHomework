package DepositCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static Scanner inScanner = new Scanner(System.in);

    public static void main(String[] args) {
        BigDecimal depositAmount = getterBigDecimal("Please enter deposit amount:");
        BigDecimal accruedInterest;
        int durationYears = getterInt("Please enter deposit duration:");
        BigDecimal rate = getterBigDecimal("Please enter rate:");
        for (int i = 0; i < durationYears; i++) {
            accruedInterest = (depositAmount.divide(new BigDecimal(100))).multiply(rate);
            System.out.println("Year "+(i+1)+": "+depositAmount+" (+"+accruedInterest+")");
            depositAmount = depositAmount.add(accruedInterest);
        }
        System.out.println("Total: "+depositAmount);
    }

    private static BigDecimal getterBigDecimal(String message) {
        BigDecimal number = null;
        do {
            System.out.println(message);
            try {
                number = new BigDecimal(inScanner.next());
                number.setScale(2, RoundingMode.DOWN);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input! Try again.");
            }
        } while (number == null);
        return number;
    }

    private static int getterInt(String message) {
        int number = 0;
        do {
            System.out.println(message);
            try {
                number = Integer.parseInt(inScanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input! Try again.");
            }
        } while (number == 0);
        return number;
    }
}