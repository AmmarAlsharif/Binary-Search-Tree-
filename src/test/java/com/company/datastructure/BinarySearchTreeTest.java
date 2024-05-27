package com.company.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.company.datastructure.exceptions.DuplicateValueException;
import com.company.datastructure.exceptions.EmptyTreeException;
import com.company.datastructure.exceptions.NoSuccessorException;
import com.company.datastructure.exceptions.NullValueException;
import com.company.datastructure.exceptions.ValueNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BinarySearchTreeTest {

  private static final int[] TREE_INTEGERS = {10, 6, 8, 5, 9, 1, 2, 7, 3, 4, 13, 12, 11};
  private static final List<Integer> expectedInOrder =
      List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
  private static final List<Integer> expectedPreOrder =
      List.of(10, 6, 5, 1, 2, 3, 4, 8, 7, 9, 13, 12, 11);
  private static final List<Integer> expectedPostOrder =
      List.of(4, 3, 2, 1, 5, 7, 9, 8, 6, 11, 12, 13, 10);
  private static final BinarySearchTree<Integer> emptyTree = new BinarySearchTree<>();
  private final BinarySearchTree<Integer> tree = new BinarySearchTree<>();

  @BeforeEach
  void init() {
    for (int data : TREE_INTEGERS) {
      tree.insert(data);
    }
  }

  @Nested
  class PreOrder {

    @Test
    void shouldReturnCorrectPreOrder() {
      ArrayList<Integer> actual = tree.preOrder();
      assertEquals(expectedPreOrder, actual);
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, emptyTree::preOrder);
    }
  }

  @Nested
  class InOrder {

    @Test
    void shouldReturnCorrectInOrder() {
      ArrayList<Integer> actual = tree.inOrder();
      assertEquals(expectedInOrder, actual);
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, emptyTree::inOrder);
    }
  }

  @Nested
  class PostOrder {

    @Test
    void shouldReturnCorrectPostOrder() {
      ArrayList<Integer> actual = tree.postOrder();
      assertEquals(expectedPostOrder, actual);
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, emptyTree::postOrder);
    }
  }

  @Nested
  class Insert {

    @Test
    void shouldInsertDataToTree() {
      BinarySearchTree<Integer> tree = new BinarySearchTree<>();
      for (int data : TREE_INTEGERS) {
        tree.insert(data);
      }
      ArrayList<Integer> actualOrderedIntegers = tree.inOrder();
      assertEquals(expectedInOrder, actualOrderedIntegers);
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
      assertThrows(NullValueException.class, () -> tree.insert(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    void shouldThrowExceptionOnDuplicateValue(int data) {
      assertThrows(DuplicateValueException.class, () -> tree.insert(data));
    }
  }

  @Nested
  class Contains {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    void shouldReturnTrueWhenValueIsFound(int data) {
      assertTrue(tree.contains(data));
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 14, 16, 17, 18, 19})
    void shouldReturnFalseWhenValueIsNotFound(int data) {
      assertFalse(tree.contains(data));
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, () -> emptyTree.contains(5));
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
      assertThrows(NullValueException.class, () -> tree.contains(null));
    }
  }

  @Nested
  class Min {

    @Test
    void shouldReturnMinValue() {
      assertEquals(1, tree.min());
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, emptyTree::min);
    }
  }

  @Nested
  class Max {

    @Test
    void shouldReturnMaxValue() {
      assertEquals(13, tree.max());
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, emptyTree::max);
    }
  }

  @Nested
  class Successor {

    @ParameterizedTest
    @MethodSource("successorTestData")
    void shouldReturnSuccessorForTheGivenValue(int data, int expectedSuccessor) {
      assertEquals(expectedSuccessor, tree.successor(data));
    }

    @Test
    void shouldThrowExceptionWhenTheGivenValueIsNotFoundInTheTree() {
      ValueNotFoundException thrown =
          assertThrows(ValueNotFoundException.class, () -> tree.successor(14));
      assertEquals("Value \"14\" not found", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTheGivenValueHasNoSuccessor() {
      NoSuccessorException thrown =
          assertThrows(NoSuccessorException.class, () -> tree.successor(13));
      assertEquals("Value \"13\" has no successor", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
      assertThrows(IllegalArgumentException.class, () -> tree.successor(null));
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, () -> emptyTree.successor(5));
    }

    static Stream<Arguments> successorTestData() {
      return Stream.of(
          Arguments.of(5, 6),
          Arguments.of(7, 8),
          Arguments.of(9, 10),
          Arguments.of(10, 11),
          Arguments.of(11, 12),
          Arguments.of(12, 13));
    }
  }

  @Nested
  class Delete {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    void shouldDeleteDataFromTree(int data) {
      ArrayList<Integer> expected = new ArrayList<>(expectedInOrder);
      expected.remove((Integer) data);
      tree.delete(data);
      ArrayList<Integer> actualValues = tree.inOrder();
      assertEquals(expected, actualValues);
    }

    @Test
    void shouldThrowExceptionWhenValueIsNotFound() {
      ValueNotFoundException thrown =
          assertThrows(ValueNotFoundException.class, () -> tree.delete(14));
      assertEquals("Value \"14\" not found", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTreeIsEmpty() {
      assertThrows(EmptyTreeException.class, () -> emptyTree.delete(2));
    }
  }
}
