package app.searching;


public class BSTDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        System.out.println("Sorted: " + bst.TreeToSortedList());
        System.out.println("Bevat 40? " + bst.contains(40)); // true
        System.out.println("Bevat 90? " + bst.contains(90)); // false
    }
}