package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import akguen.liquidschool.paulirotlite.R;
import model.SchuelerMitCheck;
import model.VorfallModel;

public class CustomAdapterVorfaelle extends BaseAdapter {

        private Context context;
        public static ArrayList<VorfallModel> vorfallModels;

        public CustomAdapterVorfaelle(Context context, ArrayList<VorfallModel> vorfallModels) {

            this.context = context;
            this.vorfallModels = vorfallModels;
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
            return vorfallModels.size();
        }

        @Override
        public Object getItem(int position) {
            return vorfallModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_vorfaelleliste, null, true);

                holder.tvName = (TextView) convertView.findViewById(R.id.tv_item_schuelername);
                holder.tvStraffpunkt = (TextView) convertView.findViewById(R.id.tv_item_straffpunkt);
                holder.tvVergeben = (TextView) convertView.findViewById(R.id.tv_item_vergeben);
                holder.tvDatum = (TextView) convertView.findViewById(R.id.tv_item_zeitpunkt);

                convertView.setTag(holder);


            }else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder)convertView.getTag();
            }


           // holder.checkBox.setText("Checkbox "+position);
            holder.tvName.setText(vorfallModels.get(position).getSchuelerName());


            return convertView;
        }

        private class ViewHolder {

            private TextView tvName,
                    tvStraffpunkt,
                    tvVergeben,
                    tvDatum ;

        }
}
