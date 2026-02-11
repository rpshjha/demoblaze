package demoblaze.tests;

import demoblaze.testdata.TestData;
import demoblaze.utils.TestLogger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * DemoBlazeTest - Main test suite that automates the complete DemoBlaze scenario.
 *
 * <p>Test Scenario: 1. Navigate to DemoBlaze website 2. Sign up a new user 3. Login with new user
 * credentials 4. Validate login success and correct user is logged in 5. Select products from
 * different categories and add to cart: - Phones: Samsung Galaxy s7 - Laptops: MacBook Air -
 * Monitors: Apple Monitor 24 6. Validate all items are in cart 7. Calculate and validate total
 * price 8. Place order and complete purchase 9. Validate purchase confirmation 10. Logout
 *
 * <p>This test demonstrates: - Page Object Model pattern - SOLID principles - Meaningful assertions
 * - Clear test structure - DRY principle with reusable methods
 */
public class DemoBlazeTest extends BaseTest {

  /**
   * Main test method - Complete DemoBlaze scenario. This is the primary test case for end-to-end
   * automation.
   */
  @Test(priority = 1, description = "Complete DemoBlaze Purchase Scenario")
  public void testCompleteDemoBlazeScenario() {
    TestLogger.testStart("testCompleteDemoBlazeScenario");

    try {
      // Step 1: Navigate to home page
      TestLogger.testStep("Step 1: Navigate to DemoBlaze home page");
      navigateToHomePage();
      Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
      TestLogger.assertion("Home page loaded successfully");

      // Step 2: Sign up new user
      TestLogger.testStep("Step 2: Sign up new user");
      signUpNewUser(TestData.User.USERNAME, TestData.User.PASSWORD);
      TestLogger.assertion("Sign up completed - User: " + TestData.User.USERNAME);

      // Step 3: Login with new user
      TestLogger.testStep("Step 3: Login with new user credentials");
      loginUser(TestData.User.USERNAME, TestData.User.PASSWORD);
      TestLogger.assertion("Login attempt completed");

      // Step 4: Validate login success
      TestLogger.testStep("Step 4: Validate login succeeded and correct user is logged in");
      Assert.assertTrue(headerComponent.isUserLoggedIn(), "User should be logged in");
      String loggedInUser = headerComponent.getLoggedInUsername();
      Assert.assertTrue(
          loggedInUser.contains(TestData.User.USERNAME),
          "Logged in user should match: " + TestData.User.USERNAME);
      TestLogger.assertion("Login validation successful - User: " + loggedInUser);

      // Step 5 & 6: Add products to cart from different categories
      TestLogger.testStep("Step 5 & 6: Add products from different categories to cart");

      // Add Samsung Galaxy s7 from Phones
      addProductToCartFromCategory(TestData.Categories.PHONES, TestData.Products.SAMSUNG);
      TestLogger.assertion("Samsung Galaxy s7 added to cart");

      // Add MacBook Air from Laptops
      addProductToCartFromCategory(TestData.Categories.LAPTOPS, TestData.Products.MACBOOK);
      TestLogger.assertion("MacBook Air added to cart");

      // Add Apple Monitor 24 from Monitors
      addProductToCartFromCategory(TestData.Categories.MONITORS, TestData.Products.APPLE_MONITOR);
      TestLogger.assertion("Apple Monitor 24 added to cart");

      // Step 7: Navigate to cart and validate items
      TestLogger.testStep("Step 7: Navigate to cart and validate all items");
      headerComponent.clickCart();
      cartPage.waitForCartPageToLoad();

      // Verify all products are in cart
      Assert.assertTrue(
          cartPage.isProductInCart(TestData.Products.SAMSUNG),
          "Samsung Galaxy s7 should be in cart");
      Assert.assertTrue(
          cartPage.isProductInCart(TestData.Products.MACBOOK), "MacBook Air should be in cart");
      Assert.assertTrue(
          cartPage.isProductInCart(TestData.Products.APPLE_MONITOR),
          "Apple Monitor 24 should be in cart");
      TestLogger.assertion("All 3 products validated in cart");

      // Verify cart item count
      int cartItemCount = cartPage.getCartItemCount();
      Assert.assertEquals(cartItemCount, 3, "Cart should contain 3 items");
      TestLogger.assertion("Cart item count validated: " + cartItemCount);

      // Step 8: Validate total price
      TestLogger.testStep("Step 8: Validate total price calculation");
      double totalPrice = cartPage.getTotalPrice();
      Assert.assertTrue(totalPrice > 0, "Total price should be greater than 0");
      TestLogger.assertion("Total price in cart: $" + totalPrice);

      // Step 9: Place order
      TestLogger.testStep("Step 9: Place order");
      Assert.assertTrue(
          cartPage.isPlaceOrderButtonVisible(), "Place Order button should be visible");
      cartPage.clickPlaceOrderButton();
      TestLogger.assertion("Place Order button clicked");

      // Step 10: Complete checkout
      TestLogger.testStep("Step 10: Complete checkout with order details");
      String checkoutTotal =
          checkoutPage.completePurchase(
              TestData.Order.NAME,
              TestData.Order.COUNTRY,
              TestData.Order.CITY,
              TestData.Order.CARD_NUMBER,
              TestData.Order.MONTH,
              TestData.Order.YEAR);

      // Verify purchase success
      Assert.assertTrue(
          checkoutTotal.contains("Thank you") || checkoutTotal.contains("purchase"),
          "Purchase confirmation message should be displayed");
      TestLogger.assertion("Purchase completed successfully: " + checkoutTotal);

      // Step 10.1: Close purchase confirmation modal
      checkoutPage.clickConfirmButton();

      // Step 11: Validate logout
      TestLogger.testStep("Step 11: Logout user");
      logoutUser();

      // Verify logout
      Assert.assertTrue(
          headerComponent.isLoginVisible(), "Login button should be visible after logout");
      TestLogger.assertion("Logout validation successful");

      TestLogger.info("===== TEST PASSED: Complete DemoBlaze scenario executed successfully =====");

    } catch (AssertionError e) {
      TestLogger.error("Test assertion failed: ", e);
      throw e;
    } catch (Exception e) {
      TestLogger.error("Test failed with exception: ", e);
      throw e;
    } finally {
      TestLogger.testEnd("testCompleteDemoBlazeScenario");
    }
  }
}
