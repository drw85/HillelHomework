package Lesson11.ListImplementation;

import java.util.List;

public class MainLinkedList {
    public static void main(String[] args) {
        MyLinkedListImpl arrFirst = new MyLinkedListImpl();
        System.out.println("arrFirst.size() : " + arrFirst.size());
        System.out.println("arrFirst.isEmpty() : " + arrFirst.isEmpty());

        System.out.println("arrFirst.add(a) : " + arrFirst.add("a"));
        System.out.println("arrFirst.add(b) : " + arrFirst.add("b"));
        System.out.println("arrFirst.add(c) : " + arrFirst.add("c"));
        System.out.println("arrFirst.add(d) : " + arrFirst.add("d"));
        System.out.println("arrFirst.add(e) : " + arrFirst.add("e"));
        System.out.println("arrFirst.add(f) : " + arrFirst.add("f"));

        System.out.println("arrFirst.get(0) : " + arrFirst.get(0));
        System.out.println("arrFirst.set(0, A) : " + arrFirst.set(0, "A"));
        System.out.println("'remove by value' arrFirst.remove(a) : " + arrFirst.remove("a"));
        System.out.println("'remove by value' arrFirst.remove(A) : " + arrFirst.remove("A"));
        System.out.println("arrFirst.toString() : " + arrFirst.toString());
        System.out.println("'arrFirst add by index' arrFirst.add(0, a : void)");
        arrFirst.add(0, "a");
        System.out.println("arrFirst.toString() : " + arrFirst.toString());

        System.out.println("'remove by index' arrFirst.remove(5) : " + arrFirst.remove(5));
        System.out.println("arrFirst.toString() : " + arrFirst.toString());

        System.out.println("arrFirst.size() : " + arrFirst.size());

        System.out.println("arrFirst.contains(a) : " + arrFirst.contains("a"));
        System.out.println("arrFirst.contains(z) : " + arrFirst.contains("z"));

        System.out.println("init arrSecond");
        MyLinkedListImpl arrSecond = new MyLinkedListImpl();
        System.out.println("arrSecond.add(x) : " + arrSecond.add("x"));
        System.out.println("arrSecond.add(y) : " + arrSecond.add("y"));
        System.out.println("arrSecond.add(z) : " + arrSecond.add("z"));
        System.out.println("arrFirst.addAll(arrSecond) : " + arrFirst.addAll(arrSecond));
        System.out.println("arrFirst.toString() : " + arrFirst.toString());
        System.out.println("'addAll with index' arrFirst.add(1, arrSecond) : " + arrFirst.addAll(4, arrSecond));
        System.out.println("arrFirst.toString() : " + arrFirst.toString());
        System.out.println("arrFirst.indexOf(x) : " + arrFirst.indexOf("x"));
        System.out.println("arrFirst.lastIndexOf(x) : " + arrFirst.lastIndexOf("x"));
        System.out.println("init sublist arrThird  List arrThird = arrFirst.subList(2, 6);");
        List arrThird = arrFirst.subList(2, 6);
        System.out.println("arrThird.toString() : " + arrThird.toString());
        System.out.println("arrFirst.containsAll(arrThird) : " + arrFirst.containsAll(arrThird));
        System.out.println("arrFirst.removeAll(arrThird) : "+arrFirst.removeAll(arrThird));
        System.out.println("arrFirst.toString() : " + arrFirst.toString());
        System.out.println("arrSecond.retainAll(arrThird) : "+arrSecond.retainAll(arrThird));
        System.out.println("arrSecond.toString() : " + arrSecond.toString());
    }
}
