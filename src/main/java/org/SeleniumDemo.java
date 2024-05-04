package org;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

public class SeleniumDemo {

    public static void main(String[] args) throws MalformedURLException {

        Wait fluentWait;

        ChromeOptions cd = new ChromeOptions();
        WebDriver wd = new RemoteWebDriver(new URL("http://localhost:4444"), cd); // Inovking selenium grid
        wd.get("https://demo.evershop.io/account/login");

        wd.findElement(By.name("email")).sendKeys("abcdeffgh");
      //  wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); /// --->> Implicit Wait Example

        // Explicit wait
     //   WebDriverWait  wait = new WebDriverWait(wd, Duration.ofSeconds(30));
     //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        //Fluent Wait
        fluentWait = new FluentWait(wd)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        wd.findElement(By.name("password")).sendKeys("gsgksjdghdk");
        wd.quit();

        //  wd.findElement(By.name("email")).sendKeys("abc");

    }
}
