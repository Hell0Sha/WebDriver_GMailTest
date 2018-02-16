package mail;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class DraftsPage extends AbstractPage {

    public static final String SEND_EMAIL_TO = "shaadt_helena@mail.ru";

    @FindBy(xpath = "//a[contains(text(),'Drafts')]")
    WebElement draftButton;

    @FindBy(xpath = "//div[@class='no']//div[@role = 'main']/div/div/div/table/tbody/tr[1]/td[6]")
    WebElement draftSubjectField;

    @FindBy(xpath = "//form/div[2]/div[1]/span")
    WebElement draftEmailTo;

    @FindBy(xpath = "//div[text() = 'Send']")
    WebElement draftSendEmail;


    public static final By DRAFT_SUBJECT_FIELD_LOCATOR = By.xpath("//div[@class='no']//div[@role = 'main']/div/div/div/table/tbody/tr[1]/td[6]");
    public static final By DRAFT_EMAIL_TO_CHECK_LOCATOR = By.xpath("//form/div[2]/div[1]/span");

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    public DraftsPage getDrafts(){
        highLightElement(getDriver(),draftButton);
        getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        new Actions(getDriver()).click(draftButton).build().perform();
        waitForElementPresent(DRAFT_SUBJECT_FIELD_LOCATOR);
        return this;
    }


    public DraftsPage checkEmailInDrafts(){
        draftSubjectField.click();
        waitForElementPresent(DRAFT_EMAIL_TO_CHECK_LOCATOR);
        String emailToData = draftEmailTo.getText();
        System.out.println(emailToData);
        Assert.assertEquals(SEND_EMAIL_TO, emailToData);
        return this;
    }

    public DraftsPage sendEmailFromDrafts(){
        draftSendEmail.click();
        return new DraftsPage(getDriver());

    }
}
