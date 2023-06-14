package data.tests;

import data.BaseData;
import data.pages.RegisterUserPageData;

public class TestExampleData extends BaseData {

    private RegisterUserPageData registerUserPageData;

    public RegisterUserPageData getRegisterUserPageData() {
        return registerUserPageData;
    }

    public void setRegisterUserPageData(RegisterUserPageData data) {
        this.registerUserPageData = data;
    }
}
