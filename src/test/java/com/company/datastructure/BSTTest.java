package com.company.datastructure;

import com.company.datastructure.exceptions.ValueNotFoundException;
import com.company.datastructure.exceptions.EmptyTreeException;
import com.company.datastructure.exceptions.NoSuccessorException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {


    @Test
    void givenIntegers_whenCallingAdd_thenSuccess() {
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
        assertEquals(expectedOrderedIntegers, actualOrderedIntegers);
    }

    @Test
    void givenStrings_whenCallingAdd_thenSuccess() {
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
        assertEquals(expectedOrderedStrings, actualOrderedStrings);

    }

    @Test
    void givenCharacters_whenCallingAdd_thenSuccess() {
        BST<Character> treeOfStrings = new BST<>();
        treeOfStrings.add('A');
        treeOfStrings.add('a');
        treeOfStrings.add('b');
        treeOfStrings.add('B');
        treeOfStrings.add('c');

        ArrayList<Character> expectedOrderedStrings = new ArrayList<>();
        expectedOrderedStrings.add('A');
        expectedOrderedStrings.add('B');
        expectedOrderedStrings.add('a');
        expectedOrderedStrings.add('b');
        expectedOrderedStrings.add('c');

        ArrayList<Character> actualOrderedStrings = treeOfStrings.inorder();
        assertEquals(expectedOrderedStrings, actualOrderedStrings);

    }

    @Test
    void givenIncludedDoubles_whenCallingContains_thenReturnAsExpected() {
        BST<Double> treeOfDoubles = new BST<>();
        treeOfDoubles.add(5.2);
        treeOfDoubles.add(2.3);
        treeOfDoubles.add(-5.0);
        treeOfDoubles.add(10.0);
        treeOfDoubles.add(-11.2);
        assertTrue(treeOfDoubles.contains(2.3));
        assertTrue(treeOfDoubles.contains(5.2));
        assertTrue(treeOfDoubles.contains(10.0));
        assertTrue(treeOfDoubles.contains(-11.2));
        assertTrue(treeOfDoubles.contains(-5.0));
    }

    @Test
    void givenIncludedStrings_whenCallingContains_thenReturnAsExpected() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("AB");
        treeOfStrings.add("BA");
        treeOfStrings.add("CD");
        treeOfStrings.add("DC");
        assertTrue(treeOfStrings.contains("AB"));
        assertTrue(treeOfStrings.contains("BA"));
        assertTrue(treeOfStrings.contains("CD"));
        assertTrue(treeOfStrings.contains("DC"));
    }

    @Test
    void givenIncludedCharacters_whenCallingContains_thenReturnAsExpected() {
        BST<Character> treeOfStrings = new BST<>();
        treeOfStrings.add('A');
        treeOfStrings.add('B');
        treeOfStrings.add('a');
        treeOfStrings.add('C');
        assertTrue(treeOfStrings.contains('A'));
        assertTrue(treeOfStrings.contains('B'));
        assertTrue(treeOfStrings.contains('a'));
        assertTrue(treeOfStrings.contains('C'));
    }


    @Test
    void givenExcludedDoubles_whenCallingContains_thenReturnAsExpected() {
        BST<Double> treeOfDoubles = new BST<>();
        treeOfDoubles.add(5.2);
        treeOfDoubles.add(2.3);
        treeOfDoubles.add(-5.0);
        treeOfDoubles.add(10.0);
        treeOfDoubles.add(-11.2);
        assertFalse(treeOfDoubles.contains(1.0));
    }

    @Test
    void givenExcludedStrings_whenCallingContains_thenReturnAsExpected() {
        BST<String> treeOfStrings = new BST<>();
        treeOfStrings.add("AB");
        treeOfStrings.add("BA");
        treeOfStrings.add("CD");
        treeOfStrings.add("DC");
        assertFalse(treeOfStrings.contains("A"));
    }

    @Test
    void givenExcludedCharacters_whenCallingContains_thenReturnAsExpected() {
        BST<Character> treeOfStrings = new BST<>();
        treeOfStrings.add('A');
        treeOfStrings.add('B');
        treeOfStrings.add('a');
        treeOfStrings.add('C');
        assertFalse(treeOfStrings.contains('c'));
    }

    @Test
    void givenEmptyTree_whenCallingMin_thenThrowException() {
        BST<Integer> emptyTree = new BST<>();
        assertThrows(EmptyTreeException.class, emptyTree::min);
    }

    @Test
    void givenValidTree_whenCallingMin_thenReturnAsExpected() {
        BST<Character> treeOfCharacters = new BST<>();
        treeOfCharacters.add('b');
        treeOfCharacters.add('C');
        treeOfCharacters.add('g');
        treeOfCharacters.add('b');
        treeOfCharacters.add('a');
        treeOfCharacters.add('A');
        assertEquals('A', treeOfCharacters.min());
    }

    @Test
    void givenEmptyTree_whenCallingMax_thenThrowException() {
        BST<Integer> emptyTree = new BST<>();
        assertThrows(EmptyTreeException.class, emptyTree::max);
    }

    @Test
    void givenValidTree_whenCallingMax_thenReturnAsExpected() {
        BST<Character> treeOfCharacters = new BST<>();
        treeOfCharacters.add('b');
        treeOfCharacters.add('C');
        treeOfCharacters.add('g');
        treeOfCharacters.add('b');
        treeOfCharacters.add('a');
        treeOfCharacters.add('A');
        assertEquals('g', treeOfCharacters.max());
    }

    @Test
    void givenNull_whenCallingSuccessor_thenThrowException() {
        BST<Integer> tree = new BST<>();
        assertThrows(EmptyTreeException.class, () -> tree.successorValueOf(null));
    }

    @Test
    void givenElementWithNoSuccessor_whenCallingSuccessor_thenThrowException() {
        BST<Integer> tree = new BST<>();
        tree.add(1);
        tree.add(5);
        tree.add(4);
        tree.add(2);
        tree.add(3);
        NoSuccessorException thrown = assertThrows(NoSuccessorException.class,
                () -> tree.successorValueOf(5));
        assertEquals("Value \"5\" has no successor", thrown.getMessage());
    }

    @Test
    void givenNoneIncludedValue_whenCallingSuccessor_thenThrowException() {
        BST<Integer> tree = new BST<>();
        tree.add(3);
        tree.add(-6);
        ValueNotFoundException thrown = assertThrows(ValueNotFoundException.class, () -> tree.successorValueOf(5));
        assertEquals("Value \"5\" not found", thrown.getMessage());
    }

    @Test
    void givenEmptyTree_whenCallingDelete_thenThrowException() {
        BST<String> tree = new BST<>();
        assertThrows(EmptyTreeException.class, () -> tree.delete("myString"));
    }

    @Test
    void givenNonIncludedValue_whenDelete_thenThrowException() {
        BST<String> tree = new BST<>();
        tree.add("string1");
        tree.add("string2");
        tree.add("string3");
        ValueNotFoundException thrown = assertThrows(ValueNotFoundException.class, () -> tree.delete("string"));
        assertEquals("Value \"string\" not found", thrown.getMessage());
    }

    @Test
    void givenIncludedValue_whenCallingDelete_thenSuccess() {
        BST<String> tree = new BST<>();
        tree.add("word4");
        tree.add("word1");
        tree.add("word3");
        tree.add("word2");

        ArrayList<String> expectedValues = new ArrayList<>();
        expectedValues.add("word1");
        expectedValues.add("word3");
        expectedValues.add("word4");

        tree.delete("word2");
        ArrayList<String> actualValues = tree.inorder();
        assertEquals(expectedValues, actualValues);
    }
}