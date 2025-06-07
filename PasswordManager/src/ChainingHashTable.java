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
    private LinkedList<V> cellLinkedList; // LL decleration for individual cells which allows chaining for collision resoulution
    private V[] associativeArray;

    // I think that this might have to be an array of LL's
    //private V[] associativeArray; // Decleration of the associative array referencing the individual cellsLL for chaining
    public ChainingHashTable() {
        
    }

    public ChainingHashTable(int numCells){
        // Cast the array to a generic type after compilation - typical array decleration will result in an error
        associativeArray = (V[]) new Object[numCells];
    }

    private int getIndex(K key) {
        return key.hashCode() % size();
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
        // key.hashCode() returns a unique integer based on that key
        int index = key.hashCode() % size();
        System.out.println(index);
        // check if the value already exists within the hashtable
        if (associativeArray[index]) {
            
        }
        associativeArray[index] = value; // insert the 
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public V remove(K key) {
        // Determine the index
        int index = key.hashCode() % size();
        V valueAtIndex = associativeArray[index];
        associativeArray[index] = null;
        return valueAtIndex;
    }

    @Override
    public V get(K key) {
        //         
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        
    }
}
