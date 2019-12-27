package com.company.datastructure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {


    @Test
    void givenSomeNumbers_whenAdding_thenSuccess() {
        /*
            5
          4   6
        4
     -1
        0

         */
        BST<Integer> tree = new BST<>();
        tree.add(5);
        tree.add(4);
        tree.add(4);
        tree.add(6);
        tree.add(-1);
        tree.add(0);
        ArrayList<Integer> expectedOrderedItems = new ArrayList<>();
        expectedOrderedItems.add(-1);
        expectedOrderedItems.add(0);
        expectedOrderedItems.add(4);
        expectedOrderedItems.add(4);
        expectedOrderedItems.add(5);
        expectedOrderedItems.add(6);
        ArrayList<Integer> actualOrderedItems = tree.inorder();

        assertNotNull(actualOrderedItems);
        assertEquals(expectedOrderedItems, actualOrderedItems);
    }
}