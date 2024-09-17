package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class YourCartPage {

    private WebDriver driver;

    public YourCartPage(WebDriver driver){
        this.driver = driver;
    }

    //Add to cart item and assert from products page Scenario 2 (homework from class 34, 02.09.2024)
    private By itemSauceLabsBikeLightTitle = By.id("item_0_title_link");
    private By sauceLabsBikeLightDescription = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[1]");
    private By sauceLabsBikeLightPrice = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div");

    private By yourCartTitle = By.className("title");

    //Return to Products page Scenario 3 (homework from class 34, 02.09.2024)
    private By continueShoppingButton = By.id("continue-shopping");
    private By checkoutButton = By.id("checkout");

    //Remove item from cart
    private By removeButton = By.id("remove-sauce-labs-bike-light");
    private By cartItemNumber = By.id("shopping_cart_container");

    public String getSLBLTitleCartText(){
        return driver.findElement(itemSauceLabsBikeLightTitle).getText();
    }

    public String getSLBLDescriptionCartText() {
        return driver.findElement(sauceLabsBikeLightDescription).getText();
    }

    public String getSLBLCartPrice(){
        return driver.findElement(sauceLabsBikeLightPrice).getText();
    }

    public String getTextFromYourCartTitle(){
        return driver.findElement(yourCartTitle).getText();
    }

    public void clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
    }

    public String getRemoveButtonText(){
        return driver.findElement(removeButton).getText();
    }

    public void clickRemoveButton (){
        driver.findElement(removeButton).click();
    }

    public boolean itemCountOnCart(){
        return driver.findElement(cartItemNumber).getText().equals("1");
    }

    public Boolean isSLBLItemDisplayed(){
        try{
            driver.findElement(itemSauceLabsBikeLightTitle).getText();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }
}