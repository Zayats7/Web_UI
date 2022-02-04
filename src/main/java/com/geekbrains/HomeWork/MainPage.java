package com.geekbrains.HomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.logging.Logger;

public class MainPage extends Base {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(MainPage.class));
    private static final By WRITE_MESSAGE = By.xpath(".//a[contains(@title, 'Написать письмо')]");
    private static final By MESSAGE_LAYER = By.xpath(".//div[contains(@class, 'compose-app__compose')]");
    private static final By WHOM = By.xpath(".//div[contains(@data-type, 'to')]");
    private static final By FAST_MESSAGE_LAYER = By.xpath(".//div[contains(@class, 'datalist--3ogk- datalist_visible--3Ip9Z datalist_show--2PGmR fadeInUp--2yJ0y')]");
    private static final By MYSELF_MESSAGE = By.xpath(".//div[contains(@data-test-id, 'letter-to-yourself')]");
    private static final By TOPIC = By.xpath(".//input[contains(@name, 'Subject')]");
    private static final By TEXT_BOX = By.xpath(".//div[contains(@role, 'textbox')]");
    private static final By SENT_BUTTON = By.xpath(".//span[contains(@data-title-shortcut, 'Cmd+Enter')]");
    private static final By SEND_MESSAGE_LAYER = By.xpath(".//*[contains(@class, 'layer__link')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void writeMessageButton() {
        LOGGER.warning("Проверяем видимость леера сообщения");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(WRITE_MESSAGE));
        LOGGER.info("Нажимаем на кнопку 'Написать письмо'");
        driver.findElement(WRITE_MESSAGE).click();
    }

    public void messageLayer() {
        LOGGER.warning("Ждем появления леера письма");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_LAYER));
    }

    public void whomLine() {
        LOGGER.info("Кликаем на поле 'Кому'");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(WHOM));
        driver.findElement(WHOM).click();
    }

    public void quickPeople() {
        LOGGER.warning("Ждем видимость леера быстрых адресатов");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(FAST_MESSAGE_LAYER));
    }

    public void mySelfButton() {
        LOGGER.info("Кликаем на 'Отправить себе'");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(MYSELF_MESSAGE));
        driver.findElement(MYSELF_MESSAGE).click();
    }

    public void topic() {
        LOGGER.info("В теме вводим сообщение");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(TOPIC));
        driver.findElement(TOPIC).sendKeys("Test");
    }

    public void texBox() {
        LOGGER.info("Вводим сообщение в основном поле для ввода сообщения");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(TEXT_BOX));
        driver.findElement(TEXT_BOX).sendKeys("Hello world it's test!");
    }

    public void sendMessageButton() {
        LOGGER.info("Нажимаем 'Отправить'");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(SENT_BUTTON));
        driver.findElement(SENT_BUTTON).click();
    }

    public WebElement sendMessageLayer() {
        LOGGER.warning("Провеверям отображение подтверждения отправки сообщения");
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(SEND_MESSAGE_LAYER));

    }

}
