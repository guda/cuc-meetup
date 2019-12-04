package hooks;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Hooks {
    static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    // I will use cucumber Before, not JUnit one as recommended by Cucumber - to avoid side effects
    @Before
    public void setUp() {
        // It is recommended to avoid using hooks if possible and to use Background,
        // but, there are times we need to use them
        // I could have used them for (connecting to external services/dbs, creating threadsafe webdriver instances...)

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        // I destroy webdriver instance here after each Scenario
        // I could have had manipulated scenario here
        // Usually, I record test failure here (screenshot, console log,...)

        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                FileUtils.writeByteArrayToFile(new File(testName + ".png"), screenshot);
            } catch (WebDriverException | ClassCastException | IOException somePlatformsDontSupportScreenshots) {
                somePlatformsDontSupportScreenshots.printStackTrace();
            }
        }

        driver.quit();
    }

    @After("@tag1 and not @tag2") // conditional hook, when you need something less explicit then Background
    public void doSomethingAfter(Scenario scenario) {
        System.out.println("I run only if @tag1 is selected for run and only if @tag2 is not selected for run");
        driver.quit();
    }
}
