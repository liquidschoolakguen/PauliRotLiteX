package tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import akguen.liquidschool.coredata.db.DataSource_Lerngruppe;
import akguen.liquidschool.coredata.model.Lerngruppe;

public class Gruppe2PagerAdapter extends FragmentStatePagerAdapter {
    private int noOfItems;


    public Gruppe2PagerAdapter(FragmentManager fm, int noOfItems, Context c) {
        super(fm);
        this.noOfItems = noOfItems;

    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){

            return Gruppe2ProfilFragment.newInstance(position);
        }
        if(position==1){

            return Gruppe2DynamicFragment.newInstance(position);
        }

        if(position==2){

            return Gruppe2DynamicFragment.newInstance(position);
        }


        if(position==3){

            return Gruppe2DynamicFragment.newInstance(position);
        }

        if(position==4){

            return Gruppe2DynamicFragment.newInstance(position);
        }

        if(position==5){

            return Gruppe2DynamicFragment.newInstance(position);
        }

        return null;




        return WaehleSchuelerDynamicFragment.newInstance(position + 1);
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

            return "Separatoren";
        }

        if(position==5){

            return "Subjekte";
        }

        return "";
    }
}
