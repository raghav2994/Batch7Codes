
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.apache.logging.log4j.*;

public class DataProviderTest extends ThreadLocalDriver{
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

    @DataProvider(name = "LoginData")
    public Object[][] generateData() throws IOException {
        return ExcelManager.getData();
    }

    @Test(dataProvider = "LoginData")
    public void successLogin(String UN, String PW) throws MalformedURLException {
        WebDriver wd = ThreadLocalDriver.getWebDriver(); // Retrieve WebDriver from ThreadLocal
        // Inovking selenium grid
        wd.get("https://demo.evershop.io/account/login");
        wd.findElement(By.name("email")).sendKeys(UN);
        wd.findElement(By.name("password")).sendKeys(PW);
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
