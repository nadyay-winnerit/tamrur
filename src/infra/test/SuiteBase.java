//package infra.test;
//
//import infra.data.DataProcessor;
//import objects.BaseData;
//import objects.pages.RegisterUserPageData;
//import objects.tests.TestNewRegisterUserData;
//import org.junit.Test;
//import org.junit.runner.JUnitCore;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Suite;
//import tests.ContactsTest;
//
//import java.util.Collection;
//import java.util.Collections;
//
////@RunWith(@Sui)
////@Suite.SuiteClasses({CONTACTTEST.class,})
//
//@RunWith(Parameterized.class)
//public abstract class SuiteBase {
//
//    public static String currentTestId;
//
//    @Test
//    public abstract void suite();
//
//    @Parameterized.Parameters()
//    public static Collection<Object[]> data() {
//        return DataProcessor.readCSV(TestExample.class);
//    }
//
//    protected void runTest(Class<? extends TestBase> testClass, String testId) {
//        if () {
//            currentTestId = testId;
//            JUnitCore.runClasses(testClass);
//        }
//    }
//
//}
//
//
//class SuiteExample extends SuiteBase {
//
//    SuiteExampleData suiteData;
//
//    @Parameterized.Parameter
//    public static Collections data() {
//        return DataProcessor.readCSV(ContactsTest.class);
//    }
//
//    public void suite() {
//        runTest(ContactsTest.class, suiteData.getTestNewRegisterUser());
//        //runStep(ContactsTest.class, suiteData.getContactTest());
//        //runStep(TestIvchun.class, "Contact without credit card");
//    }
//}
//
//class SuiteExampleData extends BaseData{
//}
//
//class TestExample  {
//
//}
//
//class TestExampleData extends BaseData{
//
//}