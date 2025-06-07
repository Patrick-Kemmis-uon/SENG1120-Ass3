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

    private ChainingHashTable hashTable = new ChainingHashTable<>();
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
        Credential newCredential = new Credential(site, username, password);
        // the comparble key is the site name
        String comparableKey = site;
        try {
            // check if key already within BST or hashtable
            if (!binarySearchTree.isEmpty() || !hashTable.isEmpty()) {    
                if (hashTable.contains(site)) { // Check whether the entry already exists
                    throw new IllegalArgumentException("Duplicate Entry " + site + "already exists");
                } // if - contains
            } // if - is empty
            
            // if no such value exists then insert the data
            binarySearchTree.insert(site, newCredential);
            hashTable.insert(site, newCredential);
        }
        catch (Exception e) { // exceptions caught related to a credential alreay existing
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
        Credential newCredential = new Credential(site, newUsername, newPassword);
        // determine the comparable key based on the site
        String comparableKey = String.valueOf(site.hashCode());
        
        // check if the site exists 
        if (hashTable.contains(comparableKey)) {
            return false; // the site did not exist whithin the system
        }
        
        
        // update the data within the BST & hashtable
        binarySearchTree.insert(comparableKey, newCredential);
        hashTable.insert(comparableKey, newCredential);
        
        
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
        if (hashTable.isEmpty()) {return null;} // there are no credentials because the data strucutres are empty therefor return null
        
        if (!hashTable.contains(site)) { // check if the credential exists
            return null; // if it already exists return null
        }
        return (Credential)hashTable.get(site);
    }

    /**
     * Removes a credential from the password manager by site name.
     * The credential is removed from both the hash table and the binary search tree.
     * 
     * @param site the name of the site
     * @return true if the credential was removed successfully, false if the site does not exist
     */
    public boolean removeCredential(String site) {
        
        return false;
    }

    /**
     * Lists all credentials in the password manager.
     * The credentials are printed in order of their site names.
     * This method uses the binary search tree for ordered traversal, specifically in-order traversal using the iterator.
     * 
     * Results are printed one per line, and should be printed using the string representation of the Credential object.
     */
    public void listAllCredentials() {
        
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

    }
}