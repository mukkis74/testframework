package com.example.tests.unit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.example.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Unit tests for the ExtentReportManager class.
 */
public class ExtentReportManagerTest {
    
    private ExtentReports originalExtent;
    private ThreadLocal<ExtentTest> originalTest;
    
    @BeforeMethod
    public void setUp() throws Exception {
        // Save original static fields
        originalExtent = getExtentReportsInstance();
        originalTest = getExtentTestThreadLocal();
        
        // Reset static fields for testing
        setExtentReportsInstance(null);
        setExtentTestThreadLocal(new ThreadLocal<>());
    }
    
    @AfterMethod
    public void tearDown() throws Exception {
        // Restore original static fields
        setExtentReportsInstance(originalExtent);
        setExtentTestThreadLocal(originalTest);
    }
    
    @Test(description = "Test initialization of reports")
    public void testInitReports() {
        // Act
        ExtentReportManager.initReports();
        
        // Assert
        try {
            ExtentReports extent = getExtentReportsInstance();
            Assert.assertNotNull(extent, "ExtentReports instance should be initialized");
            
            // Verify report file path
            File reportFile = new File("test-output/reports/extent-report.html");
            Assert.assertTrue(reportFile.getParentFile().exists() || reportFile.getParentFile().mkdirs(), 
                    "Report directory should exist or be created");
        } catch (Exception e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test(description = "Test creation of a test in the report")
    public void testCreateTest() {
        // Arrange
        String testName = "Sample Test";
        
        // Act
        ExtentReportManager.createTest(testName);
        
        // Assert
        try {
            ThreadLocal<ExtentTest> testThreadLocal = getExtentTestThreadLocal();
            Assert.assertNotNull(testThreadLocal.get(), "ExtentTest should be created and stored in ThreadLocal");
        } catch (Exception e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test(description = "Test logging an info message")
    public void testLogInfo() throws Exception {
        // Arrange
        ExtentReportManager.initReports();
        ExtentReportManager.createTest("Test Log Info");
        String message = "This is an info message";
        
        // Act
        ExtentReportManager.logInfo(message);
        
        // No direct way to assert the log was added, but we can verify no exceptions are thrown
    }
    
    @Test(description = "Test logging a pass message")
    public void testLogPass() throws Exception {
        // Arrange
        ExtentReportManager.initReports();
        ExtentReportManager.createTest("Test Log Pass");
        String message = "This is a pass message";
        
        // Act
        ExtentReportManager.logPass(message);
        
        // No direct way to assert the log was added, but we can verify no exceptions are thrown
    }
    
    @Test(description = "Test logging a fail message")
    public void testLogFail() throws Exception {
        // Arrange
        ExtentReportManager.initReports();
        ExtentReportManager.createTest("Test Log Fail");
        String message = "This is a fail message";
        
        // Act
        ExtentReportManager.logFail(message);
        
        // No direct way to assert the log was added, but we can verify no exceptions are thrown
    }
    
    @Test(description = "Test logging a warning message")
    public void testLogWarning() throws Exception {
        // Arrange
        ExtentReportManager.initReports();
        ExtentReportManager.createTest("Test Log Warning");
        String message = "This is a warning message";
        
        // Act
        ExtentReportManager.logWarning(message);
        
        // No direct way to assert the log was added, but we can verify no exceptions are thrown
    }
    
    @Test(description = "Test flushing reports")
    public void testFlushReports() {
        // Arrange
        ExtentReportManager.initReports();
        
        // Act
        ExtentReportManager.flushReports();
        
        // No direct way to assert the flush occurred, but we can verify no exceptions are thrown
    }
    
    // Helper methods to access and modify private static fields using reflection
    
    private ExtentReports getExtentReportsInstance() throws Exception {
        Field field = ExtentReportManager.class.getDeclaredField("extent");
        field.setAccessible(true);
        return (ExtentReports) field.get(null);
    }
    
    private void setExtentReportsInstance(ExtentReports extent) throws Exception {
        Field field = ExtentReportManager.class.getDeclaredField("extent");
        field.setAccessible(true);
        field.set(null, extent);
    }
    
    @SuppressWarnings("unchecked")
    private ThreadLocal<ExtentTest> getExtentTestThreadLocal() throws Exception {
        Field field = ExtentReportManager.class.getDeclaredField("test");
        field.setAccessible(true);
        return (ThreadLocal<ExtentTest>) field.get(null);
    }
    
    private void setExtentTestThreadLocal(ThreadLocal<ExtentTest> testThreadLocal) throws Exception {
        Field field = ExtentReportManager.class.getDeclaredField("test");
        field.setAccessible(true);
        field.set(null, testThreadLocal);
    }
}