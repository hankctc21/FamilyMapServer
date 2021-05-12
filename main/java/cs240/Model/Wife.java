package cs240.Model;

public class Wife{
    private User wifeUser;
    private Person wifePerson;
    private Event wifeEvent;

    public Wife(){
        wifeUser = new User();
        wifePerson = new Person();
        wifeEvent = new Event();
    }

    public User getWifeUser() {
        return wifeUser;
    }

    public Person getWifePerson() {
        return wifePerson;
    }

    public Event getWifeEvent() {
        return wifeEvent;
    }

    public void setWifeUser(User wifeUser) {
        this.wifeUser = wifeUser;
    }

    public void setWifePerson(Person wifePerson) {
        this.wifePerson = wifePerson;
    }

    public void setWifeEvent(Event wifeEvent) {
        this.wifeEvent = wifeEvent;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
