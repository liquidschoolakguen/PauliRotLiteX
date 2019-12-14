package akguen.liquidschool.paulirotlite.activities.debug_activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import akguen.liquidschool.mylib2.db.DataSource_Gueltigkeitsbereich;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Gueltigkeitsbereich;
import akguen.liquidschool.mylib2.model.SP_Fach;


public class Speichern_Gueltigkeitsbereich extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Gueltigkeitsbereich.class.getSimpleName();

    private DataSource_Gueltigkeitsbereich dataSource;




    private ListView mGueltigkeitsbereichsListView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gueltigkeitsbereich_sq);




        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Gueltigkeitsbereich(this);






        initializeGueltigkeitsbereichsListView();

        activateAddButton();
        activateFillButton();
        activateFindButton();
        initializeContextualActionBar();



    }



    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }

    private void initializeGueltigkeitsbereichsListView() {
        List<Gueltigkeitsbereich> emptyListForInitialization = new ArrayList<>();

        mGueltigkeitsbereichsListView = (ListView) findViewById(R.id.listview_gueltigkeitsbereichs);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Gueltigkeitsbereich> gueltigkeitsbereichArrayAdapter = new ArrayAdapter<Gueltigkeitsbereich>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Gueltigkeitsbereich memo = (Gueltigkeitsbereich) mGueltigkeitsbereichsListView.getItemAtPosition(position);

                // Hier prüfen, ob Eintrag abgehakt ist. Falls ja, Text durchstreichen
//                if (memo.isChecked()) {
//                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                    textView.setTextColor(Color.rgb(175,175,175));
//                }
//                else {
//                    textView.setPaintFlags( textView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
//                    textView.setTextColor(Color.DKGRAY);
//                }

                return view;
            }
        };

        mGueltigkeitsbereichsListView.setAdapter(gueltigkeitsbereichArrayAdapter);

        mGueltigkeitsbereichsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Gueltigkeitsbereich gueltigkeitsbereich = (Gueltigkeitsbereich) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Gueltigkeitsbereich updatedGueltigkeitsbereich = dataSource.updateGueltigkeitsbereich(gueltigkeitsbereich.getId(), gueltigkeitsbereich.getVorname(), gueltigkeitsbereich.getItemType(), gueltigkeitsbereich.getPasswort(), gueltigkeitsbereich.getKuerzel(), gueltigkeitsbereich.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedGueltigkeitsbereich.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Gueltigkeitsbereich> gueltigkeitsbereichList = dataSource.getAllGueltigkeitsbereichs();

        ArrayAdapter<Gueltigkeitsbereich> adapter = (ArrayAdapter<Gueltigkeitsbereich>) mGueltigkeitsbereichsListView.getAdapter();

        adapter.clear();
        adapter.addAll(gueltigkeitsbereichList);
        adapter.notifyDataSetChanged();
    }

    DatePickerDialog picker1;
    DatePickerDialog picker2;
    DatePickerDialog picker3;
    DatePickerDialog picker4;
    private void activateAddButton() {


        Button buttonAddGueltigkeitsbereich = (Button) findViewById(R.id.button_add_gueltigkeitsbereich);
        final EditText editText1 = (EditText) findViewById(R.id.editText_gueltigkeitsbereich_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_gueltigkeitsbereich_22);
        final EditText editText3 = (EditText) findViewById(R.id.editText_gueltigkeitsbereich_33);
        final EditText editText4 = (EditText) findViewById(R.id.editText_gueltigkeitsbereich_44);
        final EditText editText5 = (EditText) findViewById(R.id.editText_gueltigkeitsbereich_55);


        editText4.setInputType(InputType.TYPE_NULL);
        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker1 = new DatePickerDialog(Speichern_Gueltigkeitsbereich.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editText4.setText(dayOfMonth + "." + (month + 1) + "." + year);
                            }


                        }, year, month, day);
                picker1.show();
            }
        });


        editText5.setInputType(InputType.TYPE_NULL);
        editText5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker2 = new DatePickerDialog(Speichern_Gueltigkeitsbereich.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editText5.setText(dayOfMonth + "." + (month + 1) + "." + year);
                            }


                        }, year, month, day);
                picker2.show();
            }
        });






        buttonAddGueltigkeitsbereich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();
                String s5 = editText5.getText().toString();


   /*             if (TextUtils.isEmpty(s2)) {
                    editText2.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if (TextUtils.isEmpty(s3)) {
                    editText3.setError(getString(R.string.editText_errorMessage));
                    return;
                }*/


                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");


                dataSource.createGueltigkeitsbereich(s1, s2, s3, asMillis(s4), asMillis(s5));

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();
            }
        });

    }

    private long asMillis(String a) {

//Specifying the pattern of input date and time
        //SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy hh:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        String dateString = a;
        System.out.println("ll : "+a);
        try{
            //formatting the dateString to convert it into a Date
            Date date = sdf.parse(dateString);
            System.out.println("Given Time in milliseconds : "+date.getTime());

            Calendar calendar = Calendar.getInstance();
            //Setting the Calendar date and time to the given date and time
            calendar.setTime(date);
            System.out.println("Given Time in milliseconds : "+calendar.getTimeInMillis());
            return date.getTime();
        }catch(ParseException e){
            e.printStackTrace();
            return 0;
        }


    }


    private String asDateString(long k){


        Date date = new Date(k);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");

       return sdf.format(date);
    }


    private void activateFillButton() {
        Button buttonFillGueltigkeitsbereich = (Button) findViewById(R.id.button_add_gueltigkeitsbereichs);


        buttonFillGueltigkeitsbereich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     Filler f = new Filler();
                String[] tempArray;


                String delimiter = "--";

                tempArray = f.alleGueltigkeitsbereichString.split(delimiter);

                for (int i = 0; i < tempArray.length; i++) {
                    //Log.i("click", tempArray[i]);

                    String s1 = null;
                    String s2 = null;
                    String s3 = null;
                    String s4 = null;
                    String s5 = null;


                    String[] tempTempArray;
                    String deDelimiter = ",";

                    tempTempArray = tempArray[i].split(deDelimiter);



                    for (int ii = 0; ii < tempTempArray.length; ii++) {

                        String newString = tempTempArray[ii].replaceAll("[\u0000-\u001f]", "");

                        if (ii == 0) {
                            Log.i("click", "s1: " + newString);
                            s1 = newString;
                        }
                        if (ii == 1) {
                            Log.i("click", "s2: " + newString);
                            s2 = newString;
                        }
                        if (ii == 2) {
                            Log.i("click", "s3: " + newString);
                            s3 = newString;
                        }
                        if (ii == 4) {
                            Log.i("click", "s4: " + newString);

                            s4 = newString;
                        }


                    }

                    Log.i("click", "----------------------------------");




                    dataSource.createGueltigkeitsbereich(s2, s1, s3, s4, s5);







                }

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();


*/

            }
        });


    }


    private void activateFindButton() {
        Button buttonFindGueltigkeitsbereich = (Button) findViewById(R.id.button_select_gueltigkeitsbereich);


        buttonFindGueltigkeitsbereich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<SP_Fach> s = dataSource.getSP_FachsFromGueltigkeitsbereichById(2);

                if (s != null && !s.isEmpty()) {


                    for (int i = 0; i < s.size(); i++) {
                        Log.i("click", "Gesuchter SP_Fach: " + s.get(i).getTeststring() + " " + s.get(i).getGueltigkeitsbereich_id());
                    }

                } else {

                    Log.i("click", "keine SP_Fach gefunden");
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView gueltigkeitsbereichsListView = (ListView) findViewById(R.id.listview_gueltigkeitsbereichs);
        gueltigkeitsbereichsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        gueltigkeitsbereichsListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            int selCount = 0;

            // In dieser Callback-Methode zählen wir die ausgewählen Listeneinträge mit
            // und fordern ein Aktualisieren der Contextual Action Bar mit invalidate() an
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selCount++;
                } else {
                    selCount--;
                }
                String cabTitle = selCount + " " + getString(R.string.cab_checked_string);
                mode.setTitle(cabTitle);
                mode.invalidate();
            }

            // In dieser Callback-Methode legen wir die CAB-Menüeinträge an
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_contextual_action_bar_sq, menu);
                return true;
            }

            // In dieser Callback-Methode reagieren wir auf den invalidate() Aufruf
            // Wir lassen das Edit-Symbol verschwinden, wenn mehr als 1 Eintrag ausgewählt ist
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuItem item = menu.findItem(R.id.cab_change);
                if (selCount == 1) {
                    item.setVisible(true);
                } else {
                    item.setVisible(false);
                }

                return true;
            }

            // In dieser Callback-Methode reagieren wir auf Action Item-Klicks
            // Je nachdem ob das Löschen- oder Ändern-Symbol angeklickt wurde
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                boolean returnValue = true;
                SparseBooleanArray touchedGueltigkeitsbereichsPositions = gueltigkeitsbereichsListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedGueltigkeitsbereichsPositions.size(); i++) {
                            boolean isChecked = touchedGueltigkeitsbereichsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedGueltigkeitsbereichsPositions.keyAt(i);
                                Gueltigkeitsbereich gueltigkeitsbereich = (Gueltigkeitsbereich) gueltigkeitsbereichsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + gueltigkeitsbereich.toString());
                                dataSource.deleteGueltigkeitsbereich(gueltigkeitsbereich);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedGueltigkeitsbereichsPositions.size(); i++) {
                            boolean isChecked = touchedGueltigkeitsbereichsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedGueltigkeitsbereichsPositions.keyAt(i);
                                Gueltigkeitsbereich gueltigkeitsbereich = (Gueltigkeitsbereich) gueltigkeitsbereichsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + gueltigkeitsbereich.toString());

                                AlertDialog editGueltigkeitsbereichDialog = createEditGueltigkeitsbereichDialog(gueltigkeitsbereich);
                                editGueltigkeitsbereichDialog.show();
                            }
                        }

                        mode.finish();
                        break;

                    default:
                        returnValue = false;
                        break;
                }
                return returnValue;
            }

            // In dieser Callback-Methode reagieren wir auf das Schließen der CAB
            // Wir setzen den Zähler auf 0 zurück
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selCount = 0;
            }
        });

    }

    private AlertDialog createEditGueltigkeitsbereichDialog(final Gueltigkeitsbereich gueltigkeitsbereich) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_gueltigkeitsbereich_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_gueltigkeitsbereich_1);
        editText1.setText(gueltigkeitsbereich.getSchuljahr());

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_gueltigkeitsbereich_2);
        editText2.setText(gueltigkeitsbereich.getTyp());

        final EditText editText3 = (EditText) dialogsView.findViewById(R.id.editText_gueltigkeitsbereich_3);
        editText3.setText(gueltigkeitsbereich.getNummer());

        final EditText editText4 = (EditText) dialogsView.findViewById(R.id.editText_gueltigkeitsbereich_4);
        editText4.setText(asDateString(gueltigkeitsbereich.getVon()));
        editText4.setInputType(InputType.TYPE_NULL);
        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker3 = new DatePickerDialog(Speichern_Gueltigkeitsbereich.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editText4.setText(dayOfMonth + "." + (month + 1) + "." + year);
                            }


                        }, year, month, day);
                picker3.show();
            }
        });





        final EditText editText5 = (EditText) dialogsView.findViewById(R.id.editText_gueltigkeitsbereich_5);
        editText5.setText(asDateString(gueltigkeitsbereich.getBis()));
        editText5.setInputType(InputType.TYPE_NULL);
        editText5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker4 = new DatePickerDialog(Speichern_Gueltigkeitsbereich.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editText5.setText(dayOfMonth + "." + (month + 1) + "." + year);
                            }


                        }, year, month, day);
                picker4.show();
            }
        });

        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();
                        String s3 = editText3.getText().toString();
                        String s4 = editText4.getText().toString();
                        String s5 = editText5.getText().toString();

/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        Gueltigkeitsbereich updatedGueltigkeitsbereich = dataSource.updateGueltigkeitsbereich(gueltigkeitsbereich.getId(), s1, s2, s3, asMillis(s4), asMillis(s5));

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + gueltigkeitsbereich.getId() + " Inhalt: " + gueltigkeitsbereich.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedGueltigkeitsbereich.getId() + " Inhalt: " + updatedGueltigkeitsbereich.toString());

                        showAllListEntries();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.dialog_button_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}