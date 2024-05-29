package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashBoardPage extends CommonMethods {
    @FindBy(xpath = "//span[text()='Подтвердить другим способом']")
    public WebElement inputCod;

    public DashBoardPage(){
        PageFactory.initElements(driver,this);
    }
}
