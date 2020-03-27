package Lesson11.ListImplementation;

import java.util.*;

public class MyLinkedListImpl implements List {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedListImpl() {
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

        public void setData(Object obj) {
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

    private class MyListIterator implements ListIterator {
        private int cursor;
        private MyLinkedListImpl list;

        public MyListIterator(MyLinkedListImpl list) {
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
        public Object next() {
            Node node = getNode(cursor);
            cursor++;
            return node.getData();
        }

        @Override
        public boolean hasPrevious() {
            return getNode(cursor - 1) != null;
        }

        @Override
        public Object previous() {
            Node node = getNode(cursor - 1);
            cursor--;
            return node.getData();
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
        public void set(Object o) {
            getNode(cursor).setData(o);
        }

        @Override
        public void add(Object o) {
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
        Iterator itr = this.iterator();
        while (itr.hasNext()) {
            Object current = itr.next();
            if (current.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        Iterator itr = new MyListIterator(this);
        return itr;
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
        if (objToAdd == null) {
            throw new NullPointerException("null not allowed here");
        }
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
    public boolean addAll(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("null not allowed here");
        }
        Collection col = collection;
        Iterator iter = col.iterator();
        while (iter.hasNext()) {
            Object toAdd = iter.next();
            if (toAdd == null) {
                throw new NullPointerException("null not allowed here");
            }
            add(toAdd);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (c == null) {
            throw new NullPointerException("null not allowed here");
        }
        int cursor = index;
        Iterator iter = c.iterator();
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
    public Object get(int index) {
        if (index >= 0 && index < size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (!isEmpty()) {
            Node node = getNode(index);
            return node.getData();
        } else {
            return null;
        }
    }

    @Override
    public Object set(int index, Object element) {
        if (index >= 0 && index < size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (!isEmpty()) {
            Node nodeToSet = getNode(index);
            Object dataToReturn = nodeToSet.getData();
            nodeToSet.setData(element);
            return dataToReturn;
        } else {
            return null;
        }
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 && index > size) {
            throw new IndexOutOfBoundsException();
        }
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
        }
    }

    @Override
    public Object remove(int index) {
        if (isIndexValid(index)) {
            Object objToReturn = null;
            if (index == 0) {
                objToReturn = head.getData();
                head = head.getNext();
                size--;
                return objToReturn;
            }
            if (index == size - 1) {
                Node lastNode = getNode(size - 1);
                tail = lastNode.getPrev();
                objToReturn = lastNode.getData();
                lastNode.getPrev().setNext(null);
                size--;
                return objToReturn;
            }
            try {
                Node nodeToRemove = getNode(index);
                objToReturn = nodeToRemove.getData();
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
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(Object o) {
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
    public ListIterator listIterator() {
        return new MyListIterator(this);
    }

    @Override
    public ListIterator listIterator(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return new MyListIterator(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < toIndex){
            throw new FromLessThenToException("FROM is smaller then TO for sublist");
        }
        if (isIndexValid(fromIndex) && isIndexValid(toIndex)) {
            MyLinkedListImpl listToReturn = new MyLinkedListImpl();
            for (int i = fromIndex; i < toIndex; i++) {
                listToReturn.add(getNode(i).getData());
            }
            return listToReturn;
        } else {
          throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean retainAll(Collection collection) {
        if (collection==null){
            throw new NullPointerException("Can't retain null collection");
        }
        if (collection.size() == 0) {
            return false;
        } else {
            ArrayList objectsToDelete = new ArrayList();
            Object[] arr = toArray();
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
    public boolean removeAll(Collection collection) {
        if (collection==null){
            throw new NullPointerException("Can't remove null collection");
        }
        if (collection.size() == 0) {
            return false;
        } else {
            Iterator itr = collection.iterator();
            while (itr.hasNext()) {
                Object objToDelete = itr.next();
                while (contains(objToDelete)) {
                    remove(objToDelete);
                }
            }
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection collection) {
        if (collection==null){
            throw new NullPointerException("Can't check null collection");
        }
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
