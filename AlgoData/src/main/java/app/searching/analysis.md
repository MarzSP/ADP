## Binary Search Analyse

Ik heb een binary search geïmplementeerd in de class BinarySearch.
Binary search werkt op een gesorteerde array (oplopend). In plaats van 1 voor 1 te zoeken, kijkt het algoritme steeds naar het midden van het zoekgebied.

De regel is:

- is target gelijk aan het midden? dan gevonden

- is target kleiner dan het midden? dan zoek verder in de linker helft

- is target groter dan het midden? dan zoek verder in de rechter helft

Hierdoor wordt het zoekgebied elke stap gehalveerd.

## Functies in BinarySearch

- binarySearch(int[] a, int target):
Zoekt 'target' in een gesorteerde array en geeft de index terug, of -1 als het element niet bestaat.

### Time Complexity

Bij binary search hangt de performance af van hoeveel keer je de array kunt halveren.

Elke iteratie halveert het zoekgebied:
Best case: O(1)
Als target meteen gelijk is aan het midden-element bij de eerste vergelijking.

Worst case: O(log n)
Als target niet aanwezig is, of pas na meerdere halveringen gevonden wordt.
Dan doe je ongeveer log2(n) stappen.


Het verschil zit in wanneer je de waarde vindt (of niet vindt):

Best case: 1 vergelijking (midden is raak) dus constant.

Worst case: meerdere vergelijkingen, telkens halveren dus groeit langzaam mee met log n.

Dus: binary search schaalt goed, omdat de dataset steeds wordt gehalveerd.

### Execution time:

In BinarySearchDemo meet ik de uitvoeringstijd met System.nanoTime().

Belangrijk hierbij:

Voor een kleine array is het verschil nauwelijks zichtbaar.

Bij een grote array zie je dat zoeken nog steeds snel blijft, omdat het aantal stappen beperkt blijft tot ongeveer log2(n).

Execution time: groei is logaritmisch.

## Verbeterpunten:
1) Voorwaarde: array moet gesorteerd zijn

Binary search werkt alleen correct als de array oplopend gesorteerd is. Als de data nog niet gesorteerd is, moet je eerst sorteren.
Sorteren kost meestal O(n log n) en kan dus “duurder” zijn dan de zoekactie zelf.

Verbetering:

- Als er veel zoekacties zijn: sorteer één keer en zoek daarna vaak.

- Als er weinig zoekacties zijn en data verandert steeds: kan andere datastructuur kan beter zijn (bijv. HashMap want O(1).

2) Iteratief i.p.v. recursief

Mijn implementatie is iteratief. Dat voorkomt overhead van extra function calls en stack usage.
Dit is klein verschil, maar wel goed voor performance en eenvoud.

3) Mid-berekening overflow-proof

De manier van berekenen van mid:

left + (right - left) / 2

In plaats van:

(left + right) / 2

Dit voorkomt overflow bij grote indices.