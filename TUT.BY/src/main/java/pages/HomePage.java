package pages;


import helperClass.Waiters;
import locators.Locators;
import org.openqa.selenium.*;

public class HomePage {
    private static By USER_HEADER = Locators.get("UserHeaderLink");
    private static By USER_DROPDOWN_CONTEXT = Locators.get("UserDropdownContext");
    private static By MESSAGE_SUBJECT_LINK = Locators.get("MessageSubjectLink");
    private static By SENT_LETTERS_LINK = Locators.get("SentLettersLink");
    private static By MESSAGE_FROM_TEXT_LINK = Locators.get("MessageFromTextLink");

    public static boolean verifySentEmail(WebDriver driver,String fullFirstUserLogin,String subject){
        driver.findElement(SENT_LETTERS_LINK).click();
        driver.findElements(MESSAGE_FROM_TEXT_LINK).get(0)
                .getAttribute("title").contains(fullFirstUserLogin);
        if(!driver.findElements(MESSAGE_SUBJECT_LINK).get(0).getAttribute("title").contains(subject)) {
            return false;
        }
        return true;
    }

    public static void returnToMainPage(WebDriver driver){
        Waiters.waitForPageLoaded();
        driver.findElement(USER_HEADER).click();

        int size = driver.findElements(USER_DROPDOWN_CONTEXT).size();
        driver.findElements(USER_DROPDOWN_CONTEXT).get(size-1).click();
        Waiters.waitForPageLoaded();
    }

    public static boolean checkMessageInInbox(WebDriver driver, String fullSecondUserLogin , String subject){
        driver.findElements(MESSAGE_FROM_TEXT_LINK).get(0)
                .getAttribute("title").contains(fullSecondUserLogin);
        if(!driver.findElements(MESSAGE_SUBJECT_LINK).get(0).getAttribute("title").contains(subject)){
            return false;
        }
        return true;
    }
}
