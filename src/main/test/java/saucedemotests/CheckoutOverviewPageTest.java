package saucedemotests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutOverviewPageTest {

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
    public void cancelButton(){
        productsPage.clickCartButton();
        yourCartPage.clickCheckoutButton();
        checkoutYourInformationPage.enterFirstName("Denis");
        checkoutYourInformationPage.enterLastName("Denkovski");
        checkoutYourInformationPage.enterZipCode("1300");
        checkoutYourInformationPage.clickContinueButton();
        assertEquals("Checkout: Overview", checkoutOverviewPage.getCheckoutOverviewTitle());
        checkoutOverviewPage.clickCancelButton();
        assertTrue(productsPage.isProductsPageDisplayed());
    }

    @Test
    public void finishButton(){

        productsPage.clickCartButton();
        yourCartPage.clickCheckoutButton();
        checkoutYourInformationPage.enterFirstName("Denis");
        checkoutYourInformationPage.enterLastName("Denkovski");
        checkoutYourInformationPage.enterZipCode("1300");
        checkoutYourInformationPage.clickContinueButton();
        assertEquals("Checkout: Overview", checkoutOverviewPage.getCheckoutOverviewTitle());
        checkoutOverviewPage.clickFinishButton();
        assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed());
    }

    @Test
    public void validationOfPaymentShippingTaxAndPriceTest(){
        productsPage.clickAddToCartSLBL();
        productsPage.clickAddToCartSLFJ();
        productsPage.clickCartButton();
        yourCartPage.clickCheckoutButton();
        checkoutYourInformationPage.enterFirstName("Denis");
        checkoutYourInformationPage.enterLastName("Denkovski");
        checkoutYourInformationPage.enterZipCode("1300");
        checkoutYourInformationPage.clickContinueButton();
        assertTrue(checkoutOverviewPage.getPaymentInformation());
        assertTrue(checkoutOverviewPage.getShippingInformation());
        double finalValue = checkoutOverviewPage.getSLBLPriceAsFloat() + checkoutOverviewPage.getSLFJPriceAsFloat() + checkoutOverviewPage.getTaxLabelPriceAsFloat();
        finalValue = Math.round(finalValue * 100D) / 100D;

        assertEquals("Tax: $4.80", checkoutOverviewPage.getTaxLabelPrice());
        assertEquals("Total: $" + finalValue, checkoutOverviewPage.getPriceTotal());
    }

    @After
    public void closeDriver() {
        driver.close();
    }
}