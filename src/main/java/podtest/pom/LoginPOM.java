package podtest.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPOM {

    WebDriver wd;

   /*Using constructor to avoid invoking webDriver on method level.
    As when this call is called constructor will be invoked automaticakky
    and so we are overloading the constructor with WebDriver as parameter */

    public LoginPOM(WebDriver wd){
        this.wd = wd;
    }

    // Properties

         //User Name
         private By userName = By.name("email");

         // Password
         private By passwordField = By.name("password");

         //URL
    private String pageURL = "https://demo.evershop.io/account/login";

         //Submit
    private By submitButton = By.xpath("//button [@type='submit']");

    //Behaviours[Methods]

        //fill in the form

    public LoginPOM fillCredentials(){

       // We should avoid hardcoding and we will be creating variables for each variable

        // findElement(By.name("email")).sendKeys("akhiljda@gmail.com");
       //  wd.findElement(By.name("password")).sendKeys("Password");

        // We should also not use the hard code values her and should pass it in a parmaeter
        wd.findElement(userName).sendKeys("akhiljda@gmail.com");
       wd.findElement(passwordField).sendKeys("Password");

//        wd.findElement(userName).sendKeys(userNameText);
//        wd.findElement(passwordField).sendKeys(passwordText);
        return this;

    }

        //click on Submit, using return type as class name to achieve the concept of chaining

    public void clickOnSubmit(){
        wd.findElement(submitButton).click();
    }

    public LoginPOM get(){
        wd.get(pageURL);
        return this;
    }
}
