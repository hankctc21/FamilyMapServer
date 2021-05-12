package cs240.Service;


import java.sql.SQLException;

import cs240.DAO.DAOManager;
import cs240.Result.ClearResult;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */



public class ClearService {



    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and
     generated person and event data.
     */
    public ClearResult clear()  throws SQLException{
        ClearResult CR = new ClearResult();
        DAOManager DM = new DAOManager();
        String message = "";


            if(DM.dropTables()){
                message = "Clear succeeded";
                CR.setMessage(message);
                if(!DM.createTables()){
                    throw new SQLException("Error from SQL");
                }

            }



        return CR;
    }
}
