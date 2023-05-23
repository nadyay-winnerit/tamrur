package tests;

import infra.data.DataProcessor;
import infra.test.TestBase;
import objects.pages.medicalFile.RelatedMedicalFilePageData;
import objects.tests.TestMedicalFileData;
import org.junit.runners.Parameterized;
import pages.NavbarPage;
import pages.medicalFile.*;


import java.util.Collection;

public class TestMedicalFile extends TestBase {
    private TestMedicalFileData testMedicalFile;

    public TestMedicalFile(String testCase, TestMedicalFileData testMedicalFileData) {
        super(testCase, testMedicalFileData);
        this.testMedicalFile = testMedicalFileData;
    }

    @Parameterized.Parameters(name = "{index}::{0}")
    public static Collection data() {
        return DataProcessor.createSuiteData(TestMedicalFileData.class);
    }

    @Override
    public void test() {
        NavbarPage.medicalFilesClick();
        NavbarPage.plusClick();
        new FileDetailsPage().assertPage().fillPage(testMedicalFile.getFileDetailsPageData()).finish();
        new PatientDetailsPage().assertPage().fillPage(testMedicalFile.getPatientDetailsData()).finish();
        NavbarPage.nextClick();
        new ReferenceDetailsPage().finish();
        NavbarPage.nextClick();
        new ContactPage().assertPage().fillPage(testMedicalFile.getContactPageData()).finish();
        NavbarPage.nextClick();
        new RelatedMedicalFilePage().finish();
        NavbarPage.nextClick();
        new TreatmentPage().finish();
        NavbarPage.nextClick();
        new TaskPage().finish();
        NavbarPage.nextClick();
        new CorrespondencePage().finish();
        NavbarPage.nextClick();
        new TreatmentPage().finish();
        NavbarPage.nextClick();
        new DocumentPage().finish();
        NavbarPage.nextClick();
        new ReportPage().finish();
        NavbarPage.saveClick();
    }
}
