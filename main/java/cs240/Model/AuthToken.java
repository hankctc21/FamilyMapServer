package cs240.Model;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class AuthToken {
    private String authToken;
    private String userName;
    private String personID;


    public AuthToken(){

    }


    /**
     * create unique authToken and check if its ID exist in User class.
     * measure time of authToken.
     *
     * @param authToken instance unique authToken for user

     */
    public AuthToken(String authToken, String userName, String personID){
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUserName() {
        return userName;
    }

    public String getPersonID() { return personID;}



    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPersonID(String personID) { this.personID = personID; }

    public String toString(){
        return "aToken:"+ authToken + ", " + "uName:" +userName  + ", " + "pID:"+ personID   + "\n";
    }
}
