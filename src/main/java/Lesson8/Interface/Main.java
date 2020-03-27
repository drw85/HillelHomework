package Lesson8.Interface;

public class Main {
    public static void main(String[] args) {
        System.out.println("DEMO START :-)");
        System.out.println("init myCal");
        MyCollection myCol = new MyCollection();
        System.out.println("myCol isEmpty: " + myCol.isEmpty());
        System.out.println("Add String A: " + myCol.add("A"));
        System.out.println("Add String B: " + myCol.add("B"));
        System.out.println("Add String C: " + myCol.add("C"));
        System.out.println("Add String D: " + myCol.add("D"));
        System.out.println("myCol isEmpty: " + myCol.isEmpty());
        System.out.println("myCol size is " + myCol.size());
        System.out.println("Remove String D: " + myCol.remove("D"));
        System.out.println("myCol size is " + myCol.size());
        System.out.println("myCol contains A: " + myCol.contains("A"));
        System.out.println("myCol contains D: " + myCol.contains("D"));
        System.out.println("iterator(): " + myCol.iterator());
        System.out.println("toArray(): " + myCol.toArray());
        System.out.println("init myCol2");
        MyCollection myCol2 = new MyCollection();
        System.out.println("Add int 1: " + myCol2.add(1));
        System.out.println("Add int 2: " + myCol2.add(2));
        System.out.println("Add int 3: " + myCol2.add(3));
        System.out.println("myCol.containsAll(myCol2): " + myCol.containsAll(myCol2));
        System.out.println("myCol.addAll(myCol2): " + myCol.addAll(myCol2));
        System.out.println("myCol.containsAll(myCol2): " + myCol.containsAll(myCol2));
        System.out.println("myCol.retainAll(myCol2): " + myCol.retainAll(myCol2));
        System.out.println("myCol.removeAll(myCol2): " + myCol.removeAll(myCol2));
        System.out.println("myCol isEmpty: " + myCol.isEmpty());
        System.out.println("myCol2.clear()");
        myCol2.clear();
        System.out.println("myCol2 isEmpty: " + myCol2.isEmpty());

    }
}
