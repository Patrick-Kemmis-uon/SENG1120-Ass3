import java.util.NoSuchElementException;

/**
 * A simple password strength checker, which is defined by a static method.
 * It evaluates the strength of a password based on length, character types, and complexity.
 * 
 * @author <Patrick Kemmis> <c3430982>
 * @version <1.0>, <6/6/25>
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
     * Of note:
     *  multiple satisfactions of the same criteria do not result in additional strength
     *      e.g: 2 uppercase letters will not equate to +2 strength
     * 
     * @param password the password to evaluate
     * @return a string indicating the strength of the password, as one of the following:
     * "Very Weak", "Weak", "Moderate", "Strong", "Very Strong"
     */
    public static String evaluate(String password) {
        // criteriaSatisfaction as an array where each index represents one of the criteria
        int[] criteriaSatisfaction = new int[5]; 
        int length = password.length();

        // check if the length criteria has been met, if so increment criteriaSatisfaction
        if (length >= 8) { criteriaSatisfaction[0] =1;} 
        
        // loop through the password to check the strength
        for (int i = 0; i < length; i++) { /* CHECK for off by 1 ERROR */
            char currChar = password.charAt(i); 
            if (Character.isLowerCase(currChar)) { // Check if the character is an lower case
                criteriaSatisfaction[1] ++; // if so increment the criteriaSatisfaction for the relevant index
            }
            else if (Character.isUpperCase(currChar)) { // check if the character is an upper case
                criteriaSatisfaction[2] ++; // if so increment the criteriaSatisfaction at the relevant index
            }
            else if (Character.isDigit(currChar)) {
                criteriaSatisfaction[3] ++; // if there is a digit at the index increment the criteriaSatisfaction at the relevant index
            }
            else { // if none of these other things then the character must be a special character
                criteriaSatisfaction[4] ++;
            }
        } // for
        
        // count the strength
        int strength = 0;
        // Loop through each of the criteria to see if they have been satisfied
        for (int criteria : criteriaSatisfaction) {
            if (criteria >= 1) {// if they have been satisfied atleast once
                strength ++; // increment the strength
            }
        }
        
        // check which strength level the strength corresponds to & return it
        return strengthLevel(strength);
    }

    /**
     * The strength levels are:
     * - Very Weak: 0 or 1 criteria met
     * - Weak: 2 criteria met
     * - Moderate: 3 criteria met
     * - Strong: 4 criteria met
     * - Very Strong: 5 criteria met
     * @param strength
     * @return
     */
    private static String strengthLevel(int strength) { // use of a private helper method to make code more readable
        switch (strength) { // each strength integer ammount references a String
            case 0:
                return "Very Weak";
            case 1:
                return "Very Weak";
            case 2:
                return "Weak";
            case 3:
                return "Moderate";
            case 4:
                return "Strong";
            case 5:
                return "Very Strong";
            default:
                throw new IndexOutOfBoundsException("the strength variable was outside its default bounds");        
        }
        
    }
}
