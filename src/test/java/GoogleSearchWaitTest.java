import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearchWaitTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver");
        this.driver = new FirefoxDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://www.duckduckgo.com");
    }

    @Test
    public void testGoogleSearch() {
        String searchText = "JUnit";

        WebElement searchBox = this.driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys(searchText);
        searchBox.submit();

        // Espera implicita
        //this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Espera explicita
        //WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //eWait.until(ExpectedConditions.titleContains("JUnit"));

        //assertEquals(searchText + " at DuckDuckGo", this.driver.getTitle());

        // Espera fluida
        Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        WebElement element = fWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.partialLinkText("JUnit 5"));
            }
        });

        assertEquals("JUnit 5", element.getText());
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}