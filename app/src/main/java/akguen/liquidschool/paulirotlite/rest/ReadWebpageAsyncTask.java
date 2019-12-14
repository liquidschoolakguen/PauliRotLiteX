package akguen.liquidschool.paulirotlite.rest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import akguen.liquidschool.paulirotlite.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


// imports cut out for brevity

public class ReadWebpageAsyncTask extends Activity {
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

                DownloadWebPageTask task = new DownloadWebPageTask();
                task.execute(new String[] { "http://www.google.com:80/" });
            }
        });
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(urls[0])
                            .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.isSuccessful()) {
                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return "Download failed";
    }

    @Override
    protected void onPostExecute(String result) {
       // textView.setText(result);
        Log.i("ReadWebpageAsyncTask: ", result);
    }
}


}