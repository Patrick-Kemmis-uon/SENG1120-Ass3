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
     * Local Variables for overall tree structure
     *  root declares the root of the tree
     *  parent - is a tempary node for 
     */
    private BinaryNode root;
    private BinaryNode parent;
    private int size;
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
        size = 0;
    }

    /**
     * Initial 
     */
    @Override
    public void insert(K key, V value) {
        


        // if empty insert at the root
        if (size() == 0) {
            root = new BinaryNode(new KeyValueEntry<K,V>(key, value));
            return;
        }
        else {
            insert(key, value, root);
        }
        // otherwise find where to insert
    }

    // private method for recursive calls
    private void insert(K key, V value, BinaryNode p) {
        // base case - if either of the children don't exist; can be inserted their
        if (p.left == null) { // this method detection might be faulty
            p.left = new BinaryNode(new KeyValueEntry<K,V>(key, value));
        }  
        else if (p.right == null) {
            p.right = new BinaryNode(new KeyValueEntry<K,V>(key, value));
        }
        // recursive call - if the e > p; place it in the right subtree, if e <= ; place it in the left subtree
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, value)) <= 0) { // compare to returns a nu
            insert(key, value, p.left);
        }
        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, value)) > 0) {
            insert(key, value, p.right);
        }
        
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
        // check if the key matches the root -ie the base case
        if  (root.element.compareTo(new KeyValueEntry<K,V>(key, null))== 0) {
            // if so return the element as found
            return root.element.getValue();// Without getValue this will return a KeyValueEntry
        }
        // otherwise search the rest of the BST through a recursive function
        return find(key, root);
    }

    private V find(K key, BinaryNode p) {
        // Base Case  - the key of either of the children == p.key
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) == 0) {
            return p.element.getValue(); // return the value of the child that matches the key
        }
        
        // otherwise check which side of the tree that the element is
        // recursive call - if the e > p; place it in the right subtree, if e <= ; place it in the left subtree
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) <= 0) { // compare to returns a nu
            return find(key, p.left);
        }
        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) > 0) {
            return find(key, p.right);
        }
        throw new NoSuchElementException("The element was not in the BST | or some other weird error has occured"); // need to double check what exception type would be appropriate
    }
    @Override
    public V findMin() {
        return null;
    }

    // While also likely need an additional helper method to find the successor to an
    public BinaryNode findLeftMost() {
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
