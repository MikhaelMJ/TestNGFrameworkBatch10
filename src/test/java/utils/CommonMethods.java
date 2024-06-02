package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() throws IOException {
        ConfigReader.readProperties(Constants.CONFIGURATION);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                /*System.setProperty("webdriver.chrome.drivers", "drivers/chromdriver.exe");*/
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RemoteException("Invalid browser name");

        }

        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }


    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();

    }

    public static JavascriptExecutor getJSWExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    //клик спомощью JSWExecutor
    public static void jsClick(WebElement element) {
        getJSWExecutor().executeScript("arguments[0].click()", element);
    }

    //сделать скиншот
    public static void takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + " " +getTimeStamp("yyyy-MM-dd-HH-mm-ss")+ ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //метод получения даты
    public static String getTimeStamp(String pattern) {
        Date date = new Date();
         //формат yyyy-MM-dd-HH-mm-ss
        //отформотируем дату
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);

    }
    //закрыть браузер
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
