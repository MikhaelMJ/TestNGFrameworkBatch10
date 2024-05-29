package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

import java.io.IOException;
import java.rmi.RemoteException;

public class LoginTest extends CommonMethods {

    @Test()
    public void adminLogin() {
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernameBox, ConfigReader.getPropertyValue("username"));//null?
        //sendText(loginPage.passwordBox,ConfigReader.getPropertyValue("password"));
        click(loginPage.loginBtn);

        DashBoardPage dashBoardPage = new DashBoardPage();
        Assert.assertTrue(dashBoardPage.inputCod.isDisplayed());

    }


}
