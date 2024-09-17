package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    private WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
    }

    private By checkoutOverviewTitle = By.className("title");
    private By paymentInformation = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[2]");
    private By shippingInformation = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[4]");
    private By taxLabel = By.className("summary_tax_label");
    private By priceTotal = By.className("summary_total_label");
    private By priceSLFJ = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]/div");
    private By priceSLBL = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div");

    //Cancel and Finish Button
    private By cancelButton = By.id("cancel");
    private By finishButton = By.id("finish");


    public String getCheckoutOverviewTitle(){
        return driver.findElement(checkoutOverviewTitle).getText();
    }

    public Boolean getPaymentInformation() {
        return driver.findElement(paymentInformation).getText().equals("SauceCard #31337");
    }

    public Boolean getShippingInformation() {
        return driver.findElement(shippingInformation).getText().equals("Free Pony Express Delivery!");
    }

    public String getTaxLabelPrice() {
        return driver.findElement(taxLabel).getText();
    }

    public String getPriceTotal() {
        return driver.findElement(priceTotal).getText();
    }

    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }

    public void clickFinishButton(){
        driver.findElement(finishButton).click();
    }

    private float parsePrice(String priceText) {
        try {
            return Float.parseFloat(priceText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format: " + priceText);
        }
        return 0.0f;
    }

    public double getSLBLPriceAsFloat() {
        String priceText = driver.findElement(priceSLBL).getText().substring(1); // Remove $
        return parsePrice(priceText);
    }

    public double getSLFJPriceAsFloat() {
        String priceText = driver.findElement(priceSLFJ).getText().substring(1); // Remove $
        return parsePrice(priceText);
    }

    public double getTaxLabelPriceAsFloat() {
        String priceText = driver.findElement(taxLabel).getText().substring(6); // Remove $
        return parsePrice(priceText);
    }
}