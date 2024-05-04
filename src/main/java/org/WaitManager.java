package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WaitManager {

    public WebDriver wd;

    public WaitManager(WebDriver wd) {
        this.wd = wd;
    }

    public WebDriverWait wait;
    public Wait fluentWait;

    public void implicitWait() {
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void explicitWait(By locator) {
        wait = new WebDriverWait(wd, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fluentWait(By locator) {
        fluentWait = new FluentWait(wd)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}