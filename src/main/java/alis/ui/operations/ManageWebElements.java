package alis.ui.operations;

import org.openqa.selenium.WebElement;

/**
 * Created by JK on 7/9/2017.
 */
public class ManageWebElements {

    public static void setElementValue(WebElement element, String value){

        if("input".equals(element.getTagName())){
            element.clear();
            element.sendKeys(value);
        }
        /*else if("button".equals(element.getTagName()) || "button".equals(element.getAttribute("role"))){
            element.click();
        }*/
    }

    public static void clickElement(WebElement element) {
        element.click();
    }
}
