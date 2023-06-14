package data.pages.newTherapist;

import data.BaseData;

public class ProfessionalDetailsPageData extends BaseData {
    private String SpecializationMenu;
    private String treatmentType;
    private String treatmentPrice;
    private String treatmentDuration;
    private String generalComments;

    //setter
    public void setSpecializationMenu(String specializationMenu) {
        SpecializationMenu = specializationMenu;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public void setTreatmentPrice(String treatmentPrice) {
        this.treatmentPrice = treatmentPrice;
    }

    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public void setGeneralComments(String generalComments) {
        this.generalComments = generalComments;
    }

    //getter

    public String getSpecializationMenu() {
        return SpecializationMenu;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public String getTreatmentPrice() {
        return treatmentPrice;
    }

    public String getTreatmentDuration() {
        return treatmentDuration;
    }

    public String getGeneralComments() {
        return generalComments;
    }
}
