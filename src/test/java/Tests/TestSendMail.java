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
        LOGGER.info("Запускаем браузер");
        loginPage.start();
        LOGGER.info("Логин");
        loginPage.doLogin();
        loginPage.doCheckBox();
//        LOGGER.info("Сравниваем введенный адрес почты и отображаемый"); //TODO реализовать проверку валидности почты
//        String emailInfo = driver.findElement(CHECK_E_MAIL).getText();
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

        loginPage.quiet();
        LOGGER.warning("Письмо отправлено, тест пройден");
    }

}
