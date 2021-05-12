package cs240.Model;

public class Husband{

    private User husbandUser;
    private Person husbandPerson;
    private Event husbandEvent;

    public Husband(){
        husbandUser = new User();
        husbandPerson = new Person();
        husbandEvent = new Event();
    }



    public User getHusbandUser() {
        return husbandUser;
    }

    public Person getHusbandPerson() {
        return husbandPerson;
    }

    public Event getHusbandEvent() {
        return husbandEvent;
    }

    public void setHusbandUser(User husbandUser) {
        this.husbandUser = husbandUser;
    }

    public void setHusbandPerson(Person husbandPerson) {
        this.husbandPerson = husbandPerson;
    }

    public void setHusbandEvent(Event husbandEvent) {
        this.husbandEvent = husbandEvent;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
