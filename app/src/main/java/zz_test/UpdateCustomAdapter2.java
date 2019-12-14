package zz_test;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Schueler;

public class UpdateCustomAdapter2 extends ArrayAdapter<Schueler> implements View.OnClickListener, View.OnLongClickListener {
    private static final String LOG_TAG = UpdateCustomAdapter2.class.getSimpleName();

     ArrayList<Schueler> dataSet;
    Context mContext;


    public void addItem(Schueler item) {
        this.dataSet.add(item);
        notifyDataSetChanged();
    }


    public void replaceItem(Schueler item, int id) {
        this.dataSet.set(id, item);
        notifyDataSetChanged();
    }

    public void replaceItem2(Schueler item, int id) {
        this.dataSet.remove(id);
        this.dataSet.add(id, item);
        notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

      /*  int position = (Integer) v.getTag();
        Object object = getItem(position);
        Schueler schueler = (Schueler) object;
        Log.d(LOG_TAG, "onClick+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        Snackbar.make(v, "Name" + schueler.getVorname(), Snackbar.LENGTH_LONG)
                .setAction("No action", null).show();*/

    }

    public UpdateCustomAdapter2(ArrayList<Schueler> data, Context context) {
        super(context, R.layout.waehle_schueler_row, data);
        this.dataSet = data;
        this.mContext = context;

    }


    // return position here
    @Override
    public long getItemId(int position) {
        return position;
    }

    // return size of list
    @Override
    public int getCount() {
        return dataSet.size();
    }


    public Schueler getItem(int position) {
        return dataSet.get(position);
    }

    public  ArrayList<Schueler> getData(){

        return dataSet;
    }
    public int getPos(Schueler s){


        return dataSet.indexOf(s);
    }


    @Override
    public boolean onLongClick(View v) {






        return false;
    }


    static class ViewHolder {
        RadioGroup geschlecht;
        TextView title;


        RadioButton männlich;
        RadioButton weiblich;
        TextView strafpunktUpdate;
        Button pluss;
        Button minus;


        TextView schuelername;
        TextView strafpunkt;


        TextView plusButton;

        ViewHolder(View convertView) {
            geschlecht = (RadioGroup) convertView.findViewById(R.id.toggle1);
            männlich = (RadioButton) convertView.findViewById(R.id.b_m_1);
            weiblich = (RadioButton) convertView.findViewById(R.id.b_w_1);

            title = (EditText) convertView.findViewById(R.id.editText_lg_updaten_1);


            strafpunktUpdate = (TextView) convertView.findViewById(R.id.tv_strafpunkt_1);

            pluss = (Button) convertView.findViewById(R.id.btn_strafpunkt_plus_1);
            minus = (Button) convertView.findViewById(R.id.btn_strafpunkt_minus_1);



        }


    }



public void gogo(View view, Schueler g){
  /*  Schueler s = new Schueler();
    s.setItemType("1");
    s.setGeburtstag("0");
    s.setVorname("");
    s.setGeschlecht("M");
    insert(s,dataSet.size()-1);

    notifyDataSetChanged();*/

    System.out.println("8888888888888gogo()8888888888888888888888888888  "+dataSet.size());
    int machwas=0;
    List<Integer> myCoords = new ArrayList<Integer>();
    for(int i=0; i<dataSet.size()-1; i++)
    {
        // Die Ausgabe findet fünfmal statt (von 0 bis 4)
        System.out.println("i ist "+dataSet.get(i).getVorname());

        if(dataSet.get(i).getVorname().equals(g.getVorname())){

            System.out.println("i mmmmmmmmmmmmmmmmmm ist "+i);
            Snackbar.make(view, dataSet.get(i).getVorname()+"\n"+dataSet.get(i).getGeburtstag(), Snackbar.LENGTH_LONG)
                    .setAction("No action", null).show();


            machwas= i;
            myCoords.add(i);



        }


    }


    for (Integer number : myCoords) {
        Schueler s = new Schueler();
        s.setItemType("1");
        s.setGeburtstag(dataSet.get(number).getItemType());
        s.setVorname(dataSet.get(number).getVorname());
        s.setGeschlecht(dataSet.get(number).getGeschlecht());
        replaceItem2(s,number);

    }
    notifyDataSetChanged();

}


    // Return an integer representing the type by fetching the enum type ordinal
    @Override
    public int getItemViewType(int position) {
        if(getItem(position)==null){

            System.out.println("8888888888888getItem(position)8888888888888888888888888888");
        }else if(getItem(position).getItemType()==null){

            System.out.println("88888888888888888getItem(position).getItemType()888888888888888888888888");
        }
        return Integer.parseInt(getItem(position).getItemType());
    }

    // Total number of types is the number of enum values
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    LayoutInflater inflater;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vholder;
        Schueler p = getItem(position);


       inflater = LayoutInflater.from(getContext());

        if (convertView == null) {
            convertView = getInflatedLayoutForType(getItemViewType(position));


            vholder = new ViewHolder(convertView);


            if (getItemViewType(position) == 1) {

                vholder = prepareOne(convertView, vholder, position);

            } else if (getItemViewType(position) == 2) {

                vholder = prepareTwo(convertView, vholder, position);

            } else if (getItemViewType(position) == 3) {

                //vholder = prepareThree(convertView, vholder, position);


            }


            convertView.setTag(vholder);

        } else {

            vholder = (ViewHolder) convertView.getTag();
        }


        if (getItemViewType(position) == 1) {


            vholder = setOne(convertView, vholder, position);
        } else if (getItemViewType(position) == 2) {


            vholder = setTwo(convertView, vholder, position);
        } else if (getItemViewType(position) == 3) {


            //vholder = setThree(convertView, vholder, position);
            vholder.plusButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Schueler s = new Schueler();
                    s.setItemType("1");
                    s.setGeburtstag("0");
                    s.setVorname("");
                    s.setGeschlecht("M");
                    insert(s,dataSet.size()-1);

                    notifyDataSetChanged();



                    ListView vvv = (ListView)  parent;
                    vvv.smoothScrollToPosition(getCount());


                }
            });
        }


        return convertView;
    }


    private ViewHolder setThree(View v, ViewHolder vholder, int position) {

        vholder.plusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Schueler s = new Schueler();
                s.setItemType("2");
                s.setGeburtstag("0");
                s.setVorname("");
                s.setGeschlecht("M");
                insert(s,dataSet.size()-1);
                notifyDataSetChanged();






            }
        });


        return vholder;
    }


    private ViewHolder setTwo(View v, ViewHolder vholder, int position) {

        vholder.schuelername.setText(getItem(position).getVorname());
        vholder.strafpunkt.setText(getItem(position).getGeburtstag());


        return vholder;
    }

    private ViewHolder setOne(View v, ViewHolder vholder, int position) {


        if (vholder.minus == null) {

            System.out.println("88888888888888888888888888888888888888888");

        }

        vholder.minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int strafpunkt = Integer.parseInt(vholder.strafpunktUpdate.getText().toString());
                if (strafpunkt < 9) {
                    vholder.strafpunktUpdate.setText(Integer.toString(strafpunkt + 1));
                }
            }
        });
        vholder.pluss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int strafpunkt = Integer.parseInt(vholder.strafpunktUpdate.getText().toString());
                if (strafpunkt > 0) {
                    vholder.strafpunktUpdate.setText(Integer.toString(strafpunkt - 1));
                }
            }
        });


        vholder.title.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {

/*
                    Schueler itemOld = (Schueler) getItem(position);
                    if (itemOld != null) {
                        if (itemOld.getVorname() != null) {

                            if (s.toString() != null && !s.toString().equals("")) {
                                Schueler itemNeu = itemOld;


                                // }
                            }
                        }
                    }*/

            }
        });
        vholder.geschlecht.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                 /*   String gesch;
                    Schueler item = (Schueler) getItem(position);
                    if (checkedId == vholder.männlich.getId()) {
                        gesch = "M";

                    } else {

                        gesch = "W";

                    }

                    item.setGeschlecht(gesch);
                    dataSet.set(position, item);*/
            }
        });


        vholder.title.setText(getItem(position).getVorname());


        vholder.strafpunktUpdate.setText(getItem(position).getGeburtstag());

        String ggg = getItem(position).getGeschlecht();
        int _id;
        if (ggg.equals("M")) {

            _id = vholder.männlich.getId();
        } else {

            _id = vholder.weiblich.getId();
        }
        vholder.geschlecht.check(_id);




        return vholder;
    }



    private ViewHolder prepareTwo(View v, ViewHolder vholder, int position) {

        vholder.schuelername = (TextView) v.findViewById(R.id.tv_item_schuelername);
        vholder.strafpunkt = (TextView) v.findViewById(R.id.tv_item_strafpunkt);


        return vholder;
    }

    private ViewHolder prepareOne(View convertView, ViewHolder vholder, int position) {

        vholder.geschlecht = (RadioGroup) convertView.findViewById(R.id.toggle1);
        vholder.männlich = (RadioButton) convertView.findViewById(R.id.b_m_1);
        vholder.weiblich = (RadioButton) convertView.findViewById(R.id.b_w_1);

        vholder.title = (EditText) convertView.findViewById(R.id.editText_lg_updaten_1);


        vholder.strafpunktUpdate = (TextView) convertView.findViewById(R.id.tv_strafpunkt_1);

        vholder.pluss = (Button) convertView.findViewById(R.id.btn_strafpunkt_plus_1);
        vholder.minus = (Button) convertView.findViewById(R.id.btn_strafpunkt_minus_1);


        vholder.minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int strafpunkt = Integer.parseInt(vholder.strafpunktUpdate.getText().toString());
                if (strafpunkt < 9) {
                    vholder.strafpunktUpdate.setText(Integer.toString(strafpunkt + 1));
                }
            }
        });
        vholder.pluss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int strafpunkt = Integer.parseInt(vholder.strafpunktUpdate.getText().toString());
                if (strafpunkt > 0) {
                    vholder.strafpunktUpdate.setText(Integer.toString(strafpunkt - 1));
                }
            }
        });


        vholder.title.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {


                Schueler itemOld = (Schueler) dataSet.get(position);
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
        vholder.geschlecht.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                String gesch;
                Schueler item = (Schueler) dataSet.get(position);
                if (checkedId == vholder.männlich.getId()) {
                    gesch = "M";

                } else {

                    gesch = "W";

                }

                item.setGeschlecht(gesch);
                dataSet.set(position, item);
            }
        });


        vholder.title.setText(getItem(position).getVorname());


        vholder.strafpunktUpdate.setText(getItem(position).getGeburtstag());

        String ggg = getItem(position).getGeschlecht();
        int _id;
        if (ggg.equals("M")) {

            _id = vholder.männlich.getId();
        } else {

            _id = vholder.weiblich.getId();
        }
        vholder.geschlecht.check(_id);


        return vholder;
    }


    private View getInflatedLayoutForType(int type) {
        if (type == 1) {
            return inflater.inflate(R.layout.update_row, null);
        } else if (type == 2) {
            return inflater.inflate(R.layout.waehle_schueler_row, null);
        } else if (type == 3) {
            return inflater.inflate(R.layout.zz_plus_row, null);
        } else {
            return null;
        }
    }


}
