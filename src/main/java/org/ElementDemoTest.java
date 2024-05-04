package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class ElementDemoTest {

    ChromeOptions chromeOptions;
    WebDriver wd;

    @BeforeClass(alwaysRun = true)
    public void beforeClassMethod() throws MalformedURLException {
        chromeOptions = new ChromeOptions();
        wd = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
    }

    @BeforeMethod(alwaysRun = true)
    public void preMethodsteps () throws MalformedURLException {
        wd.get("https://selectorshub.com/xpath-practice-page/");

    }

    @Test
    public void dropdownTC() {
        // Using Select Approach
        WebElement el1 = wd.findElement(By.cssSelector("select[name=cars]"));
        Select s1 = new Select(el1); // First show this line and then line 35
        s1.selectByValue("saab");


        // Using Find Elements - 2nd approach
        // basically here findElements will return me list of element and hence I am storing it in List variable
        List<WebElement> options = wd.findElements(By.cssSelector("select[name=cars] option"));
        Iterator<WebElement> i1 = options.iterator(); // Now will just iterate through list
        //The below loop will just check for the next element and will print it if present
        while(i1.hasNext()) {
            System.out.println(i1.next().getAttribute ( "value"));
        }

        //Suppose now you want to select value "Saab" you can write the code like -
        String ddValue = "saab";
        WebElement e2 = null;
        while(i1.hasNext()) {
            e2 = i1.next();
            if (e2.getAttribute( "saab").equals(ddValue)) {
                e2.click();
            }
        }
    }
}
