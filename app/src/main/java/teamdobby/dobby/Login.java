package teamdobby.dobby;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Marie on 13.04.2016.
 */
public class Login extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginData.main();
//Test
        final Button confirm;
        confirm = (Button)findViewById(R.id.loginConfirm);

        final EditText NameText = (EditText) findViewById(R.id.inputName);
        final EditText PassText = (EditText) findViewById(R.id.inputPassword);


        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CharSequence text = "";
                if(v==confirm){
                    String tmpname=NameText.getText().toString();
                    String tmppass=PassText.getText().toString();
                    if(LoginData.isValidName(tmpname)){
                        if(LoginData.isValidPass(tmpname,tmppass)){
                            text="Successfully logged in";
                            Intent myIntent = new Intent(Login.this, ConnectDrawer.class);
                            Login.this.startActivity(myIntent);
                            LoginData.setCurrUser(tmpname);
                            LoginData.setLogged();
                            finish();
                        }
                        else {
                            text="Wrong Password";
                        }
                    }
                    else {
                        text = "User not found";
                    }

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
