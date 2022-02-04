package Tests;

import com.geekbrains.HomeWork.LoginPage;
import com.geekbrains.HomeWork.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

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
    WebDriver driver;

    @Test
    @Feature("Message")
    @Feature("Login")
    @Description("Проверка отправки сообщения после логина")
    public void testSentMessage() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.start();
        loginPage.doLogin();
        loginPage.doCheckBox();
//        LOGGER.info("Сравниваем введенный адрес почты и отображаемый"); //TODO реализовать проверку валидности почты
//        String emailInfo = driver.findElement(CHECK_E_MAIL).getText();
//        Assertions.assertTrue(emailInfo.contains(LOGIN));
        loginPage.doPassword();
        loginPage.goToMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.writeMessageButton();
        mainPage.messageLayer();
        mainPage.whomLine();
        mainPage.quickPeople();
        mainPage.mySelfButton();
        mainPage.topic();
        mainPage.texBox();
        mainPage.sendMessageButton();
        Assert.assertTrue(mainPage.sendMessageLayer().isDisplayed());

        loginPage.quiet();
        LOGGER.warning("Письмо отправлено, тест пройден");
    }
}
//TODO сдлеать логирование понятным
//TODO По возможности создать отдельные классы с методами
