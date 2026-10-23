package lesson11;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsMainPage {
    private final WebDriver driver;

    private final By cookieButton = By.id("cookie-agree");
    private final By blockTitle = By.cssSelector(".pay__wrapper h2");
    private final By partnerLogos = By.cssSelector(".pay__partners img");
    private final By moreDetailsLink = By.linkText("Подробнее о сервисе");
    private final By paymentTypeSelect = By.cssSelector(".pay__form .select__header");
    private final By phoneInput = By.id("connection-phone");
    private final By amountInput = By.id("connection-sum");
    private final By continueButton = By.cssSelector("#pay-connection button[type='submit']");

    private final By paymentFrame = By.cssSelector("iframe.payment-widget-iframe");
    private final By frameAmount = By.cssSelector(".pay-description__cost");
    private final By frameDescription = By.cssSelector(".pay-description__text");
    private final By framePayButton = By.cssSelector("button[type='submit']");
    private final By cardNumberLabel = By.xpath("//input[@id='cc-number']/following-sibling::label");
    private final By expiryLabel = By.xpath("//input[@formcontrolname='expirationDate']/following-sibling::label");
    private final By cvcLabel = By.xpath("//input[@formcontrolname='cvc']/following-sibling::label");
    private final By holderLabel = By.xpath("//input[@formcontrolname='holder']/following-sibling::label");
    private final By cardIcons = By.cssSelector(".cards-brands__container > img");

    public MtsMainPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait waitFor(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    @Step("Закрыть баннер cookies")
    public void acceptCookiesIfPresent() {
        try {
            waitFor(5).until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
        } catch (TimeoutException ignored) {
        }
    }

    @Step("Получить название блока")
    public String getBlockTitle() {
        return driver.findElement(blockTitle).getAttribute("textContent").replaceAll("\\s+", " ").trim();
    }

    @Step("Получить логотипы платёжных систем")
    public List<WebElement> getPartnerLogos() {
        return driver.findElements(partnerLogos);
    }

    @Step("Нажать ссылку «Подробнее о сервисе»")
    public void clickMoreDetailsLink() {
        driver.findElement(moreDetailsLink).click();
    }

    @Step("Проверить, что адрес страницы содержит {part}")
    public boolean isUrlContains(String part) {
        try {
            return waitFor(10).until(ExpectedConditions.urlContains(part));
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Выбрать вариант оплаты «{name}»")
    public void selectPaymentType(String name) {
        By option = By.xpath("//p[@class='select__option' and text()='" + name + "']");
        driver.findElement(paymentTypeSelect).click();
        waitFor(5).ignoring(ElementClickInterceptedException.class).until(d -> {
            d.findElement(option).click();
            return true;
        });
    }

    @Step("Получить надпись в поле {fieldId}")
    public String getPlaceholder(String fieldId) {
        return waitFor(5).until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldId)))
                .getAttribute("placeholder");
    }

    @Step("Ввести номер телефона {phone}")
    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    @Step("Ввести сумму {amount}")
    public void enterAmount(String amount) {
        driver.findElement(amountInput).sendKeys(amount);
    }

    @Step("Получить номер из поля")
    public String getPhoneValue() {
        return driver.findElement(phoneInput).getAttribute("value");
    }

    @Step("Получить сумму из поля")
    public String getAmountValue() {
        return driver.findElement(amountInput).getAttribute("value");
    }

    @Step("Нажать «Продолжить»")
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    @Step("Проверить, что открылось окно оплаты")
    public boolean isPaymentFrameDisplayed() {
        try {
            return waitFor(15).until(ExpectedConditions.visibilityOfElementLocated(paymentFrame)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Перейти в окно оплаты")
    public void switchToPaymentFrame() {
        waitFor(15).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentFrame));
        waitFor(15).until(ExpectedConditions.visibilityOfElementLocated(frameAmount));
    }

    @Step("Получить сумму в окне оплаты")
    public String getFrameAmount() {
        return driver.findElement(frameAmount).getText();
    }

    @Step("Получить описание платежа в окне оплаты")
    public String getFrameDescription() {
        return driver.findElement(frameDescription).getText();
    }

    @Step("Получить текст кнопки оплаты")
    public String getPayButtonText() {
        return driver.findElement(framePayButton).getText();
    }

    @Step("Получить надпись поля «Номер карты»")
    public String getCardNumberLabel() {
        return driver.findElement(cardNumberLabel).getText();
    }

    @Step("Получить надпись поля «Срок действия»")
    public String getExpiryLabel() {
        return driver.findElement(expiryLabel).getText();
    }

    @Step("Получить надпись поля «CVC»")
    public String getCvcLabel() {
        return driver.findElement(cvcLabel).getText();
    }

    @Step("Получить надпись поля «Имя и фамилия на карте»")
    public String getHolderLabel() {
        return driver.findElement(holderLabel).getText();
    }

    @Step("Получить иконки платёжных систем в окне оплаты")
    public List<WebElement> getCardIcons() {
        return driver.findElements(cardIcons);
    }
}