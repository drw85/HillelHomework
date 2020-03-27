package Lesson8.Interface;


import java.util.*;

public class MyCollection implements Collection {
    private Object[] data;

    public MyCollection() {
        this.data = new Object[0];
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        Iterator iter = this.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new MyCollectionItr(this.data);
    }

    @Override
    public Object[] toArray() {
        return data;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length >= this.data.length) {
            for (int i = 0; i < this.data.length; i++) {
                a[i] = this.data[i];
            }
        } else {
            a = this.data;
        }
        return a;
    }

    @Override
    public boolean add(Object o) {
        try {
            Object[] tempArr = new Object[this.data.length + 1];
            if (this.data.length == 0) {
                tempArr[0] = o;
            } else {
                for (int i = 0; i < this.data.length; i++) {
                    tempArr[i] = this.data[i];
                }
                tempArr[this.data.length] = o;
            }
            this.data = tempArr;
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        try {
            if (contains(o)) {
                int numOfContains = 0;
                Iterator itr = this.iterator();
                while (itr.hasNext()) {
                    if (itr.next().equals(o)) {
                        numOfContains++;
                    }
                }
                int counter = 0;
                Object[] tempArr = new Object[this.data.length - numOfContains];
                for (int i = 0; i < this.data.length; i++) {
                    if (!this.data[i].equals(o)) {
                        tempArr[counter] = this.data[i];
                        counter++;
                    }
                }
                this.data = tempArr;
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    @Override
    public boolean addAll(Collection c) {
        try {
            Iterator itr = c.iterator();
            while (itr.hasNext()) {
                this.add(itr.next());
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public void clear() {
        this.data = new Object[0];
    }

    @Override
    public boolean retainAll(Collection c) {
        try {
            Iterator iter = this.iterator();
            while (iter.hasNext()) {
                Object toCheck = iter.next();
                if (!c.contains(toCheck)) {
                    this.remove(toCheck);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        try {
            Iterator iter = c.iterator();
            while (iter.hasNext()) {
                Object toCheck = iter.next();
                if (this.contains(toCheck)) {
                    this.remove(toCheck);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c.size() > 0) {
            int counter = 0;
            Iterator itr = c.iterator();
            while (itr.hasNext()) {
                if (this.contains(itr.next())) {
                    counter++;
                }
            }
            return counter == c.size();
        } else {
            return false;
        }
    }

    private class MyCollectionItr implements Iterator {
        private int cursor;
        private final Object[] data;

        public MyCollectionItr(Object[] data) {
            this.cursor = 0;
            this.data = data;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < this.data.length;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i >= this.data.length) {
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return this.data[i];
        }
    }
}