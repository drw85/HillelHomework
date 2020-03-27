package SimpleCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static Scanner inScanner = new Scanner(System.in);
    public static BigDecimal firstNumber;
    public static BigDecimal secondNumber;

    public static void main(String[] args) {
        firstNumber = numberGetter("Please input first number:");
        secondNumber = numberGetter("Please input second number:");
        printResult();
    }

    private static void printResult() {
        switch (getOperation()) {
            case "+":
                System.out.println("Sum is: " + firstNumber.add(secondNumber));
                break;
            case "-":
                System.out.println("Difference is: " + firstNumber.subtract(secondNumber));
                break;
            case "*":
                System.out.println("Multiplication is: " + firstNumber.multiply(secondNumber));
                break;
            case "/":
                System.out.println("Division is: " + firstNumber.divide(secondNumber, 5, RoundingMode.DOWN));
                break;
        }
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

    private static String getOperation() {
        String operation = "";
        do {
            System.out.println("Please select operation(+,-,*,/):");
            operation = inScanner.next();
        } while (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/"));
        return operation;
    }
}