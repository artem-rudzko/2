package helperClass;


import core.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class Listener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        TestBase.logger.info("Test class started: " +iTestResult.getTestClass().getName());
        TestBase.logger.info("Test started: " + iTestResult.getName());

    }

    public void onTestSuccess(ITestResult iTestResult) {
        TestBase.logger.info("Test SUCCESS: " + iTestResult.getName());

        Object[] methodParameters;
        methodParameters =  iTestResult.getParameters();
        String emailSender = methodParameters[0].toString();
        String emailRecipient = methodParameters[2].toString();
        TestBase.logger.warn("Sending email from; " + emailSender + " to " + emailRecipient + " Passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        Object[] methodParameters;
        methodParameters =  iTestResult.getParameters();
        String emailSender = methodParameters[0].toString();
        String emailRecipient = methodParameters[2].toString();

        Utils.makeScreenshot("failure screenshot");
        try {
            Utils.shoot("failure screenshot");
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestBase.logger.error("Test FAILED: " + iTestResult.getName());
        if (iTestResult.getThrowable()!=null) {
            iTestResult.getThrowable().printStackTrace();
        }
        TestBase.logger.warn("Sending email from; " + emailSender + " to " + emailRecipient + "Failed"+
                iTestResult.getThrowable().getStackTrace());
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
