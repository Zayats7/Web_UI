package com.geekbrains.HomeWork;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class Base {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(MainPage.class));
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    public Base(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

}