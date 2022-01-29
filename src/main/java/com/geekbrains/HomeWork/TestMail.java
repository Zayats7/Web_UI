package com.geekbrains.HomeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class TestMail {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestMail.class));
    //private static final By WEB_SITE = By.xpath("https://mail.ru/");
    private static final By LOGIN_BOX = By.xpath(".//input[contains(@name, 'login')]");
    private static final By PASSWORD_BOX = By.xpath(".//input[contains(@name, 'password')]");
    private static final String LOGIN = "margleibblan@mail.ru";
    private static final String PASSWORD = "qwedcxzasdfghjkl;'\\";
    private static final By CHECKBOX = By.xpath(".//input[contains(@type, 'checkbox')]");
    private static final By GO_TO_PASSWORD = By.xpath(".//button[contains(@data-testid, 'enter-password')]");
    //private static final By CHECK_E_MAIL = By.xpath(".//*[@id= 'mailbox']/div[1]/div[2]/text()");
    private static final By GO_TO = By.xpath(".//button[contains(@data-testid, 'login-to-mail')]");
    private static final By WRITE_MESSAGE = By.xpath(".//a[contains(@title, 'Написать письмо')]");
    private static final By MESSAGE_LAYER = By.xpath(".//div[contains(@class, 'compose-app__compose')]");
    private static final By WHOM = By.xpath(".//div[contains(@data-type, 'to')]");
    private static final By FAST_MESSAGE_LAYER = By.xpath(".//div[contains(@class, 'datalist--3ogk- datalist_visible--3Ip9Z datalist_show--2PGmR fadeInUp--2yJ0y')]");
    private static final By MYSELF_MESSAGE = By.xpath(".//div[contains(@data-test-id, 'letter-to-yourself')]");
    private static final By TOPIC = By.xpath(".//input[contains(@name, 'Subject')]");
    private static final By TEXT_BOX = By.xpath(".//div[contains(@role, 'textbox')]");
    private static final By SENT_BUTTON = By.xpath(".//span[contains(@data-title-shortcut, 'Cmd+Enter')]");
    private static final By LOGIN_VISIBLE = By.name("login");
    private static final By PASSWORD_VISIBLE = By.name("password");
    @Test
    public void testSentMessage() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        LOGGER.info("Открываем браузер");
        driver.get("https://mail.ru/"); //TODO убрать локатор из теста
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        LOGGER.info("Находим поле для ввода почты");
        driver.findElement(LOGIN_BOX);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_VISIBLE));
        LOGGER.info("Вводим почту");
        driver.findElement(LOGIN_VISIBLE).sendKeys(LOGIN);
        LOGGER.info("Выключаем чекбокс 'Запомнить'");
        driver.findElement(CHECKBOX).click();
        LOGGER.info("Нажимаем на кнопку 'Ввести пароль'");
        driver.findElement(GO_TO_PASSWORD).click();
        //LOGGER.info("Сравниваем введенный адрес почты и отображаемый"); //TODO реализовать проверку валидности почты
        //Assert.assertEquals(LOGIN, CHECK_E_MAIL, "Введенный адрес: " + LOGIN + " и Фактический не совпадают");
        LOGGER.info("Находим поле для ввода пароля");
        driver.findElement(PASSWORD_BOX);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_VISIBLE));
        LOGGER.info("Вводим пароль");
        driver.findElement(PASSWORD_VISIBLE).sendKeys(PASSWORD);
        LOGGER.info("Нажимаем на кнопку 'Войти'");
        driver.findElement(GO_TO).click();

        Thread.sleep(5000);
        LOGGER.info("Нажимаем на кнопку 'Написать письмо'");
        driver.findElement(WRITE_MESSAGE).click();
        LOGGER.info("Ждем появления леера письма");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_LAYER));
        LOGGER.info("Кликаем на поле 'Кому'");
        driver.findElement(WHOM).click();
        LOGGER.info("Ждем видимость леера быстрых адресатов");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(FAST_MESSAGE_LAYER));
        LOGGER.info("Кликаем на 'Отправить себе'");
        driver.findElement(MYSELF_MESSAGE).click();
        LOGGER.info("В теме вводим сообщение");
        driver.findElement(TOPIC).sendKeys("Test");
        LOGGER.info("Вводим сообщение в основном поле для ввода сообщения");
        driver.findElement(TEXT_BOX).sendKeys("Hello world it's test!");
        LOGGER.info("Нажимаем 'Отправить'");
        driver.findElement(SENT_BUTTON).click();
        //TODO написать проверку на видимость леера подтверждения отправки письма

        Thread.sleep(5000);
        driver.quit();
        LOGGER.info("Письмо отправлено, тест пройден");
    }
}
//TODO сдлеать логирование понятным
//TODO Добавить больше Assert
//TODO По возможности создать отдельные классы с методами
