package data.pages;

import data.BaseData;

public class LoginPageData extends BaseData {

    private String userNameUi;
    private String passWordUi;
    private boolean rememberCheckBoxUi;

    public String getUserNameUi() {
        return userNameUi;
    }

    public String getPassWordUi() {
        return passWordUi;
    }

    public boolean getRememberCheckBoxUi() {
        return rememberCheckBoxUi;
    }

    public void setPassWordUi(String passWordUi) {
        this.passWordUi = passWordUi;
    }

    public void setRememberCheckBoxUi(boolean rememberCheckBoxUi) {
        this.rememberCheckBoxUi = rememberCheckBoxUi;
    }

    public void setUserNameUi(String userNameUi) {
        this.userNameUi = userNameUi;
    }
}
