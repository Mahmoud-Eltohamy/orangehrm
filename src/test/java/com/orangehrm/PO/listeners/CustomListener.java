package com.orangehrm.PO.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrm.PO.utilities.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;

public class CustomListener implements ITestListener {
    private static ExtentReports extent;

    /**
     * This method is called when any test starts. Here we are configuring the Extent Report.
     * We are creating two reports, one for all the test cases and one for failed test cases.
     * The test cases are categorised based on the test status.
     * @param context - Provides the test context.
     */
    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\target\\extent-report.html");

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Report Name");
        sparkReporter.config().setDocumentTitle("Document Title");

        ExtentSparkReporter failedReport = new ExtentSparkReporter(System.getProperty("user.dir") + "\\target\\failed-report.html");
        failedReport.filter().statusFilter().as(new Status[]{Status.FAIL});
        failedReport.config().setTheme(Theme.DARK);
        failedReport.config().setReportName("Failed Cases");
        failedReport.config().setDocumentTitle("Failed test cases");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter, failedReport);
    }

    /**
     * This method is called when each test case starts. Here we are creating a test node in the extent report
     * and setting the test name as the name of the test case.
     * @param result - Provides the test result.
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(extentTest);
    }

    /**
     * This method is called when a test case is passed. The test case name is added to the extent report as a pass.
     * @param result - Provides the test result.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentManager.getTest().pass("Test case ---->" + methodName + " is passed");
    }

    /**
     * This method is called when a test case fails. The test case name is added to the extent report as a fail.
     * The error message is also added to the report.
     * @param result - Provides the test result.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentManager.getTest().fail("Test case ---> " + methodName + "\nError---> " + result.getThrowable());
    }

    /**
     * This method is called when a test case is skipped. The test case name is added to the extent report as a skip.
     * The error message is also added to the report.
     * @param result - Provides the test result.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentManager.getTest().skip("Test case ---> " + methodName + " got skipped" + result.getThrowable());
    }

    /**
     * This method is called when all the test cases in a test suite has finished execution.
     * This method is used to flush the extent report, so that all the test results are written to the report.
     * @param context - Provides the test context.
     */
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
