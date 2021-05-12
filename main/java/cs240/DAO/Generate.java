package cs240.DAO;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import cs240.Handler.EnDecoder;
import cs240.Model.Couple;
import cs240.Model.LocData;
import cs240.Model.Location;
import cs240.Model.Person;


/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-02.
 */
public class Generate {
    DAOManager DM = new DAOManager();
    Connection c = DM.getConnection();
    EnDecoder ED = new EnDecoder();

    private ArrayList<String> data;


    public Generate() {
        data = new ArrayList<>();
    }


        private static String generateRandom(String aToZ) {
        Random rand = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randIndex = rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        }
        return res.toString();
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public String getRandomID() throws SQLException {
        boolean find;

        String aToZ = "abcdefghijklmnopqrstuvwxyz0123456789"; // 36 letter.

        String randomID = generateRandom(aToZ);

        Person person = new Person(randomID);

        if(findCurPersonID(person)){
            find = true;
        }
        else {
            find = false;
        }

        if(find == false){
            return randomID;
        }
        return getRandomID();
    }

    public boolean findCurPersonID(Person p) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results;

        String PID = p.getPersonID();

            try{
                String sql = " SELECT * FROM PERSON WHERE PersonID = ?";
                stmt = c.prepareStatement(sql);

                stmt.setString(1, PID);

                results = stmt.executeQuery();

                while (results.next()) {
                    String PrsnID = results.getString(1);

                    if(PID.equals(PrsnID)){
                        return true;
                    }
                }
                results.close();

                stmt.executeQuery();

            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        System.out.println("Records not found ");
        return false;
    }

    public String getRandUserName() throws IOException {
        String input = ED.fileToString("fms/fnames.json");


        Gson myGson = new Gson();

        Generate gen = myGson.fromJson(input, Generate.class);




        String randUser;
        int randomNum;
        int minimum = 1;
        int maximum = gen.getData().size();

        randomNum = minimum + (int)(Math.random() * maximum);
        randUser = gen.getData().get(randomNum-1);

        return randUser;
    }

    public String getRandFirstName(String userName){
        String firstName;

        firstName = userName.substring(0,1).toUpperCase() + userName.substring(1).toLowerCase();

        return firstName;

    }

    public String getRandLastName() throws IOException {

        String input = ED.fileToString("fms/snames.json");

        Gson myGson = new Gson();

        Generate gen = myGson.fromJson(input, Generate.class);


        String str;
        String randLast;

        int randomNum;
        int minimum = 1;
        int maximum = gen.getData().size();

        randomNum = minimum + (int)(Math.random() * maximum);
        str = gen.getData().get(randomNum-1);
        randLast = str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();

        return randLast;
    }

    public String getRandGender(){
        ArrayList<Character> randGenCharList = new ArrayList<>();

        randGenCharList.add('m');
        randGenCharList.add('f');

        int randNum;
        int min = 1;
        int max = 2;

        randNum = min + (int)(Math.random() * max);

        char randGender = randGenCharList.get(randNum-1);

        return Character.toString(randGender);
    }

    public String getRandEmail(String userName){
        String email;

        int randNum1;
        int min1 = 1;
        int max1 = 99;

        randNum1 = min1 + (int)(Math.random() * max1);

        ArrayList<String> emailCompList = new ArrayList<>();

        emailCompList.add("gmail.com");
        emailCompList.add("yahoo.com");
        emailCompList.add("hotmail.com");

        int randNum2;
        int min2 = 1;
        int max2 = 3;
        randNum2 = min2 + (int)(Math.random() * max2);

        String eComp = emailCompList.get(randNum2-1);

        email = userName + randNum1 +"@" + eComp;

        return email;
    }

    public int getRandYear(){
        int randYear;
        int min = 0;
        int max = 100;

        randYear = min + (int)(Math.random() * max);
        return randYear + 1910;
    }

    public int getRandNumber(){
        int randNum;
        int min = 0;
        int max = 35;

        randNum = min + (int)(Math.random() * max);
        return randNum + 17;
    }

    public int getRandNumber2(){
        int randNum2;
        int min = 0;
        int max = 10;
        randNum2 = min + (int)(Math.random() * max);
        return randNum2;
    }

    public LocData getRandLocation() throws IOException {
        String input = ED.fileToString("fms/locations.json");

        Gson myGson = new Gson();

        Location loc = myGson.fromJson(input, Location.class);

        LocData randData;
        int randomNum;
        int minimum = 1;
        int maximum = loc.getData().size();

        randomNum = minimum + (int)(Math.random() * maximum);
        randData = loc.getData().get(randomNum-1);

        return randData;
    }

    public String getRandEventType(){

        ArrayList<String> typeList = new ArrayList<>();

        typeList.add("birth");
        typeList.add("baptism");
        typeList.add("christening");
        typeList.add("marriage");
        typeList.add("death");
        typeList.add("accident");
        String randType;


        int randNum;
        int min = 1;
        int max = typeList.size();

        randNum = min + (int)(Math.random() * max);

        randType = typeList.get(randNum-1);

        return randType;
    }

    public Couple randomCouple(int year) throws SQLException, IOException {
        Couple randCouple = new Couple();


        //Husband
        LocData randLocData = getRandLocation();
        //Sets User
        randCouple.getHusband().getHusbandUser().setUserName(getRandUserName());
        randCouple.getHusband().getHusbandUser().setPassWord(getRandomID());
        randCouple.getHusband().getHusbandUser().setPersonID(getRandomID());
        randCouple.getHusband().getHusbandUser().setAuthToken(getRandomID());
        randCouple.getHusband().getHusbandUser().setEmail(getRandEmail(randCouple.getHusband().getHusbandUser().getUserName()));
        randCouple.getHusband().getHusbandUser().setFirstName(getRandFirstName(randCouple.getHusband().getHusbandUser().getUserName()));
        randCouple.getHusband().getHusbandUser().setLastName(getRandLastName());
        randCouple.getHusband().getHusbandUser().setGender("m");

        //Sets Person
        randCouple.getHusband().getHusbandPerson().setPersonID(randCouple.getHusband().getHusbandUser().getPersonID());
        randCouple.getHusband().getHusbandPerson().setFirstName(randCouple.getHusband().getHusbandUser().getFirstName());
        randCouple.getHusband().getHusbandPerson().setLastName(randCouple.getHusband().getHusbandUser().getLastName());
        randCouple.getHusband().getHusbandPerson().setGender(randCouple.getHusband().getHusbandUser().getGender());
        randCouple.getHusband().getHusbandPerson().setFatherID(getRandomID());
        randCouple.getHusband().getHusbandPerson().setMotherID(getRandomID());

        //Sets Event
        randCouple.getHusband().getHusbandEvent().setEventID(getRandomID());
        randCouple.getHusband().getHusbandEvent().setPersonID(randCouple.getHusband().getHusbandUser().getPersonID());
        randCouple.getHusband().getHusbandEvent().setLatitude(randLocData.getLatitude());
        randCouple.getHusband().getHusbandEvent().setLongitude(randLocData.getLongitude());
        randCouple.getHusband().getHusbandEvent().setCountry(randLocData.getCountry());
        randCouple.getHusband().getHusbandEvent().setCity(randLocData.getCity());
        randCouple.getHusband().getHusbandEvent().setEventType(getRandEventType());
        randCouple.getHusband().getHusbandEvent().setYear(year+getRandNumber2());


        //wife
        LocData randLocData2 = getRandLocation();
        //Sets User
        randCouple.getWife().getWifeUser().setUserName(getRandUserName());
        randCouple.getWife().getWifeUser().setPassWord(getRandomID());
        randCouple.getWife().getWifeUser().setPersonID(getRandomID());
        randCouple.getWife().getWifeUser().setAuthToken(getRandomID());
        randCouple.getWife().getWifeUser().setEmail(getRandEmail(randCouple.getWife().getWifeUser().getUserName()));
        randCouple.getWife().getWifeUser().setFirstName(getRandFirstName(randCouple.getWife().getWifeUser().getUserName()));
        randCouple.getWife().getWifeUser().setLastName(getRandLastName());
        randCouple.getWife().getWifeUser().setGender("f");

        //Sets Person
        randCouple.getWife().getWifePerson().setPersonID(randCouple.getWife().getWifeUser().getPersonID());
        randCouple.getWife().getWifePerson().setFirstName(randCouple.getWife().getWifeUser().getFirstName());
        randCouple.getWife().getWifePerson().setLastName(randCouple.getWife().getWifeUser().getLastName());
        randCouple.getWife().getWifePerson().setGender(randCouple.getWife().getWifeUser().getGender());
        randCouple.getWife().getWifePerson().setFatherID(getRandomID());
        randCouple.getWife().getWifePerson().setMotherID(getRandomID());


        //Sets Event
        randCouple.getWife().getWifeEvent().setEventID(getRandomID());
        randCouple.getWife().getWifeEvent().setPersonID(randCouple.getWife().getWifeUser().getPersonID());
        randCouple.getWife().getWifeEvent().setLatitude(randLocData2.getLatitude());
        randCouple.getWife().getWifeEvent().setLongitude(randLocData2.getLongitude());
        randCouple.getWife().getWifeEvent().setCountry(randLocData2.getCountry());
        randCouple.getWife().getWifeEvent().setCity(randLocData2.getCity());
        randCouple.getWife().getWifeEvent().setEventType(getRandEventType());
        randCouple.getWife().getWifeEvent().setYear(year+getRandNumber2());

        //Sets SpouseIDs
        randCouple.getHusband().getHusbandPerson().setSpouseID(randCouple.getWife().getWifeUser().getPersonID());
        randCouple.getWife().getWifePerson().setSpouseID(randCouple.getHusband().getHusbandUser().getPersonID());



        return randCouple;
    }

    public String toString(){

        return data.toString();
    }

}





