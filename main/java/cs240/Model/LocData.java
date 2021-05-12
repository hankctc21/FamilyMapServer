package cs240.Model;

public class LocData{
    private String country;
    private String city;
    private double latitude;
    private double longitude;


    public LocData(){

    }
    public LocData(String ctry, String cntry, double lati, double longi){
        setCity(ctry);
        setCountry(cntry);
        setLatitude(lati);
        setLongitude(longi);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString(){
        return country + ", " + city + ", "+ + latitude + ", " + longitude;
    }
}
