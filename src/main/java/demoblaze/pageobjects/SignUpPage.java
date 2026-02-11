package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** SignUpPage - Page Object for the Sign Up modal. Handles user registration functionality. */
public class SignUpPage extends BasePage {

  // Locators
  private By signupUsernameInput = By.id("sign-username");
  private By signupPasswordInput = By.id("sign-password");
  private By signupButton = By.xpath("//button[contains(text(), 'Sign up')]");
  private By signupModal = By.id("signInModal");
  private By closeButton =
      By.xpath("//button[@data-dismiss='modal' and ancestor::*[@id='signInModal']]");

  public SignUpPage(WebDriver driver) {
    super(driver);
  }

  /** Waits for Sign Up modal to be visible. */
  public void waitForSignUpModal() {
    TestLogger.debug("Waiting for Sign Up modal to be visible");
    WaitUtils.waitForElementToBeVisible(driver, signupUsernameInput);
  }

  /** Enters username in Sign Up form. */
  public void enterUsername(String username) {
    TestLogger.debug("Entering username: {}", username);
    sendKeys(signupUsernameInput, username);
  }

  /** Enters password in Sign Up form. */
  public void enterPassword(String password) {
    TestLogger.debug("Entering password for Sign Up");
    sendKeys(signupPasswordInput, password);
  }

  /** Clicks Sign Up button. */
  public void clickSignUpButton() {
    TestLogger.testStep("Click Sign Up button");
    click(signupButton);
  }

  /** Signs up a new user with provided credentials. */
  public void signUp(String username, String password) {
    TestLogger.testStep("Sign Up new user: {}", username);
    waitForSignUpModal();
    enterUsername(username);
    enterPassword(password);
    clickSignUpButton();

    // Wait for success alert
    String alertMessage = getAlertText();
    TestLogger.info("Sign Up response: {}", alertMessage);
  }

  /** Closes the Sign Up modal. */
  public void closeSignUpModal() {
    TestLogger.debug("Closing Sign Up modal");
    click(closeButton);
  }
}
