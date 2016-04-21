package teamdobby.dobby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/*import android.os.Handler;*/
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Marie on 13.04.2016.
 */
public class Splashscreen extends Activity {
    /*// Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;*/

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = (ImageView) findViewById(R.id.imgLogo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        /*new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            /*@Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splashscreen.this, ConnectDrawer.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);*/
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
