package tabs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import akguen.liquidschool.coredata.db.Controller;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.coredata.model.Schueler;
import akguen.liquidschool.paulirotlite.R;
import zz_test.SpeedyLinearLayoutManager;

public class GruppeViewAdapterForSeparatorErstellen extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    public GruppeViewAdapterForSeparatorErstellen(Context context, List<Radio> itemList, RecyclerView recyclerView) {
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
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gruppe_separator_erstellen_row, parent, false);
                return new ViewHolderNormal(layoutView);



        }

        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Radio shownRadio = dataSet.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderNormal viewHolderNormal = (ViewHolderNormal) holder;


                viewHolderNormal.radioname.setText(shownRadio.getName());


                viewHolderNormal.radioname.requestFocus();


                viewHolderNormal.radioname.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    public void afterTextChanged(Editable s) {

                        dataSet.get(position).setName(s.toString());

                    }
                });



                viewHolderNormal.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        dataSet.get(position).setDefault_checked(isChecked);
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




    public class ViewHolderNormal extends RecyclerView.ViewHolder  {

        EditText radioname;
        Switch sw;


        public ViewHolderNormal(View itemView) {
            super(itemView);


            radioname = (EditText) itemView.findViewById(R.id.et_radio_name);
            sw = (Switch) itemView.findViewById(R.id.simpleSwitch);



        }





    }










}