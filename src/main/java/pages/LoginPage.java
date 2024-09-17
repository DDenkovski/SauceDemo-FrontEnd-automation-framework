package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;


public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.xpath( "//*[@id='login_button_container']/div/form/div[3]/h3");
    private By getErrorMessageContainer = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]");

    public void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public String getTextFromErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

    public String getUsernameFieldBorderBottomColor(){
        Color loginButtonBackgroundColour = Color.fromString(driver.findElement(usernameField).getCssValue("border-bottom-color"));
    return loginButtonBackgroundColour.asHex();
    }

    public String getPasswordFieldBorderBottomColor(){
        Color loginButtonBackgroundColour = Color.fromString(driver.findElement(passwordField).getCssValue("border-bottom-color"));
        return loginButtonBackgroundColour.asHex();
    }

    public String getErrorMessageContainerColor(){
        Color loginButtonBackgroundColour = Color.fromString(driver.findElement(getErrorMessageContainer).getCssValue("background-color"));
        return loginButtonBackgroundColour.asHex();
    }

    public String getUsernameFieldFontType(){
        return driver.findElement(usernameField).getCssValue("font-family");
    }

    public String getUsernameFieldFontSize(){
        return driver.findElement(usernameField).getCssValue("font-size");
    }

    public String getPasswordFieldFontType(){
        return driver.findElement(passwordField).getCssValue("font-family");
    }

    public String getPasswordFieldFontSize(){
        return driver.findElement(passwordField).getCssValue("font-size");
    }

    public String getLoginButtonFontType(){
        return driver.findElement(loginButton).getCssValue("font-family");
    }

    public String getLoginButtonFontSize(){
        return driver.findElement(loginButton).getCssValue("font-size");
    }

    public String getLoginButtonColor(){
        Color loginButtonBackgroundColour = Color.fromString(driver.findElement(loginButton).getCssValue("background-color"));
        return loginButtonBackgroundColour.asHex();
    }
}