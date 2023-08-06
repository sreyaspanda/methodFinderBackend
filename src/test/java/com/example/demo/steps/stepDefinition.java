package com.example.demo.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import com.example.demo.pageObjects.BaseSetup;
import com.example.demo.pageObjects.LoginPageSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class stepDefinition {
    WebDriver driver;

    BaseSetup baseSetup = new BaseSetup();

    LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Before
    public void setUp() {
//        Agent agent = Agent.attach();
        driver = baseSetup.setDriver();
    }

    @Given("User lands on login page")
    public void userLandsOnLoginPage() {
        loginPageSteps.loadPage(driver);
    }

    @When("User enters {string} and {string}")
    public void userEntersAnd(String username, String password) {
        loginPageSteps.enterLoginCredentials(driver, username, password);
    }

    @Then("User should login to home page")
    public void userShouldLoginToHomePage() throws InterruptedException {
        loginPageSteps.verifyLogin(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
