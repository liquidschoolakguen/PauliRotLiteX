package zz_test;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import akguen.liquidschool.paulirotlite.R;

public class ViewHolderNormalX extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    TextView schuelername;
    TextView strafpunkt;

    public ViewHolderNormalX(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        schuelername = (TextView) itemView.findViewById(R.id.tv_item_schuelername);
        strafpunkt = (TextView) itemView.findViewById(R.id.tv_item_strafpunkt);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(v.getContext(), "LONG CLICK Position = " + getPosition(), Toast.LENGTH_SHORT).show();


        return true;
    }
}