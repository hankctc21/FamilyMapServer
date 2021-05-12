package cs240;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.SQLException;

import cs240.DAO.DAOManager;
import cs240.DAO.EventDao;
import cs240.DAO.PersonDao;
import cs240.DAO.UserDao;
import cs240.Handler.EnDecoder;
import cs240.Service.EventService;
import cs240.Service.RegisterService;


public class Main {


    public Main() throws SQLException, ClassNotFoundException {
    }

    public static void main(String[] args) throws Exception {
        DAOManager DM = new DAOManager();
        UserDao ud = new UserDao();
        PersonDao pd = new PersonDao();
        EventDao ed = new EventDao();
        Gson myGson = new GsonBuilder().setPrettyPrinting().create();

        EventService ES = new EventService();
        EnDecoder ED = new EnDecoder();

        RegisterService RS = new RegisterService();


        System.out.println("OK");
    }
}