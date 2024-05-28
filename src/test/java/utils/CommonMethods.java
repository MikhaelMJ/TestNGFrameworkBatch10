package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() throws IOException {
        ConfigReader.readProperties(Constants.CONFIGURATION);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
               // System.setProperty("webdriver.chrome.drivers", "drivers/chromdriver.exe"); - уже не нужен
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

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
