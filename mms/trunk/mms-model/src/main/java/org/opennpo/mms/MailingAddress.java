package org.opennpo.mms;

/**
 * Java Bean for holding a mailing address.
 * @author Nate Jones
 */
public class MailingAddress {
    private String address;
    private String city;
    private String stateOrProvince;
    private String postCode;
    private String country;
    
    public MailingAddress(){
        address = "";
        city = "";
        stateOrProvince = "";
        postCode = "";
        country = "";
    }
    
    public MailingAddress(String address, String city, String stateOrProvince, 
            String postCode, String country){
        this.address = address;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.postCode = postCode;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
