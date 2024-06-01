package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReading;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginTest extends CommonMethods {


    @Test(groups = {"smoke"})
    public void adminLogin()  {
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernameBox, ConfigReader.getPropertyValue("username"));//null?
        //sendText(loginPage.passwordBox,ConfigReader.getPropertyValue("password"));
        click(loginPage.loginBtn);

        DashBoardPage dashBoardPage = new DashBoardPage();
        Assert.assertTrue(dashBoardPage.inputCod.isDisplayed());

    }

    @Test(groups = {"smoke"})
    public void adminLoginXLSX()  {
        LoginPage loginPage = new LoginPage();

        List<Map<String,String>> loginFromExcel = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,"LoginData");
        Iterator <Map<String,String>> it = loginFromExcel.iterator();
        while (it.hasNext()){
            Map<String,String> maploginFromExcel = it.next();
            sendText(loginPage.usernameBox, maploginFromExcel.get("username"));
        }
        /*sendText(loginPage.usernameBox, ConfigReader.getPropertyValue("username"));*/
        //sendText(loginPage.passwordBox,ConfigReader.getPropertyValue("password"));
        click(loginPage.loginBtn);

        DashBoardPage dashBoardPage = new DashBoardPage();
        Assert.assertTrue(dashBoardPage.inputCod.isDisplayed());

    }

}
