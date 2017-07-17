package alis.ui.pages;

import alis.ui.operations.Driver;
import alis.ui.operations.FindPageElements;
import alis.ui.operations.ManageWebElements;
import alis.ui.operations.WaitForPageToLoad;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void login(String URL , String username, String password, String dataBase) throws InterruptedException {
        Driver.getBaseUrl(URL);

        ManageWebElements.setElementValue("user.name",username);
        ManageWebElements.setElementValue("user.password",password);
        ManageWebElements.setComboValue("login-combo",dataBase);
        waitForPageLoad();
        ManageWebElements.clickElement("login.button");
        waitForPageLoad();

        /**WaitForPageToLoad WFP = new WaitForPageToLoad();
        WFP.setTimeToWait(1000);*/

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


    public void waitForPageLoad( ) {
        final int TIMEOUT = 10;

        JavascriptExecutor js =(JavascriptExecutor)driver;
        Object result = js.executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

        while(!((result != null) && (result instanceof Boolean) && (Boolean) result)){
            System.out.println(result.toString());
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        }

/**
        List<WebElement> lodingsindicators = driver.findElements(By.className("v-loading-indicator"));
        if(lodingsindicators.size()>0) {
            final WebElement webElement = lodingsindicators.get(lodingsindicators.size() - 1);

            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
                @NotNull
                public Boolean apply(WebDriver arg0) {
                    try {
                        webElement.isDisplayed();
                        return false;
                    } catch (NoSuchElementException e) {
                        return true;
                    } catch (StaleElementReferenceException f) {
                        return true;
                    }
                }
            };
            wait.until(elementIsDisplayed);

        }
*/
        //System.out.println(result.toString());

        /**Object str = js.executeScript("return document.readyState;");
        while (!str.equals("complete")) {
            System.out.println(str.toString());
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        }*/

       // System.out.println(str.toString());


    }
}
