package com.example.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Page object for the Chrono24 search results page using Playwright.
 */
public class PlaywrightSearchResultsPage extends PlaywrightBasePage {

    // CSS selectors for elements on the search results page
    private static final String WATCH_ITEMS_SELECTOR = ".article-item";
    private static final String LOADING_INDICATOR_SELECTOR = ".loading-indicator";
    private static final String NEXT_PAGE_BUTTON_SELECTOR = ".pagination-next";

    /**
     * Constructor for PlaywrightSearchResultsPage.
     * 
     * @param page The Playwright Page instance
     */
    public PlaywrightSearchResultsPage(Page page) {
        super(page);
        waitForPageToLoad();
    }

    /**
     * Wait for search results to load.
     */
    public void waitForResultsToLoad() {
        try {
            // Wait for loading indicator to disappear
            page.waitForSelector(LOADING_INDICATOR_SELECTOR, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
            // Wait a bit more to ensure results are fully loaded
            page.waitForTimeout(1000);
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
        Locator watchItems = page.locator(WATCH_ITEMS_SELECTOR);
        int count = watchItems.count();
        if (count > index) {
            scrollToElement(WATCH_ITEMS_SELECTOR + ":nth-child(" + (index + 1) + ")");
            watchItems.nth(index).click();
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
        return page.locator(WATCH_ITEMS_SELECTOR).count() > 0;
    }

    /**
     * Go to the next page of search results.
     */
    public void goToNextPage() {
        if (isElementDisplayed(NEXT_PAGE_BUTTON_SELECTOR)) {
            scrollToElement(NEXT_PAGE_BUTTON_SELECTOR);
            click(NEXT_PAGE_BUTTON_SELECTOR);
        } else {
            throw new IllegalStateException("Next page button is not displayed");
        }
    }
}
