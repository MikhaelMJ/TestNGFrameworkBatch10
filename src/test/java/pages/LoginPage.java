package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginPage extends CommonMethods {
    @FindBy(id = "index_email")
    public WebElement usernameBox;

    @FindBy(id = "")
    public WebElement passwordBox;

    @FindBy(xpath = "//span[text()='Войти']")
    public WebElement loginBtn;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public void login(String username){
        sendText(usernameBox, username);
        click(loginBtn);
    }
}
