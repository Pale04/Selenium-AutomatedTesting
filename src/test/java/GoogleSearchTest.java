import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
        this.driver = new ChromeDriver();
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
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        assertEquals(searchText + " at DuckDuckGo", this.driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}