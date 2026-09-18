package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pages.LoginPage;
import pages.ProductsPage;
import shared.BaseTest;

public class ProductsSortTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeEach
    public void setupPage() {
        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.successfullyLogin();

    }

    // a-z name
    @Test
    public void selectAZSortsProductsAlphabetically() {
        productsPage.selectSortOption("Name (A to Z)");
        List<String> actual = productsPage.getAllProductNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(String::compareTo);
        assertEquals(expected, actual);
    }

    // z-a name

    @Test
    public void selectZASortsProductsAlphabeticallyDesc() {
        productsPage.selectSortOption("Name (Z to A)");
        List<String> actual = productsPage.getAllProductNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Comparator.reverseOrder());
        assertEquals(expected, actual);
    }

    // price high to low
    @Test
    public void selectPriceHighToLowSortsByPriceDesc() {
        productsPage.selectSortOption("Price (high to low)");
        List<Double> actual = productsPage.getAllProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Comparator.reverseOrder());
        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @CsvSource({
            "Name (A to Z), Sauce Labs Backpack",
            "Name (Z to A), Test.allTheThings() T-Shirt (Red)",
            "Price (high to low), Sauce Labs Fleece Jacket",
            "Price (low to high), Sauce Labs Onesie)"
    })
    public void sortingProductsPlacesExpectedProductFirst(String sortOption, String expectedProductName) {
        productsPage.selectSortOption(sortOption);
        System.out.println(sortOption + " sort option");
        List<String> productNames = productsPage.getAllProductNames();
        assertEquals(expectedProductName, productNames.get(0));

    }

}
