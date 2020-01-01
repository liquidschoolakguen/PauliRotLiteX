package tabs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


import akguen.liquidschool.coredata.db.Controller;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Separator;
import akguen.liquidschool.coredata.db.MyDbHelper;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.coredata.model.Separator;
import akguen.liquidschool.paulirotlite.R;
import zz_test.SpeedyLinearLayoutManager;

public class GruppeViewAdapterForUntergruppeBilden extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Separator operateSeparator = null;

    private RecyclerView recyclerView;
    List<Separator> dataSet;
    private Context context;
    private ItemClickListener mItemClickListener;
    private Gruppe gey;


    private Activity activity;

    public List<Separator> getDataSet() {
        return dataSet;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public GruppeViewAdapterForUntergruppeBilden(Context context, List<Separator> itemList, RecyclerView recyclerView, Gruppe gey) {
        this.dataSet = itemList;
        this.context = context;
        this.recyclerView = recyclerView;
        this.gey = gey;


        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(context, SpeedyLinearLayoutManager.VERTICAL, false));


    }





    @Override
    public int getItemViewType(int position) {


        return dataSet.get(position).isSelected()? 1:0;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_row, parent,false);
        View layoutView;



        switch (viewType) {

            case 0:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gruppe_untergruppe_bilden_row, parent, false);
                return new ViewHolderNormal(layoutView);


            case 1:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gruppe_untergruppe_bilden_row_options, parent, false);
                return new ViewHolderSeparatorOption(layoutView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Separator shownSeparator = dataSet.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderNormal viewHolderNormal = (ViewHolderNormal) holder;


                viewHolderNormal.separatorname.setText(shownSeparator.getName());
                break;




            case 1: //lernseparatornNamen disable Item
                ViewHolderSeparatorOption viewHolderSchuelerOption = (ViewHolderSeparatorOption) holder;


                viewHolderSchuelerOption.separatorname.setText(shownSeparator.getName());

                viewHolderSchuelerOption.separatorname.setText(shownSeparator.getName());

                selectedSeparatorHolder = viewHolderSchuelerOption;
                selectedSeparatorHolder.animationOn.start();


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

    public ViewHolderSeparatorOption selectedSeparatorHolder = null;



    public class ViewHolderNormal extends RecyclerView.ViewHolder implements  View.OnLongClickListener {

        TextView separatorname;


        public ViewHolderNormal(View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(this);
            separatorname = (TextView) itemView.findViewById(R.id.tv_item_separator_name);

        }



        @Override
        public boolean onLongClick(View v) {

            if (operateSeparator == null) {


                dataSet.get(getLayoutPosition()).setSelected(true);
                operateSeparator = dataSet.get(getLayoutPosition());

                notifyDataSetChanged();

                return true;

            }else{

                dataSet.get(dataSet.indexOf(operateSeparator)).setSelected(false);
                operateSeparator = null;

                selectedSeparatorHolder.animationOff.start();
                selectedSeparatorHolder = null;

                notifyDataSetChanged();


                return true;

            }

        }


    }




    public class ViewHolderSeparatorOption extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        TextView separatorname;
        TextView separatorstringId;



        ValueAnimator animationOn;
        ValueAnimator animationOff;

        ImageView okButton;
        ImageView löschButton;


        public ViewHolderSeparatorOption(View itemView) {
            super(itemView);

            separatorname = (TextView) itemView.findViewById(R.id.tv_item_separator_name);

            layout = (RelativeLayout) itemView.findViewById(R.id.layout_sep);
            okButton = (ImageView) itemView.findViewById(R.id.okkb);


            animationOn = ValueAnimator.ofInt(180, 275);
            animationOn.setDuration(200);
            animationOn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    layout.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                    layout.requestLayout();
                }
            });
            animationOn.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {

                }
            });


            animationOff = ValueAnimator.ofInt(275, 180);
            animationOff.setDuration(200);
            animationOff.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    layout.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                    layout.requestLayout();
                }
            });
            animationOff.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {

                }
            });

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    dataSet.get(dataSet.indexOf(operateSeparator)).setSelected(false);
                    operateSeparator = null;

                    selectedSeparatorHolder.animationOff.start();
                    selectedSeparatorHolder = null;

                    notifyDataSetChanged();


                }
            });


            ContentValues i_to_TrueFalse = new ContentValues();
            ContentValues i_to_StringId = new ContentValues();


            DataSource_Separator v = new DataSource_Separator(context);
            v.open();

            DataSource_Radio rrr = new DataSource_Radio(context);
            rrr.open();


            int ii = 0;

            List<Radio> hhh = v.getRadiosFromSeparatorById(operateSeparator.getStringId());


            for(Radio rr : hhh){
                    ii++;
                i_to_StringId.put(Integer.toString(ii), rr.getStringId());

                ToggleButton toggleButton = new ToggleButton(context);
                toggleButton.setText(rr.getName());
                toggleButton.setId(ii);
                toggleButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        i_to_TrueFalse.put(Integer.toString(buttonView.getId()), (isChecked ? true : false));
                        String msg = "Toggle Button ["+ buttonView.getId() +"] is " + (isChecked ? "ON" : "OFF");
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                });

                // Add ToggleButton to LinearLayout
                if (layout != null) {
                    layout.addView(toggleButton);
                }


            }

            Button btn1 = new Button(context);
            btn1.setText("Button_text");

            layout.addView(btn1);


            int finalIi = ii;
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int gg = i_to_TrueFalse.size();
                    String msg="";
                    for(int i = 0; i >= gg; i++)
                    {
                        boolean mimi = i_to_TrueFalse.getAsBoolean(Integer.toString(i));
                        msg = msg +"Toggle Button ["+ Integer.toString(i) +"] is " + mimi+ "\n";




                        for (Radio g :hhh){

                            if(g.getStringId().equals(i_to_StringId.getAsString(Integer.toString(i)))){

                                g.setChecked(mimi);
                                rrr.updateRadio(g.getId(), g.getStringId(), g.getName(), g.getSeparator_id(),g.isChecked());

                            }
                        }
                        //System.out.println(msg);
                    }


                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();


                    Controller kei = new Controller(context);
                    kei.openAll();


                    Gruppe newGruppe;
                    kei.buildGruppe(gey,operateSeparator);


                    Intent intent = new Intent(v.getContext(), GruppeTabsActivity.class);
                    intent.putExtra("stringId", dataSet.get(getAdapterPosition()).getStringId());
                    v.getContext().startActivity(intent);
                    Activity activity = (Activity) v.getContext();
                    activity.finish();

                }
            });




        }




    }






}