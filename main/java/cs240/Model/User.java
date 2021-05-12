package cs240.Model;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class User {

    private User user;
    private String userName;
    private String password;
    private String authToken;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    public User(){

    }


    /**
     * get all the information and store.
     *
     * @param userName
     * @param passWord
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */
    public User(String userName, String passWord, String email, String firstName, String lastName, String gender, String authToken, String personID){
        this.userName = userName;
        this.password = passWord;
        this.personID = personID;
        this.authToken = authToken;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassWord(){return this.password;}

    public String getAuthToken(){return this.authToken;}

    public String getEmail(){return this.email;}

    public String getFirstName(){return this.firstName;}

    public String getLastName() { return this.lastName;}

    public String getPersonID(){ return this.personID;}

    public String getGender(){ return this.gender;}



    public void setUserName(String value){ this.userName = value; }

    public void setPassWord(String value){
        this.password = value;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setEmail(String value){
        this.email = value;
    }

    public void setFirstName(String value){
        this.firstName = value;
    }

    public void setLastName(String value){
        this.lastName = value;
    }

    public void setPersonID(String value){
        this.personID = value;
    }

    public void setGender(String c){
        this.gender = c;
    }


    public String toString(){
        return "\n" + userName + ", " + password + ", " + personID  + ", " + authToken  + ", " + email  + ", " + firstName  + ", " + lastName  + ", " + gender + "\n";

    }



}
