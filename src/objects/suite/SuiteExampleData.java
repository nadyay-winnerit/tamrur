package objects.suite;

import objects.BaseData;
import objects.tests.TestTherapistData;

public class SuiteExampleData extends BaseData {

    TestTherapistData testExampleData;

    public TestTherapistData getTestExampleData() {
        return testExampleData;
    }

    public void setTestExampleData(TestTherapistData testExampleData) {
        this.testExampleData = testExampleData;
    }
}
