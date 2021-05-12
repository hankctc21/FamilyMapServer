package cs240.Model;

public class Couple{
    private Husband husband;
    private Wife wife;

    public Couple(){

        husband = new Husband();
        wife = new Wife();
    }

    public Husband getHusband() {
        return husband;
    }

    public Wife getWife() {
        return wife;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }



    public void setFather(){}

    public void setMother(){}

    public String toString(){
        return husband.toString() + "\n" + wife.toString();
    }



}
