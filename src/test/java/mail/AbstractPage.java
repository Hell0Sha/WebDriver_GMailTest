package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    private static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    private boolean isElementPresent(By locator){
        return !driver.findElements(locator).isEmpty();
    }

    public void open(String string){
        driver.get(string);
    }
    public void waitForElementPresent(By locator){
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void highLightElement(WebDriver driver, WebElement element){
        String bgcolor = element.getCssValue("backgroundColor");
        JavascriptExecutor jsexec = ((JavascriptExecutor) driver);
        jsexec.executeScript("arguments[0].style.backgroundColor = '" + "blue" + "'", element );
        jsexec.executeScript("arguments[0].style.backgroundColor = '" + bgcolor + "'", element);
    }
}
