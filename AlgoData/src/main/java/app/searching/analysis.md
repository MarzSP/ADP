## BST Analyse
Ik heb een Binary Search Tree (BST) geïmplementeerd met een Node class.
Elke node bevat een value en verwijzingen naar left en right.

De BST volgt deze regel:

- links staan waarden kleiner dan de node

- rechts staan waarden groter dan de node

Hierdoor kan ik efficiënt invoegen en zoeken zonder eerst een lijst te sorteren.


### Functies in BST

**insert(value)**: voegt een waarde toe op de juiste plek

**contains(value)**: zoekt of een waarde aanwezig is

**size()**: geeft het aantal nodes terug

**findMin()**: loopt helemaal naar links voor de kleinste waarde

**findMax()**: loopt helemaal naar rechts voor de grootste waarde

**inOrder()**: traversal die alle waarden in gesorteerde volgorde teruggeeft


#### Time Complexity

Bij een BST hangt de performance vooral af van de hoogte van de boom (h):

Als de boom gebalanceerd is: h ≈ log n

Als de boom scheef is (bijv. oplopend ingevoerd): h ≈ n

**insert(value)**

Best case: O(1)
Bijvoorbeeld: boom is leeg (root wordt direct gezet) of de waarde kan direct als kind geplaatst worden.

Worst case: O(n)
Als de boom een “linked list” wordt (scheef), moet je langs bijna alle nodes.

**contains(value)**

Best case: O(1)
Als de target gelijk is aan de root.

Worst case: O(n)
Als de boom scheef is en je moet bijna alles aflopen.

**findMin() en findMax()**

Best case: O(1)
Als de root al de min/max is (bijv. geen linker of rechter kind).

Worst case: O(n)
Als de boom scheef is, loop je langs een hele ketting.

**inOrder()**

Altijd O(n)
Elke node wordt precies één keer bezocht.

Best case vs Worst case

Het verschil tussen best en worst case komt neer op de vorm van de boom:

Best/average case (meer gebalanceerd):
Invoegen en zoeken volgen ongeveer een pad van lengte log n.

Worst case (scheef):
Invoegen en zoeken gedragen zich als een linked list: je doet ~n stappen.

Een klassiek worst-case voorbeeld is het invoegen van waarden in oplopende volgorde:
1, 2, 3, 4, 5, ...
Dan komt elke nieuwe waarde steeds “helemaal rechts”.