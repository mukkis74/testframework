package com.example.tests.regression;

import com.example.core.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.SearchResultsPage;
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
 * Regression tests for the filter functionality on the search results page.
 * These tests verify that the filter functionality works correctly after changes.
 */
@Epic("Regression Tests")
@Feature("Filter Functionality")
public class FilterFunctionalityRegressionTest extends BaseTest {
    
    /**
     * Test that the pagination works correctly.
     */
    @Test(description = "Verify pagination functionality works correctly")
    @Story("Pagination")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the pagination functionality works correctly and displays the next page of results")
    public void testPaginationFunctionality() {
        ExtentReportManager.logInfo("Starting pagination functionality test");
        
        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        
        // Accept cookies if present
        homePage.acceptCookiesIfPresent();
        
        // Search for a term that will return many results
        String searchTerm = "Luxury Watch";
        ExtentReportManager.logInfo("Searching for: " + searchTerm);
        SearchResultsPage searchResultsPage = homePage.searchForWatch(searchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();
        
        // Verify that search results are displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed for term: " + searchTerm);
        
        // Navigate to the next page
        ExtentReportManager.logInfo("Navigating to the next page");
        searchResultsPage.goToNextPage();
        
        // Wait for the next page to load
        searchResultsPage.waitForResultsToLoad();
        
        // Verify that search results are still displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed on the next page");
        
        ExtentReportManager.logPass("Pagination functionality works correctly");
    }
    
    /**
     * Test that multiple searches work correctly.
     */
    @Test(description = "Verify multiple searches functionality works correctly")
    @Story("Multiple Searches")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that multiple consecutive searches work correctly")
    public void testMultipleSearchesFunctionality() {
        ExtentReportManager.logInfo("Starting multiple searches functionality test");
        
        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        
        // Accept cookies if present
        homePage.acceptCookiesIfPresent();
        
        // First search
        String firstSearchTerm = "Rolex";
        ExtentReportManager.logInfo("First search for: " + firstSearchTerm);
        SearchResultsPage searchResultsPage = homePage.searchForWatch(firstSearchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();
        
        // Verify that search results are displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed for term: " + firstSearchTerm);
        
        // Navigate back to home page
        homePage.navigateTo();
        
        // Second search
        String secondSearchTerm = "Omega";
        ExtentReportManager.logInfo("Second search for: " + secondSearchTerm);
        searchResultsPage = homePage.searchForWatch(secondSearchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();
        
        // Verify that search results are displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed for term: " + secondSearchTerm);
        
        ExtentReportManager.logPass("Multiple searches functionality works correctly");
    }
}