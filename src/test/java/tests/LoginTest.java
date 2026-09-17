package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import config.ConfigReader;
import pages.LoginPage;
import pages.ProductsPage;
import shared.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void validCredentialsLogInSuccessfully() {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.successfullyLogin();
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("inventory.html"),
                "The URL does not contain inventory.html, instead it is " + url);
        assertEquals("Products", productsPage.titleText());

        // WebElement usernameInput = driver.findElement(By.id("user-name"));
        // usernameInput.sendKeys(ConfigReader.standardUsername());

        // driver.findElement(By.id("password")).sendKeys(ConfigReader.standardPassword());

        // driver.findElement(By.id("login-button")).click();

        // String currentUrl = driver.getCurrentUrl();
        // System.out.println(currentUrl);
        // assertTrue(currentUrl.contains("inventory.html"),
        // "The URL does not contain inventory.html, instead it is " + currentUrl);

        // // WebElement title =
        // //
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        // // String pageTitle = title.getText();
        // // assertEquals("Products", pageTitle);

    }

    @Test
    public void incorrectPasswordShowsErrorMEssage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.attemptLoginAs(ConfigReader.standardUsername(), "INVALID PASSWORD");

        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(expectedErrorMessage, loginPage.getErrorMessage());

    }

}
