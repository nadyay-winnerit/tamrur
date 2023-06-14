package data.pages.newTherapist;

import data.BaseData;
import infra.enums.Gender;

public class UserInformationPageData extends BaseData {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String loginPassword;
    private Boolean isActive;
    private Boolean isExternal;

    //setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public void setIsExternal(Boolean external) {
        isExternal = external;
    }

    //getter

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Boolean getExternal() {
        return isExternal;
    }
}
