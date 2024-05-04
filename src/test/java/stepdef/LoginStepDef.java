package stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginStepDef {

   @Given("User is on Login Page")
    public void openLoginPage(){
       System.out.println("User is on the application LOGIN PAGE");

   }
    @When("Fill credentials {string} and {string}")
    public void fillCredentials(String UN, String PW){
       System.out.println("UserName"+ " " +UN);
        System.out.println("Password" + " " +  PW);
   }

   @Then("User is loggedIn")
    public void verifyLogin(){
       System.out.println("User is logged in");
   }

}
