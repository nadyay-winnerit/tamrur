package infra.test;

import infra.Browser;
import infra.Utils;
import infra.data.DataProcessor;
import infra.reporter.Reporter;
import objects.BaseData;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.model.Statement;

//@RunWith(@Sui)
//@Suite.SuiteClasses({CONTACTTEST.class,})

@RunWith(Parameterized.class)
public abstract class SuiteBase {

    public static Reporter report = Reporter.reporter();

    public SuiteBase(String testCase,BaseData data) {
    }

    @Test
    public abstract void suite();

    protected void runTest(Class<? extends TestBase> testClass, String testId) {
        if (!Utils.isNullOrEmpty(testId)) {
            DataProcessor.currentTestId = testId;
            JUnitCore.runClasses(testClass);

        }
    }

    protected void runTest(Class<? extends TestBase> testClass, BaseData dataId) {
        if (dataId != null) {
            DataProcessor.currentTestData = dataId;
            JUnitCore.runClasses(testClass);

        }
    }

    @Rule
    public TestRule junitRule = new TestWatcher() {
        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() {
                    try {
                        starting(description);
                        base.evaluate();  //לולאה?
                        succeeded(description);
                    } catch (Throwable t) {
                        failed(t, description);
                    } finally {
                        finished(description);
                    }
                }
            };
        }


        @Override
        protected void starting(Description description) {
            report.resetErrorCounter();
            report.openLevel("the test" + description + " started", null);
            before();
        }

        @Override
        protected void succeeded(Description description) {
            if (!report.ifNoErrors()) {
                report.error("the test has been failed", null);
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            //if the test failed, the reporter writes an error msg, and counts the errors
            Assert.fail("the test failed");
        }

        @Override
        protected void finished(Description description) {
            after();
            Browser.close();
            report.closeLevel();
        }
    };

    public void before() {
    }

    public void after() {
    }

}


