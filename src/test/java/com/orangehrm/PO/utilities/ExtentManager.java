package com.orangehrm.PO.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.orangehrm.PO.base.TestBase;

public class ExtentManager {
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * Returns the current test.
     *
     * @return The current test.
     */
    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    /**
     * Sets the current test.
     *
     * @param test The test to set.
     */
    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    /**
     * Logs a message in the extent report.
     * 
     * @param status The status of the log message. Can be {@link Status#PASS},
     *               {@link Status#FAIL}, {@link Status#SKIP}, or
     *               {@link Status#INFO}.
     * @param message The message to log.
     * @throws NullPointerException If the extent test instance is null. Ensure
     *                              that the test has been properly started.
     */
    public static synchronized void log(Status status, String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.log(status, message);
        } else {
            throw new NullPointerException("ExtentTest instance is null. Ensure that the test has been properly started.");
        }
    }

    public static synchronized void logPass(String message) {
        ExtentTest extentTest = getTest();
        if (extentTest != null) {
            extentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(TestBase.captureScreenshot()).build());
        } else {
            throw new NullPointerException("ExtentTest instance is null. Ensure that the test has been properly started.");
        }
    }

    /**
     * Logs a fail message in the extent report. This method will also take a
     * screenshot and attach it to the report.
     * 
     * @param message The message to log.
     */
    public static synchronized void logFail(String message) {
        getTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(TestBase.captureScreenshot()).build());
    }

    /**
     * Logs an info message in the extent report. This method will also take a
     * screenshot and attach it to the report.
     * 
     * @param message The message to log.
     */
    public static synchronized void logInfo(String message) {
        getTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(TestBase.captureScreenshot()).build());
    }

    /**
     * Logs a skip message in the extent report. This method will also take a
     * screenshot and attach it to the report.
     * 
     * @param message The message to log.
     */
    public static synchronized void logSkip(String message) {
        getTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(TestBase.captureScreenshot()).build());
    }
}
