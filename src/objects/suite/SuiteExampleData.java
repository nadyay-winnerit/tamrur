package objects.suite;

import objects.BaseData;
import objects.tests.TestExampleData;

public class SuiteExampleData extends BaseData {

    TestExampleData testExampleData;

    public TestExampleData getTestExampleData() {
        return testExampleData;
    }

    public void setTestExampleData(TestExampleData testExampleData) {
        this.testExampleData = testExampleData;
    }
}
