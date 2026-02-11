# DemoBlaze Selenium Automation Framework

A comprehensive Selenium WebDriver Java automation framework for testing the DemoBlaze e-commerce website. Built with clean architecture, best practices, and industry-standard design patterns.

## 📋 Overview

This framework automates a complete end-to-end scenario on DemoBlaze:

1. ✅ Navigate to DemoBlaze website
2. ✅ Sign up a new user
3. ✅ Login with new user credentials
4. ✅ Validate login success
5. ✅ Select and add products from multiple categories to cart:
   - **Phones**: Samsung Galaxy s7
   - **Laptops**: MacBook Air
   - **Monitors**: Apple Monitor 24
6. ✅ Validate cart contents and total price
7. ✅ Place order and complete purchase
8. ✅ Validate purchase confirmation
9. ✅ Logout and validate session termination

## 🏗️ Architecture & Design Patterns

### **Page Object Model (POM)**
- **Separation of Concerns**: Locators and page logic are isolated in dedicated page classes
- **Reusability**: Common methods centralized in `BasePage`
- **Maintainability**: UI changes affect only page objects, not test code

### **Design Patterns Implemented**

| Pattern | Usage | Location |
|---------|-------|----------|
| **Page Object** | Encapsulates page elements & interactions | `pageobjects/` package |
| **Factory** | Creates WebDriver instances | `BrowserFactory.java` |
| **Singleton** | Configuration management | `Config.java` |
| **Component** | Reusable UI components | `HeaderComponent.java` |
| **Dependency Injection** | Page object initialization | `BaseTest.java` |
| **Template Method** | Test setup/teardown | `BaseTest.java` |

### **SOLID Principles**

- **S**ingle Responsibility: Each class has one reason to change
  - `BrowserFactory` → WebDriver creation
  - `WaitUtils` → Explicit wait management
  - `TestLogger` → Logging functionality
  
- **O**pen/Closed: Open for extension, closed for modification
  - Page objects extend `BasePage` for new pages
  - Utilities are non-final and can be extended
  
- **L**iskov Substitution: All page objects inherit from `BasePage`
  
- **I**nterface Segregation: Methods grouped by responsibility
  
- **D**ependency Inversion: Tests depend on abstractions (page objects) not concrete implementations

### **DRY Principle**
- Common wait logic → `WaitUtils`
- Common page actions → `BasePage`
- Common test setup → `BaseTest`
- Test data centralization → `TestData`

## 📁 Project Structure

```
demoblaze/
├── pom.xml                                  # Maven configuration with dependencies
├── testng.xml                               # TestNG test suite configuration
├── README.md                                # This file
│
├── src/main/java/demoblaze/
│   ├── config/
│   │   └── Config.java                      # Application configuration (Singleton)
│   │
│   ├── utils/
│   │   ├── BrowserFactory.java             # WebDriver factory (supports Chrome, Firefox, Edge)
│   │   ├── WaitUtils.java                  # Explicit wait strategies
│   │   └── TestLogger.java                 # Centralized logging utility
│   │
│   ├── pageobjects/
│   │   ├── BasePage.java                   # Base class with common methods
│   │   ├── HomePage.java                   # Home page interactions
│   │   ├── SignUpPage.java                 # Sign-up modal interactions
│   │   ├── LoginPage.java                  # Login modal interactions
│   │   ├── HeaderComponent.java            # Shared header component (login, logout, cart)
│   │   ├── CategoryPage.java               # Product category page
│   │   ├── ProductPage.java                # Individual product page
│   │   ├── CartPage.java                   # Shopping cart page
│   │   └── CheckoutPage.java               # Order checkout page
│   │
│   ├── testdata/
│   │   └── TestData.java                   # Centralized test data management
│   │
│   └── resources/
│       └── logback.xml                      # Logging configuration
│
├── src/test/java/demoblaze/tests/
│   ├── BaseTest.java                       # Base test class with setup/teardown
│   └── DemoBlazeTest.java                  # Main test suite
│
└── target/
    ├── test-reports/                       # TestNG HTML reports
    └── test-logs/                          # Test execution logs
```

## 🔧 Technologies & Tools

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 11+ | Programming language |
| **Selenium WebDriver** | 4.40.0 | Browser automation |
| **TestNG** | 7.12.0 | Test framework & runner |
| **Maven** | 3.6+ | Build automation |
| **SLF4J + Logback** | 2.0.9 + 1.4.12 | Logging |
| **Gson** | 2.10.1 | JSON processing |

## 📦 Dependencies

All dependencies are managed through Maven POM:
- **Selenium WebDriver**: Browser automation
- **TestNG**: Test execution and reporting
- **SLF4J & Logback**: Structured logging
- **Gson**: JSON handling
- **Apache Commons Lang3**: Utility functions

Install all dependencies:
```bash
mvn clean install
```

## 🚀 Getting Started

### Prerequisites

- **Java 11 or higher**: [Download Java](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6 or higher**: [Download Maven](https://maven.apache.org/download.cgi)
- **Git**: For cloning the repository
- **Chrome/Firefox/Edge Browser**: For test execution

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/rpshjha/demoblaze.git
   cd demoblaze
   ```

2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify Setup**
   ```bash
   mvn test-compile
   ```

## 🧪 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
mvn clean test -Dtest=DemoBlazeTest
```

### Run Specific Test Method
```bash
mvn clean test -Dtest=DemoBlazeTest#testCompleteDemoBlazeScenario
```

### Run in Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Run with Different Browser
```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
mvn clean test -Dbrowser=chrome
```

### Run Using TestNG Configuration
```bash
mvn clean test -Dsuite=testng.xml
```

## 📊 Test Reports

After test execution, reports are generated in:

- **TestNG HTML Report**: `target/surefire-reports/index.html`
- **Test Logs**: `target/test-logs/test-execution.log`

View HTML report in browser:
```bash
open target/surefire-reports/index.html
```

## 📝 Test Data Management

All test data is externalized in `TestData.java`:

```java
public static class User {
    public static final String USERNAME = "testuser_" + System.currentTimeMillis();
    public static final String PASSWORD = "SecurePass123!";
}

public static class Products {
    public static final String SAMSUNG = "Samsung Galaxy s7";
    public static final String MACBOOK = "MacBook Air";
    public static final String APPLE_MONITOR = "Apple Monitor 24";
}

public static class Order {
    public static final String NAME = "John Doe";
    public static final String COUNTRY = "United States";
    public static final String CITY = "New York";
    public static final String CARD_NUMBER = "4532015112830366";
    public static final String MONTH = "12";
    public static final String YEAR = "2025";
}
```

## 📜 Configuration Management

All configuration is managed in `Config.java`:

```java
public static final String BASE_URL = "https://www.demoblaze.com/";
public static final String BROWSER = System.getProperty("browser", "chrome");
public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "false"));
public static final int EXPLICIT_WAIT = 15;  // seconds
```

## 🔍 Key Features

### **Page Object Model**
```java
// NO locators in tests - they're in page objects
public class HomePage extends BasePage {
    private By phonesCategoryLink = By.linkText("Phones");
    
    public void selectPhonesCategory() {
        click(phonesCategoryLink);
    }
}

// Test only uses page object methods
homePage.selectPhonesCategory();
```

### **Reusable Utilities**

**WaitUtils.java** - Explicit wait strategies:
```java
WaitUtils.waitForElementToBeVisible(driver, locator);
WaitUtils.waitForElementToBeClickable(driver, locator);
WaitUtils.waitForAlert(driver);
```

**BrowserFactory.java** - Multi-browser support:
```java
WebDriver driver = BrowserFactory.createDriver();  // Auto-detects browser from config
BrowserFactory.quitDriver(driver);  // Safe cleanup
```

**TestLogger.java** - Structured logging:
```java
TestLogger.testStep("Perform action");
TestLogger.assertion("Validation passed");
TestLogger.info("Additional info: {}", value);
```

### **Meaningful Assertions**
```java
Assert.assertTrue(homePage.isHomePageLoaded(), 
    "Home page should be loaded");
Assert.assertEquals(cartItemCount, 3, 
    "Cart should contain 3 items");
```

## 🔄 Test Execution Flow

### Main Test Scenario: `testCompleteDemoBlazeScenario`

1. **Navigation**: Open DemoBlaze website
2. **Sign Up**: Create new user with unique email
3. **Login**: Authenticate with newly created credentials
4. **Validation**: Verify user is logged in
5. **Shopping**:
   - Navigate to Phones → Select Samsung Galaxy s7 → Add to cart
   - Navigate to Laptops → Select MacBook Air → Add to cart
   - Navigate to Monitors → Select Apple Monitor 24 → Add to cart
6. **Cart Verification**: Confirm 3 items in cart, validate total price
7. **Checkout**: Place order with test data
8. **Confirmation**: Verify purchase success message
9. **Logout**: Sign out and verify login button reappears

## ✅ Validations & Assertions

| Validation | Description | Status |
|-----------|------------|--------|
| Home page load | Products visible | ✅ |
| Sign up success | Alert message received | ✅ |
| Login success | User logged in state | ✅ |
| Correct user | Username matches | ✅ |
| Products added | All 3 items in cart | ✅ |
| Item count | Exactly 3 items | ✅ |
| Total price | Price > 0 and calculated | ✅ |
| Place order button | Visible when needed | ✅ |
| Purchase confirmation | Success message shown | ✅ |
| Logout complete | Login button visible | ✅ |

## 🛠️ Extensibility & Maintenance

### Add a New Page Object
```java
public class NewPage extends BasePage {
    private By locator1 = By.id("element1");
    
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    public void performAction() {
        click(locator1);
    }
}
```

### Add a New Test
```java
@Test(priority = 4, description = "New test description")
public void testNewScenario() {
    navigateToHomePage();
    // Your test steps here
}
```

### Modify Wait Strategy
Edit `Config.java`:
```java
public static final int EXPLICIT_WAIT = 20;  // Changed from 15
```

## 📋 Logging & Debugging

### Log Levels
- **INFO**: Test steps and major actions
- **DEBUG**: Detailed element interactions
- **WARN**: Non-critical issues
- **ERROR**: Test failures

### View Logs
```bash
tail -f target/test-logs/test-execution.log
```

### Console Output Sample
```
2026-02-11 15:30:45.123 [main] INFO  DemoBlazeTest - TEST STEP: Step 1: Navigate to DemoBlaze home page
2026-02-11 15:30:46.456 [main] DEBUG WaitUtils - Waiting for element to be visible: By.linkText: Phones
2026-02-11 15:30:47.789 [main] INFO  DemoBlazeTest - ASSERTION: Home page loaded successfully
```

## 🐛 Troubleshooting

### Issue: "WebDriver not found"
**Solution**: 
```bash
mvn clean install
```

### Issue: "Element not found" exceptions
**Solution**: Check element locators in page objects. The website structure might have changed.

### Issue: Tests timing out
**Solution**: Increase `EXPLICIT_WAIT` in `Config.java`:
```java
public static final int EXPLICIT_WAIT = 20;  // Increased from 15
```

### Issue: Browser doesn't open
**Solution**: Ensure browser is installed and matching the version with your WebDriver

## 💡 Best Practices Implemented

✅ **Single Responsibility**: Each class has one clear purpose  
✅ **DRY Principle**: No code duplication - reusable utilities  
✅ **Page Object Model**: UI changes isolated in page objects  
✅ **Dependency Injection**: Page objects injected in tests  
✅ **Configuration Management**: Externalized configuration  
✅ **Test Data Management**: Centralized test data  
✅ **Meaningful Logging**: Clear, structured logging  
✅ **Explicit Waits**: No hardcoded sleeps  
✅ **Assertions**: Clear, meaningful assertions  
✅ **Error Handling**: Try-catch with proper logging  

## 🔐 Security Notes

- Test data contains dummy credit card (not real)
- Passwords are test data only
- No sensitive credentials in code
- Use environment variables for production credentials

## 📞 Support & Contribution

For issues, questions, or improvements:
1. Check logs in `target/test-logs/`
2. Review page object locators if tests fail
3. Verify element selectors match current website structure

## 📚 Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [SOLID Principles](https://en.wikipedia.org/wiki/SOLID)

## 📄 License

This project is provided as-is for educational and testing purposes.

---

**Happy Testing! 🎉**

For the best experience, ensure your browser drivers are up-to-date and match your installed browser version.
