package zz_test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.S4_WaehleVergehen;
import db.DataSource_Schueler;
import db.DataSource_Schueler_Lerngruppe;
import db.MyDbHelper;
import model.Schueler;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Schueler operateSchueler = null;
    private Schueler virtual = null;
    private ViewHolderUpdate operateViewHolderUpdate = null;
    private RecyclerView recyclerView;
    List<Schueler> dataSet;
    private Context context;
    private ItemClickListener mItemClickListener;
    private DataSource_Schueler dS_Schueler;
    private DataSource_Schueler_Lerngruppe dS_Schueler_Lerngruppe;
    private int lb_id;
    private boolean newLG = false;
    private Activity activity;

    public List<Schueler> getDataSet() {
        return dataSet;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public RecyclerViewAdapter(Context context, List<Schueler> itemList, RecyclerView recyclerView, int lb_id, boolean newLG, Activity activity) {
        this.dataSet = itemList;
        this.context = context;
        this.recyclerView = recyclerView;
        this.lb_id = lb_id;
        this.newLG = newLG;
        this.activity = activity;



        // recyclerView.setLayoutManager(kkk);
        //kkk.smoothScrollToPosition(dataSet.size());*/

        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(context, SpeedyLinearLayoutManager.VERTICAL, false));


        dS_Schueler = new DataSource_Schueler(context);
        dS_Schueler_Lerngruppe = new DataSource_Schueler_Lerngruppe(context);

        dS_Schueler.open();
        dS_Schueler_Lerngruppe.open();

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        View vv = (View) recyclerView.getParent();
    }


    @Override
    public int getItemViewType(int position) {
        // 1 : normal
        // 2 : update
        // 3 : plus
        // 4 : plusDisable
        // 5 : normalDisable
        Schueler selectedSchueler = dataSet.get(position);

System.out.println("------------------ "+selectedSchueler.getNachname());
        return Integer.parseInt(selectedSchueler.getNachname());
    }


    LinearLayout mumu;
    Button momo;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_row, parent,false);
        View layoutView;
        View vv = (View) parent.getParent();
        mumu = (LinearLayout) vv.findViewById(R.id.lin_respekt);
        momo = (Button) vv.findViewById(R.id.bnt_nrespekt);

        switch (viewType) {

            case 1:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.waehle_schueler_row, parent, false);
                return new ViewHolderNormal(layoutView);

            case 2:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_row, parent, false);
                return new ViewHolderUpdate(layoutView);
            case 3:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zz_plus_row, parent, false);
                return new ViewHolderPlus(layoutView);

            case 4:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zz_plus_row_disable, parent, false);
                return new ViewHolderPlusDisable(layoutView);

            case 5:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.waehle_schueler_row_disable, parent, false);
                return new ViewHolderNormalDisable(layoutView);

            case 6:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zz_new_lb_row, parent, false);
                return new ViewHolderLB_Normal(layoutView);
            case 7:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zz_new_lb_row_update, parent, false);
                return new ViewHolderLB_Update(layoutView);
            case 8:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zz_new_lb_row_disable, parent, false);
                return new ViewHolderLB_NormalDisable(layoutView);

            case 9:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zz_end_row, parent, false);
                return new ViewHolderEndRow(layoutView);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Schueler selectedSchueler = dataSet.get(position);
        switch (holder.getItemViewType()) {
            case 1:
                ViewHolderNormal viewHolderNormal = (ViewHolderNormal) holder;

                if (selectedSchueler.getGeburtstag().equals("0")) {

                    viewHolderNormal.strafpunkt.setVisibility(View.INVISIBLE);
                } else {

                    viewHolderNormal.strafpunkt.setVisibility(View.VISIBLE);
                }
                if (newLG && selectedSchueler.getVorname().equals("")) {
                    viewHolderNormal.schuelername.setText("MusterschülerIn " + (position));

                } else {
                    viewHolderNormal.schuelername.setText(selectedSchueler.getVorname());

                }

                if (selectedSchueler.getGeschlecht().equals("W")) {

                    viewHolderNormal.layout.setBackgroundColor(Color.parseColor("#0FFF1E39"));
                } else {

                    viewHolderNormal.layout.setBackgroundColor(Color.parseColor("#00000000"));
                }
                viewHolderNormal.strafpunkt.setText(selectedSchueler.getGeburtstag());
                break;

            case 2:
                ViewHolderUpdate viewHolderUpdate = (ViewHolderUpdate) holder;

                viewHolderUpdate.minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(v.getContext(), "+: "+dataSet.get(holder.getAdapterPosition()).getGeburtstag(), Toast.LENGTH_SHORT).show();
                        String f = viewHolderUpdate.strafpunktUpdate.getText().toString();
                        if (f == null || f.equals("")) {

                            viewHolderUpdate.strafpunktUpdate.setText("0");
                        }
                        int strafpunkt = Integer.parseInt(viewHolderUpdate.strafpunktUpdate.getText().toString());
                        if (strafpunkt < 9) {
                            viewHolderUpdate.strafpunktUpdate.setText(Integer.toString(strafpunkt + 1));


                          /*
                            Schueler g = dataSet.get(holder.getAdapterPosition());
                            g.setGeburtstag(Integer.toString(Integer.parseInt(g.getGeburtstag()) + 1));
                            dataSet.set(holder.getAdapterPosition(), g);
                            //  Toast.makeText(v.getContext(), "+: "+dataSet.get(holder.getAdapterPosition()).getGeburtstag(), Toast.LENGTH_SHORT).show();
                            notifyItemChanged(holder.getAdapterPosition());

                            //dS_Schueler.updateSchueler(g.getId(),g.getVorname(),g.getNachname(),g.getRufname(),g.getGeschlecht(),g.getStatus(),g.getGeburtstag(),g.getGeburtsort());
*/
                            if (virtual == null) {

                                virtual = new Schueler();
                                virtual.setId(selectedSchueler.getId());
                            }
                            virtual.setGeburtstag(viewHolderUpdate.strafpunktUpdate.getText().toString());
                        }


                    }
                });


                viewHolderUpdate.pluss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(v.getContext(), "+: "+dataSet.get(holder.getAdapterPosition()).getGeburtstag(), Toast.LENGTH_SHORT).show();
                        String f = viewHolderUpdate.strafpunktUpdate.getText().toString();
                        if (f == null || f.equals("")) {

                            viewHolderUpdate.strafpunktUpdate.setText("0");
                        }

                        int strafpunkt = Integer.parseInt(viewHolderUpdate.strafpunktUpdate.getText().toString());
                        if (strafpunkt > 0) {
                            viewHolderUpdate.strafpunktUpdate.setText(Integer.toString(strafpunkt - 1));


                          /*  Schueler g = dataSet.get(holder.getAdapterPosition());
                            g.setGeburtstag(Integer.toString(Integer.parseInt(g.getGeburtstag()) - 1));
                            dataSet.set(holder.getAdapterPosition(), g);
                            //Toast.makeText(v.getContext(), "+: "+dataSet.get(holder.getAdapterPosition()).getGeburtstag(), Toast.LENGTH_SHORT).show();
                            notifyItemChanged(holder.getAdapterPosition());
                            // dS_Schueler.updateSchueler(g.getId(),g.getVorname(),g.getNachname(),g.getRufname(),g.getGeschlecht(),g.getStatus(),g.getGeburtstag(),g.getGeburtsort());
*/
                            if (virtual == null) {

                                virtual = new Schueler();
                                virtual.setId(selectedSchueler.getId());
                            }

                            virtual.setGeburtstag(viewHolderUpdate.strafpunktUpdate.getText().toString());
                        }


                    }
                });
                viewHolderUpdate.geschlecht.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                       /* Schueler g = dataSet.get(holder.getAdapterPosition());
                        if (g.getGeschlecht() != null) {
                            if (g.getGeschlecht().equals("M")) {
                                g.setGeschlecht("W");

                            } else {

                                g.setGeschlecht("M");
                            }

                        }else{
                            g.setGeschlecht("M");
                        }*/

                        if (virtual == null) {

                            virtual = new Schueler();
                            virtual.setId(selectedSchueler.getId());
                        }

                        String geschlechti = null;
                        if (checkedId == viewHolderUpdate.männlich.getId()) {

                            geschlechti = "M";
                            viewHolderUpdate.layout.setBackgroundColor(Color.parseColor("#00000000"));
                        } else {

                            geschlechti = "W";
                            viewHolderUpdate.layout.setBackgroundColor(Color.parseColor("#0FFF1E39"));
                        }
                        virtual.setGeschlecht(geschlechti);


                    }
                });

                viewHolderUpdate.title.requestFocus();


                viewHolderUpdate.title.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    public void afterTextChanged(Editable s) {


                       /* /Schueler g = dataSet.get(holder.getAdapterPosition());
                    g.setVorname(s.toString());
                        System.out.println("          -           " + g.getVorname());*/
                        if (virtual == null) {

                            virtual = new Schueler();
                            virtual.setId(selectedSchueler.getId());
                        }

                        virtual.setVorname(s.toString());

                    }
                });


                viewHolderUpdate.title.setText(selectedSchueler.getVorname());
                viewHolderUpdate.title.setSelection(viewHolderUpdate.title.getText().length());

                if ((selectedSchueler.getGeburtstag() != null && !selectedSchueler.getGeburtstag().equals("")) && !newLG) {
                    viewHolderUpdate.strafpunktUpdate.setVisibility(View.VISIBLE);
                    viewHolderUpdate.minus.setVisibility(View.VISIBLE);
                    viewHolderUpdate.pluss.setVisibility(View.VISIBLE);
                    viewHolderUpdate.strafpunktUpdate.setText(selectedSchueler.getGeburtstag());
                } else {

                    viewHolderUpdate.strafpunktUpdate.setVisibility(View.GONE);
                    viewHolderUpdate.minus.setVisibility(View.GONE);
                    viewHolderUpdate.pluss.setVisibility(View.GONE);
                }

                String ggg = selectedSchueler.getGeschlecht();
                int _id;

                if (ggg != null) {
                    if (ggg.equals("M")) {

                        _id = viewHolderUpdate.männlich.getId();


                        viewHolderUpdate.layout.setBackgroundColor(Color.parseColor("#00000000"));


                    } else {

                        _id = viewHolderUpdate.weiblich.getId();

                        viewHolderUpdate.layout.setBackgroundColor(Color.parseColor("#0FFF1E39"));
                    }
                    viewHolderUpdate.geschlecht.check(_id);
                } else {

                    viewHolderUpdate.geschlecht.check(viewHolderUpdate.männlich.getId());
                    viewHolderUpdate.layout.setBackgroundColor(Color.parseColor("#00000000"));
                }
                break;

            case 3:
                ViewHolderPlus viewHolderPlus = (ViewHolderPlus) holder;
                viewHolderPlus.plusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(v.getContext(), "+: "+dataSet.get(holder.getAdapterPosition()).getGeburtstag(), Toast.LENGTH_SHORT).show();


                        if (operateSchueler == null) {

                            // Toast.makeText(v.getContext(), "LONG CLICK Position = " + getPosition(), Toast.LENGTH_SHORT).show();
                            Schueler g = new Schueler();

                            g.setNachname("2");
                            dataSet.add(dataSet.size() - 1, g);
                            //Toast.makeText(v.getContext(), "+: "+dataSet.get(holder.getAdapterPosition()).getGeburtstag(), Toast.LENGTH_SHORT).show();
                            // notifyDataSetChanged();
                            operateSchueler = g;


                            gotoNightMode(selectedSchueler);

                        }

                        recyclerView.smoothScrollToPosition(dataSet.size());
                    }


                });
                break;

            case 4:
                ViewHolderPlusDisable viewHolderPlusDisable = (ViewHolderPlusDisable) holder;
                break;

            case 5:
                ViewHolderNormalDisable viewHolderNormalDisable = (ViewHolderNormalDisable) holder;

                if (selectedSchueler.getGeburtstag().equals("0")) {

                    viewHolderNormalDisable.strafpunktDis.setVisibility(View.GONE);
                } else {

                    viewHolderNormalDisable.strafpunktDis.setVisibility(View.VISIBLE);
                }

                if (newLG && selectedSchueler.getVorname().equals("")) {
                    viewHolderNormalDisable.schuelernameDis.setText("MusterschülerIn " + (position));

                } else {
                    viewHolderNormalDisable.schuelernameDis.setText(selectedSchueler.getVorname());

                }
                if (selectedSchueler.getGeschlecht().equals("W")) {

                    viewHolderNormalDisable.layout.setBackgroundColor(Color.parseColor("#0FFF1E39"));
                } else {

                    viewHolderNormalDisable.layout.setBackgroundColor(Color.parseColor("#00000000"));
                }
                viewHolderNormalDisable.strafpunktDis.setText(selectedSchueler.getGeburtstag());
                break;

            case 6: //lerngruppenNamen Item
                ViewHolderLB_Normal viewHolderLB_Normal = (ViewHolderLB_Normal) holder;
                viewHolderLB_Normal.lbname.setText(selectedSchueler.getVorname());
                viewHolderLB_Normal.lbname.setFocusableInTouchMode(true);
                viewHolderLB_Normal.lbname.requestFocus();

                break;

            case 7: //lerngruppenNamen UPDATE Item

                ViewHolderLB_Update viewHolderLB_Update = (ViewHolderLB_Update) holder;
                viewHolderLB_Update.lbname.requestFocus();

                viewHolderLB_Update.lbname.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    public void afterTextChanged(Editable s) {


                       /* /Schueler g = dataSet.get(holder.getAdapterPosition());
                    g.setVorname(s.toString());
                        System.out.println("          -           " + g.getVorname());*/
                        if (virtual == null) {

                            virtual = new Schueler();
                            virtual.setId(selectedSchueler.getId());
                        }

                        virtual.setVorname(s.toString());




                    }
                });


                viewHolderLB_Update.lbname.setText(selectedSchueler.getVorname());
                break;


            case 8: //lerngruppenNamen disable Item
                ViewHolderLB_NormalDisable viewHolderLB_NormalDisable = (ViewHolderLB_NormalDisable) holder;
                viewHolderLB_NormalDisable.lgnameDis.setText(selectedSchueler.getVorname());
                break;

            case 9: //lerngruppenNamen disable Item
                ViewHolderEndRow viewHolderEndRow = (ViewHolderEndRow) holder;

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


    public class ViewHolderNormal extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        LinearLayout layout;
        TextView schuelername;
        TextView strafpunkt;

        public ViewHolderNormal(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            schuelername = (TextView) itemView.findViewById(R.id.tv_item_schuelername);
            strafpunkt = (TextView) itemView.findViewById(R.id.tv_item_strafpunkt);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_1);
        }

        @Override
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

        @Override
        public boolean onLongClick(View v) {

            if (operateSchueler == null) {


                gotoNightMode(dataSet.get(getPosition()));
                return true;

            }
            return false;
        }
    }


    public class ViewHolderUpdate extends RecyclerView.ViewHolder {

        LinearLayout layout;
        RadioGroup geschlecht;
        EditText title;


        RadioButton männlich;
        RadioButton weiblich;
        TextView strafpunktUpdate;
        Button pluss;
        Button minus;


        TextView plusButton;

        public ViewHolderUpdate(View itemView) {
            super(itemView);

            layout = (LinearLayout) itemView.findViewById(R.id.layout_2);

            //itemView.setOnClickListener(this);
            geschlecht = (RadioGroup) itemView.findViewById(R.id.toggle1);
            männlich = (RadioButton) itemView.findViewById(R.id.b_m_1);
            weiblich = (RadioButton) itemView.findViewById(R.id.b_w_1);

            title = (EditText) itemView.findViewById(R.id.editText_lg_updaten_1);


            strafpunktUpdate = (TextView) itemView.findViewById(R.id.tv_strafpunkt_1);

            pluss = (Button) itemView.findViewById(R.id.btn_strafpunkt_plus_1);
            minus = (Button) itemView.findViewById(R.id.btn_strafpunkt_minus_1);


        }


    }


    public class ViewHolderPlus extends RecyclerView.ViewHolder {

        TextView plusButton;


        public ViewHolderPlus(View itemView) {
            super(itemView);

            plusButton = (TextView) itemView.findViewById(R.id.plus_button);

        }


    }

    public class ViewHolderPlusDisable extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView plusButton;


        public ViewHolderPlusDisable(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            plusButton = (TextView) itemView.findViewById(R.id.plus_button_disable);

        }

        @Override
        public void onClick(View view) {

            gotoDayMode(view);

        }


    }


    public class ViewHolderNormalDisable extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout layout;
        TextView schuelernameDis;
        TextView strafpunktDis;

        public ViewHolderNormalDisable(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            schuelernameDis = (TextView) itemView.findViewById(R.id.tv_item_schuelername);
            strafpunktDis = (TextView) itemView.findViewById(R.id.tv_item_strafpunkt);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_5);
        }

        @Override
        public void onClick(View view) {
            // Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();

            gotoDayMode(view);


        }

    }


    public class ViewHolderLB_Normal extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView lbname;


        public ViewHolderLB_Normal(View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(this);
            lbname = (TextView) itemView.findViewById(R.id.tv_item_lgname);

        }


        @Override
        public boolean onLongClick(View v) {

            if (operateSchueler == null) {

                gotoNightMode(dataSet.get(getPosition()));


                return true;

            }
            return false;
        }
    }


    public class ViewHolderLB_Update extends RecyclerView.ViewHolder {

        TextView lbname;

        public ViewHolderLB_Update(View itemView) {
            super(itemView);


            lbname = (EditText) itemView.findViewById(R.id.editText_lergruppenname_updaten);


        }


    }


    public class ViewHolderLB_NormalDisable extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lgnameDis;


        public ViewHolderLB_NormalDisable(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            lgnameDis = (TextView) itemView.findViewById(R.id.tv_item_lgname_disable);

        }

        @Override
        public void onClick(View view) {
            // Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();

            gotoDayMode(view);


        }

    }


    public class ViewHolderEndRow extends RecyclerView.ViewHolder {

        TextView lgnameDis;


        public ViewHolderEndRow(View itemView) {
            super(itemView);



        }



    }






    private void gotoNightMode(Schueler selected) {

        mumu.setVisibility(View.GONE);
        momo.setVisibility(View.GONE);


        for (Schueler gg : dataSet) {


            if (gg.equals(selected)) {
                if (gg.getNachname().equals("1")) {
                    gg.setNachname("2");
                    //dataSet.set(dataSet.indexOf(gg), gg);

                    if(gg.getId()<1){
                        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        //Find the currently focused view, so we can grab the correct window token from it.
                        View view = activity.getCurrentFocus();





                        //If no view currently has focus, create a new one, just so we can grab a window token from it
                        if (view == null) {
                            view = new View(activity);
                            view.requestFocus();
                        }
                        //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        imm.showSoftInput(view,0);


                    }
                    operateSchueler = gg;
                } else if (gg.getNachname().equals("6")) {

                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    //Find the currently focused view, so we can grab the correct window token from it.
                    View view = activity.getCurrentFocus();
                    //System.out.println("---------------OK----------------");//If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (view == null) {
                        //System.out.println("---------------FE----------------");
                        view = new View(activity);
                        view.setFocusable(true);
                        view.requestFocus();

                    }
                    //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    imm.showSoftInput(view,0);
                    gg.setNachname("7");

                    operateSchueler = gg;
                } else if (gg.getNachname().equals("3")) {

                   /* Schueler g = new Schueler();
                    g.setNachname("2");
                    g.setGeburtstag("0");
                    dataSet.add(dataSet.size() - 1, g);*/

                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    //Find the currently focused view, so we can grab the correct window token from it.
                    View view = activity.getCurrentFocus();
                    //If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (view == null) {
                        view = new View(activity);
                        view.setFocusable(true);
                        view.requestFocus();
                    }
                    //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    imm.showSoftInput(view,0);
                    imm.showSoftInput(view,0);
                    imm.showSoftInput(view,0);
                    imm.showSoftInput(view,0);
                    imm.showSoftInput(view,0);
                   //operateSchueler = g;


                    gg.setNachname("4");

                } else {

                    //fehler
                }

            } else {
                if (gg.getNachname().equals("1")) {
                    gg.setNachname("5");
                } else if (gg.getNachname().equals("6")) {
                    gg.setNachname("8");
                } else if (gg.getNachname().equals("3")) {


                    gg.setNachname("4");

                } else {

                    //fehler
                }


            }


        }

        notifyDataSetChanged();

    }


    private void gotoDayMode(View view) {
        mumu.setVisibility(View.VISIBLE);
        momo.setVisibility(View.VISIBLE);


        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        if (operateSchueler == null) {

            System.out.println("---------------FEHLER----------------");

        } else {
            Schueler g = operateSchueler;

            for (Schueler k : dataSet) {
                if (k.getNachname().equals("4")) {
                    k.setNachname("3");

                } else if (k.getNachname().equals("8")) {
                    k.setNachname("6");

                } else if (k.getNachname().equals("7")) {
                    k.setNachname("6");

                } else if (k.getNachname().equals("9")) {


                }else {
                    k.setNachname("1");

                }

            }
            //dataSet.get(dataSet.size() - 1).setNachname("3");
            //notifyDataSetChanged();

        }


        if (operateSchueler.getGeburtstag() == null) {
            operateSchueler.setGeburtstag("0");
        }
        if (operateSchueler.getVorname() == null) {
            operateSchueler.setVorname("");
        }
        if (operateSchueler.getGeschlecht() == null) {
            operateSchueler.setGeschlecht("M");
        }


        if (operateSchueler.getId() > 0) {
            if (operateSchueler.getVorname().trim().length() == 0) {

                dS_Schueler.deleteSchueler(operateSchueler);
                dataSet.remove(operateSchueler);

                // Toast.makeText(view.getContext(), +operateSchueler.getId() + "wech JUPP" + operateSchueler.getId() + operateSchueler.getVorname() + " (" + operateSchueler.getGeschlecht() + ") " + operateSchueler.getGeburtstag(), Toast.LENGTH_LONG).show();


                //delete
            } else {


                if ((virtual.getVorname().equals(operateSchueler.getVorname()) && virtual.getGeschlecht() == null && virtual.getGeburtstag() == null)) {
                    // Toast.makeText(view.getContext(), +operateSchueler.getId() + ")) Es wurde nichts verändert verändert " + operateSchueler.getVorname() + " (" + operateSchueler.getGeschlecht() + ") " + operateSchueler.getGeburtstag(), Toast.LENGTH_LONG).show();

                } else {

                    if (virtual.getVorname().trim().length() == 0) {
                        dS_Schueler.deleteSchueler(operateSchueler);
                        dataSet.remove(operateSchueler);

                        // Toast.makeText(view.getContext(), +operateSchueler.getId() + "wech " + operateSchueler.getId() + operateSchueler.getVorname() + " (" + operateSchueler.getGeschlecht() + ") " + operateSchueler.getGeburtstag(), Toast.LENGTH_LONG).show();


                    } else {


                        if (operateSchueler.getGeburtstag() == null || operateSchueler.getGeburtstag().equals("")) {
                            operateSchueler.setGeburtstag("0");
                        }

                        operateSchueler.setVorname(virtual.getVorname());
                        if (virtual.getGeburtstag() != null && !virtual.getGeburtstag().equals("")) {
                            operateSchueler.setGeburtstag(virtual.getGeburtstag());
                        }

                        if (virtual.getGeschlecht() != null && !virtual.getGeschlecht().equals("")) {
                            operateSchueler.setGeschlecht(virtual.getGeschlecht());
                        }


                        virtual = null;
                        //Toast.makeText(view.getContext(), operateSchueler.getId() + "))Es wurde was verändert " + operateSchueler.getVorname() + " (" + operateSchueler.getGeschlecht() + ") " + operateSchueler.getGeburtstag(), Toast.LENGTH_LONG).show();

                        dS_Schueler.updateSchueler(operateSchueler.getId(), operateSchueler.getVorname(), operateSchueler.getNachname(), operateSchueler.getRufname(), operateSchueler.getGeschlecht(), operateSchueler.getStatus(), operateSchueler.getGeburtstag(), operateSchueler.getGeburtsort());
                    }
                    //update
                }


            }

        } else {
            if (virtual.getVorname().trim().length() == 0) {
                if (!newLG) {
                    dataSet.remove(operateSchueler);
                }else{

                    operateSchueler.setVorname(virtual.getVorname());
                }
                //nix
            } else {

                operateSchueler.setVorname(virtual.getVorname());
                if (virtual.getGeburtstag() != null && !virtual.getGeburtstag().equals("")) {
                    operateSchueler.setGeburtstag(virtual.getGeburtstag());
                }

                if (virtual.getGeschlecht() != null && !virtual.getGeschlecht().equals("")) {
                    operateSchueler.setGeschlecht(virtual.getGeschlecht());
                }


                virtual = null;


                //Toast.makeText(view.getContext(), operateSchueler.getId() + "))Es wurde was gespeichert " + operateSchueler.getVorname() + " (" + operateSchueler.getGeschlecht() + ") " + operateSchueler.getGeburtstag(), Toast.LENGTH_LONG).show();


                if (dS_Schueler.getSchuelerByVorname(operateSchueler.getVorname()) == null) {

                    if (!newLG) {
                        //Toast.makeText(view.getContext(), operateSchueler.getId() + "))Der Schüler mit diesem Namen existiert NICHT und wird gespeichert/verknüpft" + operateSchueler.getVorname() + " (" + operateSchueler.getGeschlecht() + ") " + operateSchueler.getGeburtstag(), Toast.LENGTH_LONG).show();
                        dS_Schueler.createSchueler(operateSchueler.getVorname(), operateSchueler.getNachname(), operateSchueler.getRufname(), operateSchueler.getGeschlecht(), operateSchueler.getStatus(), operateSchueler.getGeburtstag(), operateSchueler.getGeburtsort());

                        int z = dataSet.indexOf(operateSchueler);

                        Schueler created = dS_Schueler.getSchuelerByVorname(operateSchueler.getVorname());

                        dataSet.set(z,created);
                    }

                } else {
                    if (!newLG) {
                        Toast.makeText(view.getContext(), "Der Schüler " + operateSchueler.getVorname() +" ist bereits in der Gruppe.", Toast.LENGTH_LONG).show();
                        dataSet.remove(operateSchueler);
                    }
                }


                if (!newLG) {
                    Schueler schue = dS_Schueler.getSchuelerByVorname(operateSchueler.getVorname());


                    ContentValues values = new ContentValues();
                    values.put(MyDbHelper.SL_SCHUELER_ID, schue.getId());
                    values.put(MyDbHelper.SL_LERNGRUPPE_ID, lb_id);


                    dS_Schueler_Lerngruppe.createSchueler_Lerngruppe(values);

                }
                //create
            }

        }


        notifyDataSetChanged();
        operateSchueler = null;

    }


}