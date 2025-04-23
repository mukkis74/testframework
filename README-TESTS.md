# Test Documentation

This document provides information about the tests added to the Chrono24 Test Automation Framework.

## Types of Tests Added

### Unit Tests
- **ExtentReportManagerTest**: Tests the functionality of the ExtentReportManager utility class, including report initialization, test creation, and logging operations.

### Smoke Tests
- **HomepageSmokeTest**: Verifies that the basic functionality of the homepage is working correctly, including page loading and search functionality.

### Regression Tests
- **FilterFunctionalityRegressionTest**: Tests the filter functionality on the search results page, including pagination and multiple searches.

### End-to-End Tests
- **BrowseAndSearchE2ETest**: Simulates a complete user journey from the homepage to viewing product details, including searching, viewing details, and navigating between pages.

## Running the Tests

### Running All Tests
To run all tests, use the following Maven command:

```bash
mvn test
```

This will execute all tests defined in the TestNG XML file (`src/test/resources/testng.xml`).

You can also run the tests with specific JVM arguments if needed:

```bash
mvn test -DargLine="-Xmx2048m"
```

### Running Tests with the Maven Failsafe Plugin
As an alternative to the Surefire plugin, you can use the Failsafe plugin to run the tests:

```bash
mvn failsafe:integration-test
```

This can be useful if you're experiencing issues with the Surefire plugin.

### Running Tests with the TestNG XML File
You can explicitly specify the TestNG XML file to use:

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Running Specific Test Classes
To run a specific test class, use the following Maven command:

```bash
mvn test -Dtest=com.example.tests.unit.ExtentReportManagerTest
```

You can also run multiple test classes:

```bash
mvn test -Dtest=com.example.tests.unit.ExtentReportManagerTest,com.example.tests.smoke.HomepageSmokeTest
```

### Running Specific Test Types
To run specific types of tests, you can use the following Maven commands:

#### Unit Tests
```bash
mvn test -Dgroups=unit
```

#### Smoke Tests
```bash
mvn test -Dgroups=smoke
```

#### Regression Tests
```bash
mvn test -Dgroups=regression
```

#### End-to-End Tests
```bash
mvn test -Dgroups=e2e
```

### Running Tests with a Specific Browser
To run tests with a specific browser, use the following Maven command:

```bash
mvn test -Dbrowser=firefox
```

Supported browsers:
- chrome (default)
- firefox
- edge
- safari

### Running Tests in Headless Mode
To run tests in headless mode, use the following Maven command:

```bash
mvn test -Dheadless=true
```

## Test Structure

The tests follow the Page Object Model design pattern, with the following structure:

- **BaseTest**: Base class for all test classes, providing common functionality like driver initialization and cleanup.
- **BasePage**: Base class for all page objects, providing common functionality for page interactions.
- **Page Objects**: Classes representing pages on the website, encapsulating the page structure and behavior.
- **Test Classes**: Classes containing the actual test methods, organized by test type.

## Test Reports

After running the tests, you can find the test reports in the following locations:

- **ExtentReports**: `test-output/reports/extent-report.html`
- **Allure Reports**: Generate with `mvn allure:report` and view at `target/allure-report/index.html`

## Known Issues

- The tests are designed to work with the Chrono24 website, but the actual implementation may need adjustments based on the current website structure.
- There are issues with the Maven Surefire plugin detecting TestNG tests. This is a known issue with the Surefire plugin and TestNG. As a workaround, you can use the Maven Failsafe plugin to run the tests.
- The tests may not run correctly in headless mode due to issues with the WebDriver configuration. If you encounter issues, try running the tests in non-headless mode by setting the `headless` system property to `false`.

## Recent Fixes

- Fixed Lombok configuration issues by adding the proper dependency and compiler configuration.
- Added JVM arguments to resolve access issues between Lombok and Java modules.
- Moved the BaseTest class from the main source directory to the test source directory to resolve compilation issues.
- Removed the deprecated MaxPermSize JVM option from the Maven Surefire plugin configuration.
- Added the Maven Failsafe plugin to provide an alternative way to run TestNG tests.

## Troubleshooting

If you encounter any issues running the tests, try the following:

1. **Lombok Issues**: If you see errors related to Lombok, make sure you have the Lombok plugin installed in your IDE. You may also need to enable annotation processing in your IDE settings.

2. **Java Module Access Issues**: If you see errors about illegal access to Java modules, make sure the `--add-opens` arguments are properly set in the Maven Surefire plugin configuration. These arguments allow Lombok to access internal Java classes.

3. **Browser Driver Issues**: If you see errors about browser drivers not being found, make sure you have the appropriate browser drivers installed and in your PATH. You can also use WebDriverManager to automatically download and configure the drivers.

4. **TestNG Detection Issues**: If the Maven Surefire plugin is not detecting your TestNG tests, try using the Maven Failsafe plugin instead:
   ```bash
   mvn failsafe:integration-test
   ```

5. **Headless Mode Issues**: If tests fail in headless mode, try running them in non-headless mode:
   ```bash
   mvn test -Dheadless=false
   ```

6. **JVM Arguments Issues**: If you see errors related to JVM arguments, try running the tests with simplified JVM arguments:
   ```bash
   mvn test -DargLine="-Xmx1024m"
   ```
