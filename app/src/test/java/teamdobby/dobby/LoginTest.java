package teamdobby.dobby;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockContext;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.isA;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import android.app.Activity;
import android.widget.Toast;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

/**
 * Created by Marie on 03.05.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Login.class})
public class LoginTest {

    private Login login;

    @Before
    public void setUp() throws Exception {
        login = PowerMockito.spy(new Login());

        doNothing().when(login).finish();
        suppress(method(Login.class, "toastShow"));
        suppress(method(Login.class, "setUser"));
    }

    @Test
    public void testOnClickFunction_withNoUser() {
        login.Name = "";
        login.Pass = "";
        LoginData.main();

        Button mockButton = mock(Button.class);
        CharSequence should = "User not found";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'User not found'", login.text, is(should));
    }

    @Test
    public void testOnClickFunction_withWrongUser() {
        login.Name = "xy";
        login.Pass = "xy";
        LoginData.main();

        Button mockButton = mock(Button.class);
        CharSequence should = "User not found";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'User not found'", login.text, is(should));
    }

    public void testOnClickFunction_withRightUserButWrongPassword() {
        login.Name = "Dobby";
        login.Pass = "";
        LoginData.main();

        Button mockButton = mock(Button.class);
        CharSequence should = "Wrong Password";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'Wrong Password'", login.text, is(should));
    }

    @Test
    public void testOnClickFunction_withRightUserAndRightPassword() {
        suppress(method(Login.class, "startIntent"));
        login.Name = "Dobby";
        login.Pass = "123";
        LoginData.main();

        Button mockButton = mock(Button.class);
        CharSequence should = "Successfully logged in";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'Successfully logged in'", login.text, is(should));
    }

    @Test
    public void testOnBackPressed() {
        login.onBackPressed();
        assertTrue(true);
    }
}