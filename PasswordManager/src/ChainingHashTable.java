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
    // I think that this might have to be an array of LL's
    //private V[] associativeArray; // Decleration of the associative array referencing the individual cellsLL for chaining
    public ChainingHashTable() {
        
    }

    public ChainingHashTable(int numCells){
        //V[] associativeArray = new V[numCells];
    }

    @Override
    public boolean insert(K key, V value) {
        // key.hashCode() returns a unique integer based on that key
        int index = key.hashCode() % size();
        System.out.println(index);
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V get(K key) {
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
        return 0;
    }

    @Override
    public void clear() {
        
    }
}
