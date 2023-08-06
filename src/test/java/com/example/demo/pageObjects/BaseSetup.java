package com.example.demo.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BaseSetup {
    public WebDriver setDriver() {
        return new SafariDriver();
    }
}
