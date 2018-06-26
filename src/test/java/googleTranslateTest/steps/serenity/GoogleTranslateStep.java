package googleTranslateTest.steps.serenity;

import googleTranslateTest.pages.GoogleTranslatePage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.Map;

public class GoogleTranslateStep {

    GoogleTranslatePage googleTranslatePage;


    @Step
    public void openGoogleTranslate() {
        googleTranslatePage.open();
    }

    @Step
    public void fillText(Map<String, String> data) {
        String text = data.get("english_word");
        googleTranslatePage.completeForm(text);
    }

    @Step
    public void translatedText(String text) {
        String translatedText = googleTranslatePage.getTranslatedText().getText();
        Assert.assertEquals(text, translatedText);
    }


}
