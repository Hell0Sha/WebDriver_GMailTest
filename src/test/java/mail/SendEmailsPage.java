package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SendEmailsPage extends AbstractPage {

    @FindBy(xpath = "//a[@title = 'Sent Mail']")
    WebElement sendEmailsButton;

    @FindBy(xpath = "//tr[1]/td//div[text() = \"To: \"][1]/span")
    WebElement sendEmailToField;

    @FindBy(xpath = "//div[@role = \"main\"]//table/tbody/tr[1]/td[6]//div/span[1]")
    WebElement sendEmailThemeField;

    @FindBy(xpath = "//div[@role = \"main\"]//table/tbody/tr[1]/td[6]//div/span[2]")
    WebElement sendEmailTextField;

    public static final String SEND_EMAIL_TO = "shaadt_helena@mail.ru";
    public static final String SEND_EMAIL_THEME = "test2";
    public static final String SEND_EMAIL_TEXT = ":)!";

    public SendEmailsPage(WebDriver driver) {
        super(driver);
    }

    public SendEmailsPage sendEmails(){
        sendEmailsButton.click();
        return new SendEmailsPage(getDriver());
    }

    public SendEmailsPage checkIsEmailSend(){
        String emailTo = sendEmailToField.getText();
        System.out.println(emailTo);
        Assert.assertEquals(SEND_EMAIL_TO,emailTo);

        String emailTheme = sendEmailThemeField.getText();
        System.out.println(emailTheme);
        Assert.assertEquals(SEND_EMAIL_THEME,emailTheme);

        String emailText = sendEmailTextField.getText();
        System.out.println(emailText);
        Assert.assertEquals(SEND_EMAIL_TEXT,"-"+emailText);
        return this;


        ////// TODO: ВЫНЕСТИ АССЕТС В ГЛАВНЫЙ ТЕСТ
    }
}
