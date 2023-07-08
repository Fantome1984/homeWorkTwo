package wikiUITests;


import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper extends TestBase{

    public Helper(AppiumDriver driver){this.driver = driver;}



    public WebElement elementVisibility(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement elementClick(By  locator){
        WebElement element = elementVisibility(locator);
        element.click();
        return element;
    }

    public WebElement assertElementHasText(By locator, String expected,String error){
        WebElement element = elementVisibility(locator);
        String actual = element.getAttribute("text");
        Assertions.assertEquals(expected,actual,error);
        return element;
    }

    public WebElement enteringAValue(By locator, String text){
        WebElement element = elementVisibility(locator);
        element.sendKeys(text);
        return element;
    }

    public boolean waitForElementNotPresent(By locator){
        WebDriverWait wait = new WebDriverWait(driver,10);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }



    private AppiumDriver driver;
}
