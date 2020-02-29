package com.company.datastructure;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> A = new BinarySearchTree<>();
        A.insert(15);
        A.insert(18);
        A.insert(17);
        A.insert(20);
        A.insert(6);
        A.insert(7);
        A.insert(13);
        A.insert(9);
        A.insert(3);
        A.insert(2);
        A.insert(4);
        System.out.println("Size = " + A.size());
        A.delete(6);
        System.out.println("Size = " + A.size());
        System.out.println("Max = " + A.max() + "\nMin = " + A.min());
        A.inOrder();   System.out.println();
        A.postorder(); System.out.println();
        A.preorder();  System.out.println();


        BinarySearchTree<Character> A2 = new BinarySearchTree<>();
        A2.insert('B');
        A2.insert('A');
        A2.insert('E');
        A2.insert('C');
        A2.insert('D');
        System.out.println("Size = " + A2.size());
        A2.delete('B');
        System.out.println("Size = " + A2.size());
        System.out.println("Max = " + A2.max() + "\nMin = " + A2.min());
        A2.inOrder();   System.out.println();
        A2.postorder(); System.out.println();
        A2.preorder();  System.out.println();

/*
        OutPut:
        Size = 11
        Size = 10
        Max = 20
        Min = 2
        2 3 4 7 9 13 15 17 18 20 
        2 4 3 9 13 7 17 20 18 15 
        15 7 3 2 4 13 9 18 17 20 
        Size = 5
        Size = 4
        Max = E
        Min = A
        A C D E 
        A D E C 
        C A E D        
*/
    }
}
