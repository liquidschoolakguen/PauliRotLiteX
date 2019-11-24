package zz_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import akguen.liquidschool.paulirotlite.R;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyItem> listforview;
    LayoutInflater inflator=null;
    View v;
    ViewHolder vholder;
    //Constructor
    public MyAdapter(Context con,ArrayList<MyItem> list)
    {
        super();
        context=con;
        listforview=list;
        inflator=LayoutInflater.from(con);
    }
    // return position here
    @Override
    public long getItemId(int position) {
        return position;
    }
    // return size of list
    @Override
    public int getCount() {
        return listforview.size();
    }
    //get Object from each position
    @Override
    public Object getItem(int position) {
        return listforview.get(position);
    }
    //Viewholder class to contain inflated xml views
    private  class ViewHolder
    {
        TextView title;
        ImageView image;
    }
    // Called for each view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        v=convertView;
        if(convertView==null)
        {
            //inflate the view for each row of listview
            v=inflator.inflate(R.layout.zz_test_myadapter,null);
            //ViewHolder object to contain zz_test_myadapteryadapter.xml elements
            vholder=new ViewHolder();
            vholder.title=(TextView)v.findViewById(R.id.adaptertextview);
            vholder.image=(ImageView)v.findViewById(R.id.adapterimage);
            //set holder to the view
            v.setTag(vholder);
        }
        else
            vholder=(ViewHolder)v.getTag();
        //getting MyItem Object for each position
        MyItem item=(MyItem)listforview.get(position);
        //set the id to editetxt important line here as it will be helpful to set text according to position
        vholder.title.setId(position);
        //setting the values from object to holder views for each row vholder.title.setText(item.getImageheading()); vholder.image.setImageResource(item.getImageid());
        vholder.title.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            final int id = v.getId();
                            MyItem item = listforview.get(id);
                            final EditText field = ((EditText) v);
                            listforview.get(id).setImageheading(field.getText().toString());
                        }
                    }
                }
        );
        return v;
    }
}
