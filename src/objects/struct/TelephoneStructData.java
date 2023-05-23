package objects.struct;

import objects.BaseData;

public class TelephoneStructData extends BaseData {

    //    private UiValue phoneNumber = new UiValue().label("מספר טלפון").max("1000").min("5");
    private String phoneNumber;
    private String phoneType;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

}
