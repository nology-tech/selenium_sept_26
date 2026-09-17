# Setting up a full testing project

## Create a Java Project

Add all the dependencies:

- [selenium](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
  <!-- TestNG -->
- [Junit](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.13.4)

## Create a config.properties file

```
browser=chrome
base.url=https://www.saucedemo.com/
explicit.wait.seconds=10
```

Create a class that reads values from the file. The purpose of this:

- we can override the values when running our tests:

```bash
mvn test -Dbrowser=firefox
```

- to do so, we don't need to change a single line of our code
- easy to run in different places, like Github actions
- if we need to change our key names, we only need to do it once

## Create a BaseTest class

This class will hold everything that is shared between every single test
What needs t0 happen before each test
What needs to happen after each test

## Test classes

```java

public class LoginTest extends BaseTest {

    @Test
    public void validCredentialsLogInSuccessfully() {

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(ConfigReader.standardUsername());

        driver.findElement(By.id("password")).sendKeys(ConfigReader.standardPassword());

        driver.findElement(By.id("login-button")).click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"),
                "The URL does not contain inventory.html, instead it is " + currentUrl);

        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        String pageTitle = title.getText();
        assertEquals("Products", pageTitle);

    }

    @Test
    public void incorrectPasswordShowsErrorMEssage() {

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(ConfigReader.standardUsername());

        driver.findElement(By.id("password")).sendKeys("INCORRECT PASSWORD");

        driver.findElement(By.id("login-button")).click();

        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));

        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(expectedErrorMessage, error.getText());

    }

}

```

We needed to repeat the same things for our login page tests:

- selecting the username input
- typing in the input
- selecting the password input
- typing in that input
- selecting the button
- clicking

When testing the entire application, doesn't matter what page:

- cart page
- products page
- login page
- checkout page

- we will still need to select inputs
- we will still need to type in them
- we will still need to click buttons

- Another problem - what if different Locators values change? Like the username input id

The solution to this problem - Page Object Model
