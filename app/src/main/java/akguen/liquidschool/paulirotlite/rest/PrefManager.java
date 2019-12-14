package akguen.liquidschool.paulirotlite.rest;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    Context context;

    public PrefManager(Context context) {
        this.context = context;
    }



    public void setNutzerErkanntPref() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NutzRecht", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NutzerErkannt", "1");
        editor.commit();

    }


    public String getNutzerErkanntPref() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("NutzRecht", Context.MODE_PRIVATE);
        String boo = sharedPreferences.getString("NutzerErkannt", "-1");

        return boo;
    }




    public void clearNutzerErkanntPref() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NutzRecht", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("NutzerErkannt");
        editor.commit();

        //Log.i("clear", "PrefManager - > clear " + "NutzerErkannt: " + sharedPreferences.getString("NutzerErkannt", ""));

    }




}
