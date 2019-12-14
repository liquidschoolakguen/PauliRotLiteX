package zz_test;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Schueler;

public class ViewHolderUpdateX extends RecyclerView.ViewHolder  {


    RadioGroup geschlecht;
    TextView title;


    RadioButton männlich;
    RadioButton weiblich;
    TextView strafpunktUpdate;
    Button pluss;
    Button minus;


    TextView plusButton;
    public ViewHolderUpdateX(View itemView) {
        super(itemView);
        //itemView.setOnClickListener(this);
        geschlecht = (RadioGroup) itemView.findViewById(R.id.toggle1);
        männlich = (RadioButton) itemView.findViewById(R.id.b_m_1);
        weiblich = (RadioButton) itemView.findViewById(R.id.b_w_1);

        title = (EditText) itemView.findViewById(R.id.editText_lg_updaten_1);


        strafpunktUpdate = (TextView) itemView.findViewById(R.id.tv_strafpunkt_1);

        pluss = (Button) itemView.findViewById(R.id.btn_strafpunkt_plus_1);
        minus = (Button) itemView.findViewById(R.id.btn_strafpunkt_minus_1);




/*

        minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int strafpunkt = Integer.parseInt(strafpunktUpdate.getText().toString());
                if (strafpunkt < 9) {
                    strafpunktUpdate.setText(Integer.toString(strafpunkt + 1));
                }
            }
        });
        pluss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int strafpunkt = Integer.parseInt(strafpunktUpdate.getText().toString());
                if (strafpunkt > 0) {
                    strafpunktUpdate.setText(Integer.toString(strafpunkt - 1));
                }
            }
        });
*/


       /* title.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {


                Schueler itemOld = selectedSchueler;
                if (itemOld != null) {
                    if (itemOld.getVorname() != null) {

                        if (s.toString() != null && !s.toString().equals("")) {
                            Schueler itemNeu = itemOld;


                            // }
                        }
                    }
                }

            }
        });
        viewHolderUpdate.geschlecht.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                String gesch;

                if (checkedId == viewHolderUpdate.männlich.getId()) {
                    gesch = "M";

                } else {

                    gesch = "W";

                }

                selectedSchueler.setGeschlecht(gesch);
                dataSet.set(position, selectedSchueler);
                notifyDataSetChanged();;
            }
        });
        */


    }


}