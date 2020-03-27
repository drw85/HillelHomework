package Lesson6.MyArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("MyArray DEMO");
        MyIntArray arr = new MyIntArray();
        arr.print();
        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(40);
        arr.add(50);
        System.out.println("NOW ARRAYS SIZE IS: " + arr.getSize());
        arr.print();
        System.out.println("SHOWING ELEMENTS WITH get() METHOD");
        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println(arr.get(i));
        }
        System.out.println("IS CONTAINS 30: " + arr.contains(30));
        System.out.println("IS CONTAINS 35: " + arr.contains(35));
        MyIntArray arr2 = new MyIntArray();
        arr2.add(60);
        arr2.add(70);
        arr2.add(80);
        arr2.add(90);
        arr2.add(100);
        arr.addAll(arr2);
        System.out.println("ARRAY AFTER addAll() METHOD");
        arr.print();
        System.out.println("arr EQUALS arr2: " + arr.equals(arr2));
        System.out.println("arr EQUALS arr: " + arr.equals(arr));

        arr2.clear();
        System.out.println("arr2 AFTER CLEARING");
        arr2.print();

        arr.add(20);
        arr.add(20);

        System.out.println("indexOf RETURNS THE int[] OF INDEXES");
        System.out.println(Arrays.toString(arr.indexOf(20)));

        System.out.println("arr AFTER sort()");
        arr.sort();
        arr.print();

    }
}