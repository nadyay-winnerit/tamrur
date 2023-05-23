package objects.pages.newTherapist;

import infra.enums.TypePhone;
import objects.BaseData;

public class ContactInformationPageData extends BaseData {
    private String address;
    private String cityMenu;
    private String country;
    private String emailAddress;
    private String agreeReceiveSMS;
    private String agreeReceiveEmail;
    private String phoneNumber;
    private TypePhone typePhone;

    //setter

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

    public void setAgreeReceiveSMS(String agreeReceiveSMS) {
        this.agreeReceiveSMS = agreeReceiveSMS;
    }

    public void setAgreeReceiveEmail(String agreeReceiveEmail) {
        this.agreeReceiveEmail = agreeReceiveEmail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTypePhone(TypePhone typePhone) {
        this.typePhone = typePhone;
    }

    //getter
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

    public String getAgreeReceiveSMS() {
        return agreeReceiveSMS;
    }

    public String getAgreeReceiveEmail() {
        return agreeReceiveEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public TypePhone getTypePhone() {
        return typePhone;
    }
}
