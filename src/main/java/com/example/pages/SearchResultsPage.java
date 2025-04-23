package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the Chrono24 search results page.
 */
public class SearchResultsPage extends BasePage {

    // Web elements on the search results page
    @FindBy(css = ".article-item")
    private List<WebElement> watchItems;

    @FindBy(css = ".loading-indicator")
    private WebElement loadingIndicator;

    @FindBy(css = ".pagination-next")
    private WebElement nextPageButton;

    /**
     * Constructor for SearchResultsPage.
     * 
     * @param driver The WebDriver instance
     */
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad();
    }

    /**
     * Wait for search results to load.
     */
    public void waitForResultsToLoad() {
        try {
            // Wait for loading indicator to disappear
            wait.until(driver -> !isElementDisplayed(loadingIndicator));
            // Wait for results to be fully loaded
            wait.until(driver -> !watchItems.isEmpty());
        } catch (Exception e) {
            // Loading indicator might not be present, so ignore any exceptions
        }
    }

    /**
     * Click on a watch item.
     * 
     * @param index The index of the watch item to click
     */
    public void clickWatchItem(int index) {
        if (watchItems.size() > index) {
            scrollToElement(watchItems.get(index));
            click(watchItems.get(index));
        } else {
            throw new IndexOutOfBoundsException("Watch item index out of bounds: " + index);
        }
    }

    /**
     * Check if search results are displayed.
     * 
     * @return true if search results are displayed, false otherwise
     */
    public boolean areResultsDisplayed() {
        return !watchItems.isEmpty();
    }

    /**
     * Go to the next page of search results.
     */
    public void goToNextPage() {
        if (isElementDisplayed(nextPageButton)) {
            scrollToElement(nextPageButton);
            click(nextPageButton);
        } else {
            throw new IllegalStateException("Next page button is not displayed");
        }
    }
}
