package googleTranslateTest.steps;

import cucumber.api.PendingException;
import googleTranslateTest.steps.serenity.GoogleTranslateStep;

import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class GoogleTranslateSteps {
    @Steps
    GoogleTranslateStep translatedSteps;


    @Given("^user is on google translate page$")
    public void userIsOnGoogleTranslatePage() throws Throwable {
        translatedSteps.openGoogleTranslate();
    }

    @When("^write english word$")
    public void writeEnglishWord(@Transpose Map<String, String> data) throws Throwable {
        translatedSteps.fillText(data);
    }

    @Then("^\"([^\"]*)\" must displayed$")
    public void mustDisplayed(String text) throws Throwable {
        translatedSteps.translatedText(text);
    }
}
