package NumberInfo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int number = getInt("Please enter number: ");
        System.out.println(isEven(number));
        System.out.println(isPrime(number));
        System.out.println(multiplies(number));
    }

    private static String isEven(int number) {
        if (number % 2 == 0) {
            return "Number " + number + " is even";
        } else {
            return "Number " + number + " is not even";
        }
    }

    private static String isPrime(int number) {
        int delimeters = 0;
        for (int x = number - 1; x > 2; x--) {
            if (number % x == 0) {
                delimeters++;
            }
        }
        if (delimeters == 0) {
            return "Number " + number + " is prime";
        } else {
            return "Number " + number + " is not prime";
        }
    }

    private static String multiplies(int number) {
        String mult = "";
        for (int x = number; x > 0; x--) {
            if (number % x == 0) {
                mult += " " + x;
            }
        }
        return "Number " + number + " is a multiple of: "+mult;
    }

    private static int getInt(String message) {
        Scanner inScanner = new Scanner(System.in);
        int number = 0;
        do {
            System.out.println(message);
            try {
                number = Integer.parseInt(inScanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input! Try again.");
            }
        } while (number < 1);
        return number;
    }
}