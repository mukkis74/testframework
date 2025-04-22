package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base class for all page objects.
 * Provides common functionality for page interactions.
 */
public class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    /**
     * Constructor for BasePage.
     * 
     * @param driver The WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Wait for an element to be visible.
     * 
     * @param element The WebElement to wait for
     * @return The WebElement once it's visible
     */
    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Wait for an element to be clickable.
     * 
     * @param element The WebElement to wait for
     * @return The WebElement once it's clickable
     */
    protected WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Click on an element.
     * 
     * @param element The WebElement to click
     */
    protected void click(WebElement element) {
        waitForElementClickable(element).click();
    }
    
    /**
     * Type text into an element.
     * 
     * @param element The WebElement to type into
     * @param text The text to type
     */
    protected void type(WebElement element, String text) {
        waitForElementVisible(element).clear();
        element.sendKeys(text);
    }
    
    /**
     * Check if an element is displayed.
     * 
     * @param element The WebElement to check
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Scroll to an element.
     * 
     * @param element The WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Wait for page to load completely.
     */
    protected void waitForPageToLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}