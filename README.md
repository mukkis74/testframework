# Chrono24 Test Automation Framework

A comprehensive test automation framework for [Chrono24](https://www.chrono24.de/) based on Selenium WebDriver, TestNG, and Java.

## Features

- Page Object Model design pattern
- Support for multiple browsers (Chrome, Firefox, Edge, Safari)
- Parallel test execution
- Headless mode support
- Detailed reporting with Allure Reports and ExtentReports
- Environment-specific configuration
- Support for different test types:
  - Unit tests
  - Smoke tests
  - Regression tests
  - System tests
  - End-to-End tests
  - Performance tests (JMeter integration)
  - Security tests

## Project Structure

```
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   │               └── Calculator.java
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           ├── core
│       │           │   └── BaseTest.java
│       │           ├── pages
│       │           │   ├── BasePage.java
│       │           │   ├── ContactSellerPage.java
│       │           │   ├── ForgotPasswordPage.java
│       │           │   ├── HomePage.java
│       │           │   ├── LoginPage.java
│       │           │   ├── RegistrationPage.java
│       │           │   ├── SearchResultsPage.java
│       │           │   └── WatchDetailPage.java
│       │           ├── tests
│       │           │   ├── e2e
│       │           │   │   └── BrowseAndSearchE2ETest.java
│       │           │   ├── performance
│       │           │   │   └── AdvancedPerformanceTest.java
│       │           │   ├── regression
│       │           │   │   └── FilterFunctionalityRegressionTest.java
│       │           │   ├── security
│       │           │   │   └── WebSecurityTest.java
│       │           │   ├── smoke
│       │           │   │   └── HomepageSmokeTest.java
│       │           │   ├── system
│       │           │   │   └── PerformanceSystemTest.java
│       │           │   └── unit
│       │           │       └── SampleUnitTest.java
│       │           └── utils
│       │               ├── ConfigManager.java
│       │               ├── DriverManager.java
│       │               └── ExtentReportManager.java
│       └── resources
│           ├── config.dev.properties
│           ├── config.properties
│           ├── config.qa.properties
│           └── testng.xml
├── src
│   └── test
│       └── jmeter
│           ├── chrono24_performance_test.jmx
│           └── chrono24_security_test.jmx
└── pom.xml
```

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Browsers: Chrome, Firefox, Edge, or Safari

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd chrono24-test-framework
```

### Running Tests

To run all tests:

```bash
mvn test
```

To run a specific test suite:

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

To run tests in a specific environment:

```bash
mvn test -Denv=qa
```

To run tests with a specific browser:

```bash
mvn test -Dbrowser=firefox
```

To run tests in headless mode:

```bash
mvn test -Dheadless=true
```

### Git Configuration

The repository is configured to automatically push to GitHub after each commit. This is implemented using a Git post-commit hook.

#### How it works

When you make a commit, the post-commit hook automatically pushes the changes to the GitHub repository, ensuring that your remote repository is always up-to-date with your local changes.

#### Manual Setup (if needed)

If you need to set up the automatic push feature manually, follow these steps:

1. Create a post-commit hook file in the `.git/hooks` directory:

```bash
touch .git/hooks/post-commit
```

2. Add the following content to the file:

```bash
#!/bin/sh
# Automatically push to GitHub after each commit
echo "Automatically pushing to GitHub..."
git push origin $(git symbolic-ref --short HEAD)
```

3. Make the hook executable:

```bash
chmod +x .git/hooks/post-commit
```

Now, every time you make a commit, your changes will be automatically pushed to GitHub.

### Configuration

The framework uses property files for configuration:

- `config.properties`: Default configuration
- `config.dev.properties`: Development environment configuration
- `config.qa.properties`: QA environment configuration

You can override any configuration property using system properties:

```bash
mvn test -Dapp.url=https://www.chrono24.com/ -Ddriver.implicit.wait=15
```

## Adding New Tests

### Creating a Page Object

1. Create a new class in the `com.example.pages` package
2. Extend the `BasePage` class
3. Define WebElements using `@FindBy` annotations
4. Implement methods for interacting with the page

Example:

```java
package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPage extends BasePage {

    @FindBy(css = ".element-selector")
    private WebElement element;

    public NewPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad();
    }

    public void performAction() {
        click(element);
    }
}
```

### Creating a Test Class

1. Create a new class in the appropriate test package (e.g., `com.example.tests.smoke`)
2. Extend the `BaseTest` class
3. Implement test methods using TestNG annotations

Example:

```java
package com.example.tests.smoke;

import com.example.core.BaseTest;
import com.example.pages.HomePage;
import com.example.utils.ExtentReportManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Smoke Tests")
@Feature("Homepage Functionality")
public class NewSmokeTest extends BaseTest {

    @Test(description = "Test description")
    @Story("Homepage Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the homepage loads correctly and all elements are displayed")
    public void testMethod() {
        ExtentReportManager.logInfo("Starting test");

        // Using Allure steps for better reporting
        HomePage homePage = navigateToHomePage();
        verifyHomePageElements(homePage);

        ExtentReportManager.logPass("Test completed successfully");
    }

    @Step("Navigate to the homepage")
    private HomePage navigateToHomePage() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        return homePage;
    }

    @Step("Verify homepage elements are displayed correctly")
    private void verifyHomePageElements(HomePage homePage) {
        // Test steps and assertions
        Assert.assertTrue(homePage.isLoaded(), "Homepage is not loaded correctly");
    }
}
```

## Parallel Execution

The framework supports parallel test execution. Configure the parallel mode and thread count in the TestNG XML file:

```xml
<suite name="Test Suite" parallel="tests" thread-count="3">
    <!-- Test classes -->
</suite>
```

## Reporting

### ExtentReports

ExtentReports are generated in the `test-output/reports` directory. Open the HTML report in a browser to view detailed test results.

### Allure Reports

Allure Reports provide a more detailed and interactive reporting experience. The framework is configured to generate Allure reports automatically.

To generate Allure reports after test execution:

```bash
mvn allure:report
```

This will generate the Allure report in the `target/allure-report` directory. Open the `index.html` file in a browser to view the report.

To serve the Allure report on a local web server:

```bash
mvn allure:serve
```

This will generate the report and open it in your default web browser.

#### Allure Annotations

Allure provides several annotations to enhance test reporting:

- `@Epic`: Defines the epic the test belongs to
- `@Feature`: Defines the feature being tested
- `@Story`: Defines the user story
- `@Severity`: Sets the test severity level
- `@Description`: Provides a detailed test description
- `@Step`: Defines a test step for detailed reporting

Example usage:

```java
@Epic("Authentication")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test valid user login with correct credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("validuser", "validpassword");

        Assert.assertTrue(loginPage.isLoggedIn());
    }
}
```

## Logging

The framework uses Log4j for logging. Configure logging levels in the `log4j2.xml` file.

## Performance Testing

The framework includes two approaches for performance testing:

### JMeter Integration

JMeter tests are integrated into the Maven build process using the JMeter Maven Plugin. The test scripts are located in the `src/test/jmeter` directory.

To run JMeter tests:

```bash
mvn jmeter:jmeter
```

This will execute all JMeter test scripts and generate reports in the `target/jmeter/results` directory.

The framework includes the following JMeter test scripts:

- `chrono24_performance_test.jmx`: Tests the performance of the Chrono24 website under load, including:
  - Homepage load test with 50 concurrent users
  - Search functionality test with 30 concurrent users

### Selenium-based Performance Tests

The framework also includes Selenium-based performance tests in the `com.example.tests.performance` package:

- `AdvancedPerformanceTest.java`: Contains tests for:
  - Product detail page load performance
  - Multiple consecutive searches performance
  - Pagination performance

These tests measure the response times of various user interactions and compare them against predefined thresholds.

## Security Testing

The framework includes two approaches for security testing:

### OWASP Dependency Check

The OWASP Dependency Check is integrated into the Maven build process to scan for known vulnerabilities in project dependencies.

To run the dependency check:

```bash
mvn dependency-check:check
```

This will generate a report in the `target/dependency-check` directory.

### Selenium-based Security Tests

The framework includes Selenium-based security tests in the `com.example.tests.security` package:

- `WebSecurityTest.java`: Contains tests for:
  - Cross-Site Scripting (XSS) vulnerabilities
  - SQL Injection vulnerabilities
  - Secure cookie attributes
  - Content Security Policy headers

### JMeter Security Tests

The framework also includes JMeter-based security tests:

- `chrono24_security_test.jmx`: Tests for common security vulnerabilities, including:
  - XSS attacks
  - SQL Injection attacks
  - CSRF vulnerabilities

## Continuous Integration

The framework uses GitHub Actions for continuous integration and deployment. The CI/CD pipeline is configured to:

1. Build the project
2. Run tests
3. Generate Allure reports
4. Deploy Allure reports to GitHub Pages

### GitHub Actions Workflow

The GitHub Actions workflow is defined in the `.github/workflows/maven.yml` file. The workflow is triggered on:

- Push to the main/master branch
- Pull requests to the main/master branch

The workflow includes the following steps:

1. Check out the code
2. Set up JDK 11
3. Build the project with Maven
4. Run tests
5. Generate Allure reports
6. Upload Allure reports as artifacts
7. Deploy Allure reports to GitHub Pages (only for the main/master branch)

### Viewing Reports

After a workflow run completes, you can view the Allure reports in two ways:

1. **GitHub Pages**: If the workflow was triggered by a push to the main/master branch, the Allure reports are deployed to GitHub Pages. You can access them at `https://<username>.github.io/<repository-name>/`.

2. **Workflow Artifacts**: For all workflow runs, the Allure reports are uploaded as artifacts. You can download them from the workflow run page on GitHub.

### Customizing the Workflow

You can customize the GitHub Actions workflow by editing the `.github/workflows/maven.yml` file. For example, you can:

- Change the trigger events
- Add more test commands
- Configure email notifications
- Add more deployment steps

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
