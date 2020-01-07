package com.company.datastructure;

import com.company.datastructure.exceptions.ValueNotFoundException;
import com.company.datastructure.exceptions.EmptyTreeException;
import com.company.datastructure.exceptions.NoSuccessorException;

import java.util.ArrayList;


public class BST<T extends Comparable> {
    private BTNode root;

    private int size;

    // add node to the tree
    public void add(T element) {
        BTNode newNode = new BTNode(element);
        if (isEmpty()) {
            root = newNode;
            return;
        }
        BTNode pointer = root;
        while (pointer != null) {
            if (newNode.data.compareTo(pointer.data) > 0) {
                if (!pointer.isHasRight())
                    break;
                pointer = goRight(pointer);
            } else {
                if (!pointer.isHasLeft())
                    break;
                pointer = goLeft(pointer);
            }
        }
        if (newNode.data.compareTo(pointer.data) > 0) {
            pointer.right = newNode;
            newNode.parent = pointer;
        } else {
            pointer.left = newNode;
            newNode.parent = pointer;
        }
        size++;
    }

    // search for an element k in the tree
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(BTNode root, T element) {
        if (root == null)
            return false;
        if (element.compareTo(root.data) == 0)
            return true;
        if (element.compareTo(root.data) < 0)
            return contains(root.left, element);
        return contains(root.right, element);
    }

    // we need this function in successor function
    private BTNode getNodeWithValue(BTNode startingNode, T value) {
        if (startingNode == null || value.compareTo(startingNode.data) == 0)
            return startingNode;
        if (value.compareTo(startingNode.data) < 0)
            return getNodeWithValue(startingNode.left, value);
        return getNodeWithValue(startingNode.right, value);
    }

    // find the minimum element in the tree
    public T min() {
        if (isEmpty())
            throw new EmptyTreeException();
        BTNode pointer = root;
        while (pointer.isHasLeft()) {
            pointer = goLeft(pointer);
        }
        return pointer.data;
    }

    // find the maximum element in the tree
    public T max() {
        if (isEmpty())
            throw new EmptyTreeException();
        BTNode pointer = root;
        while (pointer.isHasRight()) {
            pointer = goRight(pointer);
        }
        return pointer.data;
    }

    // find the successor element of value in the tree
    public T successorValueOf(T value) {
        if (isEmpty())
            throw new EmptyTreeException();
        BTNode node1 = getNodeWithValue(root, value);
        if (node1 == null)
            throw new ValueNotFoundException("Value \"" + value + "\" not found");
        if (node1.isHasRight()) {
            node1 = goRight(node1);
            while (node1.isHasLeft()) {
                node1 = goLeft(node1);
            }
            return node1.data;
        }
        BTNode pointer2 = node1.parent;
        while (pointer2 != null) {
            if (pointer2.left == node1)
                return pointer2.left.data;
            node1 = pointer2;
            pointer2 = goUp(pointer2);
        }
        throw new NoSuccessorException("Value \"" + value + "\" has no successor");
    }

    private BTNode goLeft(BTNode node1) {
        node1 = node1.left;
        return node1;
    }

    private BTNode goRight(BTNode node1) {
        node1 = node1.right;
        return node1;
    }

    private BTNode goUp(BTNode pointer2) {
        pointer2 = pointer2.parent;
        return pointer2;
    }

    private boolean isEmpty() {
        return root == null;
    }

    // we need this method to determine the successor node
    // when we delete a Node with 2 children
    private BTNode successorN(T x) {
        if (isEmpty()) return null;
        else {
            BTNode ptr = getNodeWithValue(root, x);
            if (ptr.isHasRight()) {
                ptr = goRight(ptr);
                while (ptr.isHasLeft()) {
                    ptr = goLeft(ptr);
                }
                return ptr;
            } else {
                BTNode ptr2 = ptr.parent;
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
    }

    // delete an element x from the tree
    public void delete(T x) {
        if (isEmpty()) System.out.println("No elements to delete");
        else {
            BTNode ptr = getNodeWithValue(root, x);
            if (ptr == null) {
                System.out.println(x + " is not found");
                return;
            }
            if (ptr.left == null && ptr.right == null) {
                if (ptr == root) root = null;
                else {
                    BTNode ptr2 = ptr.parent;
                    if (ptr2.left == ptr)
                        ptr2.left = null;
                    else
                        ptr2.right = null;
                }
                size--;
            } else if (ptr.left == null || ptr.right == null) {
                if (ptr == root && ptr.isHasLeft()) {
                    root = goLeft(root);
                    root.parent = null;
                } else if (ptr == root && ptr.isHasRight()) {
                    root = goRight(root);
                    root.parent = null;
                } else {
                    BTNode ptr2 = ptr.parent;
                    if (ptr == ptr2.left) {
                        if (ptr.isHasLeft()) ptr2.left = ptr.left;
                        else ptr2.left = ptr.right;
                        ptr2.left.parent = ptr2;
                    } else {
                        if (ptr.isHasLeft()) ptr2.right = ptr.left;
                        else ptr2.right = ptr.right;
                        ptr2.right.parent = ptr2;
                    }
                }
                size--;
            } else {
                BTNode succ = successorN(x);
                ptr.data = succ.data;
                deleteN(succ);
                size--;
            }
        }
    }

    // we need this method to delete the successor Node
    // of the node that has 2 children
    private void deleteN(BTNode ptr) {
        if (ptr.right == null) {
            ptr = goUp(ptr);
            ptr.right = null;
        } else {
            BTNode ptr2 = ptr.parent;
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
            public void preorder(BTNode ptr) {
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
    public ArrayList<T> inorder() {
        ArrayList<T> orderedItems = new ArrayList<>();
        inorder(root, orderedItems);
        return orderedItems;
    }

    private void inorder(BTNode ptr, ArrayList<T> orderedItems) {
        if (ptr == null) {
        } else {
            inorder(ptr.left, orderedItems);
            orderedItems.add((T) ptr.data);
            inorder(ptr.right, orderedItems);
        }
    }

    // postorder function
    public void postorder() {
        class post {
        }
        new post() {
            public void postorder(BTNode ptr) {
                if (ptr == null) {
                } else {
                    postorder(ptr.left);
                    postorder(ptr.right);
                    System.out.print(ptr.data + " ");
                }
            }
        }.postorder(root);
    }

    public int size() {
        return size;
    }

    private class BTNode {

        private BTNode left;
        private BTNode right;
        private BTNode parent;
        private T data;

        private BTNode(T data) {
            this.data = data;
        }

        private boolean isHasRight() {
            return this.right != null;
        }

        private boolean isHasLeft() {
            return this.left != null;
        }

        private boolean isHasParent() {
            return this.parent != null;
        }
    }
}
