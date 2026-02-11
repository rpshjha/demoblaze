package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * BasePage - Parent class for all page objects. Contains common locators and methods used across
 * all pages. Follows DRY principle by centralizing common functionality.
 */
public class BasePage {
  protected WebDriver driver;

  // Common locators
  protected By homeLink = By.linkText("Home");
  protected By cartLink = By.id("cartur");
  protected By logoutLink = By.id("logout2");
  protected By loginLink = By.id("login2");
  protected By signupLink = By.id("signin2");
  protected By navBar = By.className("navbar");

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  /** Clicks on an element after waiting for it to be clickable. */
  protected void click(By locator) {
    TestLogger.debug("Clicking on element: {}", locator);
    WaitUtils.waitForElementToBeClickable(driver, locator).click();
  }

  /** Clicks on a WebElement after waiting for it to be clickable. */
  protected void click(WebElement element) {
    TestLogger.debug("Clicking on web element");
    WaitUtils.waitForElementToBeClickable(driver, element).click();
  }

  /** Types text into an element after waiting for visibility. */
  protected void sendKeys(By locator, String text) {
    TestLogger.debug("Typing text into element: {}", locator);
    WaitUtils.waitForElementToBeVisible(driver, locator).sendKeys(text);
  }

  /** Clears and types text into an element. */
  protected void clearAndSendKeys(By locator, String text) {
    WebElement element = WaitUtils.waitForElementToBeVisible(driver, locator);
    element.clear();
    element.sendKeys(text);
  }

  /** Gets text from an element. */
  protected String getText(By locator) {
    return WaitUtils.waitForElementToBeVisible(driver, locator).getText();
  }

  /** Gets text from a WebElement. */
  protected String getText(WebElement element) {
    return WaitUtils.waitForElementToBeVisible(driver, element).getText();
  }

  /** Gets attribute value from an element. */
  protected String getAttribute(By locator, String attributeName) {
    return WaitUtils.waitForElementToBeVisible(driver, locator).getAttribute(attributeName);
  }

  /** Checks if element is displayed. */
  protected boolean isElementDisplayed(By locator) {
    try {
      return WaitUtils.waitForElementToBeVisible(driver, locator).isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /** Waits for element to be visible. */
  protected WebElement waitForElement(By locator) {
    return WaitUtils.waitForElementToBeVisible(driver, locator);
  }

  /** Switches to alert and gets text. */
  protected String getAlertText() {
    WaitUtils.waitForAlert(driver);
    String alertText = driver.switchTo().alert().getText();
    driver.switchTo().alert().accept();
    return alertText;
  }

  /** Navigates to URL. */
  protected void navigateTo(String url) {
    driver.navigate().to(url);
  }

  /** Gets current page title. */
  protected String getPageTitle() {
    return driver.getTitle();
  }

  /** Gets current page URL. */
  protected String getCurrentUrl() {
    return driver.getCurrentUrl();
  }
}
