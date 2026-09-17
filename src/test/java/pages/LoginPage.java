package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.ConfigReader;

public class LoginPage extends BasePage {

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginBtn = By.id("login-button");
    private By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage attemptLoginAs(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginBtn);
        return this;

    }

    public ProductsPage loginAs(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginBtn);
        return new ProductsPage(driver);
    }

    public ProductsPage successfullyLogin() {
        type(usernameInput, ConfigReader.standardUsername());
        type(passwordInput, ConfigReader.standardPassword());
        click(loginBtn);
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        return textOf(error);
    }

}
