package app.searching;

public class BSTDemo {

    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        int[] values = { 8, 3, 10, 1, 6, 14, 4, 7, 13 };

        System.out.println("Insert values:");
        for (int v : values) {
            System.out.print(v + " ");
            bst.insert(v);
        }

        System.out.println("\n");

        System.out.println("Size: " + bst.size());

        System.out.println("Contains 7?  " + bst.contains(7));
        System.out.println("Contains 2?  " + bst.contains(2));

        System.out.println("Min value: " + bst.findMin());
        System.out.println("Max value: " + bst.findMax());

        System.out.println("In-order traversal (sorted):");
        System.out.println(bst.inOrder());
    }
}
