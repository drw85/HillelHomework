package Lesson10.LinkedList;

import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList implements Collection {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private class Node {
        private Object data;
        private Node next;
        private Node prev;

        public Node(Object data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Object getData() {
            return data;
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

    public class MyLinkedListItr implements Iterator {
        private Node cursor;
        private Node zeroNode;

        public MyLinkedListItr(Node head) {
            this.zeroNode = new Node(null, head, null);
            cursor = this.zeroNode;
        }

        @Override
        public boolean hasNext() {
            return cursor.getNext() != null;
        }

        @Override
        public Object next() {
            cursor = cursor.getNext();
            return cursor.getData();
        }
    }

    private class InternalIterator implements Iterator {
        private Node cursor;
        private Node zeroNode;

        public InternalIterator(Node head) {
            this.zeroNode = new Node(null, head, null);
            cursor = this.zeroNode;
        }

        @Override
        public boolean hasNext() {
            return cursor.getNext() != null;
        }

        @Override
        public Node next() {
            cursor = cursor.getNext();
            return cursor;
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
        MyLinkedListItr itr = this.iterator();
        while (itr.hasNext()) {
            Object current = itr.next();
            if (current.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MyLinkedListItr iterator() {
        return new MyLinkedListItr(this.head);
    }

    @Override
    public Object[] toArray() {
        int counter = 0;
        Object[] arrayToReturn = new Object[size];
        Iterator itr = iterator();
        while (itr.hasNext()) {
            arrayToReturn[counter] = itr.next();
            counter++;
        }
        return arrayToReturn;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        int counter = 0;
        Object[] arrayToReturn = new Object[size];
        Iterator itr = iterator();
        while (itr.hasNext()) {
            arrayToReturn[counter] = itr.next();
            counter++;
        }
        return arrayToReturn;
    }

    @Override
    public boolean add(Object objToAdd) {
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
        InternalIterator intIterator = new InternalIterator(head);
        while (intIterator.hasNext()) {
            Node current = intIterator.next();
            if (current.getData().equals(o)) {
                if (current.getPrev() == null) {
                    head = current.getNext();
                } else {
                    current.getPrev().setNext(current.getNext());
                }
                if (current.getNext() == null) {
                    tail = current.getPrev();
                } else {
                    current.getNext().setPrev(current.getPrev());
                }
                size--;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection collection) {
        //ЧЕРЕЗ МАССИВ ЧТОБЫ НОРМАЛЬНО ДОБАВЛЯЛАСЬ САМА В СЕБЯ
        Object[] objectsToAdd = collection.toArray();
        for (int i = 0; i < objectsToAdd.length; i++) {
            add(objectsToAdd[i]);
        }
        return true;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size=0;
    }

    @Override
    public boolean retainAll(Collection collection) {
        if (collection.size() == 0) {
            return false;
        } else {
            Iterator iter = iterator();
            while (iter.hasNext()) {
                Object toCheck = iter.next();
                if (!collection.contains(toCheck)) {
                    remove(toCheck);
                }
            }
            return true;
        }
    }

    @Override
    public boolean removeAll(Collection collection) {
        if (collection.size() == 0) {
            return false;
        } else {
            Iterator itr = collection.iterator();
            while (itr.hasNext()) {
                Object objToDelete = itr.next();
                if (contains(objToDelete)) {
                    remove(objToDelete);
                }
            }
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection collection) {
        if (collection.size() == 0) {
            return false;
        } else {
            int counter = 0;
            Iterator itr = collection.iterator();
            while (itr.hasNext()) {
                if (contains(itr.next())) {
                    counter++;
                }
            }
            return counter == collection.size();
        }
    }
}
