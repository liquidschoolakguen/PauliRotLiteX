package akguen.liquidschool.paulirotlite;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import model.SchuelerVorfaelle;
import model.SchuelerVorfallData;


public class SchuelerVorfaelleTable extends AppCompatActivity {

    TableLayout tl;
    ProgressDialog mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schueler_vorfaelle_table);
        tl = findViewById(R.id.tlSchlVorfaelle);
        mProgressBar = new ProgressDialog(this);

        //Beispiel 1
        //erzeugeTableRowBeispiel1();

        // setup the table
        tl = (TableLayout) findViewById(R.id.tlSchlVorfaelle);
        tl.setStretchAllColumns(true);
        startLoadData();
    }

    public void startLoadData() {

        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching Invoices..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new LoadDataTask().execute(0);

    }


    public void loadData() {

        int leftRowMargin = 0;
        int topRowMargin = 0;
        int rightRowMargin = 0;
        int bottomRowMargin = 0;

        int textSize = 0, smallTextSize = 0, mediumTextSize = 0;

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

        SchuelerVorfaelle invoices = new SchuelerVorfaelle();

        SchuelerVorfallData[] data = invoices.getInvoices();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
        int rows = data.length;
        //getSupportActionBar().setTitle("Schueler (" + String.valueOf(rows) + ")");  // !!!!!

        TextView textSpacer = null;
        tl.removeAllViews();

        // -1 means heading row
        for (int i = -1; i < rows; i++) {

            SchuelerVorfallData row = null;

            if (i > -1)
                row = data[i];
            else {

                textSpacer = new TextView(this);
                textSpacer.setText("++");
            }

            // data id


            // Column 1 ->  Id
            final TextView tvId = new TextView(this);
            tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvId.setGravity(Gravity.CENTER);
            tvId.setPadding(5, 15, 0, 15);


            if (i == -1) {
                tvId.setText("Id");
                tvId.setBackgroundColor(Color.parseColor("#D32F2F"));
                tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            } else {

                tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvId.setBackgroundColor(Color.parseColor("#ffffff"));
                tvId.setTextColor(Color.parseColor("#000000"));
                tvId.setText(row.id);
            }


            // Column 2 ->  Datum

            final TextView tvDatum = new TextView(this);
            tvDatum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvDatum.setGravity(Gravity.CENTER);
            tvDatum.setPadding(5, 15, 0, 15);


            if (i == -1) {
                tvDatum.setText("Datum");
                tvDatum.setBackgroundColor(Color.parseColor("#D32F2F"));
                tvDatum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvDatum.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            } else {

                tvDatum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tvDatum.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvDatum.setBackgroundColor(Color.parseColor("#ffffff"));
                tvDatum.setTextColor(Color.parseColor("#000000"));
                tvDatum.setText(dateFormat.format(row.datum));
            }


            // Column 3 ->  Uhrzeit


            final TextView tvUhrzeit = new TextView(this);
            tvUhrzeit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tvUhrzeit.setGravity(Gravity.CENTER);
            tvUhrzeit.setPadding(5, 15, 0, 15);


            if (i == -1) {
                tvUhrzeit.setText("Uhrzeit");
                tvUhrzeit.setBackgroundColor(Color.parseColor("#D32F2F"));
                tvUhrzeit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvUhrzeit.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            } else {

                tvUhrzeit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tvUhrzeit.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvUhrzeit.setBackgroundColor(Color.parseColor("#ffffff"));
                tvUhrzeit.setTextColor(Color.parseColor("#000000"));
                tvUhrzeit.setText(row.uhrzeit);
            }

                // Column 4 -> Info


                final TextView tvInfo = new TextView(this);
                tvInfo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tvInfo.setGravity(Gravity.CENTER);
                tvInfo.setPadding(5, 15, 0, 15);


                if (i == -1) {
                    tvInfo.setText("Info");
                    tvInfo.setBackgroundColor(Color.parseColor("#D32F2F"));
                    tvInfo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tvInfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

                } else {

                    tvInfo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                    tvInfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    tvInfo.setBackgroundColor(Color.parseColor("#ffffff"));
                    tvInfo.setTextColor(Color.parseColor("#000000"));
                    tvInfo.setText(row.info);

                    // Column kollege
                }
                    final TextView tvKollege = new TextView(this);
                    tvKollege.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tvKollege.setGravity(Gravity.CENTER);
                    tvKollege.setPadding(5, 15, 0, 15);


                    if (i == -1) {
                        tvKollege.setText("Kollege");
                        tvKollege.setBackgroundColor(Color.parseColor("#D32F2F"));
                        tvKollege.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tvKollege.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

                    } else {

                        tvKollege.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                        tvKollege.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        tvKollege.setBackgroundColor(Color.parseColor("#ffffff"));
                        tvKollege.setTextColor(Color.parseColor("#000000"));
                        tvKollege.setText(row.kollegeName);

                    }
                        // Column vergehen


                        final TextView tvVergehen = new TextView(this);
                        tvVergehen.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tvVergehen.setGravity(Gravity.CENTER);
                        tvVergehen.setPadding(5, 15, 0, 15);


                        if (i == -1) {
                            tvVergehen.setText("Vergehen");
                            tvVergehen.setBackgroundColor(Color.parseColor("#D32F2F"));
                            tvVergehen.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            tvVergehen.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

                        } else {

                            tvVergehen.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                            tvVergehen.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                            tvVergehen.setBackgroundColor(Color.parseColor("#ffffff"));
                            tvVergehen.setTextColor(Color.parseColor("#000000"));
                            tvVergehen.setText(row.vergehenTitel);

                        }

                            // add table row
                            final TableRow tableRow = new TableRow(this);
                            tableRow.setId(i + 1);

                            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                            tableRow.setPadding(0, 0, 0, 0);
                            tableRow.setLayoutParams(trParams);
                            tableRow.setBackgroundColor(Color.parseColor("#E64A19"));

                            tableRow.addView(tvId);
                            tableRow.addView(tvDatum);
                            tableRow.addView(tvUhrzeit);
                            tableRow.addView(tvInfo);
                            tableRow.addView(tvKollege);
                            tableRow.addView(tvVergehen);


                            if (i > -1) {

                                tableRow.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        TableRow tr = (TableRow) v;

                                        //do whatever action is needed


                                        v.setBackgroundColor(Color.GRAY);
                                        Log.i("Row clicked: ", "   --  " + v.getId());

                                        //get the data you need
                                        //TableRow tablerow = (TableRow)v.getParent();
                                        //TextView sample = (TextView) tablerow.getChildAt(2);
                                        //String result=sample.getText().toString();



                                    }

                                });

                            }

                            tl.addView(tableRow, trParams);

                            if (i > -1) {


                                // add separator row
                                final TableRow trSep = new TableRow(this);
                                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                                trSep.setLayoutParams(trParamsSep);
                                TextView tvSep = new TextView(this);
                                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                                tvSepLay.span = 4;
                                tvSep.setLayoutParams(tvSepLay);
                                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                                tvSep.setHeight(2);
                                trSep.addView(tvSep);
                                tl.addView(trSep, trParamsSep);


                            }

                        }

                    }


                    private void erzeugeTableRowBeispiel1 () {


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

                        tl.addView(tr_head, new TableLayout.LayoutParams(
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
                            tl.addView(tr, new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.FILL_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));
                            count++;
                        }
                    }


                    //////////////////////////////////////    ////////////////////////////////////////

                    class LoadDataTask extends AsyncTask<Integer, Integer, String> {

                        @Override

                        protected String doInBackground(Integer... params) {

                            try {

                                Thread.sleep(2000);

                            } catch (InterruptedException e) {

                                e.printStackTrace();

                            }

                            return "Task Completed.";

                        }

                        @Override

                        protected void onPostExecute(String result) {

                            mProgressBar.hide();
                            loadData();

                        }

                        @Override

                        protected void onPreExecute() {
                        }

                        @Override

                        protected void onProgressUpdate(Integer... values) {
                        }

                    }



}






