package demoblaze.utils;

import demoblaze.config.Config;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WaitUtils - Utility class for handling all wait strategies. Encapsulates explicit wait logic and
 * reduces code duplication. Follows DRY principle.
 */
public class WaitUtils {
  private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);

  private WaitUtils() {
    // Private constructor to prevent instantiation
  }

  /** Wait for element to be visible. */
  public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
    logger.debug("Waiting for element to be visible: {}", locator);
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    } catch (Exception e) {
      logger.error("Element not visible within timeout: {}", locator);
      throw e;
    }
  }

  /** Wait for element to be visible. */
  public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
    logger.debug("Waiting for web element to be visible");
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      return wait.until(ExpectedConditions.visibilityOf(element));
    } catch (Exception e) {
      logger.error("Web element not visible within timeout");
      throw e;
    }
  }

  /** Wait for element to be clickable. */
  public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
    logger.debug("Waiting for element to be clickable: {}", locator);
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      return wait.until(ExpectedConditions.elementToBeClickable(locator));
    } catch (Exception e) {
      logger.error("Element not clickable within timeout: {}", locator);
      throw e;
    }
  }

  /** Wait for element to be clickable. */
  public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
    logger.debug("Waiting for web element to be clickable");
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      return wait.until(ExpectedConditions.elementToBeClickable(element));
    } catch (Exception e) {
      logger.error("Web element not clickable within timeout");
      throw e;
    }
  }

  /** Wait for element to be present in DOM. */
  public static WebElement waitForElementToBePresent(WebDriver driver, By locator) {
    logger.debug("Waiting for element to be present: {}", locator);
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    } catch (Exception e) {
      logger.error("Element not present within timeout: {}", locator);
      throw e;
    }
  }

  /** Wait for element to be invisible. */
  public static boolean waitForElementToBeInvisible(WebDriver driver, By locator) {
    logger.debug("Waiting for element to be invisible: {}", locator);
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    } catch (Exception e) {
      logger.error("Element did not become invisible within timeout: {}", locator);
      throw e;
    }
  }

  /** Wait for element to have text. */
  public static boolean waitForElementToHaveText(WebDriver driver, By locator, String text) {
    logger.debug("Waiting for element to have text: {}", text);
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    } catch (Exception e) {
      logger.error("Element did not have expected text within timeout: {}", text);
      throw e;
    }
  }

  /** Wait for alert to be present. */
  public static void waitForAlert(WebDriver driver) {
    logger.debug("Waiting for alert to be present");
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT));
      wait.until(ExpectedConditions.alertIsPresent());
    } catch (Exception e) {
      logger.error("Alert not present within timeout");
      throw e;
    }
  }

  /** General wait with custom duration. */
  public static void waitForSeconds(long seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      logger.error("Thread sleep interrupted: ", e);
      Thread.currentThread().interrupt();
    }
  }
}
