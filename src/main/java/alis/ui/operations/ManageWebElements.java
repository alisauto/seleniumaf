package alis.ui.operations;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by JK on 7/9/2017.
 */
public class ManageWebElements extends Driver{

    private static  WebDriver driver = Driver.getInstance();
    public static JavascriptExecutor js =(JavascriptExecutor)driver;

    public static void setElementValue(WebElement element, String value) throws InterruptedException {

        if("input".equals(element.getTagName())){
            element.clear();
            DoHighlight(element);
            //js.executeScript("arguments[0].style.border='3px solid red'", element);
            //Thread.sleep(200);
            element.sendKeys(value);
            js.executeScript("arguments[0].style.border='0px'", element);

        }
        /*else if("button".equals(element.getTagName()) || "button".equals(element.getAttribute("role"))){
            element.click();
        }*/
    }

    public static void clickElement(WebElement element) throws InterruptedException {

        js.executeScript("arguments[0].style.border='3px solid red'", element);
        Thread.sleep(200);
        element.click();
        Thread.sleep(200);
        //js.executeScript("arguments[0].style.border='0px'", element);

    }

    public static void DoHighlight(WebElement element) throws InterruptedException{

        for(int i=0;i<5;i++){
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            Thread.sleep(200);
            js.executeScript("arguments[0].style.border='0px'", element);
        }

    }


}
