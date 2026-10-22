package lesson9;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestClass {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private static final String BASE_URL = "https://mts.by";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;

        driver.get(BASE_URL);

        closeCookieBannerIfPresent();
    }

    private void closeCookieBannerIfPresent() {
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'cookie') or contains(text(),'Принять') or contains(text(),'Согласен')]")
            ));
            jsClick(cookieBtn);
        } catch (Exception e) {

        }
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    private void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    @Test
    @DisplayName("1. Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        WebElement titleElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Онлайн пополнение') or contains(text(),'ОНЛАЙН ПОПОЛНЕНИЕ')]")
                )
        );

        String actualTitle = titleElement.getText()
                .replace("\n", " ")
                .replace("\u00a0", " ")
                .replaceAll("\\s+", " ")
                .trim();

        String expectedTitle = "Онлайн пополнение без комиссии";

        Assertions.assertEquals(expectedTitle.toLowerCase(), actualTitle.toLowerCase(),
                "Заголовок блока не соответствует ожидаемому!");
    }

    @Test
    @DisplayName("2. Проверка наличия логотипов платёжных систем")
    public void testPaymentLogos() {
        List<WebElement> logos = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'pay')]//img | //*[contains(@class,'pay')]//ul//img")
                )
        );

        Assertions.assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены!");

        for (WebElement logo : logos) {
            Assertions.assertTrue(logo.isDisplayed(), "Один из логотипов не отображается на странице!");
        }
    }

    @Test
    @DisplayName("3. Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreAboutServiceLink() {
        WebElement detailsLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(text(),'Подробнее о сервисе')]")
                )
        );

        scrollToElement(detailsLink);

        Assertions.assertTrue(detailsLink.isDisplayed(), "Ссылка 'Подробнее о сервисе' не отображается!");

        jsClick(detailsLink);

        boolean isOpened = wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("poryadok-oplaty"),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Порядок') or contains(@class,'popup')]"))
        ));

        Assertions.assertTrue(isOpened, "После клика на ссылку не произошел переход или не отобразился требуемый элемент!");
    }

    @Test
    @DisplayName("4. Заполнение формы 'Услуги связи' и проверка работы кнопки 'Продолжить'")
    public void testFillFormAndSubmit() {
        WebElement phoneInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='Номер телефона' or @id='connection-phone']")
                )
        );
        scrollToElement(phoneInput);
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        try {
            WebElement sumInput = driver.findElement(
                    By.xpath("//input[@placeholder='Сумма' or @id='connection-sum']"));
            sumInput.clear();
            sumInput.sendKeys("10");
        } catch (Exception ignored) {
        }

        WebElement submitButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(text(),'Продолжить') or contains(text(),'ПРОДОЛЖИТЬ')]")
                )
        );

        scrollToElement(submitButton);
        Assertions.assertTrue(submitButton.isDisplayed(), "Кнопка 'Продолжить' не отображается!");

        jsClick(submitButton);

        boolean isNextStepReached = wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.urlContains("pay"),
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe | //*[contains(@class,'pay')]"))
                )
        );

        Assertions.assertTrue(isNextStepReached, "Переход на этап оплаты после нажатия 'Продолжить' не выполнен!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
