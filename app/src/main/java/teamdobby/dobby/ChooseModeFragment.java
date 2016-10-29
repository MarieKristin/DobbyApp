package teamdobby.dobby;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dd.CircularProgressButton;


import teamdobby.dobby.Joystickfolder.Main;

/**
 * Created by Marie on 29.10.2016.
 */
public class ChooseModeFragment extends Fragment{

    private CircularProgressButton btnAuto, btnMan;
    FragmentTransaction fragmentTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choosemode, container, false);

        btnAuto = (CircularProgressButton) view.findViewById(R.id.btnAutomatic);
        btnMan = (CircularProgressButton) view.findViewById(R.id.btnManual);
        btnAuto.setIndeterminateProgressMode(true);
        btnMan.setIndeterminateProgressMode(true);

        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnAuto.getProgress() == 0) {
                    btnAuto.setProgress(50);

                    btnAuto.setProgress(-1);
                    /*if(somethingGoesWrong) {
                        btnAuto.setProgress(-1);
                    }*/
                } else if (btnAuto.getProgress() == -1) {
                    btnAuto.setProgress(0);
                }
            }
        });

        btnMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnMan.getProgress() == 0) {
                    btnMan.setProgress(50);

                    fragmentTransaction = getFragmentManager().beginTransaction();
                    btnMan.setProgress(100);
                    fragmentTransaction.replace(R.id.connect_container, new Main());
                    fragmentTransaction.commit();



                    /*if(somethingGoesWrong) {
                        btnMan.setProgress(-1);
                    }*/
                } else if (btnMan.getProgress() == -1) {
                    btnMan.setProgress(0);
                }
            }
        });

        return view;
    }


}
