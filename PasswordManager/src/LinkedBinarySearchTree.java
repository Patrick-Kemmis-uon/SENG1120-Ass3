import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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
        public void deleteNode() {
            element = null;
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
     * Note that the size is only incremented if there is not an element at the 
     */
    @Override
    public void insert(K key, V value) {
        System.out.println("inserting credential with site name: " + key);
        // if empty insert at the root
        if (root == null || root.element == null) {
            root = new BinaryNode(new KeyValueEntry<K,V>(key, value));
            size ++;
            
            return; // stop execution as node has been inserted
        }
        // otherwise find where to insert
        else {
            insert(key, value, root);
        }
        
    }

    // private method for recursive calls
    private void insert(K key, V value, BinaryNode p) {
        //System.out.println("insert recurisve call with value: " + value + p.element.getValue());

        /* 
        find which direction the element needs to be inserted
        if the e > p; place it in the right subtree, if e <= ; place it in the left subtree
        */
        
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, value)) <= 0) { // compareTo returns a negative integer, zero or a postive integer, as e.key is <, ==, > p.key            
            if (p.right != null) { // check if a recursive call is needed; whether a left sub tree exists 
                insert(key, value, p.right); // if e <= p, search the left subtree for the insertion point
            }
            else { // base case- the position where the element needs to be placed has been found
                p.right = new BinaryNode(new KeyValueEntry<K,V>(key, value)); // insert the node in the empty position
                size ++; // increment the size
                System.out.println("p.right = " + value);
                System.out.println("incremented size, current size = " + size);
            }          
        }

        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, value)) > 0) {
            if (p.left != null) { // check if a recursive call is needed; whether a right sub tree exists 
                insert(key, value, p.left); // if e > p, search the right subtree for the insertion point
            }
            else { // base case- the position where the element needs to be placed has been found
                p.left = new BinaryNode(new KeyValueEntry<K,V>(key, value));
                size ++; // increment the size
                System.out.println("p.left = " + value);
                System.out.println("incremented size, current size = " + size);
                
            }

            
        }

        
        
    }


    
    // same as find function except it returns a boolean instead of a node or element
    @Override
    public boolean contains(K key) {
        // check if the key matches the root -ie the base case
        if  (root.element.compareTo(new KeyValueEntry<K,V>(key, null))== 0) {            
            return true; // the element is the root & can therefor be e
        }
        // otherwise search the rest of the BST through a recursive function (modular code allows the same function)
        V found = find(key, root);
        return found != null; // if found has a value than there matches an element with that index
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
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) > 0) {
            return find(key, p.right);
        }
        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) <= 0) { // compare to returns a nu
            return find(key, p.left);
        }
        throw new NoSuchElementException("The element was not in the BST | or some other weird error has occured"); // need to double check what exception type would be appropriate
    }
    // same functionality as normal except that the return type == BinaryNode, thus acting as a helper method for remove
    private BinaryNode findForRemove(K key) {
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
        if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) > 0) {
            return findForRemove(key, p.right);
        }
        else if (p.element.compareTo(new KeyValueEntry<K,V>(key, null)) <= 0) { 
            return findForRemove(key, p.left);
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
        // tempary binary node which indicate's desired removal
        BinaryNode found = null;
        // start at the root & search for the element
        if (root.element.compareTo(new KeyValueEntry<K,V>(key, null)) == 0) {
            // then the node found is the root
            found = root;
        }
        // if it is not the root : search the rest of the tree
        try {
            found = findForRemove(key);
        }
        catch (NoSuchElementException e) {        
            System.out.println(e.getMessage());
        }
        
        // call the recursive function remove to delete the node
        try {
            remove(found);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        
        
    }

    // private helper method used to swap nodes within the BST
    private BinaryNode swap(BinaryNode x, BinaryNode y) {
        KeyValueEntry<K,V> tempElement = x.element;
        x.element = y.element;
        y.element = tempElement;
        return x;
    }
    // recursive remove
    private void remove(BinaryNode node) { 
        // check that node is not null
        if (node == null) {
            throw new NoSuchElementException("The element was not in the BST from remove(recursive)");
        }
        BinaryNode nodeToSwap;
        // Base Case - node is a leaf & can be deleted
        if (node.left == null && node.right == null) {
            node.deleteNode(); // delete the node
            size --; // decrement the size
            return; // the function has been completed therefor return
        }
        else if (node.right != null) { // if the node has a right node find its successor i.e the leftmost node in the right subtree
            nodeToSwap = findLeftMost(node.right); // find the leftmost node
            node = swap(node, nodeToSwap);
            remove(node); // call the function recursively to account for this node have it's own subtrees
        }
        else if (node.left != null) { // if the node to be deleted only has a left node then find the predeccessor : the rightmost node in the left subtree
            nodeToSwap = findRightMost(node.left);
            node = swap(node, nodeToSwap);
            remove(node); // call the function recursively to ensure that 
        }

        
    }
    // find the leftmost node in the subtree
    private BinaryNode findLeftMost(BinaryNode p) {       
        //  check if parent has a left node/or whether p is a node altogether
        if (p.left == null ) { 
            return p; // if it doesn't not then p will be the successor
        }
        // Initialise a tempary node used to find the leftmost node
        BinaryNode current = p.left;
        // loop through all
        do {
            current = current.left;
        }
        while (current.left == null); // leave when the left child of current is null : indicates that current is leftmost
        // return the leftmost node
        return current;
    }
    // find the right most node in the subtree
    private BinaryNode findRightMost(BinaryNode p) {       
        //  check if parent has a left node/or whether p is a node altogether
        if (p.right == null ) { 
            return p; // if it doesn't not then p will be the predeccessor
        }
        // Initialise a tempary node used to find the rightmost node
        BinaryNode current = p.right;
        // loop through all
        do {
            current = current.right;
        }
        while (current.right == null); // leave when the right child of current is null : indicates that current is rightmost
        // return the rightmost node
        return current;
    }
    // if it is the 2nd case & null left will likely need to find the predessor
    // makes use of a private helper method findLeftMost to find the minimum within the subtree
    @Override
    public V findMin() {
        return findLeftMost(root).element.getValue(); // the leftmost node from the root will be the minium within the tree
    }
    @Override
    public V findMax() {
        return findRightMost(root).element.getValue(); // the rightmost node from the root will be the maxiumum within the tree
    }

    /**
     * Start with the leftmost node
     * then return the parent & 
     * then return the right child
     * slowly working your way up the tree
     *    2
     *   / \
     *  1   3
     * 
     */
    @Override
    public Iterator<V> inorderIterator() {
        // all nodes are added to a linked list which can be later iterated over
        LinkedList<V> list = new LinkedList<>();
        return inorderTraversal(root, list).iterator(); 
    }



    // private helper function which uses recursion to iterate through the BST & add all nodes to a LL for later iteration
    private LinkedList<V> inorderTraversal(BinaryNode node, LinkedList<V> list) {
        // if the BST is empty then there are no elements to loop through
        if (root.element == null) { return null; }    
        
        // check for a left subtree
        if (node.left != null) {
            inorderTraversal(node.left, list); // dive deeper into the left subtree
        }

        // Add the node to the list
        list.add(node.element.getValue());

        // check for a right subtree
        if (node.right != null) {
            inorderTraversal(node.right, list); // dive deeper into the right subtree
        }

        return list; // return the completed list
    }
    /**
     * Ordering
     *    1
     *   / \
     *  2   3
     * 
     *  General Idea:
     *      create a linked list which stores positions relating to a post-order, in-order & pre-order traversal
     *      this linked.
     *      recursive calls are then used to reach the children nodes & the ordering of these operations constitue to whether
     *      the BST is traversed using, post-order, in-order or pre-order
     *  The elements that change the traversal are:
     *      when items are added to the list -- list.add()
     *      the check for a left & right child & then the recursive calls made within them
     * 
     */
    @Override
    public Iterator<V> preorderIterator() {
        // all nodes are added to a linked list which can be later iterated over
        LinkedList<V> list = new LinkedList<>();
        return preorderTraversal(root, list).iterator(); 
    }
    // private helper function which uses recursion to iterate through the BST & add all nodes to a LL for later iteration
    private LinkedList<V> preorderTraversal(BinaryNode node, LinkedList<V> list) {
        // if the BST is empty then there are no elements to loop through
        if (root.element == null) { return null; }  

        // Add the node to the list
        list.add(node.element.getValue());

        // check for a left subtree
        if (node.left != null) {
            preorderTraversal(node.left, list); // dive deeper into the left subtree
        }
        
        // check for a right subtree
        if (node.right != null) {
            preorderTraversal(node.right, list); // dive deeper into the right subtree
        }

        return list; // return the completed list
    }
    /**
     * Ordering
     *    3
     *   / \
     *  1   2
     * 
     */
    @Override
    public Iterator<V> postorderIterator() {
        // all nodes are added to a linked list which can be later iterated over
        LinkedList<V> list = new LinkedList<>();
        return postorderTraversal(root, list).iterator(); 
    }
    // private helper function which uses recursion to iterate through the BST & add all nodes to a LL for later iteration
    private LinkedList<V> postorderTraversal(BinaryNode node, LinkedList<V> list) {
        // if the BST is empty then there are no elements to loop through
        if (root.element == null) { return null; }  

        // check for a left subtree
        if (node.left != null) {
            postorderTraversal(node.left, list); // dive deeper into the left subtree
        }
        
        // check for a right subtree
        if (node.right != null) {
            postorderTraversal(node.right, list); // dive deeper into the right subtree
        }

        // Add the node to the list
        list.add(node.element.getValue());

        return list; // return the completed list
    }
    @Override
    public boolean isEmpty() {
        if (root == null || root.element == null) {
            return true;
        }
        return false; // if the root of the tree is null the BST is empty
    }

    @Override
    public int size() {
        return size; // return the local private variable size 
    }
    
    // delete all nodes within the BST
    /*
     * Note that this is equivalent to a post order traversal
     * but instead of added each node to a least they need to be deleted from the tree
     */
    @Override
    public void clear() { 
        // encapsulate in a try catch to avoid complete program crashes
        try {
            postorderClear(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void postorderClear(BinaryNode node) {
        System.out.println("\n--------------\npostorderClear");
        // if the BST is empty then there are no elements left to traverse
        if (root.element == null) { return; }  

        
        // check for a left subtree
        if (node.left != null) {
            System.out.println("recursive call into left subtree");
            postorderClear(node.left); // dive deeper into the left subtree
        }
        
        // check for a right subtree
        if (node.right != null) {
            System.out.println("recursive call into right subtree");
            postorderClear(node.right); // dive deeper into the right subtree
        }

        // delete the node from the tree
        System.out.println(node.element.getValue() + " has been deleted");
        size --; // decrement the size
        node.deleteNode(); // delete the node reference
    }

    /**
     * {@inheritDoc}
     * 
     * This should return the default iterator for the tree, which is the inorderIterator.
     */
    @Override
    public Iterator<V> iterator() {
        return inorderIterator();
    }

    /**
     * An iterator for the tree.
     * This iterator is used to traverse the tree in a specific order.
     */
    private class TreeIterator implements Iterator<V> {
        Iterator<V> iter;
        /**
         * Constructs a new TreeIterator with the given iterator.
         * This iterator is used to traverse the tree in a specific order.
         * @param iter the iterator to be used for traversal
         */
        public TreeIterator(Iterator<V> iter) {
            this.iter = iter; // initialise the iterator
        }

        /**
         * Returns true if there are more elements to iterate over.
         * 
         * @return true if there are more elements, false otherwise
         */
        public boolean hasNext()  {
            return iter.hasNext(); // return the linkedlist hasNext() method
        }

        /**
         * This method returns the next element in the iterator.
         * If there are no more elements, it should throw a NoSuchElementException.
         * 
         * @return the next element in the iteration
         */
        public V next() throws NoSuchElementException {
            if (iter.next() == null) { // if the next element is null
                throw new NoSuchElementException("No more elements within the BST");// throw a new exception
            }
            return iter.next(); // otherwise return the Linked List .next() method which contains all 
        }

    }
}
