package demoblaze.utils;

import demoblaze.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BrowserFactory - Responsible for creating and managing WebDriver instances. Follows Factory
 * design pattern and Single Responsibility principle. Supports Chrome, Firefox, and Edge browsers
 * with headless mode option.
 */
public class BrowserFactory {
  private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);

  private BrowserFactory() {
    // Private constructor to prevent instantiation
  }

  /**
   * Creates a WebDriver instance based on browser type from configuration.
   *
   * @return WebDriver instance
   */
  public static WebDriver createDriver() {
    String browser = Config.BROWSER;
    boolean headless = Config.HEADLESS;

    logger.info("Initializing {} browser (Headless: {})", browser, headless);

    WebDriver driver;

    switch (browser) {
      case "firefox":
        driver = createFirefoxDriver(headless);
        break;
      case "edge":
        driver = createEdgeDriver(headless);
        break;
      case "chrome":
      default:
        driver = createChromeDriver(headless);
        break;
    }

    configureDriver(driver);
    logger.info("WebDriver initialized successfully");
    return driver;
  }

  /** Creates Chrome WebDriver with options. */
  private static WebDriver createChromeDriver(boolean headless) {
    ChromeOptions options = new ChromeOptions();

    if (headless) {
      options.addArguments("--headless");
    }

    options.addArguments(
        "--start-maximized",
        "--disable-blink-features=AutomationControlled",
        "--disable-extensions",
        "--disable-gpu");

    return new ChromeDriver(options);
  }

  /** Creates Firefox WebDriver with options. */
  private static WebDriver createFirefoxDriver(boolean headless) {
    FirefoxOptions options = new FirefoxOptions();

    if (headless) {
      options.addArguments("--headless");
    }

    options.addArguments("--width=1920", "--height=1080");

    return new FirefoxDriver(options);
  }

  /** Creates Edge WebDriver with options. */
  private static WebDriver createEdgeDriver(boolean headless) {
    EdgeOptions options = new EdgeOptions();

    if (headless) {
      options.addArguments("--headless");
    }

    options.addArguments("--start-maximized");

    return new EdgeDriver(options);
  }

  /** Configures driver timeouts and settings. */
  private static void configureDriver(WebDriver driver) {
    driver
        .manage()
        .timeouts()
        .implicitlyWait(java.time.Duration.ofSeconds(Config.IMPLICIT_WAIT))
        .pageLoadTimeout(java.time.Duration.ofSeconds(Config.PAGE_LOAD_TIMEOUT));
  }

  /** Safely quits the WebDriver instance. */
  public static void quitDriver(WebDriver driver) {
    if (driver != null) {
      try {
        driver.quit();
        logger.info("WebDriver closed successfully");
      } catch (Exception e) {
        logger.error("Error closing WebDriver: ", e);
      }
    }
  }
}
