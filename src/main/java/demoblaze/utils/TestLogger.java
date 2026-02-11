package demoblaze.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TestLogger - Centralized logging utility for test execution. Provides convenient methods for
 * logging test steps and assertions.
 */
public class TestLogger {
  private static final Logger logger = LoggerFactory.getLogger("TestExecution");

  private TestLogger() {
    // Private constructor to prevent instantiation
  }

  public static void info(String message) {
    logger.info(message);
  }

  public static void info(String format, Object... args) {
    logger.info(format, args);
  }

  public static void debug(String message) {
    logger.debug(message);
  }

  public static void debug(String format, Object... args) {
    logger.debug(format, args);
  }

  public static void warn(String message) {
    logger.warn(message);
  }

  public static void warn(String format, Object... args) {
    logger.warn(format, args);
  }

  public static void error(String message) {
    logger.error(message);
  }

  public static void error(String message, Throwable e) {
    logger.error(message, e);
  }

  public static void testStep(String format, Object... args) {
    String message = args.length > 0 ? String.format(format, args) : format;
    logger.info("TEST STEP: {}", message);
  }

  public static void assertion(String format, Object... args) {
    String message = args.length > 0 ? String.format(format, args) : format;
    logger.info("ASSERTION: {}", message);
  }

  public static void testStart(String testName) {
    logger.info("========== TEST START: {} ==========", testName);
  }

  public static void testEnd(String testName) {
    logger.info("========== TEST END: {} ==========", testName);
  }
}
