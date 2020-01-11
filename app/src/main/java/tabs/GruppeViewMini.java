package tabs;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.paulirotlite.R;
import zz_test.SpeedyLinearLayoutManager;

public class GruppeViewMini extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Radio operateRadio = null;


    List<Radio> dataSet;
    private Context context;
    private ItemClickListener mItemClickListener;


    private Activity activity;

    public List<Radio> getDataSet() {
        return dataSet;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public GruppeViewMini(Context context, List<Radio> itemList, RecyclerView recyclerView) {
        this.dataSet = itemList;
        this.context = context;


        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(context, SpeedyLinearLayoutManager.VERTICAL, false));


    }


    @Override
    public int getItemViewType(int position) {


        return 0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_row, parent,false);
        View layoutView;


        switch (viewType) {

            case 0:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gruppe_radio_mini_row, parent, false);
                return new ViewHolderNormal(layoutView);


        }

        return null;
    }


    List<ViewHolderNormal> t = new ArrayList<ViewHolderNormal>();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Radio shownRadio = dataSet.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderNormal viewHolderNormal = (ViewHolderNormal) holder;


                viewHolderNormal.radioname.setText(shownRadio.getName());


                viewHolderNormal.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                       // Log.d("GruppeTest2", "buuu " + isChecked);
                       // Log.d("GruppeTest2", "buuu " + dataSet.get(position).getName());
                        dataSet.get(position).setFormular_checked(isChecked);

                        if (isChecked) {

                            int i = 0;
                            for (Radio rr : dataSet) {

                                if (rr.equals(dataSet.get(position))) {

                                    rr.setFormular_checked(true);
                                    dataSet.set(i, rr);

                                } else {
                                    rr.setFormular_checked(false);
                                    dataSet.set(i, rr);

                                    t.get(i).sw.setOnCheckedChangeListener(null);
                                    t.get(i).sw.setChecked(false);
                                    t.get(i).sw.setOnCheckedChangeListener(this);

                                }

                                i++;


                            }


                        } else {
                            dataSet.get(position).setFormular_checked(false);


                        }


                        notifyDataSetChanged();

                        for (Radio rr : dataSet) {
                            Log.d("GruppeTest2", rr.getName() + ": " + rr.isFormular_checked());

                        }


                    }
                });


                break;


        }


    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }


    public class ViewHolderNormal extends RecyclerView.ViewHolder {

        TextView radioname;
        Switch sw;


        public ViewHolderNormal(View itemView) {
            super(itemView);


            radioname = (TextView) itemView.findViewById(R.id.radio_name_mini);
            sw = (Switch) itemView.findViewById(R.id.simpleSwitch_mini);
            t.add(this);


        }


    }


}