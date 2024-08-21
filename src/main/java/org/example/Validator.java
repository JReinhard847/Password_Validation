package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final static String[] commonPasswords = {"123456", "admin", "12345678", "123456789", "1234", "password", "Aa123456", "UNKNOWN", "Password", "Admin123", "********", "user"};

    public static void main(String[] args) {
    }

    public static boolean passwordIsSafe(String pw) {
        if (passwordLength(pw) < 8) {
            return false;
        }
        if (!containsDigits(pw)) {
            return false;
        }
        if (!containsUpperAndLowerCase(pw)) {
            return false;
        }
        if (!containsSpecialCharacters(pw)) {
            return false;
        }
        return !containsCommonPassword(pw);
    }

    public static int passwordLength(String pw) {
        return pw.length();
    }

    public static boolean containsDigits(String pw) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    public static boolean containsUpperAndLowerCase(String pw) {
        //idee: wenn pw nur kleinbuchstaben enthält, dann ändert pw.toLowerCase() nichts. analog für großbuchstaben
        return !pw.toUpperCase().equals(pw) && !pw.toLowerCase().equals(pw);
    }

    public static boolean containsCommonPassword(String pw) {
        for (String commonPw : commonPasswords) {
            if (pw.contains(commonPw)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSpecialCharacters(String pw) {
        // anstatt direkt nach sonderzeichen zu suchen, suchen wir nach zeichen, die weder buchstabe nach zahl sind
        Pattern pattern = Pattern.compile("[^0-9a-z]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    public static String createSafePassword() {
        int length = new Random().nextInt(8, 16);
        String res = "";
        //create randomized base string
        for (int i = 0; i < length; i++) {
            // all letter/number and special characters correspont to an int within the range of 32 and 126
            // reference: https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
            res += (char) new Random().nextInt(33, 126);
        }
        //keep track of indices that get changed to fulfill conditions to not change it twice
        HashSet<Integer> changedIndices = new HashSet<Integer>();
        int changeIndex = new Random().nextInt(length);
        changedIndices.add(changeIndex);
        //change a random indices to a digit
        int digitToAdd = new Random().nextInt(10);
        res = res.substring(0, changeIndex) + digitToAdd + res.substring(changeIndex + 1);
        changedIndices.add(changeIndex);
        while (changedIndices.contains(changeIndex)) {
            changeIndex = new Random().nextInt(length);
        }
        //add random lowercase letter
        char letterToAdd = (char) new Random().nextInt(97, 122);
        res = res.substring(0, changeIndex) + letterToAdd + res.substring(changeIndex + 1);
        changedIndices.add(changeIndex);
        while (changedIndices.contains(changeIndex)) {
            changeIndex = new Random().nextInt(length);
        }
        //add random uppercase letter
        letterToAdd = (char) new Random().nextInt(65, 90);
        res = res.substring(0, changeIndex) + letterToAdd + res.substring(changeIndex + 1);
        changedIndices.add(changeIndex);
        while (changedIndices.contains(changeIndex)) {
            changeIndex = new Random().nextInt(length);
        }
        //add random special character
        letterToAdd = (char) new Random().nextInt(33, 47);
        res = res.substring(0, changeIndex) + letterToAdd + res.substring(changeIndex + 1);
        //dont check for common passwords because the probability is astronomically small
        return res;
    }

}