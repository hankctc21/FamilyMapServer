//package cs240;
//
///**
// * Created by Jeong Suk(Jerry) Han on 2017-10-12.
// */
//
//public class Person {
//    private String personID;
//    private String descendant;
//    private String firstName;
//    private String lastName;
//    private String gender;
//    private String fatherID;
//    private String motherID;
//    private String spouseID;
//
//    public Person(String personID){
//        this.personID = personID;
//    }
//
//    /**
//     * get all the information and store.
//     *
//     * @param personID
//     * @param descendant
//     * @param firstName
//     * @param lastName
//     * @param gender
//     * @param fatherID
//     * @param motherID
//     * @param spouseID
//     */
//
//
//    public Person(String personID, String descendant, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID){
//        this.personID = personID;
//        this.descendant = descendant;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.gender = gender;
//        this.fatherID = fatherID;
//        this.motherID = motherID;
//        this.spouseID = spouseID;
//    }
//
//    public String getPersonID(){
//        return personID;
//    }
//    public String getDescendant(){return descendant;}
//    public String getFirstName(){return firstName;}
//    public String getLastName(){return lastName;}
//
//    public String getFatherID() {
//        return fatherID;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getMotherID() {
//        return motherID;
//    }
//
//    public String getSpouseID() {
//        return spouseID;
//    }
//
//    public void setPersonID(String personID) {
//        this.personID = personID;
//    }
//
//    public void setDescendant(String descendant) {
//        this.descendant = descendant;
//    }
//
//    public void setFatherID(String fatherID) {
//        this.fatherID = fatherID;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setMotherID(String motherID) {
//        this.motherID = motherID;
//    }
//
//    public void setSpouseID(String spouseID) {
//        this.spouseID = spouseID;
//    }
//}
