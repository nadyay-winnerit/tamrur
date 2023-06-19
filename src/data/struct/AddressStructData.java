package data.struct;

import data.BaseData;

public class AddressStructData extends BaseData {
    private String address;
    private String cityMenu;
    private String country;
    private String emailAddress;
    private Boolean agreeReceiveSMS;
    private Boolean agreeReceiveEmail;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCityMenu(String cityMenu) {
        this.cityMenu = cityMenu;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAgreeReceiveSMS(Boolean agreeReceiveSMS) {
        this.agreeReceiveSMS = agreeReceiveSMS;
    }

    public void setAgreeReceiveEmail(Boolean agreeReceiveEmail) {
        this.agreeReceiveEmail = agreeReceiveEmail;
    }

    public String getAddress() {
        return address;
    }

    public String getCityMenu() {
        return cityMenu;
    }

    public String getCountry() {
        return country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Boolean getAgreeReceiveSMS() {
        return agreeReceiveSMS;
    }

    public Boolean getAgreeReceiveEmail() {
        return agreeReceiveEmail;
    }


}
