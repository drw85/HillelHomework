package Lesson19.Generics;

import java.util.*;

public class MyLinkedListWithGenericsImpl<T> implements java.util.List<T> {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedListWithGenericsImpl() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private class Node<T> {

        private T data;
        private Node next;
        private Node prev;

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public T getData() {
            return data;
        }

        public void setData(T obj) {
            data = obj;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private class MyListIterator<T> implements ListIterator<T> {
        private int cursor;
        private MyLinkedListWithGenericsImpl<T> list;

        public MyListIterator(MyLinkedListWithGenericsImpl<T> list) {
            this.cursor = 0;
            this.list = list;
        }

        public MyListIterator(int cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return getNode(cursor) != null;
        }

        @Override
        public T next() {
            Node node = getNode(cursor);
            cursor++;
            return (T) node.getData();
        }

        @Override
        public boolean hasPrevious() {
            return getNode(cursor - 1) != null;
        }

        @Override
        public T previous() {
            Node node = getNode(cursor - 1);
            cursor--;
            return (T) node.getData();
        }

        @Override
        public int nextIndex() {
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            list.remove(cursor);
        }

        @Override
        public void set(T o) {
            getNode(cursor).setData((T) o);
        }

        @Override
        public void add(T o) {
            list.add(o);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        Iterator<T> itr = this.iterator();
        while (itr.hasNext()) {
            T current = itr.next();
            if (current.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> itr = new MyListIterator<T>(this);
        return itr;
    }

    @Override
    public T[] toArray() {
        int counter = 0;
        Object[] arrayToReturn = new Object[size];
        Iterator<T> itr = iterator();
        while (itr.hasNext()) {
            arrayToReturn[counter] = itr.next();
            counter++;
        }
        return (T[]) arrayToReturn;
    }

    public <T> T[] toArray(T[] objects) {
        int counter = 0;
        Object[] arrayToReturn = new Object[size];
        Iterator<T> itr = (Iterator<T>) iterator();
        while (itr.hasNext()) {
            arrayToReturn[counter] = itr.next();
            counter++;
        }
        return (T[]) arrayToReturn;
    }

    @Override
    public boolean add(T objToAdd) {
        if (head == null) {
            Node nodeToAdd = new Node(objToAdd, null, null);
            head = nodeToAdd;
            tail = nodeToAdd;
            size = 1;
            return true;
        } else {
            Node nodeToAdd = new Node(objToAdd, null, tail);
            tail.setNext(nodeToAdd);
            tail = nodeToAdd;
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        try {
            T obj = (T) o;
        } catch (ClassCastException ex) {
            return false;
        }
        Node cursor = head;
        for (int i = 0; i < size(); i++) {
            cursor = getNode(i);
            if (cursor.getData().equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Collection<? extends T> col = collection;
        Iterator<T> iter = (Iterator<T>) col.iterator();
        while (iter.hasNext()) {
            T toAdd = iter.next();
            add(toAdd);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int cursor = index;
        Iterator<T> iter = (Iterator<T>) c.iterator();
        while (iter.hasNext()) {
            add(cursor, iter.next());
            cursor++;
        }
        return true;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (!isEmpty() && (index >= 0 && index < size - 1)) {
            Node node = getNode(index);
            return (T) node.getData();
        } else {
            return null;
        }
    }

    @Override
    public T set(int index, T element) {
        if (!isEmpty() && (index >= 0 && index < size - 1)) {
            Node nodeToSet = getNode(index);
            T dataToReturn = (T) nodeToSet.getData();
            nodeToSet.setData(element);
            return dataToReturn;
        } else {
            return null;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index <= size) {
            Node nodeToAdd = new Node(element, null, null);
            if (index == 0) {
                nodeToAdd.setNext(head);
                head.setPrev(nodeToAdd);
                head = nodeToAdd;
                size++;
                return;
            }
            if (index == size) {
                Node lastNode = getNode(size - 1);
                lastNode.setNext(nodeToAdd);
                nodeToAdd.setPrev(lastNode);
                size++;
                return;
            }
            Node current = getNode(index);
            Node next = current.getNext();
            Node prev = current.getPrev();
            nodeToAdd.setNext(current);
            nodeToAdd.setPrev(prev);
            prev.setNext(nodeToAdd);
            current.setPrev(nodeToAdd);
            size++;
        } else {
            System.out.println("IndexOutOfBounds");
        }
    }

    @Override
    public T remove(int index) {
        if (isIndexValid(index)) {
            T objToReturn = null;
            if (index == 0) {
                objToReturn = (T) head.getData();
                head = head.getNext();
                size--;
                return objToReturn;
            }
            if (index == size - 1) {
                Node lastNode = getNode(size - 1);
                tail = lastNode.getPrev();
                objToReturn = (T) lastNode.getData();
                lastNode.getPrev().setNext(null);
                size--;
                return objToReturn;
            }
            try {
                Node nodeToRemove = getNode(index);
                objToReturn = (T) nodeToRemove.getData();
                Node next = nodeToRemove.getNext();
                Node prev = nodeToRemove.getPrev();
                prev.setNext(next);
                next.setPrev(prev);
                size--;
                return objToReturn;
            } catch (NullPointerException ex) {
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    public int indexOf(Object o) {
        try {
            T obj = (T) o;
        } catch (ClassCastException ex) {
            return -1;
        }
        if (contains(o)) {
            int index = 0;
            Node current = head;
            while (!current.getData().equals(o)) {
                index++;
                current = current.getNext();
            }
            return index;
        } else {
            return -1;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        try {
            T obj = (T) o;
        } catch (ClassCastException ex) {
            return -1;
        }
        if (contains(o)) {
            int index = size - 1;
            Node current = tail;
            while (!current.getData().equals(o)) {
                index--;
                current = current.getPrev();
            }
            return index;
        } else {
            return -1;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator<T>(this);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyListIterator<T>(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (isIndexValid(fromIndex) && isIndexValid(toIndex) && fromIndex < toIndex) {
            MyLinkedListWithGenericsImpl<T> listToReturn = new MyLinkedListWithGenericsImpl<T>();
            for (int i = fromIndex; i < toIndex; i++) {
                listToReturn.add((T)getNode(i).getData());
            }
            return listToReturn;
        } else {
            return null;
        }
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        } else {
            ArrayList<T> objectsToDelete = new ArrayList<T>();
            T[] arr = toArray();
            for (int i = 0; i < arr.length; i++) {
                if (!collection.contains(arr[i])) {
                    objectsToDelete.add(arr[i]);
                }
            }
            removeAll(objectsToDelete);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        } else {
            Iterator<T> itr = (Iterator<T>) collection.iterator();
            while (itr.hasNext()) {
                T objToDelete = itr.next();
                while (contains(objToDelete)) {
                    remove(objToDelete);
                }
            }
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null || collection.size() == 0) {
            return false;
        } else {
            int counter = 0;
            Iterator<T> itr = (Iterator<T>) collection.iterator();
            while (itr.hasNext()) {
                if (contains(itr.next())) {
                    counter++;
                }
            }
            return counter == collection.size();
        }
    }

    private Node getNode(int index) {
        if (!isEmpty() && (index >= 0 && index < size)) {
            int cursor = 0;
            Node current = head;
            while (cursor != index) {
                current = current.getNext();
                cursor++;
            }
            return current;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private boolean isIndexValid(int index) {
        return (index >= 0 && index < size);
    }

}
