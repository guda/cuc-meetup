package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage {
    @FindBy(id = "homebox")
    private WebElement menuWrapper;

    @FindBy(id = "nav-technology")
    private WebElement linkATechnologyHouse;

    @FindBy(id = "nav-that")
    private WebElement linkWorkThatMatters;

    @FindBy(id = "nav-moves")
    private WebElement linkLinkReadyToMove;

    @FindBy(id = "nav-people")
    private WebElement linkLinkPeoplePlacesSpaces;

    public List<WebElement> getAllMenuItems() {
        return menuWrapper.findElements(By.tagName("a"));
    }

    public WebElement getMenuWrapper() {
        return menuWrapper;
    }

    public String getTextLinkATechnologyHouse() {
        return linkATechnologyHouse.getText();
    }

    public String getTextLinkAWorkThatMatters() {
        return linkWorkThatMatters.getText();
    }

    public String getTextLinkReadyToMove() {
        return linkLinkReadyToMove.getText();
    }

    public String getTextLinkPeoplePlacesSpaces() {
        return linkLinkPeoplePlacesSpaces.getText();
    }
}
