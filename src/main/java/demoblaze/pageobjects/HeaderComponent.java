package demoblaze.pageobjects;

import demoblaze.utils.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * HeaderComponent - Manages common header elements shared across pages. Includes navigation, login,
 * signup, and logout functionality. Follows Component design pattern for reusable UI elements.
 */
public class HeaderComponent extends BasePage {

  private By userNameDisplay = By.id("nameofuser");
  private By cartLink = By.id("cartur");
  private By homeLink = By.id("nava");

  public HeaderComponent(WebDriver driver) {
    super(driver);
  }

  /** Clicks on Sign Up link. */
  public void clickSignUp() {
    TestLogger.testStep("Click on Sign Up link");
    click(signupLink);
  }

  /** Clicks on Log In link. */
  public void clickLogin() {
    TestLogger.testStep("Click on Log In link");
    click(loginLink);
  }

  /** Clicks on Logout link. */
  public void clickLogout() {
    TestLogger.testStep("Click on Log Out link");
    click(logoutLink);
  }

  /** Clicks on Cart link. */
  public void clickCart() {
    TestLogger.testStep("Click on Cart link");
    click(cartLink);
  }

  /** Clicks on Home link. */
  public void clickHome() {
    TestLogger.testStep("Click on Home link");
    click(homeLink);
  }

  /** Gets logged-in user name. */
  public String getLoggedInUsername() {
    TestLogger.debug("Getting logged-in username");
    String userDisplay = getText(userNameDisplay);
    return userDisplay;
  }

  /** Verifies if user is logged in by checking username display. */
  public boolean isUserLoggedIn() {
    TestLogger.debug("Verifying if user is logged in");
    try {
      String userDisplay = getText(userNameDisplay);
      return userDisplay != null && !userDisplay.isEmpty() && userDisplay.contains("Welcome");
    } catch (Exception e) {
      return false;
    }
  }

  /** Checks if logout button is visible. */
  public boolean isLogoutVisible() {
    return isElementDisplayed(logoutLink);
  }

  /** Checks if login button is visible. */
  public boolean isLoginVisible() {
    return isElementDisplayed(loginLink);
  }
}
