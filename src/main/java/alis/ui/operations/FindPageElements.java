package alis.ui.operations;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class FindPageElements extends Driver{

    private static WebDriver driver = Driver.getInstance();

    public static WebElement findElement(String selector) throws NoSuchElementException {

        WebElement element = null;
        element = findById(element, selector);
        if(element == null)
            element = findByClassName(element, selector);
        if(element == null)
            element = findByCssSelector(element, selector);
        if(element == null)
            element = findByLinkText(element, selector);

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
}
