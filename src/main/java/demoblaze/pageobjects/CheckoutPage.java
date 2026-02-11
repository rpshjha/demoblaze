package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * CheckoutPage - Page Object for the checkout/order placement page. Handles order form filling and
 * purchase completion.
 */
public class CheckoutPage extends BasePage {

  // Locators
  private By nameInput = By.id("name");
  private By countryInput = By.id("country");
  private By cityInput = By.id("city");
  private By cardInput = By.id("card");
  private By monthInput = By.id("month");
  private By yearInput = By.id("year");
  private By purchaseButton = By.xpath("//button[contains(text(), 'Purchase')]");
  private By checkoutModal = By.xpath("//div[@class='modal-content']//h5");
  private By totalAmountDisplay = By.id("totalm");

  public CheckoutPage(WebDriver driver) {
    super(driver);
  }

  /** Waits for checkout page/modal to load. */
  public void waitForCheckoutPageToLoad() {
    TestLogger.debug("Waiting for checkout page to load");
    WaitUtils.waitForElementToBeVisible(driver, nameInput);
  }

  /** Enters name. */
  public void enterName(String name) {
    TestLogger.debug("Entering name: {}", name);
    sendKeys(nameInput, name);
  }

  /** Enters country. */
  public void enterCountry(String country) {
    TestLogger.debug("Entering country: {}", country);
    sendKeys(countryInput, country);
  }

  /** Enters city. */
  public void enterCity(String city) {
    TestLogger.debug("Entering city: {}", city);
    sendKeys(cityInput, city);
  }

  /** Enters credit card number. */
  public void enterCardNumber(String cardNumber) {
    TestLogger.debug("Entering card number");
    sendKeys(cardInput, cardNumber);
  }

  /** Enters expiry month. */
  public void enterMonth(String month) {
    TestLogger.debug("Entering month: {}", month);
    sendKeys(monthInput, month);
  }

  /** Enters expiry year. */
  public void enterYear(String year) {
    TestLogger.debug("Entering year: {}", year);
    sendKeys(yearInput, year);
  }

  /** Gets total amount displayed on checkout page. */
  public String getTotalAmount() {
    TestLogger.debug("Getting total amount");
    return getText(totalAmountDisplay);
  }

  /** Clicks Purchase button. */
  public void clickPurchaseButton() {
    TestLogger.testStep("Click Purchase button");
    click(purchaseButton);
  }

  /** Fills checkout form with order details. */
  public void fillCheckoutForm(
      String name, String country, String city, String cardNumber, String month, String year) {
    TestLogger.testStep("Fill checkout form with order details");
    waitForCheckoutPageToLoad();

    enterName(name);
    enterCountry(country);
    enterCity(city);
    enterCardNumber(cardNumber);
    enterMonth(month);
    enterYear(year);

    TestLogger.info("Checkout form filled successfully");
  }

  /** Completes the purchase. */
  public String completePurchase(
      String name, String country, String city, String cardNumber, String month, String year) {
    TestLogger.testStep("Complete purchase");
    fillCheckoutForm(name, country, city, cardNumber, month, year);
    clickPurchaseButton();

    // Wait for success alert
    String alertMessage = getAlertText();
    TestLogger.info("Purchase response: {}", alertMessage);
    return alertMessage;
  }
}
