package com.company.datastructure;

import java.util.ArrayList;
import java.util.Collections;

public class DataProvider {
    private static final int[] INTEGERS = new int[]{10, 6, 8, 5, 9, 1, 2, 7, 3, 4, 13, 12, 11};
    private static final int[] expectedInOrder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private static final int[] expectedPreOrder = {10, 6, 5, 1, 2, 3, 4, 8, 7, 9, 13, 12, 11};

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

    static int[] getIntegers() {
        return INTEGERS;
    }
}
