package cs240.Model;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class Person {
    private String personID;
    private String descendant;
    private String firstName;
    private String lastName;
    private String gender;
    private String father;
    private String mother;
    private String spouse;

    private Person person;


    public Person (){}
    public Person(String personID){
        this.personID = personID;
    }



    /**
     * get all the information and store.
     *
     * @param personID
     * @param descendant
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */


    public Person(String personID, String descendant, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID){
        this.personID = personID;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = fatherID;
        this.mother = motherID;
        this.spouse = spouseID;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public String getPersonID(){
        return personID;
    }
    public String getDescendant(){return descendant;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}

    public String getFatherID() {
        return father;
    }

    public String getGender() {
        return gender;
    }

    public String getMotherID() {
        return mother;
    }

    public String getSpouseID() {
        return spouse;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public void setFatherID(String fatherID) {
        this.father = fatherID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMotherID(String motherID) {
        this.mother = motherID;
    }

    public void setSpouseID(String spouseID) {
        this.spouse = spouseID;
    }


    public String toString(){
        return "pID:"+ personID + ", " + "d:" +descendant  + ", " + "FN:"+ firstName   + ", " + "LN:" + lastName  + ", " + "g:" + gender   + ", " + "fID:" +father  + ", "+ "mID:"+mother + ", " + "spID:" +spouse + "\n";
    }
}
