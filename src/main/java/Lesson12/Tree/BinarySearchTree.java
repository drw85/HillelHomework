package Lesson12.Tree;

import java.util.*;

public class BinarySearchTree implements Set {
    private Node root;
    int size = 0;

    private class Node {
        private int data;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }
    }

    private class IteratorImpl implements Iterator {
        Stack<Node> stack;

        public IteratorImpl(Node root) {
            stack = new Stack<Node>();
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Object next() {
            Node node = stack.pop();
            int toReturn = node.getData();
            if (node.getRight() != null) {
                node = node.getRight();
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }
            return toReturn;
        }
    }

    private class NodeIteratorImpl implements Iterator {
        Stack<Node> stack;

        public NodeIteratorImpl(Node root) {
            stack = new Stack<Node>();
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            Node node = stack.pop();
            Node toReturn = node;
            if (node.getRight() != null) {
                node = node.getRight();
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }
            return toReturn;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        try {
            if (isObjectValid(o)) {
                Iterator itr = this.iterator();
                while (itr.hasNext()) {
                    if (itr.next().equals(o)) {
                        return true;
                    }
                }
            } else {
                throw new IllegalArgumentException("Bad data. Must be Integer.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl(root);
    }

    private Iterator nodeIterator() {
        return new NodeIteratorImpl(root);
    }

    @Override
    public Object[] toArray() {
        Object[] toReturn = new Object[this.size];
        Iterator itr = this.iterator();
        int counter = 0;
        while (itr.hasNext()) {
            toReturn[counter] = itr.next();
            counter++;
        }
        return toReturn;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length >= size) {
            int counter = 0;
            for (Object value : this) {
                a[counter] = value;
                counter++;
            }
            return a;
        } else {
            return toArray();
        }
    }

    @Override
    public boolean add(Object o) {
        try {
            if (isObjectValid(o)) {
                if (!contains((int) o)) {
                    if (root == null) {
                        root = new Node((int) o, null);
                    } else {
                        this.root = addingRecursion(root, (int) o, root.getParent());
                    }
                    this.size++;
                    return true;
                }
            } else {
                throw new IllegalArgumentException("Bad data. Must be Integer.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

    private Node addingRecursion(Node current, int data, Node parent) {
        if (current == null) {
            return new Node(data, parent);
        }
        if (data < current.getData()) {
            parent = current;
            current.setLeft(addingRecursion(current.getLeft(), data, parent));
        } else if (data > current.getData()) {
            parent = current;
            current.setRight(addingRecursion(current.getRight(), data, parent));
        } else {
            return current;
        }
        return current;
    }

    @Override
    public boolean remove(Object o) {
        if (isObjectValid(o)) {
            if (contains(o)) {
                root = removingRecursion(root, (int) o);
                size--;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private Node removingRecursion(Node root, int val) {
        if (root == null) return root;
        if (val < root.getData())
            root.setLeft(removingRecursion(root.getLeft(), val));
        else if (val > root.getData())
            root.setRight(removingRecursion(root.getRight(), val));
        else {
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();
            root.setData(minValue(root.getRight()));
            root.setRight(removingRecursion(root.getRight(), root.getData()));
        }
        return root;
    }

    private int minValue(Node root) {
        int minVal = root.getData();
        while (root.getLeft() != null) {
            minVal = root.getLeft().getData();
            root = root.getLeft();
        }
        return minVal;
    }

    @Override
    public boolean addAll(Collection c) {
        try {
            if (isCollectionValid(c)) {
                for (Object o : c) {
                    add(o);
                }
                return true;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean isChanged = false;
        try {
            if (c == null) {
                throw new NullPointerException("Can't remove null collection");
            }
            if (isCollectionValid(c)) {
                for (Object o : c) {
                    if (remove(o)) {
                        isChanged = true;
                    }
                }
                return isChanged;
            } else {
                throw new IllegalArgumentException("Not valid collection");
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.toString());
            return false;
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
        return isChanged;
    }


    @Override
    public boolean retainAll(Collection c) {
        try {
            if (!isCollectionValid(c)) {
                throw new IllegalArgumentException("Not valid collection");
            }
            if (c.size() > 0) {
                for (Object o : this) {
                    if (!c.contains(o)) {
                        remove(o);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        try {
            if (c == null) {
                throw new NullPointerException("Can't check null collection");
            }
            if (!isCollectionValid(c)) {
                throw new IllegalArgumentException("Not valid collection");
            }
            if (c.size() > 0) {
                int counter = c.size();
                for (Object o : c) {
                    if (contains(o)) {
                        counter--;
                    }
                }
                return counter == 0;
            } else {
                return false;
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.toString());
            return false;
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    private boolean isObjectValid(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }
        return true;
    }

    private boolean isCollectionValid(Collection c) {
        for (Object o : c) {
            if (!isObjectValid(o)) {
                return false;
            }
        }
        return true;
    }

    public void makeTreeBalanced() {
        int bestRoot = findBestRoot();
        System.out.println("Best root  : " + bestRoot);
        BinarySearchTree newTree = new BinarySearchTree();
        newTree.add(bestRoot);
        Iterator iter = iterator();
        while (iter.hasNext()) {
            newTree.add(iter.next());
        }
        root = newTree.root;
    }

    private int findBestRoot() {
        int bestRoot = root.getData();
        int currentDifference = size;
        Iterator iter = iterator();
        while (iter.hasNext()) {
            int current = (int) iter.next();
            int leftDepth = getLeftDepth(current);
            int rightDepth = getRightDepth(current);
            int difference = Math.abs(leftDepth - rightDepth);
            if (Math.abs(difference - (size / 2)) < Math.abs(currentDifference - (size / 2))) {
                currentDifference = difference;
                bestRoot = current;
            }
        }
        return bestRoot;
    }

    private int getLeftDepth(int val) {
        int answer = 0;
        Node current = getNodeByValue(val);
        while (current.getLeft() != null) {
            answer++;
            current = current.getLeft();
        }
        return answer;
    }

    private int getRightDepth(int val) {
        int answer = 0;
        Node current = getNodeByValue(val);
        while (current.getRight() != null) {
            answer++;
            current = current.getRight();
        }
        return answer;
    }

    private Node getNodeByValue(int val) {
        Node answer = null;
        Iterator itr = nodeIterator();
        while (itr.hasNext()) {
            answer = (Node) itr.next();
            if (answer.getData() == val) {
                break;
            }
        }
        return answer;
    }
}
