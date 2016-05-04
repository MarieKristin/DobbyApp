package teamdobby.dobby;

import android.content.Context;
import android.content.DialogInterface;
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
@PrepareForTest({Login.class,Activity.class,Button.class})
public class LoginTest {

    private Login login;

    @Before
    public void setUp() throws Exception {
        login = PowerMockito.spy(new Login());

        doNothing().when(login).finish();
        doReturn(mock(Context.class)).when(login).getApplicationContext();
        //when(login.getApplicationContext()).thenReturn(mock(Context.class));

        suppress(method(Activity.class, "onCreate", Bundle.class));
        suppress(method(Activity.class, "setContentView", int.class));
        suppress(method(Button.class, "setOnClickListener", View.OnClickListener.class));
        //suppress(method(Toast.class, "makeText", android.content.Context.class, CharSequence.class, int.class));
    }

    @Test
    public void testOnCreate() {
        //login.onCreate(null);
    }

    @Test
    public void testOnClickFunction_withNoUser() {
        suppress(method(Login.class, "toastShow"));
        Editable sequence = mock(SpannableStringBuilder.class);
        login.NameText = PowerMockito.spy(new EditText(mock(Context.class)));
        login.PassText = PowerMockito.spy(new EditText(mock(Context.class)));

        doReturn(sequence).when(login.NameText).getText();
        doReturn(sequence).when(login.PassText).getText();
        doReturn("").when(sequence).toString();

        Button mockButton = mock(Button.class);
        CharSequence should = "User not found";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'User not found'", login.text, is(should));
    }

    @Test
    public void testOnClickFunction_withWrongUser() {
        suppress(method(Login.class, "toastShow"));
        Editable sequenceName = mock(SpannableStringBuilder.class);
        Editable sequencePass = mock(SpannableStringBuilder.class);
        login.NameText = PowerMockito.spy(new EditText(mock(Context.class)));
        login.PassText = PowerMockito.spy(new EditText(mock(Context.class)));

        doReturn(sequenceName).when(login.NameText).getText();
        doReturn(sequencePass).when(login.PassText).getText();
        doReturn("xy").when(sequenceName).toString();
        doReturn("").when(sequencePass).toString();

        Button mockButton = mock(Button.class);
        CharSequence should = "User not found";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'User not found'", login.text, is(should));
    }

    /*@Test
    public void testOnClickFunction_withRightUserButWrongPassword() {
        suppress(method(Login.class, "toastShow"));
        Editable sequenceName = mock(SpannableStringBuilder.class);
        Editable sequencePass = mock(SpannableStringBuilder.class);
        login.NameText = PowerMockito.spy(new EditText(mock(Context.class)));
        login.PassText = PowerMockito.spy(new EditText(mock(Context.class)));

        doReturn(sequenceName).when(login.NameText).getText();
        doReturn(sequencePass).when(login.PassText).getText();
        doReturn("Dobby").when(sequenceName).toString();
        doReturn("xy").when(sequencePass).toString();

        assertThat(sequenceName.toString(), is("Dobby"));
        assertThat(login.NameText.getText().toString(), is("Dobby"));

        Button mockButton = mock(Button.class);
        CharSequence should = "Wrong Password";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'Wrong Password'", login.text, is(should));
    }

    @Test
    public void testOnClickFunction_withRightUserAndRightPassword() {
        suppress(method(Login.class, "toastShow"));
        Editable sequenceName = mock(SpannableStringBuilder.class);
        Editable sequencePass = mock(SpannableStringBuilder.class);
        login.NameText = PowerMockito.spy(new EditText(mock(Context.class)));
        login.PassText = PowerMockito.spy(new EditText(mock(Context.class)));

        doReturn(sequenceName).when(login.NameText).getText();
        doReturn(sequencePass).when(login.PassText).getText();
        doReturn("Dobby").when(sequenceName).toString();
        doReturn("123").when(sequencePass).toString();
        //when(login.NameText.getText().toString()).thenReturn("Dobby");
        sequenceName = login.NameText.getText();
        String mockString = sequenceName.toString();
        when(LoginData.isValidName(mockString)).thenReturn(true);
        //doReturn(true).when(LoginData).isValidName(mockString);

        Button mockButton = mock(Button.class);
        CharSequence should = "Successfully logged in";

        login.confirm = mockButton;
        login.onClickFunction(mockButton);
        assertThat("Text should be 'Successfully logged in'", login.text, is(should));
    }*/

    @Test
    public void testOnBackPressed() {
        login.onBackPressed();
        assertTrue(true);
    }
}