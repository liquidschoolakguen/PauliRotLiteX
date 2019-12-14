package akguen.liquidschool.paulirotlite.rest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import akguen.liquidschool.paulirotlite.R;


// imports cut out for brevity

public class ReadWebpageAsyncTask2 extends Activity {
    private TextView textView;

    Button bntRead;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_web);
        textView = (TextView) findViewById(R.id.simpleTextView);

        bntRead = findViewById(R.id.bntRead);

        bntRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
/*
                DatenbankTask datenbankTask = new DatenbankTask();
                try {
                    datenbankTask.execute("speichernSchule").get();
                    //datenbankTask.execute("löschenAlleSchule").get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

//1343//"http://www.schulliste.eu/type/?bundesland=&t=offentliche-schule&start=4500"


            }
        });
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {





        @Override
        protected String doInBackground(String... urls) {






            ///26860*/


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // textView.setText(result);
            //Log.i("ReadWebpageAsyncTask: ", result);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }



    }


}