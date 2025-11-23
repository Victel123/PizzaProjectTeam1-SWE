import javax.swing.undo.StateEdit;

/*
Class Address:
    @param: streetAddress:
 */

class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String fullAddress;

    public Address(String streetAddress, String city, String state, String zip){
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        fullAddress = streetAddress + city + state + zip;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    private void setStreetAddress(String streetAddress){
        this.streetAddress = streetAddress;
    }

    public String getCity(){
        return city;
    }

    private void setCity(String city){
        this.city = city;
    }

    public String getState(){
        return state;
    }

    private void setState(String state){
        this.state = state;
    }

    public String getZip(){
        return zip;
    }

    private void setZip(String zip){
        this.zip = zip;
    }

    public String getFullAddress(){
        return fullAddress;
    }

    @Override
    public String toString(){
        return fullAddress;
    }
}