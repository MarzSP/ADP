

# Verbeteringen
1. De O(n²) in ListInOrder. Dit is op te lossen voor een functie te maken om de lijst aan te maken, en 1 functie te maken om te lijst te vullen.
- Dit brengt de TC van O(n²) naar O(n).
- Nieuwe functies:
````
public List<T> toSortedList() {
    List<T> list = new ArrayList<>();
    inOrder(root, list);
    return list;
}

private void inOrder(node, list) {
    if (node == null) return;
    inOrder(node.left, list);
    list.add(node.value);
    inOrder(node.right, list);
}
````
