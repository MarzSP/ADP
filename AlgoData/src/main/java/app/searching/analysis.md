# BST Analyse
Ik heb een basic BST geimplementeerd met de volgende functies:
- Een nested TreeNode class die de waarde en pointers naar linker en rechter kinderen bevat. Deze is nested omdat hij alleen binnen de BST klasse gebruikt wordt.
Het is geen los concept buiten de BST om, het is een implementatie detail.
- 
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

### Find(T value): Controleert of een waarde in de boom zit.
1. Begin bij de root TreeNode.
2. Vergelijk de waarde met de huidige TreeNode.
3. int comparable = waarde vergelijken met huidige TreeNode
    - T < TreeNode.value? (negatief getal) Ga naar links.
    - T > TreeNode.value? (positief getal) Ga naar rechts.
    - T == TreeNode.value? Return true.
4. Als we bij een null TreeNode komen, return false.
5. Herhaal stap 2-4 totdat we de waarde vinden of bij een null TreeNode komen.

TC: Avg: O(log n), Worst: O(n) wanneer de boom scheef is gegroeid en eigenlijk een lijst is. Best case is O(1) wanneer waarde root is.

### Remove(T value): Verwijdert een waarde uit de boom.
Er zijn 3 gevallen: Een node heeft geen kinderen, 1 kind, of 2 kinderen.

while(currentNode != null) {
    int comparable = waarde vergelijken met huidige TreeNode (negatief of positief getal)
    - T < TreeNode.value? (negatief getal) Ga naar links.
    - T > TreeNode.value? (positief getal) Ga naar rechts.
    - We onthouden de ouder van de huidige node om later de link te kunnen aanpassen.
    - T == TreeNode.value? We hebben de node gevonden die we willen verwijderen.
        1. Geen kinderen: Zet de ouder's link of rechter pointer naar null.
        2. 1 kind: Zet de ouder's link of rechter pointer naar het kind van de te verwijderen node.
        3. 2 kinderen: Zoek de inorder successor (kleinste waarde in de rechter subtree). Vervang de waarde van de te verwijderen node met de inorder successor waarde. 
            Verwijder vervolgens de inorder successor node (die heeft maximaal 1 kind).
Hierna wijst er niks meer naar de verwijderde node, en is deze effectief uit de boom verwijderd. (en wordt door GC opgeruimd)
TC: Avg: O(log n), Worst: O(n) wanneer de boom scheef is gegroeid en eigenlijk een lijst is.

# Verbeteringen

