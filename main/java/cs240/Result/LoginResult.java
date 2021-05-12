package cs240.Result;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */


/**
 * get and set login Results
 */
public class LoginResult {
    private String authToken;
    private String userName;
    private String personID;

    public LoginResult(){
        setPersonID(personID);
        setAuthToken(authToken);
        setUserName(userName);
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPersonID() {
        return personID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String toString(){
        return authToken + ", " + userName + ", " + personID;
    }

}
