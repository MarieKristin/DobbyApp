package teamdobby.dobby;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Marie on 20.04.2016.
 */
public class LoginDataTest {

    @Test
    public void testIsValidName_withEmptyListAndName(){
        boolean result = LoginData.isValidName("xy");

        assertFalse(result);
    }

    @Test
    public void testIsValidName_withEmptyListAndNoName() {
        boolean result = LoginData.isValidName("");

        assertFalse(result);
    }

    @Test
    public void testIsValidName_withListAndNoName() {
        LoginData.main();
        boolean result = LoginData.isValidName("");

        assertFalse(result);
    }

    @Test
    public void testIsValidName_withListAndWrongName() {
        LoginData.main();
        boolean result = LoginData.isValidName("xy");

        assertFalse(result);
    }

    @Test /*(expected = NullPointerException.class)*/
    public void testIsValidName_withListAndCorrectName() {
        LoginData.main();
        boolean result = LoginData.isValidName("Dobby");

        assertTrue(result);

        /*assertThat("Message", result, is(expected));*/
    }

    @Test
    public void testIsValidPass_withEmptyListAndNameAndPassword() {
        boolean result = LoginData.isValidPass("xy", "xy");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withEmptyListAndNoNameAndPassword() {
        boolean result = LoginData.isValidPass("","xy");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withEmptyListAndNameAndNoPassword() {
        boolean result = LoginData.isValidPass("xy","");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withEmptyListAndNoNameAndNoPassword() {
        boolean result = LoginData.isValidPass("","");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndNoNameAndNoPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("","");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndWrongNameAndNoPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("xy","");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndCorrectNameAndNoPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("Dobby","");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndNoNameAndWrongPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("","xy");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndWrongNameAndWrongPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("xy","xy");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndCorrectNameAndWrongPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("Dobby","xy");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndNoNameAndCorrectPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("","123");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndWrongNameAndCorrectPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("xy","123");

        assertFalse(result);
    }

    @Test
    public void testIsValidPass_withListAndCorrectNameAndCorrectPassword() {
        LoginData.main();
        boolean result = LoginData.isValidPass("Dobby","123");

        assertTrue(result);
    }

    @Test
    public void testSetCurrUser_withNoName() {
        LoginData.setCurrUser("");

        assertThat("User should be empty.", LoginData.curruser, is(""));
    }

    @Test
    public void testSetCurrUser_withName() {
        LoginData.setCurrUser("xy");

        assertThat("User should be xy.", LoginData.curruser, is("xy"));
    }

    @Test
    public void testGetCurrUser_withNullCurruser() {
        LoginData.setCurrUser(null);
        String result = LoginData.getCurrUser();

        assertThat("User should be non-existent so it would be 'ERROR'.", result, is("ERROR"));
    }

    @Test
    public void testGetCurrUser_withEmptyCurruser() {
        LoginData.setCurrUser("");
        String result = LoginData.getCurrUser();

        assertThat("User should be empty so it would be 'ERROR'.", result, is("ERROR"));
    }

    @Test
    public void testGetCurrUser_withCorrectCurruser() {
        LoginData.setCurrUser("Dobby");
        String result = LoginData.getCurrUser();

        assertThat("User should be Dobby.", result, is("Dobby"));
    }

    @Test
    public void testSetLogged() {
        LoginData.setLogged();

        assertTrue(LoginData.logged);
    }

    @Test
    public void testNotLogged() {
        LoginData.notLogged();

        assertFalse(LoginData.logged);
    }

    @Test
    public void testGetLogged_withLogin() {
        LoginData.setLogged();
        boolean result = LoginData.getLogged();

        assertTrue(result);
    }

    @Test
    public void testGetLogged_withLogout() {
        LoginData.notLogged();
        boolean result = LoginData.getLogged();

        assertFalse(result);
    }
}