package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ParametersExample {
    ChromeOptions chromeoptions;

    WebDriver wd;
    FirefoxOptions firefoxOptions;

    @Parameters({"browser"})
    @BeforeClass
    public void BC(String browserName) throws MalformedURLException {
        System.out.println("value of Browser is: " + browserName);
        switch (browserName) {
            case "chrome":
                chromeoptions = new ChromeOptions();
                wd = new RemoteWebDriver(new URL("http://localhost:4444/"), chromeoptions);
                break;

            case "firefox": {
                firefoxOptions = new FirefoxOptions();
                wd = new RemoteWebDriver(new URL("http://localhost:4444/"), firefoxOptions);
                break;
            }
            default: {
                chromeoptions = new ChromeOptions();
                wd = new RemoteWebDriver(new URL("http://localhost:4444/"), chromeoptions);
                break;
            }
        }
    }

    @Test
    public void successLogin(){
        wd.get("https://demo.evershop.io/account/login");
        wd.findElement(By.name("email")).sendKeys("akhiljda@gmail.com");
        wd.findElement(By.name("password")).sendKeys("Password");
        wd.findElement(By.xpath("//button [@type='submit']")).click();
    }
}
