package Lesson10.LinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("MyLinkedList implements Collection");
        System.out.println();
        MyLinkedList list = new MyLinkedList();
        System.out.println("list.size(): " + list.size());
        System.out.println("list.isEmpty(): " + list.isEmpty());
        System.out.println("list.add(1): " + list.add(1));
        System.out.println("list.add(2): " + list.add(2));
        System.out.println("list.add(3): " + list.add(3));
        System.out.println("list.size(): " + list.size());
        System.out.println("list.isEmpty(): " + list.isEmpty());
        System.out.println("list.contains(1): " + list.contains(1));
        System.out.println("list.contains(4): " + list.contains(4));
        System.out.println("Arrays.toString(list.toArray()): " + Arrays.toString(list.toArray()));
        System.out.println("list.remove(3): " + list.remove(3));
        System.out.println("Arrays.toString(list.toArray()): " + Arrays.toString(list.toArray()));
        System.out.println("Create another MyLinkedList");
        MyLinkedList list2 = new MyLinkedList();
        System.out.println("list2.add(4): " + list2.add(4));
        System.out.println("list2.add(5): " + list2.add(5));
        System.out.println("list2.add(6): " + list2.add(6));
        System.out.println("list.addAll(list2): " + list.addAll(list2));
        System.out.println("Arrays.toString(list.toArray()): " + Arrays.toString(list.toArray()));
        System.out.println("list.containsAll(list2): " + list.containsAll(list2));
        System.out.println("list.retainAll(list2): " + list.retainAll(list2));
        System.out.println("Arrays.toString(list.toArray()): " + Arrays.toString(list.toArray()));
        System.out.println("list.removeAll(list2): " + list.removeAll(list2));
        System.out.println("Arrays.toString(list.toArray()): " + Arrays.toString(list.toArray()));
        System.out.println("list2.clear()");
        list2.clear();
        System.out.println("Arrays.toString(list.toArray()): " + Arrays.toString(list.toArray()));
        System.out.println("Arrays.toString(list2.toArray()): " + Arrays.toString(list2.toArray()));
    }
}
