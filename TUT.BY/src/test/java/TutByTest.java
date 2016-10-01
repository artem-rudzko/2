import core.TestBase;
import helperClass.HelperRandomClass;
import helperClass.Listener;
import helperClass.MailAPI;
import helperClass.NewParser;
import pages.HomePage;
import pages.MainPage;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import static org.testng.Assert.assertTrue;

@Listeners(Listener.class)

public class TutByTest extends TestBase {
    static String[] userInfoFromCSV = NewParser.getDataFrom(NewParser.Parsers.csv);
    static String[] userInfoFromSQL = NewParser.getDataFrom(NewParser.Parsers.sql);
    static String[] userInfoFromXML = NewParser.getDataFrom(NewParser.Parsers.xml);
    static String randomSubjectFromFirstLetter = HelperRandomClass.getRandomSubject();
    static String randomSubjectFromSecondLetter = HelperRandomClass.getRandomSubject();
    static String randomSubjectFromThirdLetter = HelperRandomClass.getRandomSubject();
    static String randomSubjectFromFourthLetter = HelperRandomClass.getRandomSubject();
    static String randomSubjectFromFifthLetter = HelperRandomClass.getRandomSubject();
    static String firstUserEmail = userInfoFromXML[0];
    static String firstUserPassword = userInfoFromXML[1];
    static String secondUserEmail = userInfoFromXML[2];
    static String secondUserPassword = userInfoFromXML[3];
    static String thirdUserEmail = userInfoFromSQL[4];
    static String thirdUserPassword = userInfoFromSQL[5];
    static String fourthUserEmail = userInfoFromSQL[6];
    static String fourthUserPassword = userInfoFromSQL[7];
    static String fifthUserEmail = userInfoFromCSV[8];
    static String fifthUserPassword = userInfoFromCSV[9];
    static String sixthUserEmail = userInfoFromCSV[10];
    static String sixthUserPassword = userInfoFromCSV[11];
    static String seventhUserEmail = userInfoFromCSV[12];
    static String seventhUserPassword = userInfoFromCSV[13];
    static String eighthUserEmail = userInfoFromSQL[14];
    static String eighthUserPassword = userInfoFromSQL[15];
    static String ninthUserEmail = userInfoFromSQL[16];
    static String ninthUserPassword = userInfoFromSQL[17];
    static String tenthUserEmail = userInfoFromXML[18];
    static String tenthUserPassword = userInfoFromXML[19];
    static String someMessageFromLetter ="Some message";

    static @DataProvider
    public Object[][] testDataInfo(){

        return new Object[][] {
                {
                    firstUserEmail, firstUserPassword, secondUserEmail, secondUserPassword, randomSubjectFromFirstLetter},
                {
                    thirdUserEmail, thirdUserPassword, fourthUserEmail, fourthUserPassword, randomSubjectFromSecondLetter },
                {
                    fifthUserEmail, fifthUserPassword, sixthUserEmail, sixthUserPassword, randomSubjectFromThirdLetter },
                {
                    seventhUserEmail, seventhUserPassword, eighthUserEmail, eighthUserPassword, randomSubjectFromFourthLetter},
                {
                    ninthUserEmail, ninthUserPassword, tenthUserEmail, tenthUserPassword, randomSubjectFromFifthLetter}
        };
        }

    @Test(dataProvider = "testDataInfo")
    public void sendLetterToSecondEmail(String emailSender, String passwordSender, String emailRecipient,
                                        String passwordRecipient , String subject )
            throws UnsupportedEncodingException, MessagingException {
        MailAPI.sendMessage(emailSender,passwordSender,emailRecipient,subject,someMessageFromLetter);
        MainPage.enterTheEmail(driver,emailSender,passwordSender);
        HomePage.verifySentEmail(driver,emailRecipient,subject);
        HomePage.returnToMainPage(driver);
        MainPage.enterTheEmail(driver,emailRecipient,passwordRecipient);
        assertTrue(MainPage.isDisplayedLogo(driver));
        HomePage.checkMessageInInbox(driver, emailSender, subject);
        HomePage.returnToMainPage(driver);
    }
}

