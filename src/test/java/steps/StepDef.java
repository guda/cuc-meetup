package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePage;

import java.util.List;

import static hooks.Hooks.getDriver;

public class StepDef {
    private HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);

    @Given("Symphony portal is up and running")
    public void openHomePage() {
        getDriver().get("https://symphony.is/");
    }

    @And("Title is {string}")
    public void titleIs(String pageTitle) {
        Assert.assertEquals(pageTitle, getDriver().getTitle());
    }

    @Then("We have {int} Navigation elements displayed/visible")
    public void weHaveNavigationElementSDisplayedVisible(int elements) {
        Assert.assertEquals("There are not '" + elements + "' elements in menu wrapper with tag <a>",
                elements, homePage.getAllMenuItems().size());
    }

    @Then("We have {float} Navigation elements displayed/visible")
    public void weHaveNavigationElementSDisplayedVisibleFloat(float number) {
        System.out.println("Using float instead of int: " + number);
    }

    @And("All elements are enabled")
    public void allElementsAreEnabled() {
        homePage.getAllMenuItems().forEach(webElement -> {
            Assert.assertTrue("Menu element '" + webElement.getText() + "' not enabled", webElement.isEnabled());
        });
    }

    @But("All change upon focus")
    public void allChangeUponFocus() {
        homePage.getAllMenuItems().forEach(webElement -> {
            String beforeChangeLinkText = webElement.getText();

            Actions action = new Actions(getDriver());
            action.moveToElement(webElement).build().perform();

            sleepNow();

            switch (beforeChangeLinkText) {
                case "TECHNOLOGY":
                    Assert.assertEquals("Link 'TECHNOLOGY' did not change to 'A TECHNOLOGY HOUSE'",
                            "A TECHNOLOGY HOUSE", homePage.getTextLinkATechnologyHouse());
                    return;
                case "THAT":
                    Assert.assertEquals("Link 'THAT' did not change to 'WORK THAT MATTERS'",
                            "WORK THAT MATTERS", homePage.getTextLinkAWorkThatMatters());
                    return;
                case "MOVES":
                    Assert.assertEquals("Link 'MOVES' did not change to 'READY TO MOVE?'",
                            "READY TO MOVE?", homePage.getTextLinkReadyToMove());
                    return;
                case "PEOPLE":
                    Assert.assertEquals("Link 'PEOPLE' did not change to 'PEOPLE, PLACES & SPACES'",
                            "PEOPLE, PLACES & SPACES", homePage.getTextLinkPeoplePlacesSpaces());
            }
        });
    }

    @Then("Navigation elements are all in place:")
    public void navigationElementsAreAllInPlace(List<String> navigationElementsTitle) {
        navigationElementsTitle.forEach(navigationElementText -> {
            Assert.assertTrue("Navigation element by text '" + navigationElementText + "' is not displayed!",
                    homePage.getMenuWrapper().findElement(By.linkText(navigationElementText)).isDisplayed());
        });
    }

    @Given("I have something very long but very important to say:")
    public void iHaveSomethingVeryLongButVeryImportantToSay(String veryLongString) {
        System.out.println("********* VERY LONG MESSAGE: \n" + veryLongString);
    }

    @Then("I will check {string} element")
    public void iWillCheckElement(String elementText) {
        Actions action = new Actions(getDriver());
        action.moveToElement(homePage.getMenuWrapper().findElement(By.linkText(elementText))).build().perform();

        sleepNow();

        switch (elementText) {
            case "TECHNOLOGY":
                Assert.assertEquals("Link 'TECHNOLOGY' did not change to 'A TECHNOLOGY HOUSE'",
                        "A TECHNOLOGY HOUSE", homePage.getTextLinkATechnologyHouse());
                return;
            case "THAT":
                Assert.assertEquals("Link 'THAT' did not change to 'WORK THAT MATTERS'",
                        "WORK THAT MATTERS", homePage.getTextLinkAWorkThatMatters());
                return;
            case "MOVES":
                Assert.assertEquals("Link 'MOVES' did not change to 'READY TO MOVE?'",
                        "READY TO MOVE?", homePage.getTextLinkReadyToMove());
                return;
            case "PEOPLE":
                Assert.assertEquals("Link 'PEOPLE' did not change to 'PEOPLE, PLACES & SPACES'",
                        "PEOPLE, PLACES & SPACES", homePage.getTextLinkPeoplePlacesSpaces());
        }
    }

    private void sleepNow() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // @And("^I have (\\d+) regex expression(s) here$")
    // public void iHaveRegexExpressionHere(int number) {
    // }

    // If I want to use optional text or alternative text I must use cucumber expressions, I cannot use regular expressions
    @And("I have {int} regex expression(s) here")
    public void iHaveRegexExpressionHere(int number) {
    }

    @And("I have \\(escaping) done \\{here}")
    public void iHaveEscapingDoneHere() {
    }
}
