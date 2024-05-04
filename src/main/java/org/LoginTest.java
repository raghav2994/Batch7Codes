
package org;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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

public class LoginTest extends ThreadLocalDriver{
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;
    WebDriver wd;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browserName) throws MalformedURLException {
        System.out.println("Value of Browser is: "+ browserName);
        switch (browserName) {
            case "chrome": {
                chromeOptions = new ChromeOptions();
                wd = new RemoteWebDriver( new URL( "http://localhost:4444/"), chromeOptions);
                ThreadLocalDriver.setWebDriver(wd); // Store WebDriver in ThreadLocal
                break;
            }

            case "firefox": {
                firefoxOptions = new FirefoxOptions();
                wd = new RemoteWebDriver(new URL( "http://localhost:4444/"), firefoxOptions);
                ThreadLocalDriver.setWebDriver(wd); // Store WebDriver in ThreadLocal
                break;
            }
        }
    }

    @Test
    public void successLogin() throws MalformedURLException {
        WebDriver wd = ThreadLocalDriver.getWebDriver(); // Retrieve WebDriver from ThreadLocal

        // Inovking selenium grid
        wd.get("https://demo.evershop.io/account/login");
        wd.findElement(By.name("email")).sendKeys("akhiljda@gmail.com");
        wd.findElement(By.name("password")).sendKeys("Password");
        wd.findElement(By.xpath("//button [@type='submit']")).click();
    }

    @Test
    public void unsuccessLogin() throws MalformedURLException {
        WebDriver wd = ThreadLocalDriver.getWebDriver(); // Retrieve WebDriver from ThreadLocal

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

    @AfterMethod
    public void tearDown() {
        WebDriver wd = ThreadLocalDriver.getWebDriver(); // Retrieve WebDriver from ThreadLocal
        if (wd != null) {
            wd.quit();
            ThreadLocalDriver.setWebDriver(null); // Remove WebDriver from ThreadLocal
        }
    }

}
