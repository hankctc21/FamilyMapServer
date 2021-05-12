package cs240.Result;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */

public class ClearResult {

    private String message;

    public ClearResult(){

    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and
     generated person and event data.

     * @return true if all cleared.
     */
    public String toString(){
        return message;
    }
}
