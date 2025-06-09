import java.util.Iterator;

/**
 * This is a simple Java application that demonstrates the use of a password manager.
 * It allows users to add, update, delete, and retrieve credentials (username and password) for different sites.
 * The password manager uses a hash table to store the credentials and a binary search tree for ordered traversal.
 * 
 * @author Kyle Robert Harrison
 * @version 1.0, 15 May 2025
 */
public class App {
    public static void main(String[] args) throws Exception {
        testBST();
        Controller controller = new Controller();
        controller.run();
        
    }
    // Test Binary Search Tree Iterators
    private static void testBST(){
            LinkedBinarySearchTree<Integer, String> bst = new LinkedBinarySearchTree<Integer, String>();

            bst.insert(5, "5");
            bst.insert(3, "3");
            bst.insert(6, "6");
            bst.insert(1, "1");
            bst.insert(4, "4");
            bst.insert(8, "8");
            bst.insert(7, "7");


            /**
             
    5
    / \
    3   6
    / \   \
    1   4   8
    /
    7
    preorder: 5 3 1 4 6 8 7
    inorder: 1 3 4 5 6 7 8
    postorder: 1 4 3 7 8 6 5
    */
    
    Iterator<String> i = bst.inorderIterator();
    String s = "";
    while (i.hasNext()){
    s += " " + i.next();
    }
    System.out.println("in-order:"+s);
    System.out.println("should match: 1 3 4 5 6 7 8");
    
    

    i = bst.preorderIterator();
    s = "";
    while (i.hasNext()){
        s += " " + i.next();
    }
    System.out.println("pre-order:"+s);
    System.out.println("should match: 5 3 1 4 6 8 7");
/*
    i = bst.postorderIterator();
    s = "";
    while (i.hasNext()){
    s += " " + i.next();}
    System.out.println("post-order:"+s);
    System.out.println("should match: 1 4 3 7 8 6 5");
    
    */

    }
}
