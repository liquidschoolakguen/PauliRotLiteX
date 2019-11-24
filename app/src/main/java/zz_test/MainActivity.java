package zz_test;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;

import akguen.liquidschool.paulirotlite.R;

public class MainActivity extends AppCompatActivity {
    ListView mainactivity;
    // creating arraylist of MyItem type to set to adapter
    ArrayList<MyItem> myitems=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zz_test_activity_main);
        mainactivity=(ListView)findViewById(R.id.mainactivitylistview);
//Adding data i.e images and title to be set to adapter to populate list view
//here i am passing image id from drawable and string as to be set as title as
// a parameter to MyItem Constructor as our ArrayList is type of MyItem
        myitems.add(new MyItem(1,"Christ Redeemer: Rio de Janeiro Brazil"));
                myitems.add(new MyItem(2,"Great Wall of China: China"));
        myitems.add(new MyItem(3,"Machu Picchu: Peru"));
        myitems.add(new MyItem(4,"Petra: Jordan"));
        myitems.add(new MyItem(5,"Pyramid at Chichén Itzá:YucataPeninsula, Mexico"));
        myitems.add(new MyItem(6,"Roman Colosseum: Rome, Italy"));
        myitems.add(new MyItem(7,"Taj Mahal: Agra, India"));
//Creating Adapter object for setting to listview
        MyAdapter adapter=new MyAdapter(MainActivity.this,myitems);
        mainactivity.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
//getMenuInflater().inflate(R.menu.menu_main, menu);
//this shows three dots at right corner on click settings open
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        { return true; }
        return super.onOptionsItemSelected(item);
    }
}
