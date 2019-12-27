package com.company.datastructure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {


    @Test
    void givenIntegers_whenAdding_thenSuccess() {
        BST<Integer> tree = new BST<>();
        tree.add(5);
        tree.add(4);
        tree.add(4);
        tree.add(6);
        tree.add(-1);
        tree.add(0);
        ArrayList<Integer> expectedOrderedIntegers = new ArrayList<>();
        expectedOrderedIntegers.add(-1);
        expectedOrderedIntegers.add(0);
        expectedOrderedIntegers.add(4);
        expectedOrderedIntegers.add(4);
        expectedOrderedIntegers.add(5);
        expectedOrderedIntegers.add(6);
        ArrayList<Integer> actualOrderedIntegers = tree.inorder();
        assertNotNull(actualOrderedIntegers);
        assertEquals(expectedOrderedIntegers, actualOrderedIntegers);
    }

    @Test
    void givenStrings_whenAdding_thenSuccess() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("Ab");
        treeOfStrings.add("aA");
        treeOfStrings.add("AA");
        treeOfStrings.add("aa");
        treeOfStrings.add("AaA");

        ArrayList<String> expectedOrderedStrings = new ArrayList<>();
        expectedOrderedStrings.add("AA");
        expectedOrderedStrings.add("AaA");
        expectedOrderedStrings.add("Ab");
        expectedOrderedStrings.add("aA");
        expectedOrderedStrings.add("aa");

        ArrayList<String> actualOrderedStrings = treeOfStrings.inorder();
        assertNotNull(actualOrderedStrings);
        assertEquals(expectedOrderedStrings, actualOrderedStrings);

    }

    @Test
    void givenCharacters_whenAdding_thenSuccess() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("A");
        treeOfStrings.add("a");
        treeOfStrings.add("b");
        treeOfStrings.add("B");
        treeOfStrings.add("c");

        ArrayList<String> expectedOrderedStrings = new ArrayList<>();
        expectedOrderedStrings.add("A");
        expectedOrderedStrings.add("B");
        expectedOrderedStrings.add("a");
        expectedOrderedStrings.add("b");
        expectedOrderedStrings.add("c");

        ArrayList<String> actualOrderedStrings = treeOfStrings.inorder();
        assertNotNull(actualOrderedStrings);
        assertEquals(expectedOrderedStrings, actualOrderedStrings);

    }

    @Test
    void givenIncludedIntegers_whenSearching_thenReturnAsExpected() {
        BST<Double> treeOfDoubles = new BST<>();
        treeOfDoubles.add(5.2);
        treeOfDoubles.add(2.3);
        treeOfDoubles.add(-5.0);
        treeOfDoubles.add(10.0);
        treeOfDoubles.add(-11.2);
        assertTrue(treeOfDoubles.search(2.3));
        assertTrue(treeOfDoubles.search(5.2));
        assertTrue(treeOfDoubles.search(10.0));
        assertTrue(treeOfDoubles.search(-11.2));
        assertTrue(treeOfDoubles.search(-5.0));
    }

    @Test
    void givenIncludedStrings_whenSearching_thenReturnAsExpected() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("AB");
        treeOfStrings.add("BA");
        treeOfStrings.add("CD");
        treeOfStrings.add("DC");
        assertTrue(treeOfStrings.search("AB"));
        assertTrue(treeOfStrings.search("BA"));
        assertTrue(treeOfStrings.search("CD"));
        assertTrue(treeOfStrings.search("DC"));
    }

    @Test
    void givenIncludedCharacters_whenSearching_thenReturnAsExpected() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("A");
        treeOfStrings.add("B");
        treeOfStrings.add("a");
        treeOfStrings.add("C");
        assertTrue(treeOfStrings.search("A"));
        assertTrue(treeOfStrings.search("B"));
        assertTrue(treeOfStrings.search("a"));
        assertTrue(treeOfStrings.search("C"));
    }


    @Test
    void givenExcludedInteger_whenSearching_thenReturnAsExpected() {
        BST<Double> treeOfDoubles = new BST<>();
        treeOfDoubles.add(5.2);
        treeOfDoubles.add(2.3);
        treeOfDoubles.add(-5.0);
        treeOfDoubles.add(10.0);
        treeOfDoubles.add(-11.2);
        assertFalse(treeOfDoubles.search(1.0));
    }

    @Test
    void givenExcludedStrings_whenSearching_thenReturnAsExpected() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("AB");
        treeOfStrings.add("BA");
        treeOfStrings.add("CD");
        treeOfStrings.add("DC");
        assertFalse(treeOfStrings.search("A"));
    }

    @Test
    void givenExcludedCharacters_whenSearching_thenReturnAsExpected() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("A");
        treeOfStrings.add("B");
        treeOfStrings.add("a");
        treeOfStrings.add("C");
        assertFalse(treeOfStrings.search("c"));
    }
}