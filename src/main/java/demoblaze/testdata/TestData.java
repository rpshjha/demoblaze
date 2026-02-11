package demoblaze.testdata;

/**
 * TestData - Manages all test data required for test execution. Centralizes test data to follow DRY
 * principle and enable easy maintenance.
 */
public class TestData {

  /** User data for test execution. */
  public static class User {
    public static final String FIRSTNAME = "John";
    public static final String LASTNAME = "Doe";
    public static final String EMAIL = "john_" + System.currentTimeMillis() + "@example.com";
    public static final String USERNAME = "testuser_" + System.currentTimeMillis();
    public static final String PASSWORD = "SecurePass123!";
  }

  /** Product data for test execution. */
  public static class Products {
    public static final String[] PRODUCTS_TO_ADD = {
      "Samsung Galaxy s7", "MacBook Air", "Apple Monitor 24"
    };

    public static final String SAMSUNG = "Samsung Galaxy s7";
    public static final String MACBOOK = "MacBook Air";
    public static final String APPLE_MONITOR = "Apple Monitor 24";
  }

  /** Category data. */
  public static class Categories {
    public static final String PHONES = "Phones";
    public static final String LAPTOPS = "Laptops";
    public static final String MONITORS = "Monitors";
  }

  /** Order/Checkout data. */
  public static class Order {
    public static final String NAME = "John Doe";
    public static final String COUNTRY = "United States";
    public static final String CITY = "New York";
    public static final String CARD_NUMBER = "4532015112830366";
    public static final String MONTH = "12";
    public static final String YEAR = "2025";
  }

  /** Expected messages/responses. */
  public static class ExpectedMessages {
    public static final String SIGNUP_SUCCESS = "Sign up successful.";
    public static final String LOGIN_SUCCESS = "Welcome ";
    public static final String ADD_TO_CART_SUCCESS = "Product added.";
    public static final String PURCHASE_SUCCESS = "Thank you for your purchase!";
    public static final String LOGOUT_SUCCESS = ""; // No specific message
  }

  /** Timeouts in seconds. */
  public static class Timeouts {
    public static final int DEFAULT_WAIT = 10;
    public static final int SHORT_WAIT = 5;
    public static final int LONG_WAIT = 20;
  }
}
