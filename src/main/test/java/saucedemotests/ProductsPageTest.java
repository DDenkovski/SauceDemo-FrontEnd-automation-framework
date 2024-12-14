package saucedemotests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.YourCartPage;
import pages.LoginPage;
import pages.ProductsPage;

import static org.junit.Assert.assertEquals;

public class ProductsPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private YourCartPage yourCartPage;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        yourCartPage = new YourCartPage(driver);
    }

    @Test
    public void validationSortingComponentTest(){
        assertEquals("Name (A to Z)", productsPage.getAllOptionsFromSortingDropdownBox().get(0).getText());
        assertEquals("Name (Z to A)", productsPage.getAllOptionsFromSortingDropdownBox().get(1).getText());
        assertEquals("Price (low to high)", productsPage.getAllOptionsFromSortingDropdownBox().get(2).getText());
        assertEquals("Price (high to low)", productsPage.getAllOptionsFromSortingDropdownBox().get(3).getText());
    }

    @Test
    public void sortByAtoZTest(){
        productsPage.selectSortingDropdownOption(0);

        assertEquals("Name (A to Z)", productsPage.getTextFromSortingDropdown());
        assertEquals("Sauce Labs Backpack", productsPage.getItemTitleText());
    }

    @Test
    public void sortByZtoATest(){
        productsPage.selectSortingDropdownOption(1);

        assertEquals("Name (Z to A)", productsPage.getTextFromSortingDropdown());
        assertEquals("Test.allTheThings() T-Shirt (Red)", productsPage.getItemTitleText());
    }

    @Test
    public void sortByPriceLowToHighTest(){
        productsPage.selectSortingDropdownOption(2);

        assertEquals("Price (low to high)", productsPage.getTextFromSortingDropdown());
        assertEquals("$7.99", productsPage.getItemPrice());
    }

    @Test
    public void sortByPriceHighToLowTest(){
        productsPage.selectSortingDropdownOption(3);

        assertEquals("Price (high to low)", productsPage.getTextFromSortingDropdown());
        assertEquals("$49.99", productsPage.getItemPrice());
    }

    @Test
    public void validateSauceLabsBikeLightTest(){

        assertEquals("Sauce Labs Bike Light", productsPage.getSLBLTitleText());
        assertEquals("A red light isn't the desired state in testing but it sure helps when riding your bike at night. " +
                "Water-resistant with 3 lighting modes, 1 AAA battery included.", productsPage.getSLBLDescriptionText());
        assertEquals("$9.99", productsPage.getSLBLPrice());
    }

    @Test
    public void validateSauceLabsFleeceJacketTest(){

        assertEquals("Sauce Labs Fleece Jacket", productsPage.getSLFJText());
        assertEquals("It's not every day that you come across a midweight quarter-zip fleece jacket capable of " +
                "handling everything from a relaxing day outdoors to a busy day at the office.", productsPage.getSLFJDescriptionText());
        assertEquals("$49.99", productsPage.getSLFJPrice());
    }

    @After
    public void closeDriver(){
        driver.close();
    }
}