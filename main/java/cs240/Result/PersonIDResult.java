package cs240.Result;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */


import cs240.Model.Person;

/**
 *  Returns the single Person object with the specified ID.

 */
public class PersonIDResult{
    private String descendant;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String father;
    private String mother;
    private String spouse;

    private Person data;

    public PersonIDResult(){
        setFatherID(father);
        setFirstName(firstName);
        setGender(gender);
        setSpouseID(spouse);
        setDescendant(descendant);
        setPersonID(personID);
        setLastName(lastName);
        setMotherID(mother);

    }

    public void setPerson(Person person) {
        this.data = person;
    }

    public Person getPerson() {
        return data;
    }

    public String getGender() {
        return gender;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getFatherID() {
        return father;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMotherID() {
        return mother;
    }

    public String getPersonID() {
        return personID;
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
