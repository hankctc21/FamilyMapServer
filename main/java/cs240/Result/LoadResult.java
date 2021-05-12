package cs240.Result;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-20.
 */


/**
 * loads the
 posted user, person, and event data into the database.

 */
public class LoadResult {


    private String message;

    public LoadResult(){

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
