package tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class GruppePagerAdapter extends FragmentStatePagerAdapter {
    private int noOfItems;


    public GruppePagerAdapter(FragmentManager fm, int noOfItems, Context c) {
        super(fm);
        this.noOfItems = noOfItems;

    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){

            return GruppeProfilFragment.newInstance(position+1);
        }
        if(position==1){
            //Väter
            return GruppeDynamicVaeterFragment.newInstance(position+1);
        }

        if(position==2){
            //Brüder
            return GruppeDynamicBruederFragment.newInstance(position+1);
        }


        if(position==3){
            //Söhne
            return GruppeDynamicSoehneFragment.newInstance(position+1);
        }


        if(position==4){
            //Subjekte
            return GruppeUntergruppeBildenFragment.newInstance(position+1);
        }


        if(position==5){
            //Separatoren
            return GruppeSeparatorErstellenFragment.newInstance(position+1);
        }

       /* if(position==6){
            //Subjekte
            return GruppeSubjektsFragment.newInstance();
        }*/

        return null;





    }

    @Override
    public int getCount() {
        return noOfItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position==0){

            return "Allgemein";
        }
        if(position==1){

            return "Väter";
        }

        if(position==2){

            return "Brüder";
        }


        if(position==3){

            return "Söhne";
        }

        if(position==4){

            return "Untergruppe bilden";
        }

        if(position==5){

            return "Separator erzeugen";
        }

       /* if(position==6){

            return "Subjekte";
        }*/

        return "";
    }
}
