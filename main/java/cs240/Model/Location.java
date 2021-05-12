package cs240.Model;

import java.util.ArrayList;

public class Location{

    private ArrayList<LocData> data;

    public Location(){
        data = new ArrayList<>();
    }

    public ArrayList<LocData> getData() {
        return data;
    }

    public void setData(ArrayList<LocData> data) {
        this.data = data;
    }

    public String toString(){

        String s = "";
        for (LocData loc : data){
            s += loc + "\n";
        }
        return s;
    }
}
