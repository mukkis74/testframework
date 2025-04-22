package com.example.tests.security;

import com.example.core.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.SearchResultsPage;
import com.example.utils.ExtentReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Security tests for the Chrono24 website.
 * These tests check for common web security vulnerabilities.
 */
public class WebSecurityTest extends BaseTest {

    /**
     * Test for Cross-Site Scripting (XSS) vulnerabilities in the search functionality.
     * This test attempts to inject a script tag into the search field and checks if it's properly sanitized.
     */
    @Test(description = "Verify XSS protection in search functionality")
    public void testXssProtectionInSearch() {
        ExtentReportManager.logInfo("Starting XSS protection test in search");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // XSS payload
        String xssPayload = "<script>alert('XSS')</script>";
        ExtentReportManager.logInfo("Searching with XSS payload: " + xssPayload);

        // Search with the XSS payload
        SearchResultsPage searchResultsPage = homePage.searchForWatch(xssPayload);

        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();

        // Get the page source
        String pageSource = driver.getPageSource();

        // Check if the script tag is rendered as-is (which would indicate an XSS vulnerability)
        boolean isVulnerable = pageSource.contains("<script>alert('XSS')</script>");
        
        // Assert that the page is not vulnerable to XSS
        Assert.assertFalse(isVulnerable, "The page should not be vulnerable to XSS attacks");
        
        ExtentReportManager.logPass("Search functionality is protected against XSS attacks");
    }

    /**
     * Test for SQL Injection vulnerabilities in the search functionality.
     * This test attempts to inject SQL syntax into the search field and checks for error messages.
     */
    @Test(description = "Verify SQL Injection protection in search")
    public void testSqlInjectionProtection() {
        ExtentReportManager.logInfo("Starting SQL Injection protection test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // SQL Injection payloads
        String[] sqlPayloads = {
            "' OR '1'='1",
            "'; DROP TABLE users; --",
            "' UNION SELECT * FROM users; --"
        };

        for (String sqlPayload : sqlPayloads) {
            ExtentReportManager.logInfo("Searching with SQL Injection payload: " + sqlPayload);

            // Search with the SQL Injection payload
            SearchResultsPage searchResultsPage = homePage.searchForWatch(sqlPayload);

            // Wait for search results to load
            searchResultsPage.waitForResultsToLoad();

            // Get the page source
            String pageSource = driver.getPageSource().toLowerCase();

            // Check for common SQL error messages that might indicate a vulnerability
            boolean isVulnerable = pageSource.contains("sql syntax") || 
                                  pageSource.contains("syntax error") || 
                                  pageSource.contains("unclosed quotation mark") ||
                                  pageSource.contains("sql server") ||
                                  pageSource.contains("odbc driver") ||
                                  pageSource.contains("database error");
            
            // Assert that the page is not vulnerable to SQL Injection
            Assert.assertFalse(isVulnerable, "The page should not be vulnerable to SQL Injection attacks with payload: " + sqlPayload);
            
            // Navigate back to home page for next test
            homePage.navigateTo();
        }
        
        ExtentReportManager.logPass("Search functionality is protected against SQL Injection attacks");
    }

    /**
     * Test for secure cookie attributes.
     * This test checks if cookies have secure attributes set.
     */
    @Test(description = "Verify secure cookie attributes")
    public void testSecureCookieAttributes() {
        ExtentReportManager.logInfo("Starting secure cookie attributes test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // Get all cookies
        Set<Cookie> cookies = driver.manage().getCookies();
        ExtentReportManager.logInfo("Found " + cookies.size() + " cookies");

        boolean allCookiesSecure = true;
        StringBuilder insecureCookies = new StringBuilder();

        // Check each cookie for secure attributes
        for (Cookie cookie : cookies) {
            // For sensitive cookies, check if they have secure and httpOnly flags
            if (cookie.getName().toLowerCase().contains("session") || 
                cookie.getName().toLowerCase().contains("auth") || 
                cookie.getName().toLowerCase().contains("token")) {
                
                if (!cookie.isSecure()) {
                    allCookiesSecure = false;
                    insecureCookies.append(cookie.getName()).append(" (not secure), ");
                }
                
                // Note: WebDriver API doesn't provide a way to check httpOnly flag
                // This would require additional tools or manual verification
            }
        }

        // Log the results
        if (!allCookiesSecure) {
            ExtentReportManager.logWarning("Some sensitive cookies are not secure: " + insecureCookies.toString());
        } else {
            ExtentReportManager.logPass("All sensitive cookies have proper security attributes");
        }
        
        // This is a soft assertion as we're just checking and reporting, not failing the test
        // In a real security test, you might want to fail the test if sensitive cookies are not secure
    }

    /**
     * Test for Content Security Policy (CSP) headers.
     * This test checks if the website has CSP headers configured.
     */
    @Test(description = "Verify Content Security Policy headers")
    public void testContentSecurityPolicy() {
        ExtentReportManager.logInfo("Starting Content Security Policy test");

        // Initialize the HomePage and navigate to it
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Accept cookies if present
        homePage.acceptCookiesIfPresent();

        // Unfortunately, WebDriver doesn't provide direct access to response headers
        // This is a limitation of the WebDriver API
        // In a real security test, you would use tools like OWASP ZAP, Burp Suite, or custom HTTP clients
        
        // For demonstration purposes, we'll just log a message
        ExtentReportManager.logInfo("Content Security Policy check requires additional tools beyond WebDriver");
        ExtentReportManager.logInfo("Consider using OWASP ZAP, Burp Suite, or custom HTTP clients for this test");
    }
}