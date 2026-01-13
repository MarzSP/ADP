# BST Analyse
Ik heb een basic BST geimplementeerd met de volgende functies:
- Een nested TreeNode class die de waarde en pointers naar linker en rechter kinderen bevat. Deze is nested omdat hij alleen binnen de BST klasse gebruikt wordt.
Het is geen los concept buiten de BST om, het is een implementatie detail.
- 
### Insert(T value): Voegt een waarde toe aan de boom.
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

### TreeToSortedList() & InOrderFill(TreeNode<T> node, List<T> out):
Geeft een lijst terug met alle waarden in de boom, gesorteerd van klein naar groot.
1. Maak een lege lijst aan.
2. Roep de helper functie inOrderFill aan met de root TreeNode en de lege lijst. Dit gebeurd recursief.
3. inOrderFill functie:
    - Als de TreeNode null is, return.
    - Roep inOrder aan met de linker TreeNode.
    - Voeg de waarde van de huidige TreeNode toe aan de lijst.
    - Roep inOrder aan met de rechter TreeNode.
4. Return de lijst.

TC: O(n²) in het slechtste geval (bijv. als de boom een lijst wordt).
SC: O(n) voor de lijst die we maken.

### Find(T value), FindMin, FindMax: Controleert of een waarde in de boom zit.
1. Begin bij de root TreeNode.
2. Vergelijk de waarde met de huidige TreeNode.
3. int comparable = waarde vergelijken met huidige TreeNode
    - T < TreeNode.value? (negatief getal) Ga naar links.
    - T > TreeNode.value? (positief getal) Ga naar rechts.
    - T == TreeNode.value? Return true.
4. Als we bij een null TreeNode komen, return false.
5. Herhaal stap 2-4 totdat we de waarde vinden of bij een null TreeNode komen.

TC: Avg: O(log n), Worst: O(n) wanneer de boom scheef is gegroeid en eigenlijk een lijst is. Best case is O(1) wanneer waarde root is.

### deleteNode(T value): Verwijdert een waarde uit de boom.
If(node == null) return null; waarde niet gevonden

1. Zoek de TreeNode met de comparison logica zoals in Find.
2. Bij comparison (0) is de waarde gevonden:
    - Geen kinderen: return null (verwijder de node).
    - Eén kind: return het niet-null kind (vervang de node door zijn kind).
    - Twee kinderen:
        - Zoek de inorder successor (min waarde in de rechter subtree).
        - Kopieer de waarde van de inorder successor naar de huidige node.
        - Verwijder de inorder successor uit de rechter subtree.
3. We moeten node teruggeven zodat de ouder node zijn pointer kan bijwerken en omdat root mogelijk verandert of verwijderd wordt.

### remove(T value): Wrapper voor deleteNode.
1. Roep deleteNode aan met de root en de waarde.
2. Houdt de size van de boom bij en controleer of de waarde daadwerkelijk verwijderd is (door te checken of de size veranderd is)
3. Return true als de waarde verwijderd is, anders false.

# Verbeteringen
- Duplicatie verminderen: findNodeAndParent(T value) helper maken zodat we niet steeds dezelfde code hebben in Find en Remove.
- InOrderFill is recursief, wat kan leiden tot stack overflow bij diepe bomen. Iteratieve aanpak zou dit kunnen voorkomen.
Iteratief met een arrayList als stack.
Time Complexity: O(n) altijd omdat elke node 1x bezocht wordt
Space Complexity: O(n) maar de extra stack space is O(h) waarbij h de hoogte van de boom is
````
private void inorderFillIterative(TreeNode<T> start, List<T> out) {
    List<TreeNode<T>> stack = new ArrayList<>();
    TreeNode<T> current = start;

    while (current != null || !stack.isEmpty()) {

        // 1. Ga zo ver mogelijk naar links
        while (current != null) {
            stack.add(current);              // push
            current = current.left;
        }

        // 2. Pak de bovenste node van de stack
        TreeNode<T> node = stack.remove(stack.size() - 1); // pop
        out.add(node.value);

        // 3. Ga naar rechts
        current = node.right;
    }
}

```` 
