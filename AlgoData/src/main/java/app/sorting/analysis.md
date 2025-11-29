# Sorting Algorithms Analysis
Er zijn twee sorteer algoritmes geimplementeerd: insertion sort en merge sort.
Beide algoritmes sorteren generieke arrays T[] waar T een type is dat Comparable<T> implementeert.
Hierdoor kunnen beide algoritmes elke datasoort sorteren die zichzelf kan vergelijken.

## Insertion Sort
Insertion Sort loopt door de array heen en zet elk element op de juiste plek in het reeds gesorteerde deel links van het element.
Bij elk element kan het eventueel naar links verplaatst totdat het element op de juiste plek zit.

Time Complexity:
- Best Case: O(n) wanneer de array al gesorteerd is. Elk element wordt slechts één keer bekeken en verplaatst.
- Worst Case: O(n^2) wanneer de array in omgekeerde volgorde is. Elk element moet dan n keer verplaatst worden. Hierdoor onstaan er steeds meer vergelijkingen en verschuivingen.

Space Complexity:
- O(1) omdat er geen extra opslagruimte nodig is die afhankelijk is van de inputgrootte.

Functions:
Sort(T[] array): Voert het insertion sort algoritme uit op de gegeven array.
getName(): Returns de naam van het algoritme (voor de duidelijkheid in de output en tests)

## Merge Sort
Merge Sort is een divide-and-conquer algoritme dat de array herhaaldelijk in tweeën splitst totdat de subarrays slechts één element bevatten.
Vervolgens worden deze subarrays samengevoegd in gesorteerde volgorde.

Time Complexity:
- Best Case: O(n log n) omdat de array altijd in tweeën wordt gesplitst, ongeacht de oorspronkelijke volgorde van de elementen.
- Worst Case: O(n log n) om dezelfde reden als de best case.
Merge sort is stabiel en voorspelbaar.

Space Complexity:
- O(n) omdat er extra opslagruimte nodig is voor de tijdelijke arrays (met de gesplitste delen er in) tijdens het samenvoegen van de subarrays.

Functions:
sort(T[] array): Voert het merge sort algoritme uit op de gegeven array
merge(T[] left, T[] right, T[] array): Een hulpfunctie die de twee gesorteerde subarrays samenvoegt in de originele array.
getName(): Returned de naam van het algoritme (voor de duidelijkheid in de output en tests)

## Verbeterpunten
Hoewel beide sorteer algoritmes functioneel zijn, zijn er enkele verbeterpunten die ik zou kunnen bedenken:
- Insertion Sort: De performance wordt vooral bepaald door de inner while-loop, die alle grote elementen naar rechts verschuift.
Wanneer deze lus vaak wordt doorgelopen (bijv. omgekeerde lijst) neemt de uitvoeringstijd quadratisch toe.
Ik zou een insertion sort alleen gebruiken bij kleine arrays dat er niet veel verplaatsingen nodig zijn.

- Merge Sort: maakt in de recursie stap nieuwe tijdelijke arrays aan met copyOfRange. Dit is simpel maar kan leiden tot extra geheugen
gebruik en kopieerwerk. Een optie kan zijn om een herbruikbare tijdelijke array te gebruiken. 