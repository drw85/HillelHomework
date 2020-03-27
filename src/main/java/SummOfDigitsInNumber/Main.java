package SummOfDigitsInNumber;

import java.util.Scanner;

public class Main {
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        int number = getInt("ВВЕДИТЕ ЧИСЛО:");
        int sum = 0;
        do  {
            sum+=number%10;
            number/=10;
        } while (number!=0);
        System.out.println("СУММА ЦИФР В ЧИСЛЕ РАВНА "+sum);
    }

    private static int getInt(String message) {
        int number = 0;
        do {
            System.out.println(message);
            try {
                number = Integer.parseInt(inputScanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("ОШИБКА ВВОДА! ПОПРОБУЙТЕ ЕЩЕ.");
            }
        } while (number == 0);
        return number;
    }
}
