package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import akguen.liquidschool.paulirotlite.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

        private Context context;
        public static ArrayList<SchuelerMitCheck> modelArrayList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;


    public CustomAdapter(Context context, ArrayList<SchuelerMitCheck> modelArrayList) {

        this.context = context;
        this.modelArrayList = modelArrayList;

    }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }
        @Override
        public int getItemViewType(int position) {

            return position;
        }

        @Override
        public int getCount() {
            return modelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return modelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.model, null, true);

                holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                holder.tvName = (TextView) convertView.findViewById(R.id.nameTxt);
                convertView.setTag(holder);


            }else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder)convertView.getTag();
            }


           // holder.checkBox.setText("Checkbox "+position);
            holder.tvName.setText(modelArrayList.get(position).getSchuelerName());
            holder.checkBox.setChecked(modelArrayList.get(position).isChecked());
            holder.checkBox.setTag(R.integer.btnplusview, convertView);
            holder.checkBox.setTag( position);
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                    TextView tv = (TextView) tempview.findViewById(R.id.nameTxt);
                    Integer pos = (Integer)  holder.checkBox.getTag();
                    Toast.makeText(context, "Checkbox "+ pos +" clicked!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Name:  "+ tv.getText() +" clicked!", Toast.LENGTH_SHORT).show();

                    name = tv.getText().toString();


                    if(modelArrayList.get(pos).isChecked()){
                        modelArrayList.get(pos).setChecked(false);
                    }else {
                        modelArrayList.get(pos).setChecked(true);
                    }

                }
            });

            return convertView;
        }

        private class ViewHolder {

            protected CheckBox checkBox;
            private TextView tvName;

        }
}
