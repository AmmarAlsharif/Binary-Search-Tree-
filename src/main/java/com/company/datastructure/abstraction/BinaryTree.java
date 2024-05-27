package com.company.datastructure.abstraction;

public interface BinaryTree<T> {

  void insert(T data);

  void delete(T data);

  int size();

  boolean contains(T data);

  boolean isEmpty();

  Iterable<T> preOrder();

  Iterable<T> inOrder();

  Iterable<T> postOrder();

  T successor(T data);

  T min();

  T max();
}
