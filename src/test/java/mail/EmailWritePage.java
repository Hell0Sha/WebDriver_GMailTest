package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EmailWritePage extends AbstractPage {

    String to,theme, text;

    public static final String SEND_EMAIL_TO = "shaadt_helena@mail.ru";
    public static final String SEND_EMAIL_THEME = "Test";
    public static final String SEND_EMAIL_TEXT = " Have a nice day :)";

    @FindBy(xpath = "//div[contains(text(), 'COMPOSE')]")
    WebElement writeEmailButton;

    @FindBy(xpath = "//textarea[@name='to']")
    WebElement emailInputTo;

    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement emailInputSubj;

    @FindBy(xpath = "//div[@role='textbox']")
    WebElement emailInputText;

    @FindBy(xpath = "//img[@alt='Close']")
    WebElement saveDraft;


    public static final By WRITE_EMAIL_BUTTON_LOCATOR = By.xpath("//div[contains(text(), 'COMPOSE')]");
    public static final By TO_INPUT_LOCATOR = By.xpath("//textarea[@name='to']");

    public static final By EMAIL_TO_LOCATOR = By.xpath("//tr[1]/td//div[text() = \"To: \"][1]/span");
    public static final By EMAIL_THEME_LOCATOR = By.xpath("//div[@role = \"main\"]//table/tbody/tr[1]/td[6]//div/span[1]");
    public static final By EMAIL_TEXT_LOCATOR = By.xpath("//div[@role = \"main\"]//table/tbody/tr[1]/td[6]//div/span[2]");
    public static final By SEND_EMAILS_LOCATOR = By.xpath("//a[@title = 'Sent Mail']");

    public EmailWritePage(WebDriver driver) {
        super(driver);
    }

    public EmailWritePage writeEmail(){
        waitForElementPresent(WRITE_EMAIL_BUTTON_LOCATOR);
        writeEmailButton.click();
        waitForElementPresent(TO_INPUT_LOCATOR);
        new Actions(getDriver()).sendKeys(emailInputTo,SEND_EMAIL_TO).build().perform();
        emailInputSubj.sendKeys(SEND_EMAIL_THEME);
        emailInputText.sendKeys(SEND_EMAIL_TEXT);
        return this;
    }

    public EmailWritePage saveEmailAsDraft(){
        saveDraft.click();
        return this;
    }

    public EmailWritePage getEmailSendData(){
        getDriver().findElement(SEND_EMAILS_LOCATOR).click();
        waitForElementPresent(EMAIL_TO_LOCATOR);
        to = getDriver().findElement(EMAIL_TO_LOCATOR).getText();
        theme = getDriver().findElement(EMAIL_THEME_LOCATOR).getText();
        text = getDriver().findElement(EMAIL_TEXT_LOCATOR).getText();
        System.out.println(to + theme+ "\n" + text);
        Assert.assertEquals(text," - " + SEND_EMAIL_TEXT);
        Assert.assertEquals(SEND_EMAIL_THEME, theme);
        return this;
    }


}
