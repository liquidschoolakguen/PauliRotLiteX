package tabs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import zz_test.MyDividerItemDecorator;
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


        return dataSet.get(position).isSelected() ? 1 : 0;
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

                //viewHolderSchuelerOption.separatorStringId.setText(shownSeparator.getStringId());

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


    public class ViewHolderNormal extends RecyclerView.ViewHolder implements View.OnLongClickListener {

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

            } else {


                selectedSeparatorHolder.animationOff.start();


                return true;

            }

        }


    }


    GruppeViewMini adapter;

    public class ViewHolderSeparatorOption extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView separatorname;
        //TextView separatorStringId;


        ValueAnimator animationOn;
        ValueAnimator animationOff;

        ImageView okButton;
        ImageView saveButton;
        ImageView multi_saveButton;

        public ViewHolderSeparatorOption(View itemView) {
            super(itemView);

            separatorname = (TextView) itemView.findViewById(R.id.tv_item_separator_name);
            //separatorStringId = (TextView) itemView.findViewById(R.id.tv_item_separator_stringid);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_sep);
            okButton = (ImageView) itemView.findViewById(R.id.up1);
            saveButton = (ImageView) itemView.findViewById(R.id.save);
            multi_saveButton = (ImageView) itemView.findViewById(R.id.multi_save);


            RecyclerView recyclerView = itemView.findViewById(R.id.radio_mini);
            RecyclerView.LayoutManager kkk = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(kkk);
            recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(context, SpeedyLinearLayoutManager.VERTICAL, false));


            int anfang = 180;
            int normalEnde = 225;
            int dazu = 10 * 1;


            animationOn = ValueAnimator.ofInt(anfang, normalEnde + dazu);
            animationOn.setDuration(200);
            animationOn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {


                    DataSource_Separator v = new DataSource_Separator(context);
                    v.open();
                    List<Radio> hhh = v.getRadiosFromSeparatorById(operateSeparator.getStringId());
                    Log.d("GruppeTest2", "80 * hhh.size() " + 80 * hhh.size());
                    animationOn.setIntValues(225+ (81 * hhh.size()));

                    adapter = new GruppeViewMini(context, hhh, recyclerView);
                    recyclerView.setAdapter(adapter);

                    layout.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                    layout.requestLayout();
                }
            });
            animationOn.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {









                }
            });


            animationOff = ValueAnimator.ofInt(normalEnde + dazu, anfang);
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

                    dataSet.get(dataSet.indexOf(operateSeparator)).setSelected(false);
                    operateSeparator = null;
                    selectedSeparatorHolder = null;

                    notifyDataSetChanged();


                    List<Radio> hhh = new ArrayList<Radio>();

                    adapter = new GruppeViewMini(context, hhh, recyclerView);
                    recyclerView.setAdapter(adapter);


                }
            });

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    selectedSeparatorHolder.animationOff.start();


                }
            });


            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSource_Radio vv = new DataSource_Radio(context);
                    vv.open();


                    for (Radio g : adapter.dataSet) {

                        Log.d("GruppeTest2", "ok " + g.getName() + g.isFormular_checked());
                        vv.updateRadio(g.getId(), g.getStringId(), g.getName(), g.getSeparator_id(), g.isFormular_checked(), g.isDefault_checked());


                    }
                    //System.out.println(msg);


                    Controller kei = new Controller(context);
                    kei.openAll();


                    Gruppe newGruppe = kei.buildGruppe(gey, operateSeparator);

                    for (Radio g : adapter.dataSet) {


                        vv.updateRadio(g.getId(), g.getStringId(), g.getName(), g.getSeparator_id(), false, g.isDefault_checked());


                    }


                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("selectedGruppe", newGruppe.getStringId());
                    editor.commit();

                    Intent intent = new Intent(v.getContext(), GruppeTabsActivity.class);
                    v.getContext().startActivity(intent);
                    Activity activity = (Activity) v.getContext();
                    activity.finish();

                }
            });


            multi_saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSource_Radio vv = new DataSource_Radio(context);
                    vv.open();
                    Controller kei = new Controller(context);
                    kei.openAll();

                    int i = 0;
                    for (Radio g : adapter.dataSet) {

                        Radio willChecked = adapter.dataSet.get(adapter.dataSet.indexOf(g));
                        willChecked.setFormular_checked(true);

                        for (Radio gg : adapter.dataSet) {
                            if (!gg.equals(willChecked)) {
                                adapter.dataSet.get(adapter.dataSet.indexOf(gg)).setFormular_checked(false);

                            }

                        }


                        i++;


                        for (Radio gggg : adapter.dataSet) {

                            Log.d("GruppeTest2", "save " + gggg.getName() + gggg.isFormular_checked());
                            vv.updateRadio(gggg.getId(), gggg.getStringId(), gggg.getName(), gggg.getSeparator_id(), gggg.isFormular_checked(), gggg.isDefault_checked());


                        }
                        //System.out.println(msg);




                        Gruppe newGruppe = kei.buildGruppe(gey, operateSeparator);

                        for (Radio ggg : adapter.dataSet) {


                            vv.updateRadio(ggg.getId(), ggg.getStringId(), ggg.getName(), ggg.getSeparator_id(), false, ggg.isDefault_checked());


                        }





                    }

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("selectedGruppe", "main#1");
                    editor.commit();

                    Intent intent = new Intent(v.getContext(), GruppeTabsActivity.class);
                    v.getContext().startActivity(intent);
                    Activity activity = (Activity) v.getContext();
                    activity.finish();


                }
            });


        }


    }


}