package akguen.liquidschool.paulirotlite;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mydb = new DBHelper(this);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);

        //TODO starActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        goDrawer();
    }

    private void goDrawer() {

        mDrawerLayout = findViewById(R.id.drawer_layout_);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Log.i("item", "on: "+menuItem.getItemId());

                        switch (menuItem.getItemId()) {
                            case android.R.id.home:
                                mDrawerLayout.openDrawer(GravityCompat.START);
                                return true;

                            case R.id.nav_schuelerVorfaelle:
                                Intent schuelerVorfaelleTable = new Intent(MainActivity.this, SchuelerVorfaelleTable.class);
                                schuelerVorfaelleTable.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(schuelerVorfaelleTable);
                                return true;

                            case R.id.nav_start:
                                Intent vergehenActivity = new Intent(MainActivity.this, VergehenActivity.class);
                                vergehenActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(vergehenActivity);
                                return true;
                                /*
                            case R.id.nav_schulleiter:
                                return true;
                            case R.id.nav_camera:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                Intent intentCamera = new Intent(MainActivity.this, Cameratest2.class);

                                //Intent intentCamera = new Intent(MainActivity.this, CameraActivity.class);
                                intentCamera.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intentCamera);
                                return true;
                            case R.id.nav_statistics:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                Intent statistics = new Intent(MainActivity.this, Statistics.class);
                                startActivity(statistics);
                                return true;

                            case R.id.nav_mailsend:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                Intent mailsend = new Intent(MainActivity.this, SendMailActivity.class);
                                startActivity(mailsend);
                                return true;*/
                        }

                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("item", ""+item.getItemId());

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

                // TODO kann es weg, weil es die Logout Funktion in option menu von der PauseActivity gibt.
                case R.id.logout:
                    Toast.makeText(this, "Settings wurde gedrückt", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, PauseActivity.class));

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
          /*
            case R.id.nav_schulleiter:
                return true;
            case R.id.nav_lehrer:
                return true;
            case R.id.nav_schueler:
                Log.i("item", ""+item.getItemId());
                 Intent intent = new Intent(MainActivity.this, SchuelerActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
                return true;*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

}
