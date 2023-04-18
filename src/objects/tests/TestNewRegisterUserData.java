package objects.tests;

import objects.BaseData;
import objects.pages.RegisterUserPageData;

public class TestNewRegisterUserData extends BaseData {

    String description;
    RegisterUserPageData registerUserPageData;
    String date;
    String string;
    String number;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RegisterUserPageData getRegisterUserPageData() {
        return registerUserPageData;
    }

    public void setRegisterUserPageData(RegisterUserPageData registerUserPageData) {
        this.registerUserPageData = registerUserPageData;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
