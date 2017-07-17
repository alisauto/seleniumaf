package alis.ui.operations;


import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindPageElements extends Driver{

    private static WebDriver driver = Driver.getInstance();
    private static WebDriverWait wait = new WebDriverWait(driver, 10);

    public static WebElement findElement(String selector) throws NoSuchElementException, InterruptedException {

        WebElement element = null;
        element = findById(element, selector);
        if(element == null)
            element = findByClassName(element, selector);
        if(element == null)
            element = findByCssSelector(element, selector);
        if(element == null)
            element = findByLinkText(element, selector);

        String str = element.getTagName();

        if(element != null)

            if(element.getTagName().equals("div") && !element.getAttribute("class").contains("v-button") ) {
                element = findByTagName(element, "input");
            }


        return element;
    }

    private static WebElement findById(WebElement element, String selector){
        try{
            element = driver.findElement(By.id(selector));
        }catch(NoSuchElementException e){
            element = null;
        }
        return element;
    }

    private static WebElement findByClassName(WebElement element, String selector){
        try{
            element = driver.findElement(By.className(selector));
        }catch(NoSuchElementException e){
            element = null;
        }
        return element;
    }

    private static WebElement findByCssSelector(WebElement element, String selector){
        try{
            element = driver.findElement(By.cssSelector(selector));
        }catch(NoSuchElementException e){
            element = null;
        }
        return element;
    }

    private static WebElement findByLinkText(WebElement element, String selector){
        try{
            element = driver.findElement(By.linkText(selector));
        }catch(NoSuchElementException e){
            element = null;
        }
        return element;
    }


    private static WebElement findByTagName(WebElement element, String selector) {
        try {
            element = element.findElements(By.tagName(selector)).get(0);
        } catch (NoSuchElementException e) {
            element = null;
        }
        return element;
    }

}
