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

            if (data.compareTo(current.data) < 0) {
                current = goLeft(current);
                continue;
            }
            if (data.compareTo(current.data) > 0)
                current = goRight(current);
        }

        if (data.compareTo(parent.data) < 0)
            parent.left = new Node(data);
        else
            parent.right = new Node(data);

        size++;
    }

    // search for an element k in the tree
    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node root, T element) {
        if (root == null)
            return false;
        if (element.compareTo(root.data) == 0)
            return true;
        if (element.compareTo(root.data) < 0)
            return contains(root.left, element);
        return contains(root.right, element);
    }

    // we need this function in successor function
    private Node getNodeWithValue(Node node, T value) throws ValueNotFoundException {
        if (node == null)
            throw new ValueNotFoundException("Value \"" + value + "\" not found");
        if (value.compareTo(node.data) == 0)
            return node;
        if (value.compareTo(node.data) < 0)
            return getNodeWithValue(node.left, value);
        return getNodeWithValue(node.right, value);
    }

    // find the minimum element in the tree
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

    // find the maximum element in the tree
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

    // find the successor element of value in the tree
    @Override
    public T successor(T value) throws EmptyTreeException, ValueNotFoundException {
        if (isEmpty())
            throw new EmptyTreeException();
        Node node1 = getNodeWithValue(root, value);
        if (node1.isHasRight()) {
            node1 = goRight(node1);
            while (node1.isHasLeft()) {
                node1 = goLeft(node1);
            }
            return node1.data;
        }
        Node pointer2 = node1.parent;
        while (pointer2 != null) {
            if (pointer2.left == node1)
                return pointer2.left.data;
            node1 = pointer2;
            pointer2 = goUp(pointer2);
        }
        throw new NoSuccessorException("Value \"" + value + "\" has no successor");
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
    private Node getNodeWithValue(T x) {
        Node ptr = getNodeWithValue(root, x);
        if (ptr.isHasRight()) {
            ptr = goRight(ptr);
            while (ptr.isHasLeft()) {
                ptr = goLeft(ptr);
            }
            return ptr;
        } else {
            Node ptr2 = ptr.parent;
            while (ptr2 != null) {
                if (ptr2.left == ptr) break;
                else {
                    ptr = ptr2;
                    ptr2 = goUp(ptr2);
                }
            }

            if (ptr2 == null)
                return null;
            else
                return ptr2;
        }
    }

    // delete an value from the tree
    @Override
    public void delete(T value) {
        if (isEmpty())
            throw new EmptyTreeException();
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (value.compareTo(current.data) < 0)
                current = goRight(current);
            if (value.compareTo(current.data) > 0)
                current = goLeft(current);
            if (value.compareTo(current.data) == 0)
                break;
        }
        if (current == null)
            return;
        if (value.compareTo(parent.data) > 0) {
            // TODO complete fixing this method
        }


        Node ptr = getNodeWithValue(root, value);
        if (!ptr.isHasLeft() && !ptr.isHasRight()) {
            if (ptr == root) root = null;
            else {
                Node ptr2 = ptr.parent;
                if (ptr2.left == ptr)
                    ptr2.left = null;
                else
                    ptr2.right = null;
            }
            size--;
            return;
        }
        if (!ptr.isHasLeft() || !ptr.isHasRight()) {
            if (ptr == root && ptr.isHasLeft()) {
                root = goLeft(root);
                root.parent = null;
                return;
            }
            if (ptr == root) {
                root = goRight(root);
                root.parent = null;
                return;
            }
            Node ptr2 = ptr.parent;
            if (ptr == ptr2.left) {
                if (ptr.isHasLeft())
                    ptr2.left = ptr.left;
                else
                    ptr2.left = ptr.right;
                ptr2.left.parent = ptr2;
                return;
            }
            if (ptr.isHasLeft())
                ptr2.right = ptr.left;
            else
                ptr2.right = ptr.right;
            ptr2.right.parent = ptr2;
            size--;
            return;
        }
        Node succ = getNodeWithValue(value);
        ptr.data = succ.data;
        deleteN(succ);
        size--;
    }

    // we need this method to delete the successor Node
    // of the node that has 2 children
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

    // preorder function
    public void preorder() {
        class pre {
        }
        new pre() {
            public void preorder(Node ptr) {
                if (ptr == null) {
                } else {
                    System.out.print(ptr.data + " ");
                    preorder(ptr.left);
                    preorder(ptr.right);
                }
            }
        }.preorder(root);
    }

    // inorder function
    public ArrayList<T> inOrder() {
        ArrayList<T> orderedItems = new ArrayList<>();
        inOrder(root, orderedItems);
        return orderedItems;
    }


    private void inOrder(Node ptr, ArrayList<T> orderedItems) {
        if (ptr == null) {
        } else {
            inOrder(ptr.left, orderedItems);
            orderedItems.add((T) ptr.data);
            inOrder(ptr.right, orderedItems);
        }
    }

    // postorder function
    public void postorder() {
        class post {
        }
        new post() {
            public void postorder(Node ptr) {
                if (ptr == null) {
                } else {
                    postorder(ptr.left);
                    postorder(ptr.right);
                    System.out.print(ptr.data + " ");
                }
            }
        }.postorder(root);
    }

    @Override
    public ArrayList<T> preOrder() {
        if (isEmpty())
            throw new EmptyTreeException();
        ArrayList<T> result = new ArrayList<>();
        preOrder(result::add, root);
        return result;
    }

    private void preOrder(Consumer<T> output, Node node) {
        if (node == null)
            return;
        output.accept(node.data);
        preOrder(output, node.left);
        preOrder(output, node.right);
    }

    @Override
    public ArrayList<T> postOrder() {
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

        private boolean isHasRight() {
            return this.right != null;
        }

        private boolean isHasLeft() {
            return this.left != null;
        }
    }
}
