package org.example;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void password_abc_hasLength3() {
        assertEquals(3, Validator.passwordLength("abc"));
    }

    @Test
    void emptyPasswordHasLength0() {
        assertEquals(Validator.passwordLength(""), 0);
    }

    @Test
    void emptyPasswordContainsNoDigits() {
        assertFalse(Validator.containsDigits(""));
    }

    @Test
    void password_abc_doesntContainDigits() {
        assertFalse(Validator.containsDigits("abc"));
    }

    @Test
    void password_abc1_containDigits() {
        assertTrue(Validator.containsDigits("abc1"));
    }

    @Test
    void password_abc1a_containDigits() {
        assertTrue(Validator.containsDigits("abc1a"));
    }


    @Test
    void password_abc_containsNoUpperCase() {
        assertFalse(Validator.containsUpperAndLowerCase("abc"));
    }

    @Test
    void password_a_containsNoUpperCase() {
        assertFalse(Validator.containsUpperAndLowerCase("a"));
    }

    @Test
    void password_A_containsNoUpperCase() {
        assertFalse(Validator.containsUpperAndLowerCase("A"));
    }

    @Test
    void password_ABC_containsNoLowerCase() {
        assertFalse(Validator.containsUpperAndLowerCase("ABC"));
    }

    @Test
    void password_abc1_containsNoUpperCase() {
        assertFalse(Validator.containsUpperAndLowerCase("abc1"));
    }

    @Test
    void password_AbC_containsUpperAndLowerCase() {
        assertTrue(Validator.containsUpperAndLowerCase("AbC"));
    }

    @Test
    void password_AbC123_containsUpperAndLowerCase() {
        assertTrue(Validator.containsUpperAndLowerCase("AbC123"));
    }

    @Test
    void password_admin_isCommon() {
        assertTrue(Validator.containsCommonPassword("admin"));
    }

    @Test
    void password_admin1_isCommon() {
        assertTrue(Validator.containsCommonPassword("admin1"));
    }

    @Test
    void password_jkg352g_isUncommon() {
        assertFalse(Validator.containsCommonPassword("jkg352g"));
    }

    @Test
    void password_pwtest123ab_containsNoSpecialCharacters() {
        assertFalse(Validator.containsSpecialCharacters("pwtest123ab"));
    }

    @Test
    void passwordWithSpecialCharactersActuallyContainsSpecialCharacters() {
        assertTrue(Validator.containsSpecialCharacters("pwtest123ab="));
    }

    @Test
    void passwordWithOnlySpecialCharactersActuallyContainsSpecialCharacters() {
        assertTrue(Validator.containsSpecialCharacters("?ß!-_.;"));
    }

    @Test
    void emptyPasswordContainsNoSpecialCharacters() {
        assertFalse(Validator.containsSpecialCharacters(""));
    }

    @Test
    void password_Admin1_isNotSafe() {
        assertFalse(Validator.passwordIsSafe("Admin1"));
    }

    @Test
    void password_xyZ1_isNotSafe() {
        assertFalse(Validator.passwordIsSafe("xyZ1"));
    }

    @Test
    void password_adminQ_isNotSafe() {
        assertFalse(Validator.passwordIsSafe("admin?"));
    }

    @Test
    void password_abcdefgh_isNotSafe() {
        assertFalse(Validator.passwordIsSafe("abcdefgh"));
    }

    @Test
    void password_abcdefgh1_isNotSafe() {
        assertFalse(Validator.passwordIsSafe("abcdefgh1"));
    }

    @Test
    void password_aBcdefgh1_isNotSafe() {
        assertFalse(Validator.passwordIsSafe("aBcdefgh1"));
    }


    @Test
    void password_aBcdefgh1Q_isSafe() {
        assertTrue(Validator.passwordIsSafe("aBcdefgh1?"));
    }

    @RepeatedTest(50)
    void checkIfCreatedPasswordIsSafe() {
        assertTrue(Validator.passwordIsSafe(Validator.createSafePassword()));
    }

}