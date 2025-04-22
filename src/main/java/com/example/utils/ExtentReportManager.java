package com.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Utility class for managing ExtentReports.
 */
public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initialize the ExtentReports instance.
     */
    public static void initReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/reports/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }

    /**
     * Create a new test in the report.
     * 
     * @param testName The name of the test
     */
    public static void createTest(String testName) {
        if (extent == null) {
            initReports();
        }
        test.set(extent.createTest(testName));
    }

    /**
     * Log an info message.
     * 
     * @param message The message to log
     */
    public static void logInfo(String message) {
        if (test.get() != null) {
            test.get().log(Status.INFO, message);
        }
    }

    /**
     * Log a pass message.
     * 
     * @param message The message to log
     */
    public static void logPass(String message) {
        if (test.get() != null) {
            test.get().log(Status.PASS, message);
        }
    }

    /**
     * Log a fail message.
     * 
     * @param message The message to log
     */
    public static void logFail(String message) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, message);
        }
    }

    /**
     * Log a warning message.
     * 
     * @param message The message to log
     */
    public static void logWarning(String message) {
        if (test.get() != null) {
            test.get().log(Status.WARNING, message);
        }
    }

    /**
     * Flush the report to disk.
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
