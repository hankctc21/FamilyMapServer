package cs240.Request;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */


/**
 * store login info.
 */
public class LoginRequest {

    private String userName;
    private String password;


    public LoginRequest(){
        setUserName(userName);
        setPassword(password);
    }

    public LoginRequest(String userName, String password){


    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return userName + "," + password;
    }
}
