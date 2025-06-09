import java.util.LinkedList;
/**
 * Implementation of a hash table using linked list chaining for collision resolution.
 * This implementation uses a linked list to store the entries in each cell of the hash table.
 * You should use the LinkedList class from the Java Collections Framework (i.e., java.util.LinkedList) for the implementation.
 * 
 * You should use the KeyValueEntry class to store the key-value pairs.
 * The hash table uses the (existing) hashCode() method of the key to determine the index in the table.
 * Note: the hashCode() method may return negative values, so you should use Math.abs() to ensure the index is non-negative.
 * 
 * @author <Patrick Kemmis> <c3430982>
 * @version <1.0>, <6/6/25>
 */
/**
 * Theory Notes:
 *  a hash table is an implementation of an associative array that uses a hash function for key generation
 */
public class ChainingHashTable<K extends Comparable<K>,V> implements HashTableADT<K,V> {
    private LinkedList<KeyValueEntry<K,V>>[] associativeArray;// LL decleration for individual cells collision resoulution strategy
    private int size;
    // I think that this might have to be an array of LL's
    //private V[] associativeArray; // Decleration of the associative array referencing the individual cellsLL for chaining
    public ChainingHashTable() {
        
    }

    public ChainingHashTable(int numCells){
        // Cast the array to a generic type after compilation - typical array decleration will result in an error
        associativeArray = (LinkedList<KeyValueEntry<K,V>>[]) new Object[numCells];
        size = 0;
        for (LinkedList<KeyValueEntry<K,V>> index : associativeArray) {
            index = new LinkedList<>(); // declare each index with the start of a LL
        }
    }

    private int getIndex(K key) {
        return key.hashCode(); // hash function modulu size to generate 
    }
    /*
     * in the ADT documentation a insertion of the same key type just updats the value at that location.
     *      this kind of operation would remove the need for collision resolution
     *      because collisions are handled purely by replacing the element
     *  
     * 
     * 
     *  Key passed in is already unique as handled by the password manager
     */
    @Override
    public boolean insert(K key, V value) {
         System.out.println("inserting into hash, site name: " + key);
        // key.hashCode() returns a semi-unique integer based on the key
        int index = key.hashCode();
        System.out.println(index);
        // encapsulate in a try catch 
        try {
            // iterate through the linked list
            for (KeyValueEntry<K,V> linkedListEntry : associativeArray[index]) { // note that efficeincy will not be impacted by an empty LL cell as 
                // if an element matches the key update the value
                if (linkedListEntry.compareTo(new KeyValueEntry<>(key, value)) == 0) { // compare the keys
                    // update the value
                    linkedListEntry.setValue(value);
                    return true; // indicates a successful insertion
                }
            }
            // if the key does not match anything within the table
            associativeArray[index].add(new KeyValueEntry<K,V>(key, value)); // add it as a new element
            size ++; // & increment the size
            return true; // indicates a successful insertion
        } catch (Exception e) {
            return false; // the insertion failed due to some error therefor return false
        }
        
    }

    @Override
    public V remove(K key) {
        // Determine the index
        int index = key.hashCode();
        // initialise a temp variable to store the element that was deleted
        V element;
        // loop through the linkedlist cell at the determined index 
        for (KeyValueEntry<K,V> linkedListEntry : associativeArray[index]) {
            // check if the linkedListEntry matches
            if (linkedListEntry.compareTo(new KeyValueEntry<>(key, null)) == 0) {
                element = linkedListEntry.getValue();
                linkedListEntry = null; // delete the entry
                return element;
            }
        }
        return null; // after iterating through either the empty or non empty LL cell the element was not found so therfor could not be deleted
    }

    /* return an element based on a key within the HashTable */
    @Override
    public V get(K key) {
        // determine the index based on the key
        int index = key.hashCode();
        // loop through the array at the specified index
        for (KeyValueEntry<K,V> linkedListEntry : associativeArray[index]) {// for each cell in the array
            // compare entry.key to paramter.key
            if (linkedListEntry.compareTo(new KeyValueEntry<>(key, null)) == 0) {
                return linkedListEntry.getValue(); // if they match then the element is within the hashtable
            }
        }               
        return null; // either the LL cell was empty or the element was not found in the LL
    }

    /* check if an element exists based on a key within the HashTable */
    @Override
    public boolean contains(K key) {
        if (isEmpty()) { // there is nothing in the hash table
            return false;
        }
        // determine the index based on the key
        int index = key.hashCode();
        // loop through the array
        for (KeyValueEntry<K,V> linkedListEntry : associativeArray[index]) {// for each cell in the array
            // compare entry.key to paramter.key
            if (linkedListEntry.compareTo(new KeyValueEntry<>(key, null)) == 0) {
                return true; // if they match then the element is within the hashtable
            }
        }               
        return false; // either the LL cell was empty or the element was not found in the LL
    }

    // ensure emptiness by checking that the entire array is empty
    @Override
    public boolean isEmpty() {
        if (associativeArray == null) {
            return false;
        }
        for (LinkedList<KeyValueEntry<K,V>> element : associativeArray) {
            if (!element.isEmpty()) {// if there is an element at the index
                return false; // return null
            }
        }
        return true; // the entire array has been searched and no elements have been found therefor the hash table is empty
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        // Loop through the hash table & clear every linked list cell
        for (LinkedList<KeyValueEntry<K,V>> element : associativeArray) {
            element.clear(); // clear the linked list cell
        }
    }
}
