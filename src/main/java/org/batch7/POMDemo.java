package org.batch7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;
import podtest.pom.HomePOM;
import podtest.pom.ItemPOM;
import podtest.pom.LoginPOM;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class POMDemo {

    ChromeOptions chromeOptions;
    WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void beforeClassMethod() throws MalformedURLException {


        chromeOptions = new ChromeOptions();
        wd = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);

    }


    @Test
    public void addCartTest() throws InterruptedException {
        LoginPOM login = new LoginPOM(wd);
        HomePOM home = new HomePOM(wd);
        ItemPOM item = new ItemPOM(wd);

        login.get();
        login.fillCredentials();
        login.clickOnSubmit();
        /*login.get().
                fillCredentials(UN, PW).
                clickOnSubmit();*/
        Thread.sleep(500); //Go for fluent wait instead of sleep
        home.clickItem("Mix and match chuck taylor all star");
        Assert.assertEquals(item.getItemName(), "Mix And Match Chuck Taylor All Star"); // Validation I am doing with the returned string
        Thread.sleep(200);
        item.fillQuantity("2").
                selectSize("L").
                selectColour("Brown").
                clickAddToCart();

    }

    @AfterClass
    public void TearDown() throws InterruptedException {
        Thread.sleep(10000); // Just to show you the execution, I have added it
        wd.quit();
    }
}