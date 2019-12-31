package zz_test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import akguen.liquidschool.coredata.db.DataSource_Schueler;
import akguen.liquidschool.coredata.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.coredata.model.Gruppe2;
import akguen.liquidschool.coredata.model.Schueler;
import akguen.liquidschool.paulirotlite.R;

public class Gruppe2ViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Gruppe2 operateGruppe2 = null;

    private RecyclerView recyclerView;
    List<Gruppe2> dataSet;
    private Context context;
    private ItemClickListener mItemClickListener;
    private DataSource_Schueler dS_Schueler;
    private DataSource_Schueler_Lerngruppe dS_Schueler_Lerngruppe;


    private Activity activity;

    public List<Gruppe2> getDataSet() {
        return dataSet;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public Gruppe2ViewAdapter(Context context, List<Gruppe2> itemList, RecyclerView recyclerView) {
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
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.waehle_gruppe2_row, parent, false);
                return new ViewHolderNormal(layoutView);


            case 1:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gruppe2_options_row, parent, false);
                return new ViewHolderGruppe2Option(layoutView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Gruppe2 shownGruppe2 = dataSet.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderNormal viewHolderNormal = (ViewHolderNormal) holder;


                viewHolderNormal.gruppe2name.setText(shownGruppe2.getName());
                break;




            case 1: //lerngruppenNamen disable Item
                ViewHolderGruppe2Option viewHolderSchuelerOption = (ViewHolderGruppe2Option) holder;


                viewHolderSchuelerOption.gruppe2name.setText(shownGruppe2.getName());



                selectedGruppe2Holder = viewHolderSchuelerOption;
                selectedGruppe2Holder.animationOn.start();


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

    public ViewHolderGruppe2Option selectedGruppe2Holder = null;



    public class ViewHolderNormal extends RecyclerView.ViewHolder implements  View.OnLongClickListener {

        TextView gruppe2name;




        public ViewHolderNormal(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            gruppe2name = (TextView) itemView.findViewById(R.id.tv_item_gruppe2name);




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

        @Override
        public boolean onLongClick(View v) {

            if (operateGruppe2 == null) {




                dataSet.get(getLayoutPosition()).setSelected(true);
                operateGruppe2 = dataSet.get(getLayoutPosition());

                notifyDataSetChanged();

                return true;

            }else{


                dataSet.get(dataSet.indexOf(operateGruppe2)).setSelected(false);
                operateGruppe2 = null;

                selectedGruppe2Holder.animationOff.start();
                selectedGruppe2Holder = null;

                notifyDataSetChanged();


                return true;

            }

        }
    }




    public class ViewHolderGruppe2Option extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        TextView gruppe2name;

        ValueAnimator animationOn;
        ValueAnimator animationOff;

        ImageView okButton;


        public ViewHolderGruppe2Option(View itemView) {
            super(itemView);

            gruppe2name = (TextView) itemView.findViewById(R.id.tv_item_gruppe2name);

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

                    dataSet.get(getLayoutPosition()).setSelected(false);
                    operateGruppe2 = null;

                    animationOff.start();
                    selectedGruppe2Holder = null;

                    notifyDataSetChanged();


                }
            });


            // opener();

        }





    }






}