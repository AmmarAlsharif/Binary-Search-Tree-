package com.company.datastructure;


public class DataProvider {
    private static final int[] INTEGERS = {10, 6, 8, 5, 9, 1, 2, 7, 3, 4, 13, 12, 11};
    private static final int[] expectedInOrder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private static final int[] expectedPreOrder = {10, 6, 5, 1, 2, 3, 4, 8, 7, 9, 13, 12, 11};
    private static final int[] expectedPostOrder = {4, 3, 2, 1, 5, 7, 9, 8, 6, 11, 12, 13, 10};

    static BinarySearchTree<Integer> treeOfIntegers() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int data : INTEGERS) {
            tree.insert(data);
        }
        return tree;
    }

    static BinarySearchTree<Integer> emptyTree() {
        return new BinarySearchTree<>();
    }

    static int[] expectedInOrderData() {
        return expectedInOrder;
    }

    static int[] getExpectedPreOrder() {
        return expectedPreOrder;
    }

    static int[] getExpectedPostOrder() {
        return expectedPostOrder;
    }

    static int[] getIntegers() {
        return INTEGERS;
    }
}
