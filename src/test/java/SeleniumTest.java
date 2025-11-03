

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://google.com");
    }

    @AfterEach
    void tearDown() throws Exception{
        ScreenshotUtil.takeScreenshot(driver, "google-homepage");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void openGoogle()  {

        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        assertTrue(title.equals("Google"));
    }
}