package suite;

import infra.data.DataProcessor;
import infra.test.SuiteBase;
import objects.suite.SuiteExampleData;
import org.junit.runners.Parameterized;
import tests.TestTherapist;

import java.util.Collection;

public class SuiteExample extends SuiteBase {

    private SuiteExampleData suiteData;

    public SuiteExample(String testCase,SuiteExampleData data) {
        super(testCase, data);
        suiteData = data;
    }

    @Parameterized.Parameters(name = "{index}::{0}")
    public static Collection data() {
        return DataProcessor.createSuiteData(SuiteExampleData.class);
    }

    public void suite() {
        runTest(TestTherapist.class, suiteData.getTestExampleData());
        runTest(TestTherapist.class, suiteData.getTestExampleData().getId());
        //runStep(ContactsTest.class, suiteData.getContactTest());
        //runStep(TestIvchun.class, "Contact without credit card");
    }
}
