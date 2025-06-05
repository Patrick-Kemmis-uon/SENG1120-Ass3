/**
 * A class to manage user credentials for different sites.
 * It allows adding, updating, retrieving, listing, checking password strength,
 * and removing credentials.
 * 
 * This class uses a hash table and a binary search tree to store the credentials.
 * The hash table is used for fast access to credentials by site name (which is the key of the entries),
 * while the binary search tree is used for ordered traversal of credentials (also using the site name as the key).
 * 
 * @author <your name> <student number>
 * @version <version>, <date>
 */
public class PasswordManager {

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
    public boolean addCredential(String site, String username, String password) {
        return false;
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
        return false;
    }

    /**
     * Retrieves a credential from the password manager by site name.
     * This should retrieve the credential from the hash table.
     * 
     * @param site the name of the site
     * @return the credential for the site, or null if it does not exist
     */
    public Credential getCredential(String site) {
        return null;
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