package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {

    private WebDriver driver;

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    private By checkoutCompleteTitle = By.className("title");
    private By checkoutCompleteOrder = By.className("complete-header");
    private By checkoutCompleteText = By.className("complete-text");
    private By backHomeButton = By.id("back-to-products");
    private By cartItemNumber = By.id("shopping_cart_container");

    public boolean isCheckoutCompletePageDisplayed(){
        return driver.findElement(checkoutCompleteTitle).getText().equals("Checkout: Complete!");
    }

    public String getCheckoutCompleteOrder(){
        return driver.findElement(checkoutCompleteOrder).getText();
    }

    public String getCheckoutCompleteText(){
        return driver.findElement(checkoutCompleteText).getText();
    }

    public void clickBackHomeButton(){
        driver.findElement(backHomeButton).click();
    }

    public Boolean itemCountOnCartForCompleteOrder(){
        return driver.findElement(cartItemNumber).getText().equals("2");
    }

    public String getThankYouForYourOrderFontType(){
        return driver.findElement(checkoutCompleteOrder).getCssValue("font-family");
    }

    public String getThankYouForYourOrderFontSize(){
        return driver.findElement(checkoutCompleteOrder).getCssValue("font-size");
    }
}
