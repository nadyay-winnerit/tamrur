package data.pages.newTherapist;

import data.BaseData;
import data.struct.AddressStructData;
import infra.enums.TypePhone;

public class ContactInformationPageData extends BaseData {
    private String phoneNumber;
    private TypePhone typePhone;
    private AddressStructData address = new AddressStructData();

    public void setAddress(AddressStructData address) {
        this.address = address;
    }
    //setter

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTypePhone(TypePhone typePhone) {
        this.typePhone = typePhone;
    }

    //getter

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public TypePhone getTypePhone() {
        return typePhone;
    }

    public AddressStructData getAddress() {
        return address;
    }
}
