package lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MtsMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieAcceptButton = By.xpath("//button[contains(@class, 'btn') and (contains(text(), 'Принять') or contains(text(), 'Согласен'))] | //div[contains(@class, 'cookie')]//button");

    private final By selectDropdown = By.xpath("//button[contains(@class, 'select__header')]");

    private final By continueButton = By.xpath("//form[@id='pay-connection']//button[contains(text(), 'Продолжить')] | //button[text()='Продолжить']");

    public MtsMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://mts.by");
        acceptCookiesIfPresent();
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement cookieBtn = shortWait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieBtn.click();
        } catch (Exception ignored) {
        }
    }

    public void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void selectPaymentOption(String optionText) {
        safeClick(selectDropdown);

        String optionXpath = String.format(
                "//ul[contains(@class, 'select__list')]//li[contains(normalize-space(.), '%s')]",
                optionText
        );
        safeClick(By.xpath(optionXpath));
    }

    public String getPlaceholderText(String expectedPlaceholder) {
        By inputLocator = By.xpath(String.format(
                "//input[contains(@placeholder, '%s') or contains(normalize-space(@placeholder), '%s')]",
                expectedPlaceholder, expectedPlaceholder
        ));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(inputLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        return element.getAttribute("placeholder");
    }

    public void fillConnectionFields(String phone, String sum, String email) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='connection-phone'] | //input[contains(@placeholder, 'Номер')]")));
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        WebElement sumInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='connection-sum'] | //input[contains(@placeholder, 'Сумма')]")));
        sumInput.clear();
        sumInput.sendKeys(sum);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='connection-email'] | //input[contains(@placeholder, 'E-mail')]")));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickContinue() {
        safeClick(continueButton);
    }
}