package akguen.liquidschool.paulirotlite.adapters.old_adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

import akguen.liquidschool.mylib2.model.Gueltigkeitsbereich;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.mylib2.model.Raum;
import akguen.liquidschool.mylib2.model.Thema;
import akguen.liquidschool.paulirotlite.R;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<String> expandableListTitle;
        private Map<String, List<?>> expandableListDetail;

        Thema t;
        Raum r;
        Gueltigkeitsbereich g;
        Lerngruppe l;

    public View lastViewThema;
    public View lastViewRaum;
    public View lastViewGueltig;
    public View lastViewLerngruppe;





    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Thema getT() {
        return t;
    }

    public void setT(Thema t) {
        this.t = t;
    }

    public Raum getR() {
        return r;
    }

    public void setR(Raum r) {
        this.r = r;
    }

    public Gueltigkeitsbereich getG() {
        return g;
    }

    public void setG(Gueltigkeitsbereich g) {
        this.g = g;
    }

    public Lerngruppe getL() {
        return l;
    }

    public void setL(Lerngruppe l) {
        this.l = l;
    }

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       Map<String, List<?>> expandableListDetail) {
            this.context = context;
            this.expandableListTitle = expandableListTitle;
            this.expandableListDetail = expandableListDetail;
        }

        @Override
        public Object getChild(int listPosition, int expandedListPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .get(expandedListPosition);
        }

        @Override
        public long getChildId(int listPosition, int expandedListPosition) {
            return expandedListPosition;
        }

        @Override
        public View getChildView(int listPosition, final int expandedListPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            final String expandedListText = (String) getChild(listPosition, expandedListPosition).toString();


            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.list_item_sp_fach, null);
            }
            TextView expandedListTextView = (TextView) convertView
                    .findViewById(R.id.expandedListItem);
            expandedListTextView.setText(expandedListText);


            Object o = getChild(listPosition, expandedListPosition);
            if (o instanceof Thema) {

                if(t!=null && o.equals(t)){

                    convertView.setBackgroundResource(R.color.colorPrimaryDisable);
                    lastViewThema = convertView;

                }else{

                    convertView.setBackgroundColor(Color.WHITE);
                }



            } else if (o instanceof Raum) {
                if(r!=null && o.equals(r)){

                    convertView.setBackgroundResource(R.color.colorPrimaryDisable);
                    lastViewRaum = convertView;
                }else{

                    convertView.setBackgroundColor(Color.WHITE);
                }

            } else if (o instanceof Gueltigkeitsbereich) {
                if(g!=null && o.equals(g)){

                    convertView.setBackgroundResource(R.color.colorPrimaryDisable);
                    lastViewGueltig = convertView;
                }else{

                    convertView.setBackgroundColor(Color.WHITE);
                }



            } else if (o instanceof Lerngruppe) {
                if(l!=null && o.equals(l)){

                    convertView.setBackgroundResource(R.color.colorPrimaryDisable);
                    lastViewLerngruppe = convertView;
                }else{

                    convertView.setBackgroundColor(Color.WHITE);
                }


            }




            return convertView;
        }

        @Override
        public int getChildrenCount(int listPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .size();
        }

        @Override
        public Object getGroup(int listPosition) {
            return this.expandableListTitle.get(listPosition);
        }

        @Override
        public int getGroupCount() {
            return this.expandableListTitle.size();
        }

        @Override
        public long getGroupId(int listPosition) {
            return listPosition;
        }

        @Override
        public View getGroupView(int listPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String listTitle = (String) getGroup(listPosition);
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.list_group_sp_fach, null);
            }
            TextView listTitleTextView = (TextView) convertView
                    .findViewById(R.id.listTitle);
            listTitleTextView.setTypeface(null, Typeface.BOLD);
            listTitleTextView.setText(listTitle);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int listPosition, int expandedListPosition) {
            return true;
        }





}
