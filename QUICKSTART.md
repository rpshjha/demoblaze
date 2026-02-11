# Quick Start Guide - DemoBlaze Automation Framework

## ⚡ 5-Minute Quick Start

### 1. **Prerequisites Check**
```bash
java -version      # Should be 11+
mvn -version       # Should be 3.6+
```

### 2. **Setup Project**
```bash
cd /Users/rupeshkumar/IdeaProjects/demoblaze
./setup.sh
```

### 3. **Run Tests**
```bash
mvn clean test
```

### 4. **View Results**
```bash
# HTML Report
open target/surefire-reports/index.html

# Logs
cat target/test-logs/test-execution.log
```

---

## 📋 Common Commands

### **Test Execution**

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn clean test -Dtest=DemoBlazeTest

# Run specific test method
mvn clean test -Dtest=DemoBlazeTest#testCompleteDemoBlazeScenario

# Run tests in parallel (if supported)
mvn test -DthreadCount=3

# Skip tests during build
mvn clean compile -DskipTests
```

### **Browser Options**

```bash
# Chrome (default)
mvn clean test

# Firefox
mvn clean test -Dbrowser=firefox

# Edge
mvn clean test -Dbrowser=edge

# Headless mode
mvn clean test -Dheadless=true

# Chrome + Headless
mvn clean test -Dbrowser=chrome -Dheadless=true
```

### **Test Reports & Logs**

```bash
# View TestNG HTML report
open target/surefire-reports/index.html

# View test logs
tail -f target/test-logs/test-execution.log

# Clear reports and run fresh
mvn clean test

# Generate Maven site report
mvn surefire-report:report
open target/site/surefire-report.html
```

### **Build & Debug**

```bash
# Compile only
mvn clean compile

# Compile with tests
mvn test-compile

# Debug mode (very verbose)
mvn clean test -X

# Skip dependency download
mvn clean test -o

# Run with specific maven profile
mvn clean test -P profile-name
```

---

## 🗂️ Project Structure Quick Reference

```
src/main/java/demoblaze/
├── config/          → Configuration (Config.java)
├── utils/           → Helper classes (BrowserFactory, WaitUtils, TestLogger)
├── pageobjects/     → Page Object Model (HomePage, LoginPage, etc.)
└── testdata/        → Test data (TestData.java)

src/test/java/demoblaze/tests/
├── BaseTest.java    → Base test class with setup/teardown
└── DemoBlazeTest.java → Main test suite

src/main/resources/
└── logback.xml      → Logging configuration

target/
├── surefire-reports/ → HTML test reports
└── test-logs/        → Test execution logs
```

---

## 🔧 Key Files to Know

| File | Purpose | Edit When |
|------|---------|-----------|
| `Config.java` | App configuration | Change timeouts, browser, URL |
| `TestData.java` | Test data | Add/modify test data |
| `BrowserFactory.java` | WebDriver creation | Add new browser support |
| `WaitUtils.java` | Wait strategies | Add new wait types |
| `BaseTest.java` | Test base class | Add common test methods |
| `DemoBlazeTest.java` | Test suite | Add new tests |
| `HomePage.java, etc.` | Page objects | Update element locators |
| `testng.xml` | Test configuration | Add/remove test groups |

---

## 📝 Test Data Reference

### **User Credentials**
```java
Username: testuser_<timestamp>  // Auto-generated unique
Password: SecurePass123!
```

### **Test Products**
```
1. Samsung Galaxy s7  (Phones category)
2. MacBook Air        (Laptops category)
3. Apple Monitor 24   (Monitors category)
```

### **Order Details**
```
Name: John Doe
Country: United States
City: New York
Card: 4532015112830366
Exp Month: 12
Exp Year: 2025
```

---

## 🔍 Troubleshooting Quick Tips

### **"WebDriver not found"**
```bash
mvn clean install
```

### **"Element not found"**
- Check element locators in page objects
- Website structure may have changed
- Use browser DevTools to inspect elements

### **"Test timeout"**
```bash
# Increase wait in Config.java
# public static final int EXPLICIT_WAIT = 20; // Changed from 15
```

### **"Port already in use"**
```bash
# Kill process using port
lsof -ti:4444 | xargs kill -9
```

### **"Browser not installed"**
- Ensure Chrome, Firefox, or Edge is installed
- Verify browser version matches WebDriver

---

## 📊 Test Results Interpretation

### **PASS Results**
```
testCompleteDemoBlazeScenario ............... PASS
  - All 10 assertions passed
  - User journey complete
  - Purchase successful
```

### **FAIL Results**
Check:
1. Console error message
2. Logs in `target/test-logs/test-execution.log`
3. HTML report in `target/surefire-reports/`
4. Element locators in page objects

---

## 💡 Pro Tips

1. **Use -o flag for offline mode** (if you have all dependencies)
   ```bash
   mvn clean test -o
   ```

2. **Skip expensive tests**
   ```bash
   mvn test -Dtest=DemoBlazeTest#testSignUpFunctionality
   ```

3. **Run with custom timeout**
   Edit Config.java before running:
   ```java
   public static final int EXPLICIT_WAIT = 30;
   ```

4. **Always use clean target**
   ```bash
   mvn clean test  # Better than just 'mvn test'
   ```

5. **Check logs while tests run**
   ```bash
   tail -f target/test-logs/test-execution.log
   ```

---

## 📚 Documentation Links

- [Main README](./README.md) - Comprehensive documentation
- [Implementation Summary](./IMPLEMENTATION_SUMMARY.md) - Architecture & design details
- [Selenium Docs](https://www.selenium.dev/documentation/)
- [TestNG Docs](https://testng.org/doc/)

---

## ✅ Verification Checklist

Before running tests:
- [ ] Java 11+ installed: `java -version`
- [ ] Maven 3.6+ installed: `mvn -version`
- [ ] Git available: `git --version`
- [ ] Browser installed (Chrome/Firefox/Edge)
- [ ] Project compiled: `mvn clean compile -DskipTests`
- [ ] Dependencies resolved: `mvn dependency:resolve`

---

## 🎯 Test Success Indicators

When tests run successfully, you should see:
```
✓ Maven build starts
✓ Dependencies download
✓ Project compiles
✓ Browser opens
✓ Tests execute
✓ Reports generate
✓ All assertions pass
✓ Exit code: 0
```

---

## 📞 Getting Help

1. **Check logs first**
   ```bash
   cat target/test-logs/test-execution.log
   ```

2. **Review test report**
   ```bash
   open target/surefire-reports/index.html
   ```

3. **Look at page object locators**
   - Website structure may have changed
   - Update By.xpath or By.id in page objects

4. **Increase timeout and retry**
   - Edit Config.java
   - Increase EXPLICIT_WAIT value

5. **Run in verbose mode**
   ```bash
   mvn clean test -X
   ```

---

**Ready? Run:** `mvn clean test` 🚀

**Having issues?** Check `target/test-logs/test-execution.log` 📋
