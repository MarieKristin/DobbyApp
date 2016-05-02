package teamdobby.dobby;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.test.mock.MockContext;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static org.hamcrest.CoreMatchers.any;
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
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

/**
 * Created by Marie on 02.05.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Splashscreen.class,AnimationUtils.class})
public class SplashscreenTest {

    private Splashscreen splashscreen;

    @Mock
    private Animation animation;

    @Before
    public void setUp() throws Exception{
        splashscreen = PowerMockito.spy(new Splashscreen());
        ImageView imageView = mock(ImageView.class);
        Context context = new MockContext();
        animation = mock(Animation.class);
        splashscreen.animation = animation;

        doReturn(imageView).when(splashscreen).findViewById(anyInt());
        doReturn(context).when(splashscreen).getApplicationContext();
        //doNothing().when(splashscreen).animationListener(mock(Animation.class));

        suppress(method(Activity.class, "onCreate", Bundle.class));
        suppress(method(Activity.class, "setContentView", int.class));
        suppress(method(AnimationUtils.class, "loadAnimation", Context.class, int.class));
        //suppress(method(Splashscreen.class, "animationListener", Animation.class));
    }

    @Test
    public void testOnCreate() {
        suppress(method(Splashscreen.class, "animationListener", Animation.class));

        splashscreen.onCreate(null);

        verify(splashscreen).findViewById(anyInt());
    }

    /*@Test
    public void testAnimationListener() {

        splashscreen.onCreate(mock(Bundle.class));

        verify(animation).setAnimationListener((Animation.AnimationListener) Matchers.any());
    }*/
}