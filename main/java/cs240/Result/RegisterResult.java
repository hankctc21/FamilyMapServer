package cs240.Result;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */

/**
 *Get and set register information.
 */
public class RegisterResult {


    private String authToken;
    private String userName;
    private String personID;
    private String message;



    public RegisterResult(){
        setUserName(userName);
        setPersonID(personID);
        setAuthToken(authToken);
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPersonID() {
        return personID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUserName() { return userName; }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String toString(){
        return authToken + ", " + userName + ", " + personID;
    }
}
