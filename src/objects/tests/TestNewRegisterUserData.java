package objects.tests;

import objects.BaseData;
import objects.pages.RegisterUserPageData;

public class TestNewRegisterUserData extends BaseData {

    String description;

    RegisterUserPageData registerUserPageData;

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
}
