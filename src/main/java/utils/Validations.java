package utils;

import org.testng.Assert;

import java.util.List;

public class Validations {

    public static void validateTrue(boolean condition) {
        Assert.assertTrue(condition); // assertion using testng assert true for condition
    }

    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition,message); // assertion using testng assert to verify actual equal to expected
    }

    public static void validateEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected); // assertion using testng assert to verify actual equal to expected
    }

    public static void validateEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected); // assertion using testng assert to verify actual equal to expected
    }

    public static boolean validateContains(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected)); // assertion using testng assert to verify actual contain expected
        return false;
    }

    public static boolean validateContains(List actual, List expected) {
        Assert.assertTrue(actual.contains(expected)); // assertion using testng assert to verify actual contain expected
        return false;
    }

    public static boolean validateNOTContains(String actual, String expected) {
        Assert.assertFalse(actual.contains(expected)); // assertion using testng assert to verify actual contain expected
        return true;
    }


}
