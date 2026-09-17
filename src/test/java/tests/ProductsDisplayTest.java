package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import pages.LoginPage;
import pages.ProductsPage;
import shared.BaseTest;

public class ProductsDisplayTest extends BaseTest {

    private ProductsPage productsPage;
    private Pattern priceFormat = Pattern.compile("\\$\\d+\\.\\d{2}");

    @BeforeEach
    public void setupPage() {
        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.successfullyLogin();
        System.out.println(driver.getCurrentUrl());

    }

    // $xxxx.xx
    @Test
    public void everyProductCardHasCorrectlyFormattedPrice() {

        List<WebElement> all = productsPage.getAllProductCards();
        for (WebElement product : all) {
            String price = productsPage.getProductPrice(product);
            assertTrue(priceFormat.matcher(price).matches(), "Price not formatted correctly: " + price);
        }

    }

}
