package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class SingInPage extends AbstractPage {

    public static final String BASE_URL = "https://www.google.com/gmail/about/#";
    public static final String USER_LOGIN = "testTaskEP123";
    public static final String USER_PASSWORD = "testtask123";
    public static final String USER_MAIL = "testtaskep123@gmail.com";

    public static final By SING_IN_BUTTON_LOCATOR = By.xpath("//body/nav/div/a[2]");
    public static final By EMAIL_INPUT_FORM = By.id("identifierId");
    public static final By NEXT_LOGIN_BUTTON = By.id("identifierNext");
    public static final By NEXT_PASSWORD_BUTTON = By.id("passwordNext");
    public static final By PASSWORD_INPUT_FORM = By.name("password");
    public static final By CHECK_USER_LOCATOR = By.xpath("//div/a[contains(@title, \"Google\")]/span");
    public static final By CHECK_USER_MAIL_LOCATOR = By.xpath("//div[@aria-label= \"Account Information\"]/div/div/div[2]");

    public SingInPage(WebDriver driver) {
        super(driver);
    }

    public SingInPage getBasePage(){
        getDriver().get(BASE_URL);
        return this;
    }
    public SingInPage getUserAccount(){
        waitForElementPresent(SING_IN_BUTTON_LOCATOR);
        getDriver().findElement(SING_IN_BUTTON_LOCATOR).click();
        getDriver().findElement(EMAIL_INPUT_FORM).sendKeys(USER_LOGIN);
        getDriver().findElement(NEXT_LOGIN_BUTTON).click();
        waitForElementPresent(NEXT_PASSWORD_BUTTON);
        getDriver().findElement(PASSWORD_INPUT_FORM).sendKeys(USER_PASSWORD);
        getDriver().findElement(NEXT_PASSWORD_BUTTON).click();
        getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        return this;
    }

   public SingInPage isUserSignIn(){
       waitForElementPresent(CHECK_USER_LOCATOR);
        getDriver().findElement(CHECK_USER_LOCATOR).click();
        String mail = getDriver().findElement(CHECK_USER_MAIL_LOCATOR).getText();
        Assert.assertEquals(USER_MAIL, mail);
        return this;
   }
}
