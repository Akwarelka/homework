package lesson9;

// Импорт необходимых классов библиотеки Selenium
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        // Указываем путь к chromedriver с расширением .exe для Windows
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Инициализация веб-драйвера Chrome
        WebDriver driver = new ChromeDriver();

        // Открытие страницы
        driver.get("https://google.com");

        // Рекомендуется закрывать браузер после завершения работы
        // driver.quit();
    }
}


