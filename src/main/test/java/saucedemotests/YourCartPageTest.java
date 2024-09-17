package saucedemotests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.YourCartPage;
import pages.CheckoutYourInformationPage;
import pages.LoginPage;
import pages.ProductsPage;

import static org.junit.Assert.*;

public class YourCartPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private YourCartPage yourCartPage;
    private CheckoutYourInformationPage checkoutYourInformationPage;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        yourCartPage = new YourCartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

    }

    @Test
    public void continueShoppingTest(){
        productsPage.clickCartButton();
        yourCartPage.clickContinueShoppingButton();
        assertTrue(productsPage.isProductsPageDisplayed());
    }

    @Test
    public void checkoutButtonTest(){
        productsPage.clickCartButton();
        yourCartPage.clickCheckoutButton();
        assertTrue(checkoutYourInformationPage.getCheckoutYourInformationTitle());
    }

    @Test
    public void AddOneItemToCartAndRemoveItTest(){
        productsPage.clickAddToCartSLBL();
        productsPage.clickCartButton();

        assertEquals("Your Cart", yourCartPage.getTextFromYourCartTitle());
        assertEquals("1", productsPage.itemCountOnCart());
        assertEquals(productsPage.getSLBLTitleText(), yourCartPage.getSLBLTitleCartText());
        assertEquals("A red light isn't the desired state in testing but it sure helps when riding your bike at night. " +
                "Water-resistant with 3 lighting modes, 1 AAA battery included.", yourCartPage.getSLBLDescriptionCartText());
        assertEquals("$9.99", yourCartPage.getSLBLCartPrice());
        assertEquals("Remove", yourCartPage.getRemoveButtonText());

        yourCartPage.clickRemoveButton();

        assertFalse(yourCartPage.itemCountOnCart());
        assertFalse(yourCartPage.isSLBLItemDisplayed());
    }

    @After
    public void closeDriver(){
        driver.close();
    }
}