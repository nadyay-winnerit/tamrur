package tests;

import infra.data.DataProcessor;
import objects.suite.SuiteExampleData;
import infra.test.TestBase;
import objects.BaseData;
import objects.tests.TestExampleData;
import org.junit.runners.Parameterized;

import java.util.Collection;

public class TestExample extends TestBase {

    TestExampleData testData;

    public TestExample(String testCase, TestExampleData testData) {
        super(testCase, testData);
        this.testData = testData;
    }

    @Parameterized.Parameters(name = "{index}::{0}")
    public static Collection data() {
        return DataProcessor.createTestData(SuiteExampleData.class);
    }

    @Override
    public void test() {
        System.out.println(testData);
    }
}
