package googleTranslateTest.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;


import static googleTranslateTest.Utils.Utils.WEBSITE_URL;

@DefaultUrl(WEBSITE_URL)

public class GoogleTranslatePage extends PageObject {
    @FindBy(id = "source")
    private WebElementFacade englishText;

    @FindBy(id = "gt-res-dir-ctr")
    private WebElementFacade translatedText;

    public void completeForm(String text) {
        this.englishText.type(text);
    }

    public WebElementFacade getTranslatedText() {
        return translatedText;
    }
}
