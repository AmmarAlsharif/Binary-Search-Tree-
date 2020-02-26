package com.company.datastructure;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> A = new BinarySearchTree<>();
        A.add(15);
        A.add(18);
        A.add(17);
        A.add(20);
        A.add(6);
        A.add(7);
        A.add(13);
        A.add(9);
        A.add(3);
        A.add(2);
        A.add(4);
        System.out.println("Size = " + A.size());
        A.delete(6);
        System.out.println("Size = " + A.size());
        System.out.println("Max = " + A.max() + "\nMin = " + A.min());
        A.inOrder();   System.out.println();
        A.postorder(); System.out.println();
        A.preorder();  System.out.println();


        BinarySearchTree<Character> A2 = new BinarySearchTree<>();
        A2.add('B');
        A2.add('A');
        A2.add('E');
        A2.add('C');
        A2.add('D');
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
