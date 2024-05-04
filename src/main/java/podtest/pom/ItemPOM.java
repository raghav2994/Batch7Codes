package podtest.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPOM {

    private WebDriver wd; //null


    private final By itemNameText = By.xpath(  "//h1[@class='product-single-name']");

    private final By quantityTextBox = By.xpath(  "//input[@name='qty']");

    private final By addToCartButton = By.xpath(  "//button[@type='button']");

    private String itemSizeXpathLocator = "(//ul[contains(@class, 'variant-option-list')])[1]/li/a[text()='%s']";
    private String itemColourXpathLocator = "(//ul[contains(@class, 'variant-option-list')])[2]/li/a[text()='%s']";


    public ItemPOM (WebDriver wd) {
        this.wd = wd;
    }


    public String getItemName(){
        return wd.findElement(itemNameText).getText();
    }

    public ItemPOM fillQuantity(String quantity){
        wd.findElement(quantityTextBox).clear();
        wd.findElement(quantityTextBox).sendKeys(quantity);
        return this;
    }

    public ItemPOM selectSize(String size){
        String itemSizeLocator = itemSizeXpathLocator.replace( "%s", size);
        By itemSizeLink = By.xpath(itemSizeLocator);
        wd.findElement(itemSizeLink).click();
        return this;
    }

    public ItemPOM selectColour(String colour){
        String itemColourLocator = itemColourXpathLocator.replace( "%s", colour);
        By itemColourLink = By.xpath(itemColourLocator);
        wd.findElement(itemColourLink).click();
        return this;
    }

    public ItemPOM clickAddToCart(){
        wd.findElement(addToCartButton).click();
        return this;
    }

}
