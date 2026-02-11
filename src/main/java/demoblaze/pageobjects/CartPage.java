package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import demoblaze.utils.WaitUtils;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * CartPage - Page Object for the shopping cart. Manages cart items, quantities, and price
 * calculations.
 */
public class CartPage extends BasePage {

  // Locators
  private By cartTable = By.cssSelector("tbody#tbodyid");
  private By cartItems = By.cssSelector("#tbodyid > tr");
  private By productNameInCart = By.xpath("./td[2]");
  private By productPriceInCart = By.xpath("./td[3]");
  private By deleteButton = By.xpath("./td[4]/a");
  private By totalPrice = By.id("totalp");
  private By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");

  public CartPage(WebDriver driver) {
    super(driver);
  }

  /** Waits for cart page to load. */
  public void waitForCartPageToLoad() {
    TestLogger.debug("Waiting for cart page to load");
    WaitUtils.waitForElementToBeVisible(driver, cartTable);
  }

  /** Gets total number of items in cart. */
  public int getCartItemCount() {
    TestLogger.debug("Getting cart item count");
    waitForCartPageToLoad();
    List<WebElement> items = driver.findElements(cartItems);
    return items.size();
  }

  /** Gets all items in cart. */
  public List<WebElement> getCartItems() {
    TestLogger.debug("Getting all cart items");
    return driver.findElements(cartItems);
  }

  /** Gets product name from a cart item. */
  public String getProductNameFromCartItem(WebElement cartItem) {
    return cartItem.findElement(productNameInCart).getText();
  }

  /** Gets product price from a cart item. */
  public double getProductPriceFromCartItem(WebElement cartItem) {
    String priceText = cartItem.findElement(productPriceInCart).getText();
    return Double.parseDouble(priceText);
  }

  /** Gets total price shown in cart. */
  public double getTotalPrice() {
    TestLogger.debug("Getting total price from cart");
    String totalText = getText(totalPrice);
    return Double.parseDouble(totalText);
  }

  /** Verifies if a product is in the cart. */
  public boolean isProductInCart(String productName) {
    TestLogger.debug("Checking if product is in cart: {}", productName);
    List<WebElement> items = getCartItems();

    for (WebElement item : items) {
      String itemName = getProductNameFromCartItem(item);
      if (itemName.equalsIgnoreCase(productName)) {
        TestLogger.info("Product found in cart: {}", productName);
        return true;
      }
    }
    return false;
  }

  /** Deletes a product from cart by product name. */
  public void deleteProductFromCart(String productName) {
    TestLogger.testStep("Delete product from cart: {}", productName);
    List<WebElement> items = getCartItems();

    for (WebElement item : items) {
      String itemName = getProductNameFromCartItem(item);
      if (itemName.equalsIgnoreCase(productName)) {
        item.findElement(deleteButton).click();
        TestLogger.info("Product deleted from cart: {}", productName);
        break;
      }
    }
  }

  /** Clicks Place Order button. */
  public void clickPlaceOrderButton() {
    TestLogger.testStep("Click Place Order button");
    click(placeOrderButton);
  }

  /** Verifies if place order button is visible. */
  public boolean isPlaceOrderButtonVisible() {
    return isElementDisplayed(placeOrderButton);
  }
}
