import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class MercuryTours_AutomatedTest {
    private WebDriver driver;

    By registerLinkLocator = By.linkText("REGISTER");
    By registerPageLocator = By.xpath("//img[@src='images/mast_register.gif']");
    By registerUsernameLocator = By.id("email");
    By registerPasswordLocator = By.name("password");
    By registerConfirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");
    By registerButtonLocator = By.name("submit");

    By loginUserLocator = By.name("userName");
    By loginPasswordLocator = By.name("password");
    By signInButtonLocator = By.name("submit");

    @BeforeEach
    public void setUps()  {
        System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://demo.guru99.com/test/newtours/index.php");
    }

    @AfterEach
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void registerUserSuccessfulTest () {
        driver.findElement(registerLinkLocator).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            fail("Thread cannot sleep");
        }

        if (driver.findElement(registerPageLocator).isDisplayed()) {
            driver.findElement(registerUsernameLocator).sendKeys("Jaibascript");
            driver.findElement(registerPasswordLocator).sendKeys("123456");
            driver.findElement(registerConfirmPasswordLocator).sendKeys("123456");
            driver.findElement(registerButtonLocator).click();
        }
        else {
            fail("Register page was not found");
        }

        List<WebElement> fonts = driver.findElements(By.tagName("font"));
        assertEquals("Note: Your user name is Jaibascript.", fonts.get(5).getText());
    }

    @Test
    public void signInSuccessfulTest () {
        if (driver.findElement(loginUserLocator).isDisplayed()) {
            driver.findElement(loginUserLocator).sendKeys("Jaibascript");
            driver.findElement(loginPasswordLocator).sendKeys("123456");
            driver.findElement(signInButtonLocator).click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                fail("Thread cannot sleep");
            }

            List<WebElement> h3List = driver.findElements(By.tagName("h3"));
            assertEquals("Login Successfully", h3List.get(0).getText());
        }
        else {
            fail("Login page was not found");
        }
    }
}
