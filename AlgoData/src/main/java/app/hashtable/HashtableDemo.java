package app.hashtable;

/**
 * == START ==
 * size=0
 *
 * == PUT new ==
 * put(a,1) -> old=null
 * put(b,2) -> old=null
 * put(c,3) -> old=null
 * size=3
 *
 * == GET / containsKey ==
 * get(a) = 1
 * get(x) = null
 * containsKey(b) = true
 * containsKey(x) = false
 *
 * == UPDATE existing key ==
 * put(b,20) -> old=2
 * get(b) = 20
 * size (should be same) = 3
 *
 * == REMOVE ==
 * remove(a) -> old=1
 * remove(a) again -> old=null
 * containsKey(a) = false
 * size=2
 *
 * == FORCE RESIZE (implicit) ==
 * size after bulk insert=52
 * get(k42)=42
 * get(k49)=49
 *
 * == CLEAR ==
 * size after clear=0
 * get(b) after clear=null
 * containsKey(k42) after clear=false
 *
 * == END ==
 */
public class HashtableDemo {
    public static void main(String[] args) {
        HashTable<String, Integer> t = new HashTable<>(3); // klein -> sneller resize nodig

        System.out.println("== START ==");
        System.out.println("size=" + t.size());

        // PUT (new)
        System.out.println("\n== PUT new ==");
        System.out.println("put(a,1) -> old=" + t.put("a", 1));
        System.out.println("put(b,2) -> old=" + t.put("b", 2));
        System.out.println("put(c,3) -> old=" + t.put("c", 3));
        System.out.println("size=" + t.size());

        //[0] -> [("c", 3)]
        //[1] -> [("a", 1)]
        //[2] -> [("b", 2)]

        // GET + containsKey
        System.out.println("\n== GET / containsKey ==");
        System.out.println("get(a) = " + t.get("a"));
        System.out.println("get(x) = " + t.get("x"));
        System.out.println("containsKey(b) = " + t.containsKey("b"));
        System.out.println("containsKey(x) = " + t.containsKey("x"));

        // UPDATE (same key)
        System.out.println("\n== UPDATE existing key ==");
        System.out.println("put(b,20) -> old=" + t.put("b", 20));
        System.out.println("get(b) = " + t.get("b"));
        System.out.println("size (should be same) = " + t.size());
        //[0] -> [("c", 3)]
        //[1] -> [("a", 1)]
        //[2] -> [("b", 20)]

        // REMOVE
        System.out.println("\n== REMOVE ==");
        System.out.println("remove(a) -> old=" + t.remove("a"));
        System.out.println("remove(a) again -> old=" + t.remove("a")); // null
        System.out.println("containsKey(a) = " + t.containsKey("a"));
        System.out.println("size=" + t.size());
        //[0] -> [("c", 3)]
        //[1] -> null
        //[2] -> [("b", 20)]

        // Resize snel nodig want kleine lengte
        System.out.println("\n== FORCE RESIZE  ==");
        for (int i = 0; i < 50; i++) {
            t.put("k" + i, i);
        }
        System.out.println("size after bulk insert=" + t.size());
        System.out.println("get(k42)=" + t.get("k42"));
        System.out.println("get(k49)=" + t.get("k49"));

        // CLEAR
        System.out.println("\n== CLEAR ==");
        t.clear();
        System.out.println("size after clear=" + t.size());
        System.out.println("get(b) after clear=" + t.get("b"));
        System.out.println("containsKey(k42) after clear=" + t.containsKey("k42"));

        System.out.println("\n== END ==");
    }
}