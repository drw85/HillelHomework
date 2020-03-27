package Lesson12.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree1 = new BinarySearchTree();
        System.out.println("tree1.isEmpty : " + tree1.isEmpty());
        System.out.println("Init tree");
        tree1 = init();
        System.out.println("-----------------------------------------------------------------------------------------");
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(999);
        a.add(888);
        a.add(777);
        System.out.println(tree1.retainAll(a));
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("tree1.isEmpty : " + tree1.isEmpty());
        System.out.println("tree1.size() : " + tree1.size());
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("tree1.contains(20) : " + tree1.contains(20));
        System.out.println("tree1.contains(25) : " + tree1.contains(25));
        System.out.println("tree1.add(10) : " + tree1.add(10));
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("tree1.add(10) : " + tree1.add(10));
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("tree1.remove(10) : " + tree1.remove(10));
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("tree1.remove(10) : " + tree1.remove(10));
        System.out.println("tree1.containsAll(init2()) : " + tree1.containsAll(init2()));
        System.out.println("tree1.addAll(init2()) : " + tree1.addAll(init2()));
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("tree1.containsAll(init()) : " + tree1.containsAll(init()));
        System.out.println("tree1.removeAll(init()) : " + tree1.removeAll(init()));
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("tree1.clear()");
        tree1.clear();
        System.out.println("tree1.isEmpty : " + tree1.isEmpty());
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("Init tree");
        tree1 = init();
        System.out.println("tree1.addAll(init2()) : " + tree1.addAll(init2()));
        System.out.println("tree1.toString() : " + tree1.toString());
        System.out.println("tree1.retainAll(init2()) : " + tree1.retainAll(init2()));
        System.out.println("tree1.toString() : " + tree1.toString());

        System.out.println("Init disbalanced tree");
        BinarySearchTree disbalancedTree = new BinarySearchTree();
        disbalancedTree.add(1);
        disbalancedTree.add(2);
        disbalancedTree.add(3);
        disbalancedTree.add(4);
        disbalancedTree.add(5);
        disbalancedTree.add(6);
        disbalancedTree.add(7);
        disbalancedTree.add(8);
        disbalancedTree.add(9);
        disbalancedTree.add(10);
        System.out.println("disbalancedTree.toString() : " +disbalancedTree.toString());
        disbalancedTree.makeTreeBalanced();
        System.out.println("Now disbalanced tree is (not so good) balanced!");
        System.out.println("disbalancedTree.toString() : " +disbalancedTree.toString());




    }

    private static BinarySearchTree init() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(50);
        bst.add(40);
        bst.add(60);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        return bst;
    }

    private static BinarySearchTree init2() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(45);
        bst.add(55);
        bst.add(35);
        bst.add(65);
        bst.add(25);
        bst.add(75);
        return bst;
    }
}
