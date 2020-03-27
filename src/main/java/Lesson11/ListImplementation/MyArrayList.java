package Lesson11.ListImplementation;

import java.util.*;

public class MyArrayList implements List {

    private Object[] data;
    private MyArrayList list;

    public MyArrayList() {
        this.data = new Object[0];
    }

    private class MyArrayListIterator implements ListIterator {

        int index;
        MyArrayList list;

        public MyArrayListIterator(MyArrayList arrList) {
            this.index = 0;
            list = arrList;
        }

        public MyArrayListIterator(int index, MyArrayList arrList) {
            this.index = index;
            this.list = arrList;
        }

        @Override
        public boolean hasNext() {
            return get(index) != null;
        }

        @Override
        public Object next() {
            Object dataToReturn = get(index);
            index = nextIndex();
            return dataToReturn;
        }

        @Override
        public boolean hasPrevious() {
            return get(index) != null;
        }

        @Override
        public Object previous() {
            Object dataToReturn = get(index);
            index = previousIndex();
            return dataToReturn;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (isIndexValid(index)) {
                this.list.remove(index);
            }
        }

        @Override
        public void set(Object o) {
            data[index] = o;
        }

        @Override
        public void add(Object o) {
            list.add(o);
        }
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
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        Iterator itr = new MyArrayListIterator(this);
        return itr;
    }

    @Override
    public Object[] toArray() {
        return data;
    }

    @Override
    public boolean add(Object o) {
        if (o == null) {
            throw new NullPointerException("null not allowed here");
        }
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
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (isIndexValid(index)) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection collection) {
        try {
            Iterator itr = collection.iterator();
            while (itr.hasNext()) {
                this.add(itr.next());
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    @Override
    public boolean addAll(int index, Collection collection) throws IndexOutOfBoundsException {
        int cursor = -1;
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Bad index");
        }
        if (isIndexValid(index)) {
            cursor = index;
        } else {
            return false;
        }
        if (index == data.length) {
            cursor = data.length;
        }
        Iterator itr = collection.iterator();
        while (itr.hasNext()) {
            Object objToAdd = itr.next();
            add(cursor, objToAdd);
            cursor++;
        }
        return true;
    }

    @Override
    public void clear() {
        data = new Object[0];
    }

    @Override
    public Object get(int index) {
        if (isIndexValid(index)) {
            return data[index];
        } else {
            throw new IndexOutOfBoundsException("Bad index");
        }
    }

    @Override
    public Object set(int i, Object o) {
        if (isIndexValid(i)) {
            Object objToReturn = data[i];
            data[i] = o;
            return objToReturn;
        } else {
            throw new IndexOutOfBoundsException("Bad index");
        }
    }

    @Override
    public void add(int index, Object o) {
        if (index <= 0 && index > data.length) {
            throw new IndexOutOfBoundsException();
        }
        if (isIndexValid(index)) {
            Object[] tempArr = new Object[data.length + 1];
            int cursor = 0;
            for (int i = 0; i < data.length; ) {
                if (cursor != index) {
                    tempArr[cursor] = data[i];
                    i++;
                } else {
                    tempArr[cursor] = o;
                }
                cursor++;
            }
            data = tempArr;
        }
        if (index == data.length) {
            add(o);
        }
    }

    @Override
    public Object remove(int index) {
        Object objToReturn = null;
        if (isIndexValid(index)) {
            int counter = 0;
            Object[] tempArr = new Object[this.data.length - 1];
            for (int i = 0; i < this.data.length; i++) {
                if (i != index) {
                    tempArr[counter] = this.data[i];
                    counter++;
                } else {
                    objToReturn = data[i];
                }
            }
            this.data = tempArr;
        } else {
            throw new IndexOutOfBoundsException();
        }
        return objToReturn;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        int cursor = 0;
        Iterator iter = this.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(o)) {
                index = cursor;
                return index;
            }
            cursor++;
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        int cursor = data.length - 1;
        ListIterator iter = this.listIterator(data.length - 1);
        while (iter.hasPrevious()) {
            if (iter.previous().equals(o)) {
                index = cursor;
                return index;
            }
            cursor--;
        }
        return index;
    }

    @Override
    public ListIterator listIterator() {
        return new MyArrayListIterator(this);
    }

    @Override
    public ListIterator listIterator(int i) {
        if (!isIndexValid(i)) {
            throw new IllegalArgumentException("Bad index");
        }
        return new MyArrayListIterator(i, this);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < toIndex) {
            throw new FromLessThenToException("FROM is smaller then TO for sublist");
        }
        if (isIndexValid(fromIndex) && isIndexValid(toIndex)) {
            MyArrayList listToReturn = new MyArrayList();
            for (int i = fromIndex; i < toIndex; i++) {
                listToReturn.add(data[i]);
            }
            return listToReturn;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean retainAll(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("Can't retain null collection");
        }
        Iterator itr = this.iterator();
        while (itr.hasNext()) {
            Object objToCheck = itr.next();
            if (!collection.contains(objToCheck)) {
                while (contains(objToCheck)) {
                    remove(objToCheck);
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("Can't remove null collection");
        }
        Iterator itr = collection.iterator();
        while (itr.hasNext()) {
            Object objToRemove = itr.next();
            while (contains(objToRemove)) {
                remove(objToRemove);
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("Can't check null collection");
        }
        int counter = 0;
        for (Object obj : collection) {
            if (contains(obj)) {
                counter++;
            }
        }
        return counter == collection.size();
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return data;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < data.length;
    }
}
