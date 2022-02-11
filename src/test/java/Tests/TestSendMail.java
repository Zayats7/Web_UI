package Tests;

import com.geekbrains.HomeWork.CustomLoggerNew;
import com.geekbrains.HomeWork.LoginPage;
import com.geekbrains.HomeWork.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Тест проверяет логин и отправку письма самому себе
 * 1. Залогиниться пользователем
 * 2. Открыть леер сообщений
 * 3. Выбрать в адресате себя
 * 4. Заполнить тему
 * 5. Заполнить само сообщение
 * 6. Отправить сообшение
 * 7. Проверить что сообщение отправлено
 */

@Story("Проверка отправки сообщения")
public class TestSendMail {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestSendMail.class));
    private static final String WEB_SITE = "https://mail.ru/";
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new CustomLoggerNew()).decorate(new ChromeDriver());
        driver.get(WEB_SITE);
    }

    @Test
    @Feature("Message")
    @Feature("Login")
    @Description("Проверка отправки сообщения после логина")
    public void testSentMessage() {

        LoginPage loginPage = new LoginPage(driver);
        LOGGER.info("Запускаем браузер");
        //loginPage.start();
        LOGGER.info("Логин");
        loginPage.doLogin();
        loginPage.doCheckBox();
//        TODO реализовать проверку валидности почты
//        LOGGER.info("Сравниваем введенный адрес почты и отображаемый");
        //String emailInfo = driver.findElement(CHECK_E_MAIL).getAttribute(CHECK_E_MAIL);
//        Assertions.assertTrue(emailInfo.contains(LOGIN));
        LOGGER.info("Пароль");
        loginPage.doPassword();
        LOGGER.info("Заходим в почту");
        loginPage.goToMainPage();

        MainPage mainPage = new MainPage(driver);
        LOGGER.info("Написать письмо");
        mainPage.writeMessageButton();
        mainPage.messageLayer();
        LOGGER.info("Выбираем адресата");
        mainPage.whomLine();
        mainPage.quickPeople();
        mainPage.mySelfButton();
        LOGGER.info("Заполняем тему и пишем пьсьмо");
        mainPage.topic();
        mainPage.texBox();
        LOGGER.info("Отправляем письмо");
        mainPage.sendMessageButton();
        Assert.assertTrue(mainPage.sendMessageLayer().isDisplayed());

        //loginPage.quiet();
        LOGGER.warning("Письмо отправлено, тест пройден");
    }
    @AfterEach
    void killDriver() {
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

}