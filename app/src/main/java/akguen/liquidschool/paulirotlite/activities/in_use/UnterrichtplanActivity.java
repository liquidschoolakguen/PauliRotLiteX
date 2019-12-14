package akguen.liquidschool.paulirotlite.activities.in_use;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import akguen.liquidschool.paulirotlite.R;


import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

public class UnterrichtplanActivity extends AppCompatActivity {

    TableLayout tableLayout;
    ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unterrichtplan_table);
        tableLayout = findViewById(R.id.kkk);
        mProgressBar = new ProgressDialog(this);
        //erzeugeTableRowBeispiel1();
        // setup the table
        tableLayout = findViewById(R.id.kkk);
        tableLayout.setFocusableInTouchMode(true);
        tableLayout.setStretchAllColumns(true);
        startLoadData();
        //loadData();
        init();
        //createTableRow(1);
        //onLayout();
    }

    private View createTableRow(int position) {

        //instead
        //TableRow tr = new TableRow(MainActivity.this);
        //View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_layout, tr, false);

        //try
        View v = LayoutInflater.from(this).inflate(R.layout.activity_schueler_vorfaelle_table, tableLayout, false);

        TextView textViewName = new TextView(getBaseContext());
        textViewName.setText("row "+ position);

        v.setTag(position);
        v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Log.e("output", "is reachable at position "+ position);
            }

        });

        return v;
    }


    protected void onLayout(  ) {

        List<Integer> colWidths = new LinkedList<Integer>();

        //TableLayout header = (TableLayout) findViewById( R.id.HeaderTable );
        //TableLayout body = (TableLayout) findViewById( R.id.BodyTable );

        for ( int rownum = 0; rownum < tableLayout.getChildCount(); rownum++ )
        {
            TableRow row = (TableRow) tableLayout.getChildAt( rownum );
            for ( int cellnum = 0; cellnum < row.getChildCount(); cellnum++ )
            {
                View cell = row.getChildAt( cellnum );
                Integer cellWidth = cell.getWidth();
                if ( colWidths.size() <= cellnum )
                {
                    colWidths.add( cellWidth );
                }
                else
                {
                    Integer current = colWidths.get( cellnum );
                    if( cellWidth > current )
                    {
                        colWidths.remove( cellnum );
                        colWidths.add( cellnum, cellWidth );
                    }
                }
            }
        }

       /* for ( int rownum = 0; rownum < header.getChildCount(); rownum++ )
        {
            TableRow row = (TableRow) header.getChildAt( rownum );
            for ( int cellnum = 0; cellnum < row.getChildCount(); cellnum++ )
            {
                View cell = row.getChildAt( cellnum );
                TableRow.LayoutParams params = (TableRow.LayoutParams)cell.getLayoutParams();
                params.width = colWidths.get( cellnum );
            }
        }*/
    }

    public void startLoadData() {

        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Vorfaelle wird erstellt..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new LoadDataTask().execute(0);

    }

    public void loadbData() {

        int leftRowMargin = 0;
        int topRowMargin = 0;
        int rightRowMargin = 0;
        int bottomRowMargin = 0;

        int textSize = 0, smallTextSize = 0, mediumTextSize = 0;
        textSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

        UnterrichtPlan unterrichtPlan = new UnterrichtPlan();

        StundenPlanRow[] data = unterrichtPlan.getPlan();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
        int rows = data.length;
        //getSupportActionBar().setTitle("Schueler (" + String.valueOf(rows) + ")");  // !!!!!

        TextView textSpacer = null;
        tableLayout.removeAllViews();

        // -1 means heading row
        for (int i = -1; i < rows; i++) {

            StundenPlanRow row = null;
            if (i > -1)
                row = data[i];
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("++");
            }

            // Column 1 ->  Id
            final TextView tvId = new TextView(this);
            tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvId.setGravity(Gravity.CENTER);
            tvId.setPadding(5, 15, 0, 15);

            if (i == -1) {
                tvId.setText("    ");
                tvId.setBackgroundColor(Color.parseColor("#e6198b"));
                tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvId.setBackgroundColor(Color.parseColor("#ee97c7"));
                tvId.setTextColor(Color.parseColor("#000000"));
                tvId.setText(row.stunde);
            }

            // Column 2 ->  Datum
            final TextView tvDatum = new TextView(this);
            tvDatum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvDatum.setGravity(Gravity.CENTER);
            tvDatum.setPadding(5, 15, 0, 15);

            if (i == -1) {
                tvDatum.setText("Montag");
                tvDatum.setBackgroundColor(Color.parseColor("#e6198b"));
                tvDatum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvDatum.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {

                tvDatum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tvDatum.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvDatum.setBackgroundColor(Color.parseColor("#ee97c7"));
                tvDatum.setTextColor(Color.parseColor("#000000"));
                //tvDatum.setText(dateFormat.format(row.fach));
                tvDatum.setText(row.montag);
            }

            // Column 3 ->  Uhrzeit
            final TextView tvUhrzeit = new TextView(this);
            tvUhrzeit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvUhrzeit.setGravity(Gravity.CENTER);
            tvUhrzeit.setPadding(5, 15, 0, 15);

            if (i == -1) {
                tvUhrzeit.setText("Dienstag");
                tvUhrzeit.setBackgroundColor(Color.parseColor("#e6198b"));
                tvUhrzeit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvUhrzeit.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {

                tvUhrzeit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvUhrzeit.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvUhrzeit.setBackgroundColor(Color.parseColor("#ee97c7"));
                tvUhrzeit.setTextColor(Color.parseColor("#000000"));
                tvUhrzeit.setText(row.dienstag);
            }

            // Column 4 -> Info
            final EditText etInfo = new EditText(this);
            etInfo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            etInfo.setGravity(Gravity.CENTER);
            etInfo.setPadding(5, 15, 0, 15);

            etInfo.setSingleLine(true);
            etInfo.setImeOptions(IME_ACTION_DONE);

            if (i == -1) {
                etInfo.setText("Mittwoch");
                etInfo.setBackgroundColor(Color.parseColor("#e6198b"));
                etInfo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                etInfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            } else {
                etInfo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                etInfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                etInfo.setBackgroundColor(Color.parseColor("#ee97c7"));
                etInfo.setTextColor(Color.parseColor("#000000"));
                etInfo.setText(row.mittwoch);
            }

            // Column 5 ->  kollege
            final TextView tvKollege = new TextView(this);
            tvKollege.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvKollege.setGravity(Gravity.CENTER);
            tvKollege.setPadding(5, 15, 0, 15);
            tvKollege.setFocusable(true);

            if (i == -1) {
                tvKollege.setText("Donnerstag");
                tvKollege.setBackgroundColor(Color.parseColor("#e6198b"));
                tvKollege.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvKollege.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            } else {
                tvKollege.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tvKollege.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvKollege.setBackgroundColor(Color.parseColor("#ee97c7"));
                tvKollege.setTextColor(Color.parseColor("#000000"));
                tvKollege.setText(row.donnerstag);

            }

            // Column 6 -> vergehen
            final TextView tvVergehen = new TextView(this);
            tvVergehen.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvVergehen.setGravity(Gravity.CENTER);
            tvVergehen.setPadding(5, 15, 0, 15);

            if (i == -1) {
                tvVergehen.setText("Freitag");
                tvVergehen.setBackgroundColor(Color.parseColor("#e6198b"));
                tvVergehen.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvVergehen.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {

                tvVergehen.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tvVergehen.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvVergehen.setBackgroundColor(Color.parseColor("#ee97c7"));
                tvVergehen.setTextColor(Color.parseColor("#000000"));
                tvVergehen.setText(row.freitag);
            }

            // add table row
            final TableRow tableRow = new TableRow(this);


            //tableRow.setFocusableInTouchMode(true);
            tableRow.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tableRow.setPadding(0, 0, 0, 0);
            tableRow.setLayoutParams(trParams);
            tableRow.setBackgroundColor(Color.parseColor("#E64A19")); // orange

            tableRow.addView(tvId);
            tableRow.addView(tvDatum);
            tableRow.addView(tvUhrzeit);
            tableRow.addView(etInfo);
            tableRow.addView(tvKollege);
            tableRow.addView(tvVergehen);


            if (i > -1) {
                Log.i("Vorfaelle ", " Row clicked + iii:  ");

                tableRow.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                        //do whatever action is needed
                        v.setBackgroundColor(Color.GRAY);
                        Log.i("Vorfaelle ", " Row clicked:  " + v.getId());
                        //get the data you need
                        //TableRow tablerow = (TableRow)v.getParent();
                        //TextView sample = (TextView) tablerow.getChildAt(2);
                        //String result=sample.getText().toString();
                    }
                });
            }

            tableLayout.addView(tableRow, trParams);

            if (i > -1) {
                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                trSep.setLayoutParams(trParamsSep);

                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 5; // die Anzahl der Column
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9")); //Gray
                tvSep.setHeight(3);

                trSep.addView(tvSep);

                tableLayout.addView(trSep, trParamsSep);
            }

            etInfo.setOnEditorActionListener( (textView, pos, keyEvent)-> {

                //onEditorAction(TextView v, int actionId, KeyEvent event)
                // KEY EVENT ist wictig !! von der Soft tastatur oder nicht
                //Log.d("TAG", "activateAddButton" + keyEvent.toString());

                if (pos == EditorInfo.IME_ACTION_DONE || pos == EditorInfo.IME_ACTION_NEXT || pos == EditorInfo.IME_ACTION_GO ||
                        (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    tvKollege.requestFocus();

                    // your action here
                    Log.d( "vorfalle: ", "setOnEditorActionListener:  " + etInfo.getText());
                    // etInfo.focusSearch(View.FOCUS_RIGHT);
                    // etInfo.requestFocus();
                    // etInfo.setNextFocusRightId(tvKollege.getId());
                    Log.d("vorfalle", "focusable ? " + tvKollege.isFocused());

                    return true;
                }
                return true;
            });
        }

    }

    public void init() {
        tableLayout.removeAllViews();

        TableLayout stk = (TableLayout) findViewById(R.id.kkk);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(" Sl.No ");
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText(" Product ");
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText(" Unit Price ");
        tbrow0.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText(" Stock Remaining ");
        tbrow0.addView(tv3);


        stk.addView(tbrow0);
        for (int i = 0; i < 10; i++) {
            TableRow tbrow = new TableRow(this);



            View view;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.waehle_schueler_row, null);

            RelativeLayout rela = (RelativeLayout) view.findViewById(R.id.layout_1);
            TextView t1v = (TextView)view.findViewById(R.id.tv_item_schuelername);
            if(t1v.getParent() != null) {
                ((ViewGroup)t1v.getParent()).removeView(t1v); // <- fix
            }

            t1v.setText("hey" + i);

            t1v.setGravity(Gravity.CENTER);


            tbrow.addView(t1v);
            TextView t2v = (TextView)view.findViewById(R.id.tv_item_strafpunkt);
            t2v.setText(Integer.toString(i));

            t2v.setGravity(Gravity.CENTER);

            if(t2v.getParent() != null) {
                ((ViewGroup)t2v.getParent()).removeView(t2v); // <- fix
            }
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Rs." + i);

            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10);

            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }


    private void erzeugeTableRowBeispiel1() {


        TableRow tr_head = new TableRow(this);
        //tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        TextView label_date = new TextView(this);
        //label_date.setId(20);
        label_date.setText("DATE");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 5, 5);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_weight_kg = new TextView(this);
        //label_weight_kg.setId(21);// define id that must be unique
        label_weight_kg.setText("Wt(Kg.)"); // set the text for the header
        label_weight_kg.setTextColor(Color.WHITE); // set the color
        label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg); // add the column to the table row here

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        Integer count = 0;


        while (count < 10) {
            //String date = formatdate(cursor.getString(2));// get the first variable
            String date = "12.12.12";// get the first variable

            //Double weight_kg = roundTwoDecimals(cursor.getDouble(4));// get the second variable
            Double weight_kg = 12.1;// get the second variable

            // Create the table row
            TableRow tr = new TableRow(this);
            if (count % 2 != 0) tr.setBackgroundColor(Color.GRAY);
            tr.setId(100 + count);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            //Create two columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(this);
            labelDATE.setId(200 + count);
            labelDATE.setText(date);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);
            TextView labelWEIGHT = new TextView(this);
            labelWEIGHT.setId(200 + count);
            labelWEIGHT.setText(weight_kg.toString());
            labelWEIGHT.setTextColor(Color.WHITE);
            tr.addView(labelWEIGHT);

            // finally add this to the table row
            tableLayout.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            count++;
        }
    }


    /** Hier wird die daten geladen, als Hintergrund process*/

    class LoadDataTask extends AsyncTask<Integer, Integer, String> {

        @Override

        protected String doInBackground(Integer... params) {

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task Completed.";

        }

        @Override

        protected void onPostExecute(String result) {
            mProgressBar.hide();
            //loadData();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

    }


}


