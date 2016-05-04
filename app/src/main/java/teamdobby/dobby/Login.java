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

    Button confirm;
    EditText NameText;
    EditText PassText;
    CharSequence text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginData.main();

        confirm = (Button)findViewById(R.id.loginConfirm);

        NameText = (EditText) findViewById(R.id.inputName);
        PassText = (EditText) findViewById(R.id.inputPassword);


        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickFunction(v);
            }
        });
    }

    public void onClickFunction(View v) {
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

            this.toastShow();
        }
    }

    private void toastShow() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
