package data.tests;

import data.BaseData;
import data.pages.RegisterUserPageData;
import infra.enums.Users;

public class TestRegisterUserData extends BaseData {

    private Users loginUser;
    private RegisterUserPageData registerUserPageData;

    public RegisterUserPageData getRegisterUserPageData() {
        return registerUserPageData;
    }

    public void setRegisterUserPageData(RegisterUserPageData registerUserPageData) {
        this.registerUserPageData = registerUserPageData;
    }

    public Users getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(Users loginUser) {
        this.loginUser = loginUser;
    }
}
