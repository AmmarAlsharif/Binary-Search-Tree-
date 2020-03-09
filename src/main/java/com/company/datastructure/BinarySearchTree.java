package com.company.datastructure;

import com.company.datastructure.abstraction.BinaryTree;
import com.company.datastructure.exceptions.*;

import java.util.ArrayList;
import java.util.function.Consumer;


public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {
    private Node root;

    private int size;

    @Override
    public void insert(T data) {
        if (data == null)
            throw new NullValueException();
        if (isEmpty()) {
            root = new Node(data);
            return;
        }
        Node current = root;
        Node parent = current;
        while (current != null) {
            parent = current;
            if (data.compareTo(current.data) == 0)
                throw new DuplicateValueException();

            if (data.compareTo(current.data) < 0)
                current = goLeft(current);
            else
                current = goRight(current);
        }
        if (data.compareTo(parent.data) < 0) {
            parent.left = new Node(data);
            parent.left.parent = parent;
        } else {
            parent.right = new Node(data);
            parent.right.parent = parent;
        }
        size++;
    }

    @Override
    public boolean contains(T data) {
        return contains(root, data);
    }

    private boolean contains(Node root, T data) {
        if (root == null)
            return false;
        if (data.compareTo(root.data) == 0)
            return true;
        if (data.compareTo(root.data) < 0)
            return contains(root.left, data);
        return contains(root.right, data);
    }

    // we need this function in successor function
    private Node getNodeWithValue(Node node, T data) throws ValueNotFoundException {
        if (node == null)
            throw new ValueNotFoundException("Value \"" + data + "\" not found");
        if (data.compareTo(node.data) == 0)
            return node;
        if (data.compareTo(node.data) < 0)
            return getNodeWithValue(node.left, data);
        return getNodeWithValue(node.right, data);
    }

    @Override
    public T min() {
        if (isEmpty())
            throw new EmptyTreeException();
        Node pointer = root;
        while (pointer.isHasLeft()) {
            pointer = goLeft(pointer);
        }
        return pointer.data;
    }

    @Override
    public T max() {
        if (isEmpty())
            throw new EmptyTreeException();
        Node pointer = root;
        while (pointer.isHasRight()) {
            pointer = goRight(pointer);
        }
        return pointer.data;
    }

    private T findSuccessor(T data) {
        Node current = getNodeWithValue(root, data);
        if (current.isHasRight()) {
            current = goRight(current);
            while (current.isHasLeft()) {
                current = goLeft(current);
            }
            return current.data;
        }
        Node parent = current.parent;
        while (parent != null) {
            if (parent.left == current)
                return parent.left.data;
            current = parent;
            parent = goUp(parent);
        }
        throw new NoSuccessorException("Value \"" + data + "\" has no successor");
    }

    @Override
    public T successor(T data) throws EmptyTreeException, ValueNotFoundException {
        if (isEmpty())
            throw new EmptyTreeException();
        if (data == null)
            throw new IllegalArgumentException();
        return findSuccessor(data);
    }

    private Node goLeft(Node node) {
        return node.left;
    }

    private Node goRight(Node node1) {
        node1 = node1.right;
        return node1;
    }

    private Node goUp(Node pointer2) {
        pointer2 = pointer2.parent;
        return pointer2;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }


    // we need this method to determine the successor node
    // when we delete a Node with 2 children
    private Node successor(Node current) {
        if (current.isHasRight()) {
            current = goRight(current);
            while (current.isHasLeft()) {
                current = goLeft(current);
            }
            return current;
        } else {
            Node parent = current.parent;
            while (parent != null) {
                if (parent.left == current) break;
                else {
                    current = parent;
                    parent = goUp(parent);
                }
            }
            return parent;
        }
    }

    @Override
    public void delete(T data) {
        if (isEmpty())
            throw new EmptyTreeException();
        Node nodeToBeDeleted = getNodeWithValue(root, data);
        if (nodeToBeDeleted.isHasLeft() && nodeToBeDeleted.isHasRight()) {
            Node successorNode = successor(nodeToBeDeleted);
            nodeToBeDeleted.data = successorNode.data;
            deleteN(successorNode);
            return;
        }

        Node target;
        if (nodeToBeDeleted.isHasRight())
            target = nodeToBeDeleted.right;
        else
            target = nodeToBeDeleted.left;

        if (nodeToBeDeleted.isLessThan(nodeToBeDeleted.parent))
            nodeToBeDeleted.parent.left = target;
        else
            nodeToBeDeleted.parent.right = target;

        if (!nodeToBeDeleted.isLeafNode())
            target.parent = nodeToBeDeleted.parent;
        nodeToBeDeleted.clean();
        size--;
    }

    //     we need this method to delete the successor Node
//     of the node that has 2 children
    private void deleteN(Node ptr) {
        if (ptr.right == null) {
            ptr = goUp(ptr);
            ptr.right = null;
        } else {
            Node ptr2 = ptr.parent;
            if (ptr == ptr2.left) {
                ptr2.left = ptr.right;
                ptr2.left.parent = ptr2;
            } else {
                ptr2.right = ptr.right;
                ptr2.right.parent = ptr2;
            }
        }
    }

    @Override
    public ArrayList<T> inOrder() {
        if (isEmpty())
            throw new EmptyTreeException();
        ArrayList<T> result = new ArrayList<>();
        inOrder(root, result::add);
        return result;
    }


    private void inOrder(Node node, Consumer<T> output) {
        if (node == null)
            return;
        inOrder(node.left, output);
        output.accept(node.data);
        inOrder(node.right, output);
    }

    @Override
    public ArrayList<T> preOrder() {
        if (isEmpty())
            throw new EmptyTreeException();
        ArrayList<T> result = new ArrayList<>();
        preOrder(root, result::add);
        return result;
    }

    private void preOrder(Node node, Consumer<T> output) {
        if (node == null)
            return;
        output.accept(node.data);
        preOrder(node.left, output);
        preOrder(node.right, output);
    }

    @Override
    public ArrayList<T> postOrder() {
        // TODO
        return null;
    }

    public int size() {
        return size;
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private T data;

        private Node(T data) {
            this.data = data;
        }

        private void clean() {
            this.right = null;
            this.left = null;
            this.parent = null;
        }

        private boolean isHasRight() {
            return this.right != null;
        }

        private boolean isHasLeft() {
            return this.left != null;
        }

        private boolean isLeafNode() {
            return this.left == null && this.right == null;
        }

        private boolean isLessThan(Node node) {
            return this.data.compareTo(node.data) < 0;
        }
    }
}
