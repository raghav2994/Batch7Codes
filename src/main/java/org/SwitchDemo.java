package org;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class SwitchDemo {


    ChromeOptions chromeOptions;
    WebDriver wd;

    @BeforeClass(alwaysRun = true)
    public void beforeClassMethod() throws MalformedURLException {
        chromeOptions = new ChromeOptions();
        wd = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
    }

    @BeforeMethod(alwaysRun = false)
    public void preMethodsteps () throws MalformedURLException {
        wd.get("https://selectorshub.com/iframe-scenario/");
    }

    //Dummy method to show how you work on alerts

    @Test(enabled = false)
    public void alertBox() {
        wd.findElement(By.xpath( "//button[@class='alert']")).click();
        System.out.println(wd.switchTo().alert().getText());
        wd.switchTo().alert().accept();
    }

    @Test(enabled = false)
    public void multipleTab() {

        String currentTabHandle = wd.getWindowHandle();
        System.out.println(currentTabHandle);
        //Under a same page the below three links were present and I basically clicked on it
        wd.findElement(By.xpath(  "//a[@link='Website-id']")).click();

        wd.findElement(By.xpath( "//a[@link='LinkedIn-id']")).click();

        wd.findElement(By.xpath(  "//a[@link='Youtube']")).click();

        Set<String> handles= wd.getWindowHandles();
        for (String s1 : handles) {
            System.out.println(s1);
        }

        wd.switchTo().window(currentTabHandle); // Move to the main the tab
        wd.getTitle(); // and fetch the title for the same

    }

    @Test(enabled = false)
    public void iFrameConcept() {


       // wd.findElement(By.xpath(  "//input[@id='jex']")).sendKeys("Raghav");
        wd.switchTo().frame("pact1");
        wd.findElement(By.xpath(  "//input[@id='inp_val']")).sendKeys("Raghav");
        wd.switchTo().frame("pact2");
        wd.findElement(By.xpath(  "//input[@id='jex']")).sendKeys("Singh");
        wd.switchTo().parentFrame(); // This will move to the Parent Frame of Pact 2 which is Pact1
        wd.switchTo().parentFrame(); // This will move to the Parent Frame of Pact 1 now

    }


    @Test(groups = {"Action"})
    public void actionTest() {
        Actions a1 = new Actions(wd);

        //Keyboard adding uppercase text
        wd.get("https://demo.evershop.io/account/login");
        WebElement el1 = wd.findElement(By.name("email"));
        // Concept of method chaining
        a1.moveToElement(el1).click().keyDown(Keys.SHIFT).sendKeys("raghav").keyUp(Keys.SHIFT).perform();

        //If you want to do the right click then you used use "Context Click"
         a1.moveToElement(el1).contextClick().perform();
        // or you can write the below line
      //  a1.contextClick(el1).perform();


    }

}

