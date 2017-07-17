package alis.ui.operations;

import alis.ui.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by JK on 7/9/2017.
 */
public class ManageWebElements extends Driver{

    private static  WebDriver driver = Driver.getInstance();
    public static JavascriptExecutor js =(JavascriptExecutor)driver;
    private static WebDriverWait wait = new WebDriverWait(driver, 10);

    private static boolean bDoHighlight=false;

    static LoginPage page = new LoginPage();

    public static void setElementValue(WebElement element, String value) throws InterruptedException {

        if(element.getTagName().equals("input")){
            element.clear();
            DoHighlight(element);
            element.sendKeys(value);
        }
    }


    public static void setElementValue(String identify, String value) throws InterruptedException {

        WebElement element = FindPageElements.findElement(identify);
        if(element != null) {
            if (element.getTagName().equals("input")) {
                element.clear();
                DoHighlight(element);
                element.sendKeys(value);
            }
        }
    }

    public static void setComboValue(String identify, String value) throws InterruptedException {
            setElementValue(identify,value);
            WebElement listelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("VAADIN_COMBOBOX_OPTIONLIST")));
            if(listelement != null)
                ManageWebElements.clickElement(listelement);
    }

    public static void clickElement(WebElement element) throws InterruptedException {
        DoHighlight(element);
        element.click();
        page.waitForPageLoad();
    }

    public static void clickElement(String identify) throws InterruptedException {
        WebElement element = FindPageElements.findElement(identify);
        if(element != null) {
            DoHighlight(element);
            element.click();
            page.waitForPageLoad();
        }
    }

    public static void DoHighlight(WebElement element) throws InterruptedException{
        if(bDoHighlight) {
            for (int i = 0; i < 5; i++) {
                js.executeScript("arguments[0].style.border='3px solid red'", element);
                Thread.sleep(200);
                js.executeScript("arguments[0].style.border='0px'", element);
            }
        }

    }


}
