package wikiUITests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.Transient;
import java.net.URL;
import java.util.List;

public class TestBase {

    protected AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 2 API 28");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\nsshletkin\\Downloads\\homeWorkTwo\\src\\main\\java\\apks\\Wikipedia_2.7.50446-r-2023-06-28_Apkpure.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    private String buttonSkip = "org.wikipedia:id/fragment_onboarding_skip_button";
    private String placeholder = "//android.widget.TextView[@text='Search Wikipedia']";
    private String searchBox = "org.wikipedia:id/search_container";
    private String searchText ="org.wikipedia:id/search_src_text";
    private String closeButton ="org.wikipedia:id/search_close_btn";







    @Test
    public void inputFieldValidation() {
        Helper helper = new Helper(driver);
        helper.elementClick(By.id(buttonSkip));
        helper.assertElementHasText(By.xpath(placeholder),"Search Wikipedia","Текст не совпадает");
    }
    @Test
    public void cancelSearch(){
        Helper helper = new Helper(driver);
        helper.elementClick(By.id(buttonSkip));
        helper.elementClick(By.id(searchBox));
        helper.enteringAValue(By.id(searchText),"Selenium");
        List<WebElement> elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (WebElement element:elements){
         WebDriverWait wait = new WebDriverWait(driver,10);
         wait.until(ExpectedConditions.visibilityOf(element));
        }
        helper.elementClick(By.id(closeButton));
       helper.waitForElementNotPresent(By.id(closeButton));
    }

    @Test
    public void checkingWordsInSearch(){
        Helper helper = new Helper(driver);
        helper.elementClick(By.id(buttonSkip));
        helper.elementClick(By.id(searchBox));
        helper.enteringAValue(By.id(searchText),"Selenium");
        List<WebElement> elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (WebElement element:elements){
            String actual = element.getAttribute("text");
            Assertions.assertTrue(actual.contains("Selenium"),"Поисковая выдача не содержит слово Selenium");
        }


    }


}









