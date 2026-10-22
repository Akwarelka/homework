package lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import lesson10.MtsMainPage;
import lesson10.MtsPaymentModalPage;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentTests {
    private WebDriver driver;
    private MtsMainPage mainPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MtsMainPage(driver);
        mainPage.open();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("1. Проверка плейсхолдеров незаполненных полей для всех вариантов оплаты")
    public void testPlaceholdersForAllOptions() {
        // Услуги связи
        mainPage.selectPaymentOption("Услуги связи");
        assertEquals("Номер телефона", mainPage.getPlaceholderText("Номер телефона"));
        assertEquals("Сумма", mainPage.getPlaceholderText("Сумма"));

        // Домашний интернет
        mainPage.selectPaymentOption("Домашний интернет");
        assertEquals("Номер абонента", mainPage.getPlaceholderText("Номер абонента"));
        assertEquals("Сумма", mainPage.getPlaceholderText("Сумма"));

        // Рассрочка
        mainPage.selectPaymentOption("Рассрочка");
        assertEquals("Номер счета на 44", mainPage.getPlaceholderText("Номер счета на 44"));
        assertEquals("Сумма", mainPage.getPlaceholderText("Сумма"));

        // Задолженность
        mainPage.selectPaymentOption("Задолженность");
        assertEquals("Номер счета на 2073", mainPage.getPlaceholderText("Номер счета на 2073"));
        assertEquals("Сумма", mainPage.getPlaceholderText("Сумма"));
    }

    @Test
    @DisplayName("2. Проверка работы формы и отображения данных в модальном окне оплаты")
    public void testPaymentModalWindow() {
        String phone = "297777777";
        String sum = "10.00";
        String email = "test@example.com";

        mainPage.selectPaymentOption("Услуги связи");
        mainPage.fillConnectionFields(phone, sum, email);
        mainPage.clickContinue();

        MtsPaymentModalPage modalPage = new MtsPaymentModalPage(driver);
        modalPage.switchToPaymentFrame();

        assertTrue(modalPage.getAmountFromModal().contains("10.00"), "Сумма некорректна");
        assertTrue(modalPage.getSubmitButtonText().contains("10.00"), "Сумма на кнопке некорректна");
        assertTrue(modalPage.getPhoneNumberFromModal().contains("375297777777"), "Номер телефона некорректен");

        assertTrue(modalPage.isPaymentIconsDisplayed(), "Иконки платежных систем не отображаются");
    }
}
