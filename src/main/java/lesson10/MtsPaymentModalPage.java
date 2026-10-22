package lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsPaymentModalPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By paymentIframe = By.xpath("//iframe[contains(@src, 'bepaid') or contains(@class, 'bepaid') or contains(@class, 'iframe')]");

    private final By amountText = By.xpath("//span[contains(text(), 'BYN') or contains(@class, 'pay-description__cost')] | //div[contains(@class, 'amount')]");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By phoneNumberText = By.xpath("//span[contains(text(), '375') or contains(@class, 'pay-description__text')] | //div[contains(@class, 'phone')]");
    private final By paymentIcons = By.xpath("//div[contains(@class, 'cards-brands')]//img | //ul[contains(@class, 'icons')]//img | //img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belcard')]");

    public MtsPaymentModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void switchToPaymentFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getAmountFromModal() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(amountText));
        return element.getText();
    }

    public String getSubmitButtonText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        return element.getText();
    }

    public String getPhoneNumberFromModal() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberText));
        return element.getText();
    }

    public boolean isPaymentIconsDisplayed() {
        List<WebElement> icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(paymentIcons));
        return !icons.isEmpty() && icons.stream().allMatch(WebElement::isDisplayed);
    }
}