package com.example.tests.performance;

import com.example.core.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.SearchResultsPage;
import com.example.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Advanced performance tests for the Chrono24 website.
 * These tests measure various performance aspects of the website under different conditions.
 */
public class AdvancedPerformanceTest extends BaseTest {

    // Performance thresholds in milliseconds
    private static final long PRODUCT_DETAIL_LOAD_THRESHOLD = 6000;
    private static final long MULTIPLE_SEARCHES_THRESHOLD = 15000;
    private static final long PAGINATION_THRESHOLD = 3000;

    /**
     * Test the performance of loading a product detail page.
     */
    @Test(description = "Verify product detail page load performance")
    public void testProductDetailPagePerformance() {
        ExtentReportManager.logInfo("Starting product detail page load performance test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // Search for a specific watch
        String searchTerm = "Rolex Submariner";
        ExtentReportManager.logInfo("Searching for: " + searchTerm);
        SearchResultsPage searchResultsPage = homePage.searchForWatch(searchTerm);

        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();

        // Record start time before clicking on the first result
        Instant start = Instant.now();

        // Click on the first search result to navigate to the product detail page
        searchResultsPage.clickWatchItem(0);

        // Wait for the product detail page to load
        waitForPageLoad();

        // Record end time
        Instant end = Instant.now();

        // Calculate load time
        long loadTimeMs = Duration.between(start, end).toMillis();
        ExtentReportManager.logInfo("Product detail page load time: " + loadTimeMs + " ms");

        // Verify that the load time is within the acceptable threshold
        Assert.assertTrue(loadTimeMs <= PRODUCT_DETAIL_LOAD_THRESHOLD,
                "Product detail page load time should be less than " + PRODUCT_DETAIL_LOAD_THRESHOLD + " ms");

        ExtentReportManager.logPass("Product detail page load performance is acceptable");
    }

    /**
     * Test the performance of multiple consecutive searches.
     */
    @Test(description = "Verify performance of multiple consecutive searches")
    public void testMultipleSearchesPerformance() {
        ExtentReportManager.logInfo("Starting multiple searches performance test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // List of search terms
        List<String> searchTerms = new ArrayList<>();
        searchTerms.add("Rolex");
        searchTerms.add("Omega");
        searchTerms.add("Patek Philippe");
        searchTerms.add("Audemars Piguet");
        searchTerms.add("Tag Heuer");

        // Record start time
        Instant start = Instant.now();

        // Perform multiple searches
        for (String searchTerm : searchTerms) {
            ExtentReportManager.logInfo("Searching for: " + searchTerm);
            SearchResultsPage searchResultsPage = homePage.searchForWatch(searchTerm);
            searchResultsPage.waitForResultsToLoad();

            // Verify that results are displayed
            Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                    "Search results should be displayed for term: " + searchTerm);

            // Navigate back to home page for next search
            homePage.navigateTo();
        }

        // Record end time
        Instant end = Instant.now();

        // Calculate total time for all searches
        long totalTimeMs = Duration.between(start, end).toMillis();
        ExtentReportManager.logInfo("Total time for " + searchTerms.size() + " searches: " + totalTimeMs + " ms");
        ExtentReportManager.logInfo("Average time per search: " + (totalTimeMs / searchTerms.size()) + " ms");

        // Verify that the total time is within the acceptable threshold
        Assert.assertTrue(totalTimeMs <= MULTIPLE_SEARCHES_THRESHOLD,
                "Total time for multiple searches should be less than " + MULTIPLE_SEARCHES_THRESHOLD + " ms");

        ExtentReportManager.logPass("Multiple searches performance is acceptable");
    }

    /**
     * Test the performance of pagination in search results.
     */
    @Test(description = "Verify pagination performance in search results")
    public void testPaginationPerformance() {
        ExtentReportManager.logInfo("Starting pagination performance test");

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

        // Record start time before navigating to the next page
        Instant start = Instant.now();

        // Navigate to the next page of results
        searchResultsPage.goToNextPage();

        // Wait for the next page to load
        searchResultsPage.waitForResultsToLoad();

        // Record end time
        Instant end = Instant.now();

        // Calculate pagination time
        long paginationTimeMs = Duration.between(start, end).toMillis();
        ExtentReportManager.logInfo("Pagination time: " + paginationTimeMs + " ms");

        // Verify that the pagination time is within the acceptable threshold
        Assert.assertTrue(paginationTimeMs <= PAGINATION_THRESHOLD,
                "Pagination time should be less than " + PAGINATION_THRESHOLD + " ms");

        ExtentReportManager.logPass("Pagination performance is acceptable");
    }

    /**
     * Helper method to wait for page to load completely.
     */
    private void waitForPageLoad() {
        // Wait for the page to load completely
        try {
            TimeUnit.SECONDS.sleep(2); // Basic wait for page load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
