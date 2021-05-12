package cs240.DAO;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-16.
 */

public class ErrorMessage {
    String message;

    public ErrorMessage(){
        setMessage(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
