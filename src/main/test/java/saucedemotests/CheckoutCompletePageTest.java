package saucedemotests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.Assert.*;

public class CheckoutCompletePageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private YourCartPage yourCartPage;
    private CheckoutYourInformationPage checkoutYourInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        yourCartPage = new YourCartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @Test
    public void validationOfCompleteOrderTest() {
        productsPage.clickAddToCartSLBL();
        productsPage.clickAddToCartSLFJ();
        productsPage.clickCartButton();
        yourCartPage.clickCheckoutButton();
        checkoutYourInformationPage.enterFirstName("Denis");
        checkoutYourInformationPage.enterLastName("Denkovski");
        checkoutYourInformationPage.enterZipCode("1300");
        checkoutYourInformationPage.clickContinueButton();
        assertEquals("Checkout: Overview", checkoutOverviewPage.getCheckoutOverviewTitle());
        checkoutOverviewPage.clickFinishButton();
        assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed());
        assertEquals("Thank you for your order!", checkoutCompletePage.getCheckoutCompleteOrder());
        assertEquals("\"DM Mono\", sans-serif", checkoutCompletePage.getThankYouForYourOrderFontType());
        assertEquals("24px", checkoutCompletePage.getThankYouForYourOrderFontSize());
        assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!"
                , checkoutCompletePage.getCheckoutCompleteText());
        assertFalse(checkoutCompletePage.itemCountOnCartForCompleteOrder());
        checkoutCompletePage.clickBackHomeButton();
        assertTrue(productsPage.isProductsPageDisplayed());
        assertFalse(productsPage.itemCountOnCartForCompleteOrder());
    }

    @After
    public void closeDriver() {
        driver.close();
    }
}
