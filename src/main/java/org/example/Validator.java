package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final static String[] commonPasswords = {"123456", "admin", "12345678", "123456789", "1234", "password", "Aa123456", "UNKNOWN", "Password", "Admin123", "********", "user"};

    public static void main(String[] args) {
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
        Pattern pattern = Pattern.compile("[^0-9a-zA.z]");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

}