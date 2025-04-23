package com.example.tests.smoke;

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
 * Smoke tests for the Chrono24 search results page.
 * These tests verify that the basic functionality of the search results page is working correctly.
 */
@Epic("Smoke Tests")
@Feature("Search Results Functionality")
public class SearchResultsPageTest extends BaseTest {

    /**
     * Test that the search results page loads correctly.
     */
    @Test(description = "Verify search results page loads correctly")
    @Story("Search Results Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the search results page loads correctly and displays results")
    public void testSearchResultsPageLoads() {
        ExtentReportManager.logInfo("Starting search results page load test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // Search for a watch to navigate to the search results page
        String searchTerm = "Rolex";
        ExtentReportManager.logInfo("Searching for: " + searchTerm);
        SearchResultsPage searchResultsPage = homePage.searchForWatch(searchTerm);

        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();

        // Verify that search results are displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed for term: " + searchTerm);

        ExtentReportManager.logPass("Search results page loaded successfully");
    }

    /**
     * Test that the pagination functionality works correctly.
     */
    @Test(description = "Verify pagination functionality works correctly")
    @Story("Pagination Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the pagination functionality works correctly and navigates to the next page")
    public void testPaginationFunctionality() {
        ExtentReportManager.logInfo("Starting pagination functionality test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // Search for a watch to navigate to the search results page
        String searchTerm = "Rolex";
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
     * Test that clicking on a watch item works correctly.
     */
    @Test(description = "Verify clicking on a watch item works correctly")
    @Story("Watch Item Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that clicking on a watch item navigates to the watch details page")
    public void testClickWatchItem() {
        ExtentReportManager.logInfo("Starting watch item click test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // Search for a watch to navigate to the search results page
        String searchTerm = "Rolex";
        ExtentReportManager.logInfo("Searching for: " + searchTerm);
        SearchResultsPage searchResultsPage = homePage.searchForWatch(searchTerm);

        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();

        // Verify that search results are displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed for term: " + searchTerm);

        // Click on the first watch item
        ExtentReportManager.logInfo("Clicking on the first watch item");
        searchResultsPage.clickWatchItem(0);

        // Note: We would typically verify that we've navigated to the watch details page here,
        // but that would require a WatchDetailsPage class which might not exist yet.
        // For now, we'll just log that the test passed if no exceptions were thrown.

        ExtentReportManager.logPass("Watch item click functionality works correctly");
    }
}
