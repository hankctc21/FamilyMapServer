package cs240.Service;

import java.sql.SQLException;

import cs240.DAO.DAOManager;
import cs240.DAO.Generate;
import cs240.DAO.UserDao;
import cs240.Model.User;
import cs240.Request.LoginRequest;
import cs240.Result.LoginResult;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */



public class LoginService {
    User u = new User();
    UserDao UD= new UserDao();


    public LoginService() {
    }



    /**
     * Logs in the user and get an auth token
     */
    public LoginResult login(LoginRequest r) throws SQLException {
        DAOManager DM = new DAOManager();
        UserDao UD = new UserDao();
        LoginResult LR = new LoginResult();


        if(SetUser(r)){
            User user = UD.getUser(u);
            LR.setUserName(user.getUserName());
            LR.setPersonID(user.getPersonID());
            LR.setAuthToken(user.getAuthToken());
        }

        return LR;
    }

    public boolean SetUser(LoginRequest r) throws SQLException {

        Generate gen = new Generate();

        u.setUserName(r.getUserName());
        u.setPassWord(r.getPassword());


        if(!(UD.findUser(u))){
            if((!UD.findPassword(u))){
                throw new SQLException("Error in addUser");
            }

        }else if((UD.findUser(u))){
            if((!UD.findPassword(u))) {
                throw new SQLException("Error in addUser");
            }
        }



        return true;
    }
}
