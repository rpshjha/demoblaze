package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * ProductPage - Page Object for individual product details page. Handles viewing product details
 * and adding to cart.
 */
public class ProductPage extends BasePage {

  // Locators
  private By productTitle = By.tagName("h2");
  private By productPrice = By.xpath("//h3[@class='price-container']");
  private By productDescription = By.xpath("//div[@class='product-details']//p");
  private By addToCartButton = By.cssSelector("a.btn-success");

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  /** Waits for product page to load. */
  public void waitForProductPageToLoad() {
    TestLogger.debug("Waiting for product page to load");
    WaitUtils.waitForElementToBeVisible(driver, productTitle);
  }

  /** Gets product title. */
  public String getProductTitle() {
    TestLogger.debug("Getting product title");
    return getText(productTitle);
  }

  /** Gets product price. */
  public String getProductPrice() {
    TestLogger.debug("Getting product price");
    String fullText = getText(productPrice);
    // Extract price from text like "$360\n*includes tax"
    // Split and get first part, then trim
    String priceOnly = fullText.split("\\*")[0].trim();
    return priceOnly;
  }

  /** Gets product price as double value. */
  public double getProductPriceAsDouble() {
    String priceText = getProductPrice();
    // Extract numeric value from price text (e.g., "$400" -> 400.0)
    double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    return price;
  }

  /** Gets product description. */
  public String getProductDescription() {
    TestLogger.debug("Getting product description");
    return getText(productDescription);
  }

  /** Clicks Add to Cart button. */
  public void clickAddToCart() {
    TestLogger.testStep("Click Add to Cart button");
    click(addToCartButton);

    // Wait for success alert
    String alertMessage = getAlertText();
    TestLogger.info("Add to cart response: {}", alertMessage);
  }

  /** Adds product to cart (complete action). */
  public void addProductToCart() {
    TestLogger.testStep("Add product to cart");
    waitForProductPageToLoad();
    String productName = getProductTitle();
    String productPrice = getProductPrice();
    TestLogger.info("Adding to cart - Product: {}, Price: {}", productName, productPrice);
    clickAddToCart();
  }
}
