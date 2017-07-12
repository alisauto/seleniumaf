package alis.ui.pages;

import alis.ui.operations.Driver;
import alis.ui.operations.FindPageElements;
import alis.ui.operations.ManageWebElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by JK on 7/9/2017.
 */
public class LoginPage extends Driver{

    private String username;
    private String password;
    private String dataBase;
    private WebElement element;

    private WebDriver driver = Driver.getInstance();
    private WebDriverWait wait = new WebDriverWait(driver, 10);

/*    @FindBy
    WebElement usernameBox = FindPageElements.findElement("user.name");
    WebElement passwordBox = FindPageElements.findElement("user.password");
    WebElement dataBaseFilter = FindPageElements.findElement("v-filterselect-input");
    WebElement loginButton = FindPageElements.findElement("login.button");*/

    public void login(String username, String password, String dataBase){
        WebElement usernameBox = findUserNameBox();
            ManageWebElements.setElementValue(usernameBox, username);
        WebElement passwordBox = findPasswordNameBox();
            ManageWebElements.setElementValue(passwordBox, password);
        WebElement dataBaseFilter = findDataBaseFilter();
            ManageWebElements.setElementValue(dataBaseFilter, dataBase);
        WebElement dataBaseList = findDataBaseList();
            ManageWebElements.clickElement(dataBaseList);
        WebElement loginButton = findLoginButton();
            ManageWebElements.clickElement(loginButton);
    }

    private WebElement findUserNameBox(){
        return element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user.name")));
    }
    private WebElement findPasswordNameBox(){
        return element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user.password")));
    }
    private WebElement findDataBaseFilter(){
        return element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-filterselect-input")));
    }
    private WebElement findDataBaseList(){
        return element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("VAADIN_COMBOBOX_OPTIONLIST")));
    }
    private WebElement findLoginButton(){
        return element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login.button")));
    }
}
