package chat.test.com.ping.util;

import android.util.Patterns;

/**
 * This class contains utility methods to validate various input data
 * @author Devesh Shetty
 */
public class Validation {

    /**
     * Validates whether the supplied string is a valid email id or not
     * @param email the email id
     * @return true if it is an valid email id else false
     */
    public final static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    /**
     * Validates whether the supplied String is a valid password or not
     * @param pass the password
     * @return true if it is a valid password else false
     */
    public final static boolean checkPassword(String pass){
        //password should be of minimum 7 letters
        if(pass == null || pass.length() < 7){
            return false;
        }
        return true;
    }


}
