package org;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class IFrames {
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
            case "safari": {
                safariOptions = new SafariOptions();
                wd = new RemoteWebDriver(new URL( "http://localhost:4444/"), safariOptions);
                break;
            }
        }
    }


    @Test
    public void iFrameConcept() throws InterruptedException {

        wd.get("https://www.hyrtutorials.com/p/frames-practice.html");
        Thread.sleep(2000);
        wd.findElement(By.id("name")).sendKeys("Raghvendra Singh");
        Thread.sleep(2000);

        wd.switchTo().frame(wd.findElement(By.id("frm1")));
        Select courseDD = new Select(wd.findElement(By.id("course")));
        courseDD.selectByVisibleText("Java");
        wd.switchTo().defaultContent();

        Thread.sleep(2000);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (wd != null) {
            wd.quit();
        }
    }
}
