package podtest.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePOM {
    private WebDriver wd; //null
    private String pageURL = "https://demo.evershop.io/";

    //Why we did not used the below xpath is as everytime for every product I have to create a new xpath
   //private By itemTextLink = By.xpath(  "//div[@class='listing-tem']//a/span[text()='Nike air zoom pegasus 35']");

    private String itemTextLinkXPathLocator = "//div[@class='listing-tem']//a/span[text()='%s']";

   //Created a method to click on any element,
   // since this will navigate me to the next page,
   // I should not use Class as return type,
   // basically I can give the next page class name as return type
   // since I will be doing the chaining over there but here
    //Since this is the the only operation I doing, I am using this
    public void clickItem(String itemName) {
        String itemTextLinkLocator = itemTextLinkXPathLocator.replace( "%s", itemName);
                By itemTextLink = By.xpath(itemTextLinkLocator);
        wd.findElement(itemTextLink).click();
    }

    //constructor
    public HomePOM (WebDriver wd) {
        this.wd = wd;
    }

    public HomePOM get() {
        wd.navigate().to(pageURL);
        return this;
    }


}