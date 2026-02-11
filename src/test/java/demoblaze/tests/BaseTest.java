package demoblaze.tests;

import demoblaze.pageobjects.*;
import demoblaze.utils.BrowserFactory;
import demoblaze.utils.TestLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * BaseTest - Base class for all test classes. Handles common setup and teardown logic. Follows DRY
 * principle and Single Responsibility. Implements Dependency Injection pattern for page objects.
 */
public class BaseTest {
  protected WebDriver driver;

  // Page Objects
  protected HomePage homePage;
  protected SignUpPage signupPage;
  protected LoginPage loginPage;
  protected CategoryPage categoryPage;
  protected ProductPage productPage;
  protected CartPage cartPage;
  protected CheckoutPage checkoutPage;
  protected HeaderComponent headerComponent;

  /** Setup method - runs before each test class. Initializes WebDriver and Page Objects. */
  @BeforeClass
  public void setUp() {
    TestLogger.info("===== TEST SETUP STARTED =====");

    // Create WebDriver instance
    driver = BrowserFactory.createDriver();
    TestLogger.info("WebDriver initialized");

    // Initialize Page Objects with Dependency Injection
    initializePageObjects();

    TestLogger.info("===== TEST SETUP COMPLETED =====");
  }

  /**
   * Initializes all page objects with the WebDriver instance. This follows Dependency Injection
   * pattern.
   */
  protected void initializePageObjects() {
    homePage = new HomePage(driver);
    signupPage = new SignUpPage(driver);
    loginPage = new LoginPage(driver);
    categoryPage = new CategoryPage(driver);
    productPage = new ProductPage(driver);
    cartPage = new CartPage(driver);
    checkoutPage = new CheckoutPage(driver);
    headerComponent = new HeaderComponent(driver);

    TestLogger.debug("All page objects initialized");
  }

  /** Teardown method - runs after each test class. Closes WebDriver and cleans up resources. */
  @AfterClass
  public void tearDown() {
    TestLogger.info("===== TEST TEARDOWN STARTED =====");

    BrowserFactory.quitDriver(driver);

    TestLogger.info("===== TEST TEARDOWN COMPLETED =====");
  }

  /** Navigates to home page. Common action used in multiple tests. */
  protected void navigateToHomePage() {
    homePage.navigateToHomePage();
  }

  /** Signs up a new user. */
  protected void signUpNewUser(String username, String password) {
    TestLogger.testStep("Execute Sign Up");
    headerComponent.clickSignUp();
    signupPage.signUp(username, password);
  }

  /** Logs in with provided credentials. */
  protected void loginUser(String username, String password) {
    TestLogger.testStep("Execute Login");
    headerComponent.clickLogin();
    loginPage.login(username, password);
  }

  /** Logs out current user. */
  protected void logoutUser() {
    TestLogger.testStep("Execute Logout");
    headerComponent.clickLogout();
    TestLogger.info("User logged out");
  }

  /** Adds a product from a specific category to cart. */
  protected void addProductToCartFromCategory(String categoryName, String productName) {
    TestLogger.testStep(
        "Add product to cart - Category: {}, Product: {}", categoryName, productName);

    // Navigate to category
    homePage.navigateToCategory(categoryName);

    // Wait and select product
    categoryPage.waitForCategoryPageToLoad();
    categoryPage.selectProductByName(productName);

    // Add to cart
    productPage.addProductToCart();

    // Navigate back home
    homePage.navigateToHomePage();
  }
}
