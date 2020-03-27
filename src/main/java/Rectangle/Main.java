package Rectangle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int height = getInt("Input width: ");
        int width = getInt("Input height: ");
        System.out.println("How to draw?");
        int which = getInt("(1 - ordinary, 2 - envelope, 3 - chess)");
        while (which < 1 || which > 3) {
            which = getInt("(1 - ordinary, 2 - envelope, 3 - chess)");
        }
        switch (which) {
            case 1:
                drawOrdinary(width, height);
                break;
            case 2:
                drawEnvelope(width, height);
                break;
            case 3:
                drawChess(width, height);
                break;
        }
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
        } while (number == 0);
        return number;
    }

    private static void drawOrdinary(int width, int height) {
        System.out.println("Printing ordinary:");
        for (int x = 0; x < width; x++) {
            if (x == 0 || x == width - 1) {
                for (int y = 0; y < height; y++) {
                    System.out.print(" * ");
                }
                System.out.println("");
            } else {
                for (int y = 0; y < height; y++) {
                    if (y == 0 || y == height - 1) {
                        System.out.print(" * ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.println("");
            }
        }
    }

    private static void drawEnvelope(int width, int height) {
        BigDecimal step = new BigDecimal(Double.toString((double)width/height));
        step = step.setScale(1, RoundingMode.HALF_UP);
        System.out.println("Printing envelope:");
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (y == 0 || y == width - 1 || x==0 || x==height-1) {
                    System.out.print(" * ");
                } else {
                    if (y == (int)(x*step.doubleValue())|| y == width-((int)(x*step.doubleValue()))) {
                        System.out.print(" * ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println("");
        }
    }

    private static void drawChess(int width, int height) {
        System.out.println("Printing chess:");
        for (int x = 0; x < width; x++) {
            if (x == 0 || x == width - 1) {
                for (int y = 0; y < height; y++) {
                    System.out.print(" * ");
                }
                System.out.println("");
            } else {
                for (int y = 0; y < height; y++) {
                    if (y == 0 || y == height - 1) {
                        System.out.print(" * ");
                    } else {
                        if (x % 2 == 0) {
                            if (y % 2 == 0) {
                                System.out.print("   ");
                            } else {
                                System.out.print(" * ");
                            }
                        } else {
                            if (y % 2 == 0) {
                                System.out.print(" * ");
                            } else {
                                System.out.print("   ");
                            }
                        }
                    }
                }
                System.out.println("");
            }
        }
    }
}