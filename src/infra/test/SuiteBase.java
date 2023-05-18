package infra.test;

import infra.data.DataProcessor;
import infra.general.*;
import infra.reporter.Reporter;
import infra.ui.Browser;
import objects.BaseData;
import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.*;
import org.junit.runners.Parameterized;
import org.junit.runners.model.Statement;

//@RunWith(@Sui)
//@Suite.SuiteClasses({CONTACTTEST.class,})

@RunWith(Parameterized.class)
public abstract class SuiteBase {

    public static Reporter report = Reporter.reporter();
    private String testCase;

    public SuiteBase(String testCase,BaseData data) {
        this.testCase = testCase;
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
            report.openLevel(SuiteBase.this.getClass().getSimpleName() + " [[[" + SuiteBase.this.testCase + "]]]", null);
            before();
        }

        @Override
        protected void succeeded(Description description) {
            if (!report.ifNoErrors()) {
                throw new AutomationException("the test ended, but has errors", null);
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            //if the test failed, the reporter writes an error msg, and counts the errors
            throw new AutomationException("the test has been failed", e);
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


