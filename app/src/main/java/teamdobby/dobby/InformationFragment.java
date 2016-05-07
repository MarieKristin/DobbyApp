package teamdobby.dobby;
//VIEWMODEL

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {


    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        clickListener(view);

        return view;
    }

    private void clickListener(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Soon you will have the option to mail the Dobby-team", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            //fab.setOnClickListener(new MyClickListener());
        }
    }

    /*public static class MyClickListener implements View.OnClickListener {

        boolean success = false;

        public MyClickListener() {

        }

        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Soon you will have the option to mail the Dobby-team", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            this.success = true;
        }
    }*/


}
