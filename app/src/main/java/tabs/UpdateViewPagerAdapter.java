package tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import db.DataSource_Lerngruppe;

public class UpdateViewPagerAdapter extends FragmentStatePagerAdapter {
    private int noOfItems;
    private DataSource_Lerngruppe dS_L;


    public UpdateViewPagerAdapter(FragmentManager fm, int noOfItems, Context c) {
        super(fm);
        this.noOfItems = noOfItems;
        dS_L = new DataSource_Lerngruppe(c);
        dS_L.open();
    }

    @Override
    public Fragment getItem(int position) {
        return UpdateDynamicFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return noOfItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(dS_L.getLerngruppeById(position+1)==null){

            return "+";
        }
        return dS_L.getLerngruppeById(position+1).getName();
    }
}
