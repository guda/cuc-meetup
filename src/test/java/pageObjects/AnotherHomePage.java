package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AnotherHomePage {
    @FindBy(id = "homebox")
    private WebElement menuWrapper;

    public AnotherHomePage openHomePage(WebDriver driver) {
        driver.get("https://symphony.is/");
        return PageFactory.initElements(driver, AnotherHomePage.class);
    }

    public AnotherHomePage verifyHomePageTitle(WebDriver driver, String pageTitle) {
        Assert.assertEquals(pageTitle, driver.getTitle());
        return this;
    }

    public void verifyNavMenuItemsDisplayed() {
        menuWrapper.findElements(By.tagName("a")).forEach(webElement -> {
            Assert.assertTrue("Menu element '" + webElement.getText() + "' not enabled", webElement.isEnabled());
        });
    }
}