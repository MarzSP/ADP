## Binary search
Een binary search algoritme is een manier om een element snel te vinden in een gesorteerde lijst.
Het werkt door herhaaldelijk het zoekveld te halveren totdat het gewenste element is gevonden (of anders leeg is)

Er wordt gewerkt met twee grenzen, left en right, die het huidige zoekveld definieren.
In elke iteratie wordt het middelste element (mid) van het huidige zoekveld vergeleken met het gezochte element (target).

Als het middelste element gelijk is aan het gezochte element, is de zoekopdracht geslaagd.<br><br>
Als het gezochte element kleiner is dan het middelste element, wordt de rechtergrens verplaatst naar het midden min 1.<br><br>
Als het gezochte element groter is dan het middelste element, wordt de linkergrens verplaatst naar het midden plus 1.<br><br>
Dit proces wordt herhaald totdat het element is gevonden of totdat de linkergrens groter is dan de rechtergrens, wat dus betekent dat het element niet in de lijst aanwezig is.

### Time Complexity
De tijdcomplexiteit van een binary search is O(log n), waarbij n het aantal elementen in de lijst is.
Dit komt doordat bij elke stap het zoekveld wordt gehalveerd, waardoor het aantal mogelijke posities voor het gezochte element snel afneemt.

Het beste scenario is O(1) omdat de target precies op de middelste positie kan staan.
Het slechtste scenario is O(log n) omdat het algoritme in elke stap het zoekveld halveert totdat het element is gevonden of het zoekveld leeg is.


### Verbeterpunten
Een binary search is heel erg efficient. Maar het vraagt wel dat een lijst al gesorteerd is. Een niet gesorteerde lijst levert verkeerde resultaten op.
Dus het is een verbeterpunt dat er ook gechecked wordt of een lijst al gesorteerd is of niet.
Dit kan worden gedaan met iets van:

```java
public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
    for (int i = 1; i < list.size(); i++) {
        if (list.get(i - 1).compareTo(list.get(i)) > 0) {
            return false;
       }
    return true;
    }
}
```


