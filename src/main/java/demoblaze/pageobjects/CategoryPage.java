package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * CategoryPage - Page Object for product category pages. Handles product listing and selection from
 * a specific category.
 */
public class CategoryPage extends BasePage {

  // Locators
  private final By categoryTitle = By.cssSelector("div#tbodyid");

  public CategoryPage(WebDriver driver) {
    super(driver);
  }

  /** Waits for category page to load. */
  public void waitForCategoryPageToLoad() {
    TestLogger.debug("Waiting for category page to load");
    WaitUtils.waitForElementToBeVisible(driver, categoryTitle);
  }

  /** Gets product by name and clicks on it. */
  public void selectProductByName(String productName) {
    TestLogger.testStep("Select product: " + productName);

    try {
      By productLocator = By.xpath("//div//a[text()='" + productName + "']");
      click(productLocator);
      TestLogger.info("Product selected: {}", productName);
    } catch (Exception e) {
      TestLogger.error("Product not found: " + productName, e);
      throw e;
    }
  }

  /** Checks if a product is available in the category. */
  public boolean isProductAvailable(String productName) {
    TestLogger.debug("Checking if product is available: {}", productName);
    try {
      By productLocator = By.xpath("//div//a[text()='" + productName + "']");
      return isElementDisplayed(productLocator);
    } catch (Exception e) {
      return false;
    }
  }
}
