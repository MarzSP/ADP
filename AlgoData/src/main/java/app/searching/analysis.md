# BST Analyse
Ik heb een basic BST geimplementeerd met de volgende functies:

### Add(T value): Voegt een waarde toe aan de boom.
1. Boom leeg? Maak nieuwe TreeNode als root. Boom heeft nu size 1.
2. Boom niet leeg? Vergelijk waarde T met huidige TreeNode. Eerst plek zoeken, dan nieuwe TreeNode toevoegen.
int comparable = nieuwe waarde vergelijken met de waarde waar we nu op staan
    - T < TreeNode.value? (negatief getal) Ga naar links. Maak hier een nieuwe TreeNode als plek leeg is.
    - T > TreeNode.value? (positief getal) Ga naar rechts. Maak hier een nieuwe TreeNode als plek leeg is.
    - T == TreeNode.value? Niks doen (geen duplicaten).

Stel root is 3. We voegen hierna TreeNode 1 in.

int comparable = (1.compareTo(3)) => -2 (negatief getal)

We gaan naar links, plek is leeg, dus we maken hier een nieuwe TreeNode met waarde 1.


Nog een linkere TreeNode toevoegen, nu met waarde 2.

int comparable = (2.compareTo(3)) => -1 (negatief getal)

We gaan naar links, plek is niet leeg (waarde 1). We vergelijken opnieuw:

int comparable = (2.compareTo(1)) => 1 (positief getal)

We gaan naar rechts, plek is leeg, dus we maken hier een nieuwe TreeNode met waarde 2.


Nu ziet de boom er zo uit:

````
        3
       /
      1
       \
        2
````

### ListInOrder():
Geeft een lijst terug met alle waarden in de boom, gesorteerd van klein naar groot.
1. Maak een lege lijst aan.
2. Roep de helper functie inOrder aan met de root TreeNode en de lege lijst. Dit gebeurd recursief.
3. inOrder functie:
    - Als de TreeNode null is, return.
    - Roep inOrder aan met de linker TreeNode.
    - Voeg de waarde van de huidige TreeNode toe aan de lijst.
    - Roep inOrder aan met de rechter TreeNode.
4. Return de lijst.

TC: O(n²) in het slechtste geval (bijv. als de boom een lijst wordt).
SC: O(n) voor de lijst die we maken.

### Contains(T value): Controleert of een waarde in de boom zit.
1. Begin bij de root TreeNode.
2. Vergelijk de waarde met de huidige TreeNode.
3. int comparable = waarde vergelijken met huidige TreeNode
    - T < TreeNode.value? (negatief getal) Ga naar links.
    - T > TreeNode.value? (positief getal) Ga naar rechts.
    - T == TreeNode.value? Return true.
4. Als we bij een null TreeNode komen, return false.
5. Herhaal stap 2-4 totdat we de waarde vinden of bij een null TreeNode komen.

TC: Avg: O(log n), Worst: O(n) wanneer de boom scheef is gegroeid en eigenlijk een lijst is. Best case is O(1) wanneer waarde root is.


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

private void inOrder(TreeNode, list) {
    if (TreeNode == null) return;
    inOrderTreeNode.left, list);
    list.add(TreeNode.value);
    inOrder(TreeNode.right, list);
}
````
