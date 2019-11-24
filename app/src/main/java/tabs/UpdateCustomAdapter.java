package tabs;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import akguen.liquidschool.paulirotlite.R;
import db.DataSource_Adresse;
import model.Schueler;

public class UpdateCustomAdapter extends ArrayAdapter<Schueler> implements View.OnClickListener{

    private static final String LOG_TAG = DataSource_Adresse.class.getSimpleName();
     private ArrayList<Schueler> dataSet;
    Context mContext;





    public UpdateCustomAdapter(ArrayList<Schueler> data, Context context) {
        super(context, R.layout.update_row, data);
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
            convertView = inflater.inflate(R.layout.update_row, parent, false);


        } else {

        }


        if(p!=null){
            EditText tt1 = (EditText) convertView.findViewById(R.id.editText_lg_updaten_1);

            RadioGroup g1 = (RadioGroup) convertView.findViewById(R.id.toggle1);



            TextView e1s = (TextView) convertView.findViewById(R.id.tv_strafpunkt_1);

            Button b1 = (Button) convertView.findViewById(R.id.btn_strafpunkt_minus_1);
            Button b1p = (Button) convertView.findViewById(R.id.btn_strafpunkt_plus_1);


            if (tt1 != null) {
                tt1.setText(p.getVorname());
            }
            if (e1s != null) {
                e1s.setText(p.getGeburtstag());
            }

            b1.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int strafpunkt = Integer.parseInt(e1s.getText().toString());
                    if(strafpunkt<9) {
                        e1s.setText(Integer.toString(strafpunkt + 1));
                    }
                }
            });
            b1p.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int strafpunkt = Integer.parseInt(e1s.getText().toString());
                    if(strafpunkt>0) {
                        e1s.setText(Integer.toString(strafpunkt - 1));
                    }
                }
            });






        }

        // Return the completed view to render on screen
        return convertView;
    }
}