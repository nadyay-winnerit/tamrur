package objects.pages;

public class RegisterUserPageData {


    private String emailInput;
    private String fNameInput;
    private String pwdInput;
    private String lrSelect;
    private String activeInput;

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

    public String getActiveInput() {
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

    public void setActiveInput(String activeInput) {
        this.activeInput = activeInput;
    }
}