import java.util.Iterator;

/**
 * A class to manage user credentials for different sites.
 * It allows adding, updating, retrieving, listing, checking password strength,
 * and removing credentials.
 * 
 * This class uses a hash table and a binary search tree to store the credentials.
 * The hash table is used for fast access to credentials by site name (which is the key of the entries),
 * while the binary search tree is used for ordered traversal of credentials (also using the site name as the key).
 * 
 * Therefor whenever an entry is created, updated or deleted the action needs to take place in both the BST & the Hash Table
 * 
 * @author <Patrick Kemmis> <c3430982>
 * @version <1.0>, <6/6/25>
 */
public class PasswordManager {

    private ChainingHashTable hashTable = new ChainingHashTable<>(15);
    private LinkedBinarySearchTree binarySearchTree = new LinkedBinarySearchTree<>();

    /**
     * Adds a new credential to the password manager. 
     * The credential consists of a site, username, and password.
     * The credential is stored in both a hash table and a binary search tree.
     * If the site already exists, it will not be added.
     * 
     * @param site the name of the site
     * @param username the username for the site
     * @param password the password for the site
     * 
     * @return true if the credential was added successfully, false if the site already exists or otherwise fails to insert
     */
    
    @SuppressWarnings("unchecked") // Gets rid of yellow underlines due to some type safety warnings
    public boolean addCredential(String site, String username, String password) {
        // initialise the new credential into a new credential object with the passed parameters
        Credential newCredential = new Credential(site, username, password);
        // the comparble key is the site name
        String comparableKey = site;


        /*
         * Add the credential object to the Binary Search Tree & then the Hash Table
         * 
         */

        try {
            // check if key already within BST or hashtable
            if (!binarySearchTree.isEmpty()) { // call BST isEmpty() because it has a lower time complexity
                if (binarySearchTree.contains(site)) { // Check whether the entry already exists
                    throw new IllegalArgumentException("Duplicate Entry " + site + "already exists");
                } // if - contains
            } // if - is empty
            
            // if no such value exists then insert the data
            binarySearchTree.insert(site, newCredential);
            hashTable.insert(site, newCredential);
        }
        catch (Exception e) { // exceptions caught related to a credential alreay existing
            e.printStackTrace();
            System.out.println(e.getMessage()); // print out the relevant error message that caused the error
            return false; // failure to excute insertion on either BST or hashtable
        }
        // if neither method throws an error than the data has been succesfully inserted
        return true;
    }

    /**
     * Updates an existing credential in the password manager.
     * The credential consists of a site, username, and password.
     * The credential is updated in both the hash table and the binary search tree.
     * 
     * @param site the name of the site
     * @param newUsername the new username for the site
     * @param newPassword the new password for the site
     * @return true if the credential was updated successfully, false if the site does not exist
     */
    public boolean updateCredential(String site, String newUsername, String newPassword) {
        // initialise the new credential into a new credential object with the passed parameters
        Credential newCredential = new Credential(site, newUsername, newPassword);
        // determine the comparable key based on the site
        String comparableKey = site;
        
        // check if there are any credentials within the tree
        if (binarySearchTree.isEmpty()) {
            System.out.println("there are no credentials to update");
            return false;
        }
        // check if the site exists 
        if (!binarySearchTree.contains(comparableKey)) {
            System.out.println("The requested site: " + site + "did not match any within our system");
            return false; // the site did not exist whithin the system
        }
        // otherwise

        // update the values within the BST & hashTable
        binarySearchTree.insert(site, newCredential);
        hashTable.insert(site, newCredential);
        
        // if neither method throws an error than the data has been succesfully inserted
        return true;
    }

    /**
     * Retrieves a credential from the password manager by site name.
     * This should retrieve the credential from the hash table.
     * 
     * @param site the name of the site
     * @return the credential for the site, or null if it does not exist
     */
    public Credential getCredential(String site) {
        if (binarySearchTree.isEmpty()) {return null;} // there are no credentials because the data strucutres are empty therefor return null
        
        if (hashTable.contains(site) == false) { // check if the credential exists
            return null; // if it does not exist retrun null
        }
        return (Credential)hashTable.get(site); // otherwise return the 
    }

    /**
     * Removes a credential from the password manager by site name.
     * The credential is removed from both the hash table and the binary search tree.
     * 
     * @param site the name of the site
     * @return true if the credential was removed successfully, false if the site does not exist
     */
    public boolean removeCredential(String site) {
        // set the site name as the comparable key
        String comparableKey = site;
        
        // check whether the BST is empty
        if (binarySearchTree.isEmpty()) {return false; }// there were no elements to be removed

        // call the remove function in both BST & hash table to remove elements that match the site name
        try {
            binarySearchTree.remove(site);
            hashTable.remove(site);
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return true; // the elements have been deleted successfullly with no errors
    }

    /**
     * Lists all credentials in the password manager.
     * The credentials are printed in order of their site names.
     * This method uses the binary search tree for ordered traversal, specifically in-order traversal using the iterator.
     * 
     * Results are printed one per line, and should be printed using the string representation of the Credential object.
     */
    public void listAllCredentials() {
        // return the Binary Search Tree iterator
        Iterator<Credential> i = binarySearchTree.iterator();
        // loop through all values
        if (i != null) {
            while (i.hasNext()){
                System.out.println(i.next());
            }
        }
    }

    /**
     * Checks the strength of all passwords in the password manager.
     * The strength is evaluated using the PasswordStrengthChecker class.
     * The results are printed to the console.
     * 
     * Results are printed in order of their site names, and should be printed as follows:
     * <Credential>, Password strength: <strength>
     * where <Credential> is the string representation of the Credential object, and <strength> is the strength of the password.
     */
    public void checkAllCredentials() {
        // return the Binary Search Tree iterator
        Iterator<Credential> i = binarySearchTree.iterator();
        Credential current;
        String password;
        String passwordStrength;
        // loop through all values
        if (i != null) {
            while (i.hasNext()){
                current = i.next();
                password = current.getPassword();
                passwordStrength = PasswordStrengthChecker.evaluate(password);
                System.out.println(current + ", Password strength: " + passwordStrength);
            }
        }
    }




    
}