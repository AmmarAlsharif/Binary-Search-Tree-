package com.company.datastructure.abstraction;

public interface BinaryTree<T> {

    void add(T value);
    void delete(T value);
    int size();
    boolean contains(T value);
    boolean isEmpty();
    Iterable<T> preOrder();
    Iterable<T> inOrder();
    Iterable<T> postOrder();
    T successor(T value);
    T min();
    T max();
}
