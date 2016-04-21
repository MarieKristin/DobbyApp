package teamdobby.dobby;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class ConnectDrawer extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_clse);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.connect_container, new ConnectFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Connect Fragment");

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_id:
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.connect_container,new ConnectFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Connect to Dobby");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_logout:
                        LoginData.notLogged();
                        LoginData.setCurrUser("");
                        CharSequence text = "Successfully logged out";
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent myIntent = new Intent(ConnectDrawer.this, Splashscreen.class);
                        ConnectDrawer.this.startActivity(myIntent);
                        finish();
                        break;
                    case R.id.id_information:
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.connect_container,new InformationFragment()
                        );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("General Information");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //do nothing because otherwise user could login a second time
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
