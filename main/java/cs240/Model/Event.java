package cs240.Model;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class Event {
    private String eventID;
    private String descendant;
    private String personID;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    private Event event;



    public Event(){}

    /**
     * get all the information and store.
     *
     * @param eventID
     * @param descendant
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     */
    public Event(String eventID, String descendant, String personID, double latitude, double longitude, String country, String city, String eventType, int year){
        this.eventID = eventID;
        this.descendant = descendant;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.year = year;
        this.country = country;
        this.city = city;
        this.eventType = eventType;

    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }



    public String getEventID(){ return eventID; }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPersonID() {
        return personID;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
        return descendant + ", " + eventID + ", " +  personID  + ", " +  latitude  + ", " + longitude + ", " + country + ", " + city  + ", " + eventType + ", " + year + "\n";
    }
}
