package runners;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.AnotherHomePage;

import static hooks.Hooks.getDriver;

public class UIFuncBestPractice {
    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @DisplayName("Check menu elements on Home Page")
    @Test
    public void simpleLoginTest() {
        AnotherHomePage anotherHomePage = PageFactory.initElements(getDriver(), AnotherHomePage.class);

        anotherHomePage
                .openHomePage(driver)
                .verifyHomePageTitle(driver, "Home | Symphony")
                .verifyNavMenuItemsDisplayed();
    }
}
