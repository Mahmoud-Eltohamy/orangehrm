package com.orangehrm.PO.utilities;

import org.testng.annotations.DataProvider;

public class TestData {
    /**
     * Data provider for login test cases.
     * Returns an array of objects where each object is an array of two strings,
     * the first being the username and the second being the password.
     * @return the login data to be used in test cases.
     */
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"user1", "password1"},
                {"user2", "password2"},
                {"user3", "password3"}
        };
    }
}
