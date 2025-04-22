package com.example.tests.smoke;

import com.example.core.BaseTest;
import com.example.pages.HomePage;
import com.example.utils.ExtentReportManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Smoke tests for the Chrono24 homepage.
 * These tests verify that the basic functionality of the homepage is working correctly.
 */
@Epic("Smoke Tests")
@Feature("Homepage Functionality")
public class HomepageSmokeTest extends BaseTest {
    
    /**
     * Test that the homepage loads correctly.
     */
    @Test(description = "Verify homepage loads correctly")
    @Story("Homepage Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the homepage loads correctly and all elements are displayed")
    public void testHomepageLoads() {
        ExtentReportManager.logInfo("Starting homepage load test");
        
        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        
        // Accept cookies if present
        homePage.acceptCookiesIfPresent();
        
        // Verify that the homepage is loaded
        Assert.assertTrue(homePage.isLoaded(), "Homepage should be loaded correctly");
        
        ExtentReportManager.logPass("Homepage loaded successfully");
    }
    
    /**
     * Test that the search functionality works correctly.
     */
    @Test(description = "Verify search functionality works correctly")
    @Story("Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the search functionality works correctly and returns results")
    public void testSearchFunctionality() {
        ExtentReportManager.logInfo("Starting search functionality test");
        
        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        
        // Accept cookies if present
        homePage.acceptCookiesIfPresent();
        
        // Search for a watch
        String searchTerm = "Rolex";
        ExtentReportManager.logInfo("Searching for: " + searchTerm);
        var searchResultsPage = homePage.searchForWatch(searchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();
        
        // Verify that search results are displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed for term: " + searchTerm);
        
        ExtentReportManager.logPass("Search functionality works correctly");
    }
}