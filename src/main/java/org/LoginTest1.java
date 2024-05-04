
package org;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import podtest.pom.HomePOM;
import podtest.pom.ItemPOM;
import podtest.pom.LoginPOM;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.apache.logging.log4j.*;

public class LoginTest1 extends ThreadLocalDriver{
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;

    SafariOptions safariOptions;
    WebDriver wd;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browserName) throws MalformedURLException {
        System.out.println("Value of Browser is: "+ browserName);
        switch (browserName) {
            case "chrome": {
                chromeOptions = new ChromeOptions();
                wd = new RemoteWebDriver( new URL( "http://localhost:4444/"), chromeOptions);
                break;
            }
            case "firefox": {
                firefoxOptions = new FirefoxOptions();
                wd = new RemoteWebDriver(new URL( "http://localhost:4444/"), firefoxOptions);
                break;
            }
        }
    }


    @Test(groups = {"Sanity2"}, priority = 1)
    public void successLogin() throws MalformedURLException, InterruptedException {

        // Inovking selenium grid
        wd.get("https://demo.evershop.io/account/login");
        wd.findElement(By.name("email")).sendKeys("akhiljda@gmail.com");
        wd.findElement(By.name("password")).sendKeys("Password");
        wd.findElement(By.xpath("//button [@type='submit']")).click();
        Thread.sleep(2000);
        wd.findElement(By.xpath("//div[@class='listing-tem']//a/span[text()='Nike air zoom pegasus 35']")).click();
        Thread.sleep(2000);


    }

    @Test(priority = 2)
    public void unsuccessLogin() throws MalformedURLException {

        // Inovking selenium grid
        wd.get("https://demo.evershop.io/account/login");
        FluentWait fluentWait = new FluentWait(wd)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5));
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button [@type='submit']")));
        wd.findElement(By.name("email")).sendKeys("akhiljda@gmail.com");
        wd.findElement(By.name("password")).sendKeys("Password");
        wd.findElement(By.xpath("//button [@type='submit']")).click();

    }

    @Test(groups = {"Sanity"})
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

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (wd != null) {
            wd.quit();
        }
    }

}
