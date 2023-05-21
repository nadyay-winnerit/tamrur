package objects.tests;

import objects.BaseData;
import objects.pages.RegisterUserPageData;

public class TestExampleData extends BaseData {

    private RegisterUserPageData registerUserPageData;

    public RegisterUserPageData getRegisterUserPageData() {
        return registerUserPageData;
    }

    public void setRegisterUserPageData(RegisterUserPageData data) {
        this.registerUserPageData = data;
    }
}
