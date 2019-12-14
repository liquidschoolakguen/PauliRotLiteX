package tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.model.Lerngruppe;

public class WaehleSchuelerViewPagerAdapter extends FragmentStatePagerAdapter {
    private int noOfItems;
    private DataSource_Lerngruppe dS_L;
   private List<Lerngruppe> j;

    public WaehleSchuelerViewPagerAdapter(FragmentManager fm, int noOfItems, Context c) {
        super(fm);
        this.noOfItems = noOfItems;
        dS_L = new DataSource_Lerngruppe(c);
        dS_L.open();
    }

    @Override
    public Fragment getItem(int position) {
        return WaehleSchuelerDynamicFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return noOfItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        List h = dS_L.getAllLerngruppes();
        System.out.println("..............................."+h.size());


        if(position<h.size()){

            return ((Lerngruppe) h.get(position)).getName();
        }

        if(h==null||h.isEmpty()){

            return "Deine erste Lerngruppe";
        }
        return "";
    }
}
