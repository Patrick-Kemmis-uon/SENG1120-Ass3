/**
 * A simple password strength checker, which is defined by a static method.
 * It evaluates the strength of a password based on length, character types, and complexity.
 * 
 * @author <your name> <student number>
 * @version <version>, <date>
 */
public class PasswordStrengthChecker {
    /**
     * Evaluates the strength of a password. 
     * This is a static method that checks the password against a set of criteria.
     * Hence, you need to use it by calling PasswordStrengthChecker.evaluate(password).
     * 
     * The password strength is determined by the following criteria:
     * - Length: At least 8 characters
     * - Contains at least one lowercase letter
     * - Contains at least one uppercase letter
     * - Contains at least one digit
     * - Contains at least one special character (non-alphanumeric)
     * 
     * The strength levels are:
     * - Very Weak: 0 or 1 criteria met
     * - Weak: 2 criteria met
     * - Moderate: 3 criteria met
     * - Strong: 4 criteria met
     * - Very Strong: 5 criteria met
     * 
     * @param password the password to evaluate
     * @return a string indicating the strength of the password, as one of the following:
     * "Very Weak", "Weak", "Moderate", "Strong", "Very Strong"
     */
    public static String evaluate(String password) {
        return null;
    }
}
