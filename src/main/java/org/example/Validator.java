package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
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
        String temp = pw;
        //idee: wenn pw nur kleinbuchstaben enthält, dann ändert pw.toLowerCase() nichts. analog für großbuchstaben
        return !temp.toUpperCase().equals(pw) && !temp.toLowerCase().equals(pw);
    }


}