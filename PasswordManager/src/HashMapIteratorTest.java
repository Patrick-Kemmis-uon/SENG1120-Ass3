import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class HashMapIteratorTest<K,V> implements Iterable<V> {
    private final HashMap<K,V> map = new HashMap<>();

    @Override
    public Iterator<V> iterator() {
        return inorderIterator();
    }
    public Iterator<V> inorderIterator() {
        // add nodes to a linked list which can be later iterated over
        LinkedList<V> inOrderList = new LinkedList<>();
        // we are passing a list but I am not sure in Java whether this will update the list in memory or just within the function inOrderTraversal()
        return inOrderTraversal(root, inOrderList).iterator(); 
    }
    // this function uses recursion to iterate through the BST - return type binary node to allow parents to be found
    private LinkedList<V> inOrderTraversal(BinaryNode node, LinkedList<V> list) {
        System.out.println("function inOrderTraversal called with parameters : " + node + list.toString());
        // if the BST is empty just return the head
        if (root == null) { return null; }    
        // if both chilren are null then this node is a leaf
        if (node.left == null && node.right == null) {
            list.add(node.element.getValue()); // add the leaf node to the list
        }
        
        
        // add the furthest left node to the list first
        BinaryNode left = null;
        // check for a left subtree
        if (node.left != null) {
            inOrderTraversal(node.left, list); // dive deeper into the left subtree
            // add the left node to the list
            list.add(node.left.element.getValue());
            System.out.println("added left node:" + node.left.element.getValue());
            // add the parent node to the list
            //list.add(node.element.getValue());
        }
        // check for a right subtree
        if (node.right != null) {
            inOrderTraversal(node.right, list); // dive deeper into the right subtree
            list.add(node.right.element.getValue()); // add the right node to the list
            System.out.println("added right node:" + node.right.element.getValue());
        }
        return list; // return the completed list
    }

}
