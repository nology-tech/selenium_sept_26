package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import pages.LoginPage;
import pages.ProductsPage;
import shared.BaseTest;

public class ProductsAddToCartTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeEach
    public void setupProducts() {
        productsPage = new LoginPage(driver).successfullyLogin();
    }

    @Test
    public void addingProductToCartShowsCartBadgeWithCorrectNumber() {
        productsPage.addBackPackToCart();
        assertEquals(1, productsPage.getCartBadgeCount());
    }

    // add one then delete one and make sure the bagde disappeared
    @Test
    public void addingAndRemovingProductShowsAndHidesBadge() {
        productsPage.addBackPackToCart();
        assertTrue(productsPage.isBadgeVisible());
        productsPage.removeBackpack();
        assertFalse(productsPage.isBadgeVisible());

    }

    @Test
    public void addingMultipleProductsToCartShowsCartBadgeWithCorrectNumber() {
        List<WebElement> allProductCards = productsPage.getAllProductCards();
        productsPage.addToCart(allProductCards.get(0));
        productsPage.addToCart(allProductCards.get(1));
        productsPage.addToCart(allProductCards.get(2));
        assertEquals(3, productsPage.getCartBadgeCount());

    }

    // add and remove but all
    // add and remove all - make sure the badge is gone

}
