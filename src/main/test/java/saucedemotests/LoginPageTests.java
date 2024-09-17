package saucedemotests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.ProductsPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPageTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void successfulLoginTest(){
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        assertTrue(productsPage.isProductsPageDisplayed());
    }

    @Test
    public void emptyUsernameAndPasswordFieldTest(){
        loginPage.clickLogin();
        assertEquals("Epic sadface: Username is required", loginPage.getTextFromErrorMessage());
        assertEquals("#e2231a", loginPage.getUsernameFieldBorderBottomColor());
        assertEquals("#e2231a", loginPage.getPasswordFieldBorderBottomColor());
        assertEquals("#e2231a", loginPage.getErrorMessageContainerColor());
    }

    @Test
    public void emptyUsernameFieldTest(){
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        assertEquals("Epic sadface: Username is required",loginPage.getTextFromErrorMessage());
        assertEquals("#e2231a", loginPage.getUsernameFieldBorderBottomColor());
        assertEquals("#e2231a", loginPage.getErrorMessageContainerColor());
    }
    @Test
    public void emptyPasswordFieldTest(){
        loginPage.enterUsername("standard_user");
        loginPage.clickLogin();
        assertEquals("Epic sadface: Password is required", loginPage.getTextFromErrorMessage());
        assertEquals("#e2231a", loginPage.getPasswordFieldBorderBottomColor());
        assertEquals("#e2231a", loginPage.getErrorMessageContainerColor());
    }

    @Test
    public void wrongUsernameFieldTest(){
        loginPage.enterUsername("user123");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        assertEquals("Epic sadface: Username and password do not match any user in this service"
                ,loginPage.getTextFromErrorMessage());
        assertEquals("#e2231a", loginPage.getErrorMessageContainerColor());
    }

    @Test
    public void wrongPasswordFieldTest(){
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();
        assertEquals("Epic sadface: Username and password do not match any user in this service"
        ,loginPage.getTextFromErrorMessage());
        assertEquals("#e2231a", loginPage.getPasswordFieldBorderBottomColor());
        assertEquals("#e2231a", loginPage.getErrorMessageContainerColor());
    }

    @Test
    public void suspendedPrivilegesAccountTest(){
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getTextFromErrorMessage());
        assertEquals("#e2231a", loginPage.getErrorMessageContainerColor());
    }

    @Test
    public void loginFormErrorStateUserInterfaceTest() {
        loginPage.clickLogin();
        assertEquals("#e2231a", loginPage.getUsernameFieldBorderBottomColor());
        assertEquals("#e2231a", loginPage.getPasswordFieldBorderBottomColor());
        assertEquals("#e2231a", loginPage.getErrorMessageContainerColor());
    }

    @Test
    public void loginFormFontTypeAndSizeTest(){
        assertEquals("\"DM Sans\", Arial, Helvetica, sans-serif", loginPage.getUsernameFieldFontType());
        assertEquals("14px", loginPage.getUsernameFieldFontSize());

        assertEquals("\"DM Sans\", Arial, Helvetica, sans-serif", loginPage.getPasswordFieldFontType());
        assertEquals("14px", loginPage.getPasswordFieldFontSize());

        assertEquals("\"DM Sans\", Arial, Helvetica, sans-serif", loginPage.getLoginButtonFontType());
        assertEquals("16px", loginPage.getLoginButtonFontSize());
        assertEquals("#3ddc91", loginPage.getLoginButtonColor());
    }

    @After
    public void closeDriver(){
        driver.close();
    }
}