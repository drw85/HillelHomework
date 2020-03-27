package IsPointInRectangle;

import java.util.Scanner;


public class Main {
    static Scanner inScanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] firstPoint = firstPoint = getPoint("КООРДИНАТЫ ПЕРВОЙ ТОЧКИ");
        int[] secondPoint = secondPoint = getPoint("КООРДИНАТЫ ВТОРОЙ ТОЧКИ", firstPoint);
        int[] soughtPoint = soughtPoint = getPoint("КООРДИНАТЫ ИСКОМОЙ ТОЧКИ");

        int[] dimension = calculateDimension(firstPoint, secondPoint, soughtPoint);
        printAnswer(soughtPoint, firstPoint, secondPoint);
        drawAnswer(soughtPoint, dimension, firstPoint, secondPoint);
    }

    private static int[] getPoint(String message) {
        System.out.println(message);
        int[] point = new int[2];
        point[0] = getInt("ВВЕДИТЕ X КООРДИНАТУ:");
        point[1] = getInt("ВВЕДИТЕ Y КООРДИНАТУ:");
        return point;
    }

    private static int[] getPoint(String message, int[] fPoint) {
        int numberX;
        int numberY;
        System.out.println(message);
        int[] point = new int[2];
        do {
            numberX = getInt("ВВЕДИТЕ X КООРДИНАТУ ВТОРОЙ ТОЧКИ: ");
            if (numberX <= fPoint[0]) {
                System.out.println("ТОЧКА ДОЛЖНА БЫТЬ ПРАВЕЕ");
            } else {
                point[0] = numberX;
            }

        } while (numberX <= fPoint[0]);
        do {
            numberY = getInt("ВВЕДИТЕ Y КООРДИНАТУ ВТОРОЙ ТОЧКИ: ");
            if (numberY <= fPoint[1]) {
                System.out.println("ТОЧКА ДОЛЖНА БЫТЬ НИЖЕ");
            } else {
                point[1] = numberY;
            }
        } while (numberY <= fPoint[1]);
        return point;
    }

    private static void printAnswer(int[] soughtPoint, int[] fPoint, int[] sPoint) {
        if ((soughtPoint[0] >= fPoint[0] && soughtPoint[0] <= sPoint[0]) &&
                (soughtPoint[1] >= fPoint[1] && soughtPoint[0] <= sPoint[1])) {
            System.out.println("ТОЧКА ВНУТРИ ПРЯМОУГОЛЬНИКА");
        } else {
            System.out.println("ТОЧКА НЕ ВНУТРИ ПРЯМОУГОЛЬНИКА");
        }
    }

    private static int[] calculateDimension(int[] firstPoint, int[] secondPoint, int[] soughtPoint) {
        int x = 0;
        int y = 0;
        if (x < firstPoint[0]) {
            x = firstPoint[0];
        }
        if (x < secondPoint[0]) {
            x = secondPoint[0];
        }
        if (x < soughtPoint[0]) {
            x = soughtPoint[0];
        }
        if (y < firstPoint[1]) {
            y = firstPoint[1];
        }
        if (y < secondPoint[1]) {
            y = secondPoint[1];
        }
        if (y < soughtPoint[1]) {
            y = soughtPoint[1];
        }
        return new int[]{x + 1, y + 1};
    }

    private static void drawAnswer(int[] soughtPoint, int[] dimension, int[] fPoint, int[] sPoint) {
        for (int i = 0; i <= dimension[1]; i++) {
            if (i >= fPoint[1] && i <= sPoint[1]) {
                if (i == soughtPoint[1]) {
                    drawLine(fPoint[0], sPoint[0], soughtPoint[0], (i == fPoint[1] || i == sPoint[1]), soughtPoint[0], dimension);
                    System.out.println();
                } else {
                    drawLine(fPoint[0], sPoint[0], soughtPoint[0], (i == fPoint[1] || i == sPoint[1]), 0, dimension);
                    System.out.println();
                }
            } else {
                System.out.println();
            }
        }
    }

    private static void drawLine(int x, int y, int lenght, boolean isFirstOrLast, int pointPosition, int[] dimension) {
        for (int i = 0; i <= dimension[0]; i++) {
            if (isFirstOrLast) {
                if (i >= x && i <= y) {
                    if (i == pointPosition && pointPosition > 0) {
                        System.out.print(" O ");
                    } else {
                        System.out.print(" * ");
                    }
                } else {
                    System.out.print("   ");
                }
            } else {
                if (i == x || i == y) {
                    if (i == pointPosition && pointPosition > 0) {
                        System.out.print(" O ");
                    } else {
                        System.out.print(" * ");
                    }
                } else {
                    if (i == pointPosition && pointPosition > 0) {
                        System.out.print(" O ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
        }
    }

    private static int getInt(String message) {
        int i = 0;
        do {
            System.out.println(message);
            try {
                i = Integer.parseInt(inScanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("ОШИБКА ВВОДА! ПОПРОБУЙТЕ ЕЩЕ.");
            }
        } while (i < 1);
        return i;
    }
}