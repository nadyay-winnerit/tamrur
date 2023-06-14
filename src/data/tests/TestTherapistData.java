package data.tests;

import data.BaseData;
import data.pages.newTherapist.ContactInformationPageData;
import data.pages.newTherapist.DaysAndHoursOfOperationPageData;
import data.pages.newTherapist.ProfessionalDetailsPageData;
import data.pages.newTherapist.UserInformationPageData;

public class TestTherapistData extends BaseData {

    private UserInformationPageData UserInformationPageData_OldData;
    private UserInformationPageData UserInformationPageData;
    private ProfessionalDetailsPageData professionalDetailsPageData;
    private DaysAndHoursOfOperationPageData daysAndHoursOfOperationPageData;
    private ContactInformationPageData contactInformationPageData;

    //setter
    public void setProfessionalDetailsPageData(ProfessionalDetailsPageData professionalDetailsPageData) {
        this.professionalDetailsPageData = professionalDetailsPageData;
    }

    public void setDaysAndHoursOfOperationPageData(DaysAndHoursOfOperationPageData daysAndHoursOfOperationPageData) {
        this.daysAndHoursOfOperationPageData = daysAndHoursOfOperationPageData;
    }

    public void setContactInformationPageData(ContactInformationPageData contactInformationPageData) {
        this.contactInformationPageData = contactInformationPageData;
    }

    public void setUserInformationPageData_OldData(UserInformationPageData userInformationPageData_OldData) {
        UserInformationPageData_OldData = userInformationPageData_OldData;
    }

    public void setUserInformationPageData(UserInformationPageData userInformationPageData) {
        UserInformationPageData = userInformationPageData;

    }

    //getter
    public UserInformationPageData getUserInformationPageData_OldData() {
        return UserInformationPageData_OldData;
    }

    public UserInformationPageData getUserInformationPageData() {
        return UserInformationPageData;
    }

    public ProfessionalDetailsPageData getProfessionalDetailsPageData() {
        return professionalDetailsPageData;
    }

    public DaysAndHoursOfOperationPageData getDaysAndHoursOfOperationPageData() {
        return daysAndHoursOfOperationPageData;
    }

    public ContactInformationPageData getContactInformationPageData() {
        return contactInformationPageData;
    }
}
