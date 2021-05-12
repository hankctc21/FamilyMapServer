package cs240.Result;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */

public class FillResult {


    /**
     * Populates the server's database with generated data for the specified user name.
     The required "username" parameter must be a user already registered with the server. If there is
     any data in the database already associated with the given user name, it is deleted. The
     optional “generations” parameter lets the caller specify the number of generations of ancestors
     to be generated, and must be a non-negative integer (the default is 4, which results in 31 new
     persons each with associated events).*/



    private String message;

    public FillResult(){
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return message;
    }



}
