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

  // Constants
  private static final String MESSAGE_DELIMITER = "<br>";

  // Locators - Place Order Modal
  private By nameInput = By.id("name");
  private By countryInput = By.id("country");
  private By cityInput = By.id("city");
  private By cardInput = By.id("card");
  private By monthInput = By.id("month");
  private By yearInput = By.id("year");
  private By purchaseButton = By.xpath("//button[onclick='purchaseOrder()']");
  private By totalAmountDisplay = By.id("totalm");

  // Locators - Purchase Confirmation (SweetAlert)
  private By confirmationAlert = By.xpath("//div[@class='sweet-alert showSweetAlert visible']");
  private By confirmationTitle = By.xpath("//div[@class='sweet-alert showSweetAlert visible']//h2");
  private By confirmationMessage =
      By.xpath("//div[@class='sweet-alert showSweetAlert visible']//p[@class='lead text-muted']");
  private By confirmButton =
      By.xpath(
          "//div[@class='sweet-alert showSweetAlert visible']//button[@class='confirm btn btn-lg btn-primary']");
  private By successIcon =
      By.xpath(
          "//div[@class='sweet-alert showSweetAlert visible']//div[@class='sa-icon sa-success animate']");

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

  /** Waits for purchase confirmation alert to appear. */
  public void waitForConfirmationAlert() {
    TestLogger.debug("Waiting for confirmation alert to appear");
    WaitUtils.waitForElementToBeVisible(driver, confirmationAlert);
  }

  /** Gets confirmation message text from SweetAlert. */
  public String getConfirmationMessage() {
    TestLogger.debug("Getting confirmation message");
    waitForConfirmationAlert();
    return getText(confirmationMessage);
  }

  /** Gets confirmation title from SweetAlert. */
  public String getConfirmationTitle() {
    TestLogger.debug("Getting confirmation title");
    return getText(confirmationTitle);
  }

  /** Checks if success icon is displayed. */
  public boolean isSuccessIconDisplayed() {
    TestLogger.debug("Checking if success icon is displayed");
    return isElementDisplayed(successIcon);
  }

  /** Clicks OK button on confirmation alert. */
  public void clickConfirmButton() {
    TestLogger.testStep("Click OK button on confirmation alert");
    click(confirmButton);
  }

  /** Extracts purchase ID from confirmation message. */
  public String extractPurchaseId() {
    TestLogger.debug("Extracting purchase ID from confirmation message");
    String message = getConfirmationMessage();
    // Message format: Id: 4591690<br>Amount: 1900 USD<br>...
    String[] lines = message.split(MESSAGE_DELIMITER);
    for (String line : lines) {
      if (line.contains("Id:")) {
        return line.replaceAll(".*Id:\\s*", "").trim();
      }
    }
    return null;
  }

  /** Extracts amount from confirmation message. */
  public String extractAmount() {
    TestLogger.debug("Extracting amount from confirmation message");
    String message = getConfirmationMessage();
    String[] lines = message.split(MESSAGE_DELIMITER);
    for (String line : lines) {
      if (line.contains("Amount:")) {
        return line.replaceAll(".*Amount:\\s*", "").trim();
      }
    }
    return null;
  }

  /** Extracts card number from confirmation message. */
  public String extractCardNumber() {
    TestLogger.debug("Extracting card number from confirmation message");
    String message = getConfirmationMessage();
    String[] lines = message.split(MESSAGE_DELIMITER);
    for (String line : lines) {
      if (line.contains("Card Number:")) {
        return line.replaceAll(".*Card Number:\\s*", "").trim();
      }
    }
    return null;
  }

  /** Extracts name from confirmation message. */
  public String extractName() {
    TestLogger.debug("Extracting name from confirmation message");
    String message = getConfirmationMessage();
    String[] lines = message.split(MESSAGE_DELIMITER);
    for (String line : lines) {
      if (line.contains("Name:")) {
        return line.replaceAll(".*Name:\\s*", "").trim();
      }
    }
    return null;
  }

  /** Extracts date from confirmation message. */
  public String extractDate() {
    TestLogger.debug("Extracting date from confirmation message");
    String message = getConfirmationMessage();
    String[] lines = message.split(MESSAGE_DELIMITER);
    for (String line : lines) {
      if (line.contains("Date:")) {
        return line.replaceAll(".*Date:\\s*", "").trim();
      }
    }
    return null;
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

  /** Completes the purchase and returns confirmation message. */
  public String completePurchase(
      String name, String country, String city, String cardNumber, String month, String year) {
    TestLogger.testStep("Complete purchase");
    fillCheckoutForm(name, country, city, cardNumber, month, year);
    clickPurchaseButton();

    // Wait for success alert and verify
    waitForConfirmationAlert();
    if (!isSuccessIconDisplayed()) {
      TestLogger.warn("Success icon not displayed - order may have failed");
    }

    String confirmationTitle = getConfirmationTitle();
    String confirmationMessage = getConfirmationMessage();
    TestLogger.info(
        "Purchase confirmation - Title: {}, Message: {}", confirmationTitle, confirmationMessage);

    return confirmationMessage;
  }

  /** Completes purchase and verifies confirmation details match input. */
  public boolean completePurchaseAndVerify(
      String name, String country, String city, String cardNumber, String month, String year) {
    TestLogger.testStep("Complete purchase and verify confirmation details");
    completePurchase(name, country, city, cardNumber, month, year);

    // Verify confirmation details
    boolean nameMatches = name.equals(extractName());
    boolean cardMatches = cardNumber.equals(extractCardNumber());

    TestLogger.info(
        "Purchase verification - Name matches: {}, Card matches: {}", nameMatches, cardMatches);

    return nameMatches && cardMatches;
  }
}
