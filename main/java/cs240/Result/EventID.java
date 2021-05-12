package cs240.Result;

import cs240.Model.Event;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-14.
 */

public class EventID {

    private String personID;

    private String descendant;
    private String eventID;
    private double latitude;
    private double longitude;
    private String city;
    private String country;
    private String eventType;
    private int year;

    private Event event;

    public EventID(){
        setDescendant(descendant);
        setCity(city);
        setEventID(eventID);
        setEventType(eventType);
        setLatitude(latitude);
        setLongitude(longitude);
        setYear(year);
        setPersonID(personID);
        setCountry(country);

    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Event getEvent() {
        return event;
    }


    public String getCountry() {
        return country;
    }

    public String getPersonID() {
        return personID;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getLatitude() {
        return latitude;
    }


    public int getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getEventID() {
        return eventID;
    }

    public String getEventType() {
        return eventType;
    }


    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public void setCity(String city) {
        this.city = city;
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

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
        return "\n" + eventType + ", " + personID + ", " + city  + ", " + country  + ", " + latitude  + ", " + longitude  + ", " + year + ", " + eventID + ", " + descendant + "\n";
    }


}
