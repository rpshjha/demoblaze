package demoblaze.config;

/**
 * Configuration class for managing application-level constants. Implements Singleton pattern for
 * single instance across the application.
 */
public class Config {
  private static Config instance;

  // Browser Configuration
  public static final String BROWSER = System.getProperty("browser", "chrome").toLowerCase();
  public static final boolean HEADLESS =
      Boolean.parseBoolean(System.getProperty("headless", "false"));

  // URL Configuration
  public static final String BASE_URL = "https://www.demoblaze.com/";

  // Wait Configuration (in seconds)
  public static final int IMPLICIT_WAIT = 10;
  public static final int EXPLICIT_WAIT = 15;
  public static final int PAGE_LOAD_TIMEOUT = 20;

  // Test Data
  public static final String TEST_USER_FIRSTNAME = "John";
  public static final String TEST_USER_LASTNAME = "Doe";
  public static final String TEST_USER_EMAIL =
      "john_" + System.currentTimeMillis() + "@example.com";
  public static final String TEST_USER_PASSWORD = "SecurePass123!";

  // Product Constants
  public static final String CATEGORY_PHONES = "Phones";
  public static final String CATEGORY_LAPTOPS = "Laptops";
  public static final String CATEGORY_MONITORS = "Monitors";

  public static final String PRODUCT_SAMSUNG = "Samsung Galaxy s7";
  public static final String PRODUCT_MACBOOK = "MacBook Air";
  public static final String PRODUCT_APPLE_MONITOR = "Apple Monitor 24";

  // Retry Configuration
  public static final int MAX_RETRIES = 3;
  public static final long RETRY_WAIT_MS = 500;

  private Config() {
    // Private constructor to prevent instantiation
  }

  public static synchronized Config getInstance() {
    if (instance == null) {
      instance = new Config();
    }
    return instance;
  }
}
