package demoblaze.pageobjects;

import demoblaze.config.Config;
import demoblaze.utils.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * HomePage - Page Object for the home page of DemoBlaze. Manages navigation to different product
 * categories.
 */
public class HomePage extends BasePage {

  // Locators for product categories
  private By phonesCategoryLink = By.linkText("Phones");
  private By laptopsCategoryLink = By.linkText("Laptops");
  private By monitorsCategoryLink = By.linkText("Monitors");
  private By homeLink = By.xpath("//a[@href='index.html']");
  private By pageTitle = By.cssSelector("h1");
  private By productsContainer = By.cssSelector("div#tbodyid");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  /** Navigates to the home page. */
  public void navigateToHomePage() {
    TestLogger.testStep("Navigate to DemoBlaze home page");
    navigateTo(Config.BASE_URL);
    TestLogger.info("Navigated to: {}", Config.BASE_URL);
  }

  /** Verifies if home page is loaded. */
  public boolean isHomePageLoaded() {
    TestLogger.debug("Verifying home page is loaded");
    return isElementDisplayed(phonesCategoryLink);
  }

  /** Clicks on Phones category. */
  public void selectPhonesCategory() {
    TestLogger.testStep("Select Phones category");
    click(phonesCategoryLink);
  }

  /** Clicks on Laptops category. */
  public void selectLaptopsCategory() {
    TestLogger.testStep("Select Laptops category");
    click(laptopsCategoryLink);
  }

  /** Clicks on Monitors category. */
  public void selectMonitorsCategory() {
    TestLogger.testStep("Select Monitors category");
    click(monitorsCategoryLink);
  }

  /** Navigates to a specific category by name. */
  public void navigateToCategory(String categoryName) {
    TestLogger.testStep("Navigate to category: {}", categoryName);
    switch (categoryName.toLowerCase()) {
      case "phones":
        selectPhonesCategory();
        break;
      case "laptops":
        selectLaptopsCategory();
        break;
      case "monitors":
        selectMonitorsCategory();
        break;
      default:
        throw new IllegalArgumentException("Unknown category: " + categoryName);
    }
  }

  /** Selects a product by name from the home page product grid. */
  public void selectProductByName(String productName) {
    TestLogger.testStep("Select product: {}", productName);
    By productLink = By.xpath("//div//a[text()='" + productName + "']");
    try {
      click(productLink);
      TestLogger.info("Product selected: {}", productName);
    } catch (Exception e) {
      TestLogger.error("Product not found: {}", e);
      throw e;
    }
  }

  /** Navigates back to home page. */
  public void navigateToHome() {
    TestLogger.testStep("Navigate to Home");
    click(homeLink);
    TestLogger.info("Navigated to Home");
  }

  /** Checks if product is available in the product grid. */
  public boolean isProductAvailable(String productName) {
    TestLogger.debug("Checking if product is available: {}", productName);
    try {
      By productLink = By.xpath("//div//a[text()='" + productName + "']");
      return isElementDisplayed(productLink);
    } catch (Exception e) {
      return false;
    }
  }
}
