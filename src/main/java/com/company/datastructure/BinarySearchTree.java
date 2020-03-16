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
                current = current.goLeft();
            else
                current = current.goRight();
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
        while (pointer.hasLeft()) {
            pointer = pointer.goLeft();
        }
        return pointer.data;
    }

    @Override
    public T max() {
        if (isEmpty())
            throw new EmptyTreeException();
        Node pointer = root;
        while (pointer.hasRight()) {
            pointer = pointer.goRight();
        }
        return pointer.data;
    }

    private T findSuccessor(T data) {
        Node current = getNodeWithValue(root, data);
        if (current.hasRight()) {
            current = current.goRight();
            while (current.hasLeft()) {
                current = current.goLeft();
            }
            return current.data;
        }
        Node parent = current.parent;
        while (parent != null) {
            if (parent.left == current)
                return parent.left.data;
            current = parent;
            parent = parent.goUp();
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

    @Override
    public boolean isEmpty() {
        return root == null;
    }


    // we need this method to determine the successor node
    // when we delete a Node with 2 children
    private Node successor(Node current) {
        if (current.hasRight()) {
            current = current.goRight();
            while (current.hasLeft()) {
                current = current.goLeft();
            }
            return current;
        } else {
            Node parent = current.parent;
            while (parent != null) {
                if (parent.left == current) break;
                else {
                    current = parent;
                    parent = parent.goUp();
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
        if (nodeToBeDeleted.hasLeft() && nodeToBeDeleted.hasRight()) {
            Node successorNode = successor(nodeToBeDeleted);
            nodeToBeDeleted.data = successorNode.data;
            delete(successorNode);
            return;
        }
        Node target;
        if (nodeToBeDeleted.hasRight())
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

    //      we need this method to delete the successor Node
//     of the node that has 2 children
    private void delete(Node node) {
        if (node.hasRight()) {
            if (node.isLeftChild())
                node.parent.left = node.right;
            if (node.isRightChild())
                node.parent.right = node.right;
            node.right.parent = node.parent;
        }
        if (node.isLeftChild())
            node.parent.left = null;
        else
            node.parent.right = null;
        node.clean();
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
        if (isEmpty())
            throw new EmptyTreeException();
        ArrayList<T> output = new ArrayList<>();
        postOrder(root, output::add);
        return output;
    }

    private void postOrder(Node node, Consumer<T> output) {
        if (node == null)
            return;
        postOrder(node.left, output);
        postOrder(node.right, output);
        output.accept(node.data);
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

        private boolean hasRight() {
            return this.right != null;
        }

        private boolean hasLeft() {
            return this.left != null;
        }

        private boolean isLeftChild() {
            return this.parent.left == this;
        }

        private boolean isRightChild() {
            return this.parent.right == this;
        }

        private Node goLeft() {
            return this.left;
        }

        private Node goRight() {
            return this.right;
        }

        private Node goUp() {
            return this.parent;
        }

        private boolean isLeafNode() {
            return this.left == null && this.right == null;
        }

        private boolean isLessThan(Node node) {
            return this.data.compareTo(node.data) < 0;
        }
    }
}
