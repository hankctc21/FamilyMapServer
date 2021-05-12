package cs240.Result;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */


import java.util.ArrayList;

import cs240.Model.Event;


public class EventResult {


    private ArrayList<Event> data;

    public ArrayList<Event> getEventList() {
        return data;
    }

    public void setEventList(ArrayList<Event> data) {
        this.data = data;
    }

    public String toStringList(){
        String str = "";
        for(Event e : data){
            str += e.toString() + "\n";
        }

        return str;
    }

}
