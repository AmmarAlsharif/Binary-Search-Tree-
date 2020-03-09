package com.company.datastructure;

import com.company.datastructure.exceptions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {


    @Test
    void givenNullValue_whenCallingInsert_thenThrowException() {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();
        assertThrows(NullValueException.class, () -> tree.insert(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABB", "ACB", "ABC"})
    void givenDuplicateValue_whenCallingInsert_thenThrowException(String value) {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("ABB");
        tree.insert("ACB");
        tree.insert("ABC");

        assertThrows(DuplicateValueException.class, () -> tree.insert(value));
    }

    @Test
    void givenIntegers_whenCallingInsert_thenSuccess() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(4);
        tree.insert(6);
        tree.insert(-1);
        tree.insert(0);
        ArrayList<Integer> expectedOrderedIntegers = new ArrayList<>();
        expectedOrderedIntegers.add(-1);
        expectedOrderedIntegers.add(0);
        expectedOrderedIntegers.add(4);
        expectedOrderedIntegers.add(5);
        expectedOrderedIntegers.add(6);
        ArrayList<Integer> actualOrderedIntegers = tree.inOrder();
        assertEquals(expectedOrderedIntegers, actualOrderedIntegers);
    }

    @Test
    void givenStrings_whenCallingAdd_thenSuccess() {
        BinarySearchTree<String> treeOfStrings = new BinarySearchTree<>();
        treeOfStrings.insert("Ab");
        treeOfStrings.insert("aA");
        treeOfStrings.insert("AA");
        treeOfStrings.insert("aa");
        treeOfStrings.insert("AaA");

        ArrayList<String> expectedOrderedStrings = new ArrayList<>();
        expectedOrderedStrings.add("AA");
        expectedOrderedStrings.add("AaA");
        expectedOrderedStrings.add("Ab");
        expectedOrderedStrings.add("aA");
        expectedOrderedStrings.add("aa");

        ArrayList<String> actualOrderedStrings = treeOfStrings.inOrder();
        assertEquals(expectedOrderedStrings, actualOrderedStrings);

    }

    @Test
    void givenCharacters_whenCallingInsert_thenSuccess() {
        BinarySearchTree<Character> treeOfStrings = new BinarySearchTree<>();
        treeOfStrings.insert('A');
        treeOfStrings.insert('a');
        treeOfStrings.insert('b');
        treeOfStrings.insert('B');
        treeOfStrings.insert('c');

        ArrayList<Character> expectedOrderedStrings = new ArrayList<>();
        expectedOrderedStrings.add('A');
        expectedOrderedStrings.add('B');
        expectedOrderedStrings.add('a');
        expectedOrderedStrings.add('b');
        expectedOrderedStrings.add('c');

        ArrayList<Character> actualOrderedStrings = treeOfStrings.inOrder();
        assertEquals(expectedOrderedStrings, actualOrderedStrings);

    }

    @Test
    void givenIncludedDoubles_whenCallingContains_thenReturnAsExpected() {
        BinarySearchTree<Double> treeOfDoubles = new BinarySearchTree<>();
        treeOfDoubles.insert(5.2);
        treeOfDoubles.insert(2.3);
        treeOfDoubles.insert(-5.0);
        treeOfDoubles.insert(10.0);
        treeOfDoubles.insert(-11.2);
        assertTrue(treeOfDoubles.contains(2.3));
        assertTrue(treeOfDoubles.contains(5.2));
        assertTrue(treeOfDoubles.contains(10.0));
        assertTrue(treeOfDoubles.contains(-11.2));
        assertTrue(treeOfDoubles.contains(-5.0));
    }

    @Test
    void givenIncludedStrings_whenCallingContains_thenReturnAsExpected() {
        BinarySearchTree<String> treeOfStrings = new BinarySearchTree<>();
        treeOfStrings.insert("AB");
        treeOfStrings.insert("BA");
        treeOfStrings.insert("CD");
        treeOfStrings.insert("DC");
        assertTrue(treeOfStrings.contains("AB"));
        assertTrue(treeOfStrings.contains("BA"));
        assertTrue(treeOfStrings.contains("CD"));
        assertTrue(treeOfStrings.contains("DC"));
    }

    @Test
    void givenIncludedCharacters_whenCallingContains_thenReturnAsExpected() {
        BinarySearchTree<Character> treeOfStrings = new BinarySearchTree<>();
        treeOfStrings.insert('A');
        treeOfStrings.insert('B');
        treeOfStrings.insert('a');
        treeOfStrings.insert('C');
        assertTrue(treeOfStrings.contains('A'));
        assertTrue(treeOfStrings.contains('B'));
        assertTrue(treeOfStrings.contains('a'));
        assertTrue(treeOfStrings.contains('C'));
    }


    @Test
    void givenExcludedDoubles_whenCallingContains_thenReturnAsExpected() {
        BinarySearchTree<Double> treeOfDoubles = new BinarySearchTree<>();
        treeOfDoubles.insert(5.2);
        treeOfDoubles.insert(2.3);
        treeOfDoubles.insert(-5.0);
        treeOfDoubles.insert(10.0);
        treeOfDoubles.insert(-11.2);
        assertFalse(treeOfDoubles.contains(1.0));
    }

    @Test
    void givenExcludedStrings_whenCallingContains_thenReturnAsExpected() {
        BinarySearchTree<String> treeOfStrings = new BinarySearchTree<>();
        treeOfStrings.insert("AB");
        treeOfStrings.insert("BA");
        treeOfStrings.insert("CD");
        treeOfStrings.insert("DC");
        assertFalse(treeOfStrings.contains("A"));
    }

    @Test
    void givenExcludedCharacters_whenCallingContains_thenReturnAsExpected() {
        BinarySearchTree<Character> treeOfStrings = new BinarySearchTree<>();
        treeOfStrings.insert('A');
        treeOfStrings.insert('B');
        treeOfStrings.insert('a');
        treeOfStrings.insert('C');
        assertFalse(treeOfStrings.contains('c'));
    }

    @Test
    void givenEmptyTree_whenCallingMin_thenThrowException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThrows(EmptyTreeException.class, tree::min);
    }

    @Test
    void givenValidTree_whenCallingMin_thenReturnAsExpected() {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();
        tree.insert('b');
        tree.insert('C');
        tree.insert('g');
        tree.insert('a');
        tree.insert('A');
        assertEquals('A', tree.min());
    }

    @Test
    void givenEmptyTree_whenCallingMax_thenThrowException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThrows(EmptyTreeException.class, tree::max);
    }

    @Test
    void givenValidTree_whenCallingMax_thenReturnAsExpected() {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();
        tree.insert('b');
        tree.insert('C');
        tree.insert('g');
        tree.insert('a');
        tree.insert('A');
        assertEquals('g', tree.max());
    }

    @Test
    void givenNull_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(4);
        assertThrows(IllegalArgumentException.class, () -> tree.successor(null));
    }

    @Test
    void givenEmptyTree_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThrows(EmptyTreeException.class, () -> tree.successor(5));
    }

    @Test
    void givenElementWithNoSuccessor_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(1);
        tree.insert(5);
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        NoSuccessorException thrown = assertThrows(NoSuccessorException.class,
                () -> tree.successor(5));
        assertEquals("Value \"5\" has no successor", thrown.getMessage());
    }

    @Test
    void givenNoneIncludedValue_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(3);
        tree.insert(-6);
        ValueNotFoundException thrown = assertThrows(ValueNotFoundException.class, () -> tree.successor(5));
        assertEquals("Value \"5\" not found", thrown.getMessage());
    }

    @Test
    void givenEmptyTree_whenCallingDelete_thenThrowException() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThrows(EmptyTreeException.class, () -> tree.delete("myString"));
    }

    @Test
    void givenNonIncludedValue_whenDelete_thenThrowException() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("string1");
        tree.insert("string2");
        tree.insert("string3");
        ValueNotFoundException thrown = assertThrows(ValueNotFoundException.class,
                () -> tree.delete("string"));
        assertEquals("Value \"string\" not found", thrown.getMessage());
    }

    @Test
    void givenIncludedValue_whenCallingDelete_thenSuccess() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("word4");
        tree.insert("word1");
        tree.insert("word3");
        tree.insert("word2");

        ArrayList<String> expectedValues = new ArrayList<>();
        expectedValues.add("word1");
        expectedValues.add("word3");
        expectedValues.add("word4");

        tree.delete("word2");
        ArrayList<String> actualValues = tree.inOrder();
        assertEquals(expectedValues, actualValues);
    }

    @Test
    void givenEmptyTree_whenCallingPreOrder_thenThrowException() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThrows(EmptyTreeException.class, tree::preOrder);
    }

    @Test
    void givenNonEmptyTree_whenCallingPreOrder_thenSuccess() {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();
        tree.insert('B');
        tree.insert('C');
        tree.insert('A');
        tree.insert('D');

        ArrayList<Character> expected = new ArrayList<>();
        expected.add('B');
        expected.add('A');
        expected.add('C');
        expected.add('D');

        ArrayList<Character> actual = tree.preOrder();
        assertEquals(expected, actual);
    }


    @Test
    void givenEmptyTree_whenCallingInOrder_thenThrowException() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThrows(EmptyTreeException.class, tree::inOrder);
    }


    @Test
    void givenNonEmptyTree_whenCallingInOrder_thenSuccess() {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();
        tree.insert('B');
        tree.insert('C');
        tree.insert('A');
        tree.insert('D');

        ArrayList<Character> expected = new ArrayList<>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');

        ArrayList<Character> actual = tree.inOrder();
        assertEquals(expected, actual);
    }
}