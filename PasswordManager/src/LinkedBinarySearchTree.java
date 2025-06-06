import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class implements a binary search tree using linked nodes.
 * 
 * @author <Patrick Kemmis> <c3430982>
 * @version <1.0>, <6/6/25>
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
            return; // stop execution as node has been inserted
        }
        // otherwise find where to insert
        else {
            insert(key, value, root);
        }
        
    }

    // private method for recursive calls
    private void insert(K key, V value, BinaryNode p) {
        // base case - if no children; insert node there
        if (p.left == null && p.right == null) { 
            p.left = new BinaryNode(new KeyValueEntry<K,V>(key, value));
        } // if - either child
        System.out.println("");

        System.out.println("insert recurisve call");
        // additional recursive call to account for only one possible subtree --> check if only 1 child
        if (p.right == null && p.left != null) {

        }


        /* 
        find which direction the element needs to be inserted
        if the e > p; place it in the right subtree, if e <= ; place it in the left subtree
        */
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, value)) <= 0) { // compareTo returns a negative integer, zero or a postive integer, as e.key is <, ==, > p.key
            if (p.left != null) { // check if a recursive call is needed; whether a left sub tree exists 
                insert(key, value, p.left); // if e <= p, search the left subtree for the insertion point
            }
            else { // base case- the position where the element needs to be placed has been found
                p.left = new BinaryNode(new KeyValueEntry<K,V>(key, value)); // insert the node in the empty position
            }          
        }

        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, value)) > 0) {
            if (p.right != null) { // check if a recursive call is needed; whether a right sub tree exists 
                insert(key, value, p.right); // if e > p, search the right subtree for the insertion point
            }
            else { // base case- the position where the element needs to be placed has been found
                p.right = new BinaryNode(new KeyValueEntry<K,V>(key, value));
            }

            
        }

        
        
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
    // same functionality as normal except that the return type == BinaryNode, thus acting as a helper method for remove
    public BinaryNode findForRemove(K key) {
        // check if the key matches the root -ie the base case
        if  (root.element.compareTo(new KeyValueEntry<K,V>(key, null))== 0) {
            // if so return the element as found
            return root;// Without getValue this will return a KeyValueEntry
        }
        // otherwise search the rest of the BST through a recursive function
        return findForRemove(key, root);
    }

    private BinaryNode findForRemove(K key, BinaryNode p) {
        // Base Case  - the key of either of the children == p.key
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) == 0) {
            return p; // return the value of the child that matches the key
        }
        
        // otherwise check which side of the tree that the element is
        // recursive call - if the e > p; place it in the right subtree, if e <= ; place it in the left subtree
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) <= 0) { // compare to returns a nu
            return findForRemove(key, p.left);
        }
        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) > 0) {
            return findForRemove(key, p.right);
        }
        throw new NoSuchElementException("The element was not in the BST | or some other weird error has occured"); // need to double check what exception type would be appropriate
    }
    /**
     * This function has 3 cases:
     *  1st Case:
     *      o
     *     / \
     *    x   x
     *  remove
     * ----------
     * 2nd Case
     *      o
     *     / \
     *    o   x
     *  remove left node & link p-left right subtree rightmost
     * ----------
     * 3rd case
     *      o
     *     / \
     *   o|x  o
     *         \
     *       possible further subtree
     *  therefor find the leftmost node in the right subtree continually
     * 
     * Passes the key for the item to be removed
     */
    @Override
    public void remove(K key) {
        // Temporary node for 
        BinaryNode found;
        // start at the root & search for the element
        if (root.element.compareTo(new KeyValueEntry<K,V>(key, null)) == 0) {
            // then the node found is the root
            found = root;
        }
    
        // if it is not the root : search the rest of the tree
        // encapsulate in a try catch
        found = findForRemove(key);
        // check if found == null
        // check for base case where no children
        // Once the element has been found - find the successor such that the tree does not become disconnected
        BinaryNode replacementNode;
        replacementNode = findLeftMost(found);

        found = replacementNode;
    }

    // recursive remove to keep in mind pointers -- remove in the right direction
    private BinaryNode remove(K key, BinaryNode p) { 
        // Base Case - if it is == then remove
        
        // find the element
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) <= 0) { // if the e <= p
            
            return p.left = remove(key, p.left); //return the left subtree & update the left pointer so that the tree does not become disconnected
        }
        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) <= 0) { // if the e > p
            return p.right = remove(key, p.right); // return the right subtree & update the right pointer so that the tree does not become disconnected
        }

        // ElementNotFoundException - there is no further nodes
        if (p.left == null && p.right == null) {
            throw new NoSuchElementException("No Such elemeent within the Binary Search Tree");
        }
        p.right = remove(key, p.right);
        return null;
    }

    // note that because this funciton only returns a V it can not be effectively used for the remove funcitonality
    @Override
    public V findMin() {
        return null;
    }


    // recursive function -- THIS FUNCTION COULD result in a stackOverflow because of an infiinite loop
    private BinaryNode findLeftMost(BinaryNode p) {
        // Base Case - p.left && p.right == null
        if (p.left == null && p.right == null) {
            BinaryNode temp = p;
            p = null; // remove the node
            return temp;
        }
        // Check if p.left == null && if p.right 
        else if (p.right != null) {
            return findLeftMost(p.right);
        }
        // otherwise find the leftmost node
        
        // Initialise a tempary node used to find the leftmost node
        BinaryNode current = p.left;
        // loop through all
        do {
            current = current.left;
        }
        while (current.left != null); // only leave the loop once the leftmost node == null -->
        //     L--> note that current will be the parent of left which == null so current will therefor be the leftmost assuming no right subtree
        // check for a right subtree
        if (current.right != null) {
            return findLeftMost(p.right); // if there is a right subtree call function recursively so as not to disconnect the subtree
        }
        // Additional BaseCase if there is not a right subtree return current
        return current;
    }
    // if it is the 2nd case & null left will likely need to find the predessor
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
