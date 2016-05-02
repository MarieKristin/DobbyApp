package teamdobby.dobby;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.test.mock.MockContext;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

/**
 * Created by Marie on 26.04.2016.
 */
public class InformationFragmentTest {

    @Mock
    private FloatingActionButton button;

    @Test
    public void testOnCreateView_withEmptyButton() {
        InformationFragment informationFragment = new InformationFragment();
        LayoutInflater layoutInflater = mock(LayoutInflater.class);
        ViewGroup viewGroup = mock(ViewGroup.class);
        Bundle bundle = mock(Bundle.class);
        View view = mock(View.class);

        when(layoutInflater.inflate(R.layout.fragment_information, viewGroup, false)).thenReturn(view);
        when(view.findViewById(R.id.fab)).thenReturn(null);

        View resultView = informationFragment.onCreateView(layoutInflater, viewGroup, bundle);

        verify(view).findViewById(R.id.fab);
    }

    @Test
    public void testOnCreateView_withButton() {
        InformationFragment informationFragment = new InformationFragment();
        LayoutInflater layoutInflater = mock(LayoutInflater.class);
        ViewGroup viewGroup = mock(ViewGroup.class);
        Bundle bundle = mock(Bundle.class);
        View view = mock(View.class);
        FloatingActionButton floatingActionButton = mock(FloatingActionButton.class);

        when(layoutInflater.inflate(R.layout.fragment_information, viewGroup, false)).thenReturn(view);
        when(view.findViewById(R.id.fab)).thenReturn(floatingActionButton);

        informationFragment.onCreateView(layoutInflater, viewGroup, bundle);

        verify(view).findViewById(R.id.fab);
        //verify(button).setOnClickListener((View.OnClickListener) any());
    }

    /*@Test
    public void testMyClickListener() {
        InformationFragment.MyClickListener myClickListener = new InformationFragment.MyClickListener();
        View view = mock(View.class);

        myClickListener.onClick(view);
        assertTrue(myClickListener.success);
    }*/
}