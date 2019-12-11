package akguen.liquidschool.paulirotlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import org.apache.commons.io.IOUtils;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;


    SharedPreferences sharedpreferences;
    EditText etname, etpasswort;

    Button btnLogin;

    String passwortVonDB;

    String nameVonEditText;
    String passwortVonEditText;


    int nameLaenge = 0;
    int passwortLaenge = 0;

    PrefManager prefManager;
    //
    public static final String mypreference = "mypref";
    public static final String name = "nameKey";
    public static final String passwort = "passwortKey";

    CharSequence csn, csp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);

        // goDrawer();


        prefManager = new PrefManager(this);

        prefManager.clearNutzerErkanntPref();
        /*if (prefManager.getNutzerErkanntPref().equals("1")) {

            startVergehenActivity();

        }*/

        // startStammgruppenwahlActivity();

        etname = findViewById(R.id.etLoginnamepauli);
        etpasswort = findViewById(R.id.etLoginpasswortpauli);


        btnLogin = findViewById(R.id.bntLogin);
        // btnLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDisable));
        btnLogin.setEnabled(false);


        final TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("LoginActivity", "" + String.valueOf(s.length()));
                nameLaenge = s.length();
                changeButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        };

        etname.addTextChangedListener(mTextEditorWatcher);


        final TextWatcher mTextEditorWatcher2 = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("LoginActivity", "" + String.valueOf(s.length()));
                passwortLaenge = s.length();
                changeButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        };

        etpasswort.addTextChangedListener(mTextEditorWatcher2);


        btnLogin.setOnClickListener(new View.OnClickListener() {

            // hier muss Passwort von db gechecked werden!

            //new Datenbanksachen(vergehensTitle).execute("someParams");


            @Override
            public void onClick(View view) {
                nameVonEditText = etname.getText().toString();
                passwortVonEditText = etpasswort.getText().toString();

                if (isKollegeInDatebaseVorhanden(nameVonEditText, passwortVonEditText)) {

                    prefManager = new PrefManager(LoginActivity.this);
                    prefManager.setNutzerErkanntPref();
                    startStammgruppenwahlActivity();
                } else {

                    etname.setText("");
                    etpasswort.setText("");


                }
                //Log.d("test", "" + isKollegeInDatebaseVorhanden(nameVonEditText, passwortVonEditText));


            }
        });


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
                        Log.i("item", "on: " + menuItem.getItemId());

                        switch (menuItem.getItemId()) {
                            case android.R.id.home:
                                mDrawerLayout.openDrawer(GravityCompat.START);
                                return true;


                            case R.id.nav_start:
                                Intent vergehenActivity = new Intent(LoginActivity.this, VergehenActivity.class);
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


    private void changeButton() {
        if (nameLaenge >= 3 && passwortLaenge >= 5) {

            btnLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            btnLogin.setEnabled(true);
        } else {

            // btnLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDisable));
            btnLogin.setEnabled(false);


        }
    }

    public boolean isKollegeInDatebaseVorhanden(String name, String passwort) {

/*

        DatenbankTask datenbankTask = new DatenbankTask();
        String result = "";


        try {
            datenbankTask.execute("existiertKollege", name, passwort).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String kollegenId = datenbankTask.dbId;

        if (kollegenId != null && !kollegenId.equals("")) {
            Log.i("LoginActivity: ", " gefundener Kollege (Id): " + kollegenId);
            return true;
        } else {

            Log.i("LoginActivity: ", " Kollege nicht gefunden ");
            return false;
        }
*/

        return true;
    }

    private void startStammgruppenwahlActivity() {
        Intent stammgruppenwahl = new Intent(getApplicationContext(), E_StammgruppenwahlActivity.class);
        startActivity(stammgruppenwahl);
    }


}
