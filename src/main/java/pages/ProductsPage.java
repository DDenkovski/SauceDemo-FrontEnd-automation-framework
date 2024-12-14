package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage {

    private WebDriver driver;

    private By productsTitle = By.className("title");
    private By itemName = By.className("inventory_item_name");
    private By itemPrice = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div");
    private By orderingDropdownBox = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");

    //Sauce Labs Bike Light Scenario 1 (homework from class 34, 02.09.2024)
    private By itemSauceLabsBikeLightTitle = By.id("item_0_title_link");
    private By sauceLabsBikeLightDescription = By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div[1]/div");
    private By sauceLabsBikeLightPrice = By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div[2]/div");

    //Sauce Labs Fleece Jacket Bonus from Scenario 1 (homework from class 34, 02.09.2024)
    private By itemSauceLabsFleeceJacketTitle = By.id("item_5_title_link");
    private By sauceLabsFleeceJacketDescription = By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[2]/div[1]/div");
    private By sauceLabsFleeceJacketPrice = By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[2]/div[2]/div");

    //Add to cart
    private By slblAddToCartButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By slfjAddToCartButton = By.id("add-to-cart-sauce-labs-fleece-jacket");
    private By cartButton = By.id("shopping_cart_container");
    private By cartItemNumber = By.id("shopping_cart_container");

    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isProductsPageDisplayed(){
        return driver.findElement(productsTitle).getText().equals("Products");
    }

    public String getItemTitleText(){
        return driver.findElement(itemName).getText();
    }

    public String getItemPrice(){
        return driver.findElement(itemPrice).getText();
    }

    public List<WebElement> getAllOptionsFromSortingDropdownBox(){
        Select sortingDropdownBox = new Select(driver.findElement(orderingDropdownBox));
        return sortingDropdownBox.getOptions();
    }

    public void selectSortingDropdownOption(int optionNumber){
        Select sortingDropdownBox = new Select(driver.findElement(orderingDropdownBox));
        sortingDropdownBox.selectByIndex(optionNumber);
    }

    public String getTextFromSortingDropdown(){
        Select sortingDropdownBox = new Select(driver.findElement(orderingDropdownBox));
        return sortingDropdownBox.getFirstSelectedOption().getText();
    }

//Sauce Labs Bike Light
    public String getSLBLTitleText(){
        return driver.findElement(itemSauceLabsBikeLightTitle).getText();
    }

    public String getSLBLDescriptionText() {
        return driver.findElement(sauceLabsBikeLightDescription).getText();
    }

    public String getSLBLPrice(){
        return driver.findElement(sauceLabsBikeLightPrice).getText();
    }

//Sauce Labs Fleece Jacket
    public String getSLFJText(){
        return driver.findElement(itemSauceLabsFleeceJacketTitle).getText();
    }

    public String getSLFJDescriptionText() {
        return driver.findElement(sauceLabsFleeceJacketDescription).getText();
    }

    public String getSLFJPrice(){
        return driver.findElement(sauceLabsFleeceJacketPrice).getText();
    }

//Add to cart button
    public void clickAddToCartSLBL(){
        driver.findElement(slblAddToCartButton).click();
    }

    public void clickAddToCartSLFJ(){
        driver.findElement(slfjAddToCartButton).click();
    }

    public void clickCartButton(){
        driver.findElement(cartButton).click();
    }

    public String itemCountOnCart(){
        return driver.findElement(cartItemNumber).getText();
    }

    public Boolean itemCountOnCartForCompleteOrder(){
        return driver.findElement(cartItemNumber).getText().equals("2");
    }
}