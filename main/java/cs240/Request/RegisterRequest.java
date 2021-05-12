package cs240.Request;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */


import java.util.ArrayList;

import cs240.Model.Event;
import cs240.Model.User;

/**
 * store Register Info
 */
public class RegisterRequest {
    private ArrayList<User> users;
    private ArrayList<Event> data;


    public RegisterRequest(){
        users = new ArrayList<>();
        data = new ArrayList<>();
    }



    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String latitude;
    private String longitude;
    private char gender;
    private String personID = "";



    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Event> getData(){
        return data;
    }



    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassWord() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

