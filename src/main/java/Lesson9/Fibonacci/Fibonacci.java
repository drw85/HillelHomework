package Lesson9.Fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        //int index = getterInt("ВВЕДИТЕ ИНДЕКС ИСКОМОГО ЧИСЛА РЯДА ФИБОНАЧЧИ:");
        for (int i = 1; i < 40; i++) {
            long x = fibonacci(i);
            BigDecimal y = fib(i);
            System.out.println("ДЛЯ " + i + ":  rec " + x + "  form " + y + "     РАВНЫ: " + y.equals(new BigDecimal(x)));
        }
        //System.out.println(fib(index));
        //System.out.println(fibonacci(index));
    }

    public static long fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static BigDecimal fib(int n) {
        BigInteger answer;
        double phi = (1 + Math.sqrt(5)) / 2;
        BigDecimal a = BigDecimal.valueOf(phi);
        a = a.pow(n);
        BigDecimal c;
        c = new BigDecimal(Math.sqrt(5));
        c = c.setScale(14, RoundingMode.UP);
        a = a.divide(c, RoundingMode.HALF_UP);
        a = a.setScale(0, RoundingMode.HALF_UP);
        return a;
    }

    private static int getterInt(String message) {
        Scanner inScanner = new Scanner(System.in);
        int number = 0;
        do {
            System.out.println(message);
            try {
                number = Integer.parseInt(inScanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("ИНДЕКС ЧИСЛА ДОЛЖЕН БЫТЬ ПОЛОЖИТЕЛЬНЫМ! ПОПРОБУЙТЕ ЕЩЕ РАЗ.");
            }
        } while (number < 0);
        return number;
    }
}
