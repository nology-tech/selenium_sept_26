package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;

public class BasePage {

    public final WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.explicitWait()));
    }

    // in this class we want some methods that will be needed for the entire
    // application, like selecting and typing in an input

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void type(By locator, String inputText) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(inputText);
    }

    public void click(By locator) {
        WebElement element = waitForClickable(locator);
        element.click();
    }

    public String textOf(By locator) {
        return waitForVisible(locator).getText();
    }

}
