package pages;


import helperClass.Waiters;
import locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private static By LOGIN = Locators.get("LoginLink");
    private static By PASSWORD = Locators.get("PasswordLink");
    private static By SUBMIT_BUTTON = Locators.get("SubmitButton");
    private static By LOGO_TUT_BY = Locators.get("LogoTUTBYImage");

    public static void enterTheEmail(WebDriver driver, String userLogin, String userPassword){
        driver.findElement(LOGIN).sendKeys(userLogin);
        driver.findElement(PASSWORD).sendKeys(userPassword);
        driver.findElement(SUBMIT_BUTTON).click();
        Waiters.waitForPageLoaded();
    }

    public static boolean isDisplayedLogo(WebDriver driver) {
        if (!driver.findElement(LOGO_TUT_BY).getText().contains("tut.by")) {
            return false;
        }
        return true;
    }
}

