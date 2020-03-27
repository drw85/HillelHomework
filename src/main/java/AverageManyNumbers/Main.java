package AverageManyNumbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static Scanner inScanner = new Scanner(System.in);

    public static void main(String[] args) {
        BigDecimal sum = new BigDecimal(0);
        int numbersCounter = 0;
        String str = "";
        String alreadyEntered = "Already entered: ";
        do {
            System.out.println("Please enter number: (type 'x' to stop)");
            str = inScanner.next();
            if (!str.equals("x")) {
                try {
                    BigDecimal enteredNumber = new BigDecimal(str);
                    sum = sum.add(enteredNumber);
                    alreadyEntered += " " + str + " ";
                    numbersCounter++;
                    System.out.println(alreadyEntered);
                } catch (NumberFormatException ex) {
                    System.out.println("Wrong input! Try again.");
                }
            }
        } while (!str.equals("x"));

        if (numbersCounter > 0) {
            System.out.println("Average is: " + sum.divide(new BigDecimal(numbersCounter), 2, RoundingMode.HALF_UP));
        } else {
            System.out.println("I have no suitable numbers!");
        }
    }
}