package zz_test;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import akguen.liquidschool.paulirotlite.R;

public class ViewHolderPlusX extends RecyclerView.ViewHolder implements View.OnClickListener  {

    TextView plusButton;


    public ViewHolderPlusX(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
       // plusButton = (TextView) itemView.findViewById(R.id.plus_button);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked PlusButton = " + getPosition(), Toast.LENGTH_SHORT).show();


    }
}