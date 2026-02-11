package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** LoginPage - Page Object for the Login modal. Handles user login functionality. */
public class LoginPage extends BasePage {

  // Locators
  private By loginUsernameInput = By.id("loginusername");
  private By loginPasswordInput = By.id("loginpassword");
  private By loginButton = By.xpath("//button[contains(text(), 'Log in')]");
  private By loginModal = By.id("logInModal");
  private By closeButton =
      By.xpath("//button[@data-dismiss='modal' and ancestor::*[@id='logInModal']]");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  /** Waits for Login modal to be visible. */
  public void waitForLoginModal() {
    TestLogger.debug("Waiting for Login modal to be visible");
    WaitUtils.waitForElementToBeVisible(driver, loginUsernameInput);
  }

  /** Enters username in Login form. */
  public void enterUsername(String username) {
    TestLogger.debug("Entering username for login: {}", username);
    sendKeys(loginUsernameInput, username);
  }

  /** Enters password in Login form. */
  public void enterPassword(String password) {
    TestLogger.debug("Entering password for login");
    sendKeys(loginPasswordInput, password);
  }

  /** Clicks Login button. */
  public void clickLoginButton() {
    TestLogger.testStep("Click Login button");
    click(loginButton);
  }

  /** Performs login with provided credentials. */
  public void login(String username, String password) {
    TestLogger.testStep("Login with username: {}", username);
    waitForLoginModal();
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();

    // Wait for login to complete
    WaitUtils.waitForSeconds(2);
    TestLogger.info("Login completed");
  }

  /** Closes the Login modal. */
  public void closeLoginModal() {
    TestLogger.debug("Closing Login modal");
    click(closeButton);
  }
}
