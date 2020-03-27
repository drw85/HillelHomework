package Lesson14.TreeSet;

import java.util.Comparator;

public class ComparatorForObjectsByHashCode implements Comparator {
    @Override
    public int compare(Object o1, Object o2) throws NullPointerException {
        return Integer.compare(o1.hashCode(), o2.hashCode());
    }
}
