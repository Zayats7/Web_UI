package com.geekbrains.HomeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.logging.Logger;

public class LoginPage extends Base {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));
    private static final String WEB_SITE = "https://mail.ru/";
    private static final By LOGIN_BOX = By.xpath(".//input[contains(@name, 'login')]");
    private static final By PASSWORD_BOX = By.xpath(".//input[contains(@name, 'password')]");
    private static final String LOGIN = "margleibblan@mail.ru";
    private static final String PASSWORD = "qwedcxzasdfghjkl;'\\";
    private static final By CHECKBOX = By.xpath(".//input[contains(@type, 'checkbox')]");
    private static final By GO_TO_PASSWORD = By.xpath(".//button[contains(@data-testid, 'enter-password')]");
    private static final By CHECK_E_MAIL = By.xpath(".//*[@id= 'mailbox']//div[@name= 'clb36299772']");
    private static final By GO_TO = By.xpath(".//button[contains(@data-testid, 'login-to-mail')]");
    private static final By LOGIN_VISIBLE = By.name("login");
    private static final By PASSWORD_VISIBLE = By.name("password");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

//    public void start() {
//        driver = new EventFiringDecorator(new CustomLoggerNew()).decorate(new ChromeDriver());
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        LOGGER.info("Открываем браузер");
//        driver.get(WEB_SITE);
//    }

    public void quiet() {

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        Iterator<LogEntry> iterator = logEntries.iterator();

        while (iterator.hasNext()) {
            Allure.addAttachment("Лог браузера:", iterator.next().getMessage());
        }

        for (LogEntry log : logEntries) {
            Allure.addAttachment("Лог браузера:", log.getMessage());
        }
        driver.quit();
    }

    public void doLogin() {
        LOGGER.warning("Находим поле для ввода почты");
        driver.findElement(LOGIN_BOX);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_VISIBLE));
        LOGGER.info("Вводим почту");
        driver.findElement(LOGIN_VISIBLE).sendKeys(LOGIN);
    }

    public void doCheckBox() {
        LOGGER.warning("Проверяем видимость чекбокса'");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(CHECKBOX));
        LOGGER.info("Выключаем чекбокс 'Запомнить'");
        driver.findElement(CHECKBOX).click();
    }

    public void doPassword() {
        LOGGER.warning("Проверяем видимость кнопки для ввода пароля");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(GO_TO_PASSWORD));
        LOGGER.info("Нажимаем на кнопку 'Ввести пароль'");
        driver.findElement(GO_TO_PASSWORD).click();
        LOGGER.warning("Находим поле для ввода пароля");
        driver.findElement(PASSWORD_BOX);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_VISIBLE));
        LOGGER.info("Вводим пароль");
        driver.findElement(PASSWORD_VISIBLE).sendKeys(PASSWORD);
    }

    public void goToMainPage() {
        LOGGER.warning("Проверяем видимость кнопки входа");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(GO_TO));
        LOGGER.info("Нажимаем на кнопку входа");
        driver.findElement(GO_TO).click();
    }
}