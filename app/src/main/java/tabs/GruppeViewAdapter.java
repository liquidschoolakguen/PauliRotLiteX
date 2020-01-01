package tabs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.paulirotlite.R;
import zz_test.SpeedyLinearLayoutManager;

public class GruppeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Gruppe operateGruppe = null;

    private RecyclerView recyclerView;
    List<Gruppe> dataSet;
    private Context context;
    private ItemClickListener mItemClickListener;



    private Activity activity;

    public List<Gruppe> getDataSet() {
        return dataSet;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public GruppeViewAdapter(Context context, List<Gruppe> itemList, RecyclerView recyclerView) {
        this.dataSet = itemList;
        this.context = context;
        this.recyclerView = recyclerView;


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
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gruppe_gruppe_row, parent, false);
                return new ViewHolderNormal(layoutView);


            case 1:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gruppe_gruppe_row_options, parent, false);
                return new ViewHolderGruppeOption(layoutView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Gruppe shownGruppe = dataSet.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderNormal viewHolderNormal = (ViewHolderNormal) holder;


                viewHolderNormal.gruppename.setText(shownGruppe.getName());
                break;




            case 1: //lerngruppenNamen disable Item
                ViewHolderGruppeOption viewHolderSchuelerOption = (ViewHolderGruppeOption) holder;


                viewHolderSchuelerOption.gruppename.setText(shownGruppe.getName());



                selectedGruppeHolder = viewHolderSchuelerOption;
                selectedGruppeHolder.animationOn.start();


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

    public ViewHolderGruppeOption selectedGruppeHolder = null;



    public class ViewHolderNormal extends RecyclerView.ViewHolder implements  View.OnLongClickListener, View.OnClickListener {

        TextView gruppename;


        public ViewHolderNormal(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            gruppename = (TextView) itemView.findViewById(R.id.tv_item_gruppename);

        }



        @Override
        public boolean onLongClick(View v) {

            if (operateGruppe == null) {


                dataSet.get(getLayoutPosition()).setSelected(true);
                operateGruppe = dataSet.get(getLayoutPosition());

                notifyDataSetChanged();

                return true;

            }else{

                dataSet.get(dataSet.indexOf(operateGruppe)).setSelected(false);
                operateGruppe = null;

                selectedGruppeHolder.animationOff.start();
                selectedGruppeHolder = null;

                notifyDataSetChanged();


                return true;

            }

        }

        @Override
        public void onClick(View v) {


            Intent intent = new Intent(v.getContext(), GruppeTabsActivity.class);
            intent.putExtra("stringId", dataSet.get(getAdapterPosition()).getStringId());
            v.getContext().startActivity(intent);
            Activity activity = (Activity) v.getContext();
            activity.finish();


        }
    }




    public class ViewHolderGruppeOption extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        TextView gruppename;

        ValueAnimator animationOn;
        ValueAnimator animationOff;

        ImageView okButton;
        ImageView löschButton;


        public ViewHolderGruppeOption(View itemView) {
            super(itemView);

            gruppename = (TextView) itemView.findViewById(R.id.tv_item_gruppename);

            layout = (RelativeLayout) itemView.findViewById(R.id.layout_1);
            okButton = (ImageView) itemView.findViewById(R.id.imageView7);


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

                    dataSet.get(dataSet.indexOf(operateGruppe)).setSelected(false);
                    operateGruppe = null;

                    selectedGruppeHolder.animationOff.start();
                    selectedGruppeHolder = null;

                    notifyDataSetChanged();


                }
            });


            // opener();

        }


  /*  @Override
        public void onClick(View view) {
            // Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
            if (operateSchueler == null) {
                if (!newLG) {
                    Intent intent = new Intent(view.getContext(), S4_WaehleVergehen.class);
                    intent.putExtra("schueler_id", Integer.toString(dataSet.get(getAdapterPosition()).getId()));
                    view.getContext().startActivity(intent);
                }

            } else {

                System.out.println("---------------FEHLER2----------------");
            }

        }
*/


    }






}