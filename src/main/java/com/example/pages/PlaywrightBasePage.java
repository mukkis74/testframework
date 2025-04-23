package com.example.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Base class for all Playwright-based page objects.
 * Provides common functionality for page interactions.
 */
public class PlaywrightBasePage {

    protected Page page;

    /**
     * Constructor for PlaywrightBasePage.
     * 
     * @param page The Playwright Page instance
     */
    public PlaywrightBasePage(Page page) {
        this.page = page;
    }

    /**
     * Wait for an element to be visible.
     * 
     * @param selector The CSS selector for the element
     * @return The Locator for the element
     */
    protected Locator waitForElementVisible(String selector) {
        Locator locator = page.locator(selector).first();
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return locator;
    }

    /**
     * Click on an element.
     * 
     * @param selector The CSS selector for the element
     */
    protected void click(String selector) {
        page.locator(selector).first().click();
    }

    /**
     * Type text into an element.
     * 
     * @param selector The CSS selector for the element
     * @param text The text to type
     */
    protected void type(String selector, String text) {
        Locator element = page.locator(selector).first();
        element.clear();
        element.fill(text);
    }

    /**
     * Check if an element is displayed.
     * 
     * @param selector The CSS selector for the element
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(String selector) {
        try {
            return page.locator(selector).first().isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Scroll to an element.
     * 
     * @param selector The CSS selector for the element
     */
    protected void scrollToElement(String selector) {
        page.locator(selector).first().scrollIntoViewIfNeeded();
    }

    /**
     * Wait for page to load completely.
     */
    protected void waitForPageToLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
