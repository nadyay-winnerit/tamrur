package objects.tests;

import infra.enums.Users;
import objects.BaseData;
import objects.pages.RegisterUserPageData;

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
