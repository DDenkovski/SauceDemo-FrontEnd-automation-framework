package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformationPage {

    private WebDriver driver;

    public CheckoutYourInformationPage(WebDriver driver){
        this.driver = driver;
    }

    //Checkout Scenario 4 (homework from class 34, 02.09.2024)
    private By checkoutYourInformation = By.className("title");

    //class 35 (from 04.09.2024)
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipCode = By.id("postal-code");
    private By errorMessages = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3");

    //Cancel button and Continue button
    private By cancelButton = By.id("cancel");
    private By continueButton = By.id("continue");


    public boolean getCheckoutYourInformationTitle(){
        return driver.findElement(checkoutYourInformation).getText().equals("Checkout: Your Information");
    }

    public void enterFirstName(String value){
        driver.findElement(firstName).sendKeys(value);
    }

    public void enterLastName(String value){
        driver.findElement(lastName).sendKeys(value);
    }

    public void enterZipCode(String value){
        driver.findElement(zipCode).sendKeys(value);
    }

    public String getTextFromErrorMessage(){
        return driver.findElement(errorMessages).getText();
    }

    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
}
