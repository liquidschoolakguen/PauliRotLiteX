package akguen.liquidschool.paulirotlite.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E_ExpandableListData {


    public static HashMap<String, List<String>> getData() {


        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> merkBlatt = new ArrayList<String>();
        merkBlatt.add("zu spät!");
        merkBlatt.add("schulgelände verlassen");


        expandableListDetail.put("Heute", merkBlatt);
        expandableListDetail.put("Diese Woche", merkBlatt);
        expandableListDetail.put("Diesen Monat", merkBlatt);


        return expandableListDetail;
    }

}
