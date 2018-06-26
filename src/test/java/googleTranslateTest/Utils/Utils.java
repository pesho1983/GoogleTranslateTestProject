package googleTranslateTest.Utils;


import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

import java.io.File;
import java.security.SecureRandom;
import java.util.Random;

public class Utils {

    public final static String WEBSITE_URL = "https://translate.google.bg/";
   private final static String HOME_PATH = "C:/Users/rkolev/Desktop/uploadTestImages/";

    public static String productIndividualXpath(WebElementFacade target, String replace) {
        String targetProduct = target.getWrappedElement().toString();
        String toRemove = targetProduct.substring(0, 78);
        return targetProduct.replace(toRemove, "").replace(replace, "");
    }


    public static String generateRandomImagePath() {
        StringBuilder sb = new StringBuilder();
        SecureRandom rand = new SecureRandom();
        sb.append(HOME_PATH)
                .append(rand.nextInt(100000) + 100)
                .append(System.currentTimeMillis())
                .append(System.nanoTime())
                .append(".png");

        return sb.toString();
    }


    public static void renameAllFiles() {
        File fileDirs = new File(HOME_PATH);
        for (File image : fileDirs.listFiles()) {
            if (image.isFile()) {
                String newName = generateRandomImagePath();
                if (image.renameTo(new File(newName))) {
                }
            }
        }
    }

    public static String getRandomImageFromFolder() {
        renameAllFiles();
        File fileDir = new File(HOME_PATH);
        File[] images = fileDir.listFiles();
        Random random = new Random();
        return images[random.nextInt(images.length - 1) + 1].toString();
    }

    public static String replaceWordWithWhitespace(String text, String toReplace) {
        return text.replace(toReplace, "");
    }

    public static String replaceWordWithRegex(String text, String regex) {
        return text.replaceAll(regex, "");
    }

    public static void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 3000);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    public static void javascriptWindowScroll(int scrollBy) {
        String script = "window.scrollBy(0,%s)";
        script = String.format(script, scrollBy);

        ((JavascriptExecutor) getDriver()).executeScript(script, "");
    }

    public static String removeSuffixFromPrice(WebElementFacade target) {
        String result = target.getText();
        return result.substring(0, result.length() - 3);

    }
}


