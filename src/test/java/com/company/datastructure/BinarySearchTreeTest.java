package com.company.datastructure;

import com.company.datastructure.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {


    @Test
    void givenEmptyTree_whenCallingPreOrder_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(EmptyTreeException.class, tree::preOrder);
    }

    @Test
    void givenNonEmptyTree_whenCallingPreOrder_thenSuccess() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        ArrayList<Integer> expected = convertToArrayList(DataProvider.getExpectedPreOrder());
        ArrayList<Integer> actual = tree.preOrder();
        assertEquals(expected, actual);
    }


    @Test
    void givenEmptyTree_whenCallingInOrder_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(EmptyTreeException.class, tree::inOrder);
    }


    @Test
    void givenNonEmptyTree_whenCallingInOrder_thenSuccess() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        ArrayList<Integer> expected = convertToArrayList(DataProvider.expectedInOrderData());
        ArrayList<Integer> actual = tree.inOrder();
        assertEquals(expected, actual);
    }

    @Test
    void givenEmptyTree_whenCallingPostOrder_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(EmptyTreeException.class, tree::postOrder);
    }

    @Test
    void givenNonEmptyTree_whenCallingPostOrder_thenSuccess() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        ArrayList<Integer> expected = convertToArrayList(DataProvider.getExpectedPostOrder());
        ArrayList<Integer> actual = tree.postOrder();
        assertEquals(expected, actual);
    }

    @Test
    void givenNullValue_whenCallingInsert_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(NullValueException.class, () -> tree.insert(null));
    }

    @Test
    void givenIntegers_whenCallingInsert_thenSuccess() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        ArrayList<Integer> expected = convertToArrayList(DataProvider.expectedInOrderData());
        ArrayList<Integer> actualOrderedIntegers = tree.inOrder();
        assertEquals(expected, actualOrderedIntegers);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    void givenDuplicateValue_whenCallingInsert_thenThrowException(int data) {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        assertThrows(DuplicateValueException.class, () -> tree.insert(data));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    void givenExistingData_whenCallingContains_thenReturnAsExpected(int data) {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        assertTrue(tree.contains(data));
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 14, 16, 17, 18, 19})
    void givenExcludedData_whenCallingContains_thenReturnAsExpected(int data) {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        assertFalse(tree.contains(data));
    }

    @Test
    void givenEmptyTree_whenCallingMin_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(EmptyTreeException.class, tree::min);
    }

    @Test
    void givenValidTree_whenCallingMin_thenReturnAsExpected() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        assertEquals(1, tree.min());
    }

    @Test
    void givenEmptyTree_whenCallingMax_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(EmptyTreeException.class, tree::max);
    }

    @Test
    void givenValidTree_whenCallingMax_thenReturnAsExpected() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        assertEquals(13, tree.max());
    }

    @Test
    void givenEmptyTree_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(EmptyTreeException.class, () -> tree.successor(5));
    }

    @Test
    void givenNull_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        assertThrows(IllegalArgumentException.class, () -> tree.successor(null));
    }

    @Test
    void givenDataWithNoSuccessor_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        NoSuccessorException thrown = assertThrows(NoSuccessorException.class,
                () -> tree.successor(13));
        assertEquals("Value \"13\" has no successor", thrown.getMessage());
    }

    @Test
    void givenNoneExistingData_whenCallingSuccessor_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        ValueNotFoundException thrown = assertThrows(ValueNotFoundException.class, () -> tree.successor(14));
        assertEquals("Value \"14\" not found", thrown.getMessage());
    }

    @Test
    void givenEmptyTree_whenCallingDelete_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.emptyTree();
        assertThrows(EmptyTreeException.class, () -> tree.delete(2));
    }

    @Test
    void givenNoneExistingData_whenCallingDelete_thenThrowException() {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        ValueNotFoundException thrown = assertThrows(ValueNotFoundException.class,
                () -> tree.delete(14));
        assertEquals("Value \"14\" not found", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    void givenExistingData_whenCallingDelete_thenSuccess(int data) {
        BinarySearchTree<Integer> tree = DataProvider.treeOfIntegers();
        ArrayList<Integer> expected = convertToArrayListExcluding(DataProvider.expectedInOrderData(), data);
        tree.delete(data);
        ArrayList<Integer> actualValues = tree.inOrder();
        assertEquals(expected, actualValues);
    }

    private ArrayList<Integer> convertToArrayList(int[] data) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int datum : data) {
            list.add(datum);
        }
        return list;
    }

    private ArrayList<Integer> convertToArrayListExcluding(int[] data, int excludedValue) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int datum : data) {
            if (datum == excludedValue)
                continue;
            list.add(datum);
        }
        return list;
    }
}