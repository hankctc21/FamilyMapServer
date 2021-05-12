//package cs240;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Random;
//
//import cs240.Model.*;
//
///**
// * Created by Jeong Suk(Jerry) Han on 2017-11-02.
// */
//
//public class Generate {
//    private Connection c;
//
//    public void openConnection() {
//        try {
//            c = DriverManager.getConnection("jdbc:sqlite:daoData.db");
//            c.setAutoCommit(false);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void closeConnection(boolean commit){
//        try {
//            if(commit){
//                c.commit();
//            }
//            else{
//                c.rollback();
//            }
//
//            c.close();
//            c = null;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private static String generateRandom(String aToZ) {
//        Random rand = new Random();
//        StringBuilder res = new StringBuilder();
//        for (int i = 0; i < 8; i++) {
//            int randIndex = rand.nextInt(aToZ.length());
//            res.append(aToZ.charAt(randIndex));
//        }
//        return res.toString();
//    }
//
//    public String getRandomID() throws SQLException {
//        boolean find = true;
//        openConnection();
//
//        String aToZ = "abcdefghijklmnopqrstuvwxyz0123456789"; // 36 letter.
//        String randomID = generateRandom(aToZ);
//
//        Person person = new Person(randomID);
//
//        if(findPersonID(person)){
//           find = true;
//        }
//        else {
//            find = false;
//        }
//        closeConnection(true);
//
//        if(find == false){
//            return randomID;
//        }
//        return getRandomID();
//    }
//
//    public boolean findPersonID(Person p){
//        PreparedStatement stmt = null;
//        ResultSet results = null;
//
//        String PID = p.getPersonID();
//        try {
//
//            try{
//
//                String sql = " SELECT * FROM PERSON WHERE PersonID = ?";
//                stmt = c.prepareStatement(sql);
//                stmt.setString(1, PID);
//                results = stmt.executeQuery();
//
//
//                while (results.next()) {
//                    String PrsnID = results.getString(1);
//
//                    if(PID.equals(PrsnID)){
//                        System.out.println(PID + " exist!");
//                        return true;
//                    }
//
//                }
//                results.close();
//
//                stmt.executeUpdate();
//            }finally {
//                if (stmt != null) {
//                    stmt.close();
//                    stmt = null;
//                }
//            }
//
//        }catch ( SQLException e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.out.println( PID + " doesn't exist!");
//            return false;
//        }
//        System.out.println("Records found successfully");
//        return true;
//    }
//
//
//
//
//}
