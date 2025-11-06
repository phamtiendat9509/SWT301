

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.DriverFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests using Page Object Model")
public class LoginTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static LoginPage loginPage;

    @BeforeAll
    static void setUp() {
        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    @Order(1)
    void testLoginSuccess() {
        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSuccessLocator()));
        assertTrue(successMsg.getText().contains("You logged into a secure area!"));
    }

    @Test
    @Order(2)
    void testLoginFail() {
        loginPage.navigate();
        loginPage.login("wronguser", "wrongpassword");
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getErrorLocator()));
        assertTrue(errorMsg.getText().toLowerCase().contains("invalid"));
    }

    @Order(3)
    @ParameterizedTest(name = "Test Login - Username: {0}, Password: {1}")
    @CsvSource({
            "tomsmith, SuperSecretPassword!, success",
            "wronguser, SuperSecretPassword!, error",
            "tomsmith, wrongpassword, error",
            "'', '', error"
    })
    @DisplayName("Multiple login attempts using @CsvSource")
    void testLoginWithMultipleParameters(String username, String password, String expectedResult) {
        loginPage.navigate();
        loginPage.login(username, password);

        By messageLocator = expectedResult.equals("success") ? loginPage.getSuccessLocator() : loginPage.getErrorLocator();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));

        if (expectedResult.equals("success")) {
            assertTrue(message.getText().contains("You logged into a secure area!"));
        } else {
            assertTrue(message.getText().toLowerCase().contains("invalid") || message.getText().toLowerCase().contains("error"));
        }
    }

    @Order(4)
    @ParameterizedTest(name = "Test login with: {0} / {1}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @DisplayName("Login with data from external csv file")
    void testLoginWithCSV(String username, String password, String expectedResult) {
        loginPage.navigate();

        // Convert null to empty string and trim to avoid errors from pasted CSV values
        username = (username == null) ? "" : username.trim();
        password = (password == null) ? "" : password.trim();

        loginPage.login(username, password);

        By messageLocator = expectedResult.equals("success") ? loginPage.getSuccessLocator() : loginPage.getErrorLocator();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));

        if (expectedResult.equals("success")) {
            assertTrue(message.getText().contains("You logged into a secure area!"));
        } else {
            assertTrue(message.getText().toLowerCase().contains("invalid") || message.getText().toLowerCase().contains("error"));
        }
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
