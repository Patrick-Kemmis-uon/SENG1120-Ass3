import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class implements a binary search tree using linked nodes.
 * 
 * @author <your name> <student number>
 * @version <version>, <date>
 */
public class LinkedBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeADT<K, V> {

    /**
     * A node in a binary tree.
     * Each node contains a key-value pair and references to its left and right children.
     */
    private class BinaryNode {
        /**
         * The key-value pair stored in this node.
         */
        KeyValueEntry<K, V> element;
        /**
         * The left child of this node.
         * This is null if the node has no left child.
         */
        BinaryNode left;
        /** 
         * The right child of this node.
         * This is null if the node has no right child.
         */
        BinaryNode right;

        /**
         * Constructs a new BinaryNode with the given element.
         * The left and right children are initialised to null.
         * @param element the element to be stored in the node
         */
        BinaryNode(KeyValueEntry<K, V> element) {
            this.element = element;
            left = null;
            right = null;
        }
    }


    /**
     * Constructs a new empty binary search tree.
     * The tree is initially empty, with no nodes.
     */
    public LinkedBinarySearchTree() {
        
    }

    @Override
    public void insert(K key, V value) {
        
    }


    @Override
    public void remove(K key) {
        
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V find(K key) {
        return null;
    }

    @Override
    public V findMin() {
        return null;
    }

    @Override
    public V findMax() {
        return null;
    }

    @Override
    public Iterator<V> inorderIterator() {
        return null;
    }

    @Override
    public Iterator<V> preorderIterator() {
        return null;
    }

    @Override
    public Iterator<V> postorderIterator() {
        return null;
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

    /**
     * {@inheritDoc}
     * 
     * This should return the default iterator for the tree, which is the inorderIterator.
     */
    @Override
    public Iterator<V> iterator() {
        return null;
    }

    /**
     * An iterator for the tree.
     * This iterator is used to traverse the tree in a specific order.
     */
    private class TreeIterator implements Iterator<V> {

        /**
         * Constructs a new TreeIterator with the given iterator.
         * This iterator is used to traverse the tree in a specific order.
         * @param iter the iterator to be used for traversal
         */
        public TreeIterator(Iterator<V> iter) {
 
        }

        /**
         * Returns true if there are more elements to iterate over.
         * 
         * @return true if there are more elements, false otherwise
         */
        public boolean hasNext()  {
            return false;
        }

        /**
         * This method returns the next element in the iterator.
         * If there are no more elements, it should throw a NoSuchElementException.
         * 
         * @return the next element in the iteration
         */
        public V next() throws NoSuchElementException {
            return null;
        }

    }
}
