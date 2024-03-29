package data.tests;

import data.BaseData;
import data.pages.medicalFile.ContactPageData;
import data.pages.medicalFile.FileDetailsPageData;
import data.pages.medicalFile.PatientDetailsPageData;


public class TestMedicalFileData extends BaseData {

    FileDetailsPageData fileDetailsPageData;
    PatientDetailsPageData patientDetailsData;
    // ReferenceDetailsPageData referenceDetailsData;
    ContactPageData contactPageData;
    //RelatedMedicalFilePageData relatedMedicalFile;
    //טיפולים
    // משימות
    //תכתובות
    //מסמכים
    //דוחות

    //get& set
    public PatientDetailsPageData getPatientDetailsPageData() {
        return patientDetailsData;
    }

    public void setPatientDetailsPageData(PatientDetailsPageData patientDetailsData) {
        this.patientDetailsData = patientDetailsData;
    }

    public ContactPageData getContactPageData() {
        return contactPageData;
    }

    public void setContactPageData(ContactPageData contactPageData) {
        this.contactPageData = contactPageData;
    }

    public FileDetailsPageData getFileDetailsPageData() {
        return fileDetailsPageData;
    }

    public void setFileDetailsPageData(FileDetailsPageData fileDetailsPageData) {
        this.fileDetailsPageData = fileDetailsPageData;
    }
}
