package saucedemotests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.Assert.assertEquals;

public class CheckoutYourInformationPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private YourCartPage yourCartPage;
    private CheckoutYourInformationPage checkoutYourInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;

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

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        productsPage.clickCartButton();
        yourCartPage.clickCheckoutButton();
    }

    @Test
    public void clickCancelButton(){
        checkoutYourInformationPage.clickCancelButton();
        assertEquals("Your Cart", yourCartPage.getTextFromYourCartTitle());
    }

    @Test
    public void enterAllRequiredInformation(){
        checkoutYourInformationPage.enterFirstName("Denis");
        checkoutYourInformationPage.enterLastName("Denkovski");
        checkoutYourInformationPage.enterZipCode("1300");
        checkoutYourInformationPage.clickContinueButton();
        assertEquals("Checkout: Overview", checkoutOverviewPage.getCheckoutOverviewTitle());
    }

    @Test
    public void errorMessageForFirstName(){
        checkoutYourInformationPage.clickContinueButton();
        assertEquals("Error: First Name is required", checkoutYourInformationPage.getTextFromErrorMessage());
    }

    @Test
    public void errorMessageForLastName(){
        checkoutYourInformationPage.enterFirstName("Denis");
        checkoutYourInformationPage.clickContinueButton();
        assertEquals("Error: Last Name is required", checkoutYourInformationPage.getTextFromErrorMessage());
    }
    
    @Test
    public void errorMessageForZipCode(){
        checkoutYourInformationPage.enterFirstName("Denis");
        checkoutYourInformationPage.enterLastName("Denkovski");
        checkoutYourInformationPage.clickContinueButton();
        assertEquals("Error: Postal Code is required", checkoutYourInformationPage.getTextFromErrorMessage());
    }

    @After
    public void closeDriver(){
        driver.close();
    }
}