package com.example.demo.pageObjects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

public class LoginPageSteps {

    public void loadPage(WebDriver driver) {
        driver.get("http://localhost:3000/login");
    }

    public void enterLoginCredentials(WebDriver driver, String username, String password) {
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        submitButton.click();
    }

    public void verifyLogin(WebDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        String expectedTitle = "Home Page";
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }
}
