package data.pages;

import data.BaseData;

public class RegisterUserPageData extends BaseData {

    private String emailInput;
    private String fNameInput;
    private String pwdInput;
    private String lrSelect;
    private boolean activeInput;

    public String getEmailInput() {
      return  emailInput;
    }

    public String getFNameInput() {
        return fNameInput;
    }

    public String getPwdInput() {
        return pwdInput;
    }

    public String getLrSelect() {
        return lrSelect;
    }

    public boolean getActiveInput() {
        return activeInput;
    }

    public void setEmailInput(String emailInput) {
        this.emailInput = emailInput;
    }

    public void setFNameInput(String fNameInput) {
        this.fNameInput = fNameInput;
    }

    public void setPwdInput(String pwdInput) {
        this.pwdInput = pwdInput;
    }

    public void setLrSelect(String lrSelect) {
        this.lrSelect = lrSelect;
    }

    public void setActiveInput(boolean activeInput) {
        this.activeInput = activeInput;
    }

}
