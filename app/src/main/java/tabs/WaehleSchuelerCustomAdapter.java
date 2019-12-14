package tabs;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.db.DataSource_Adresse;
import akguen.liquidschool.mylib2.model.Schueler;

public class WaehleSchuelerCustomAdapter extends ArrayAdapter<Schueler> implements View.OnClickListener{
    private static final String LOG_TAG = WaehleSchuelerCustomAdapter.class.getSimpleName();

     private ArrayList<Schueler> dataSet;
    Context mContext;


    public void addItem(Schueler item) {
        this.dataSet.add(item);
        notifyDataSetChanged();
    }


    public WaehleSchuelerCustomAdapter(ArrayList<Schueler> data, Context context) {
        super(context, R.layout.waehle_schueler_row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Schueler schueler=(Schueler)object;
        Log.d(LOG_TAG, "onClick+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                Snackbar.make(v, "Name" + schueler.getVorname(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();

    }

    private int lastPosition = -1;



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Schueler dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view


        final View result;


        Schueler p = getItem(position);
        if (convertView == null) {


            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.waehle_schueler_row, parent, false);


        } else {

        }


        if(p!=null){
            TextView tt1 = (TextView) convertView.findViewById(R.id.tv_item_schuelername);
            TextView tt2 = (TextView) convertView.findViewById(R.id.tv_item_strafpunkt);


            if (tt1 != null) {
                tt1.setText(p.getVorname());
            }

            if (tt2 != null) {
                tt2.setText(p.getGeburtstag());
            }


        }

        // Return the completed view to render on screen
        return convertView;
    }
}